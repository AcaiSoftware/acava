package gg.acai.acava.cache;

import gg.acai.acava.collect.lists.FixedSizeQueue;
import gg.acai.acava.io.Closeable;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import java.util.Collection;
import java.util.List;

/**
 * @author Clouke
 * @since 23.02.2023 16:04
 * © Acava - All Rights Reserved
 */
public class CacheLoader implements Closeable {

  private static final int DEF_MAX_SIZE = 12 * 1024;
  private final List<CacheBootstrap> bootstraps;

  public CacheLoader(int maxSize) {
    this.bootstraps = new FixedSizeQueue<>(maxSize);
  }

  public CacheLoader() {
    this(DEF_MAX_SIZE);
  }

  @SuppressWarnings("UnusedReturnValue")
  public <C extends CacheBootstrap> CacheLoader register(C bootstrapper) {
    synchronized (bootstraps) {
      bootstraps.add(bootstrapper);
      return this;
    }
  }

  public CacheLoader registerAll(Collection<? extends CacheBootstrap> bootstraps) {
    for (CacheBootstrap bootstrapper : bootstraps) {
      register(bootstrapper);
    }
    return this;
  }

  @SuppressWarnings("unchecked")
  public <R extends CacheBootstrap> R get(Class<R> clazz) {
    synchronized (bootstraps) {
      for (CacheBootstrap bootstrapper : bootstraps) {
        if (bootstrapper.getClass().equals(clazz)) {
          return (R) bootstrapper;
        }
      }
    }

    return null;
  }

  public void load() {
    synchronized (bootstraps) {
      for (CacheBootstrap bootstrapper : bootstraps) {
        bootstrapper.load();
        bootstraps.remove(bootstrapper);
      }
    }
  }

  public void loadSpecific(Class<? extends CacheBootstrap> clazz) {
    synchronized (bootstraps) {
      for (CacheBootstrap bootstrapper : bootstraps) {
        if (bootstrapper.getClass().equals(clazz)) {
          bootstrapper.load();
          bootstraps.remove(bootstrapper);
          break;
        }
      }
    }
  }

  public AsyncPlaceholder<CacheLoader> loadAsync() {
    return Schedulers.supplyAsync(() -> {
      load();
      return this;
    });
  }

  public AsyncPlaceholder<CacheLoader> loadSpecificAsync(Class<? extends CacheBootstrap> clazz) {
    return Schedulers.supplyAsync(() -> {
      loadSpecific(clazz);
      return this;
    });
  }

  @Override
  public void close() {
    synchronized (bootstraps) {
      try {
        for (CacheBootstrap bootstrapper : bootstraps) {
          try {
            bootstrapper.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } finally {
        bootstraps.clear();
      }
    }
  }
}
