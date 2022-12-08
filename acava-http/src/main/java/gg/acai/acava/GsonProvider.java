package gg.acai.acava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Clouke
 * @since 08.12.2022 23:26
 * © Acava - All Rights Reserved
 */
public final class GsonProvider {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static Gson getGson() {
        return GSON;
    }

}
