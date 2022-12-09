package gg.acai.acava.io.logging;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 04.12.2022 17:15
 * © Acava - All Rights Reserved
 */
public class StandardLogger implements Logger {

    private static final Map<Color, String> COLORS = new HashMap<>();

    static {
        COLORS.put(Color.BLACK, gg.acai.acava.io.logging.Color.BLACK);
        COLORS.put(Color.RED, gg.acai.acava.io.logging.Color.RED);
        COLORS.put(Color.GREEN, gg.acai.acava.io.logging.Color.GREEN);
        COLORS.put(Color.YELLOW, gg.acai.acava.io.logging.Color.YELLOW);
        COLORS.put(Color.BLUE, gg.acai.acava.io.logging.Color.BLUE);
        COLORS.put(Color.CYAN, gg.acai.acava.io.logging.Color.CYAN);
        COLORS.put(Color.WHITE, gg.acai.acava.io.logging.Color.WHITE);
    }

    private final String prefix;
    private final String color;

    public StandardLogger(String prefix, String color) {
        this.prefix = prefix;
        this.color = color;
    }

    @Override
    public void log(String... messages) {
        System.out.println(color + prefix + " " + String.join(" ", messages));
    }

    @Override
    public void log(Color color, String... messages) {
        System.out.println(COLORS.getOrDefault(color, "") + prefix + " " + String.join(" ", messages));
    }
}
