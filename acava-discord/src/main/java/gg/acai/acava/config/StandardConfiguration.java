package gg.acai.acava.config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Clouke
 * @since 03.12.2022 15:17
 * © Acava - All Rights Reserved
 */
public final class StandardConfiguration implements Configuration {

    private final Dotenv dotenv;

    public StandardConfiguration(String directory, String file) {
        this.dotenv = Dotenv.configure()
                .directory(directory)
                .filename(file)
                .load();
    }

    @Override
    public String asString(String key) {
        return this.dotenv.get(key);
    }

    @Override
    public Number asNumber(String key) {
        return new Number() {
            @Override
            public int intValue() {
                return Integer.parseInt(dotenv.get(key));
            }

            @Override
            public long longValue() {
                return Long.parseLong(dotenv.get(key));
            }

            @Override
            public float floatValue() {
                return Float.parseFloat(dotenv.get(key));
            }

            @Override
            public double doubleValue() {
                return Double.parseDouble(dotenv.get(key));
            }
        };
    }

    @Override
    public Boolean asBoolean(String key) {
        return Boolean.parseBoolean(this.dotenv.get(key));
    }
}
