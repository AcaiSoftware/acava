package gg.acai.acava.io.logging;

/**
 * @author Clouke
 * @since 04.12.2022 17:14
 * © Acava - All Rights Reserved
 */
public final class LoggerContextBuilder {

    private String prefix = "";
    private String defaultColor = "";

    public LoggerContextBuilder prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public LoggerContextBuilder defaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
        return this;
    }

    public Logger build() {
        if (!prefix.startsWith("[")) prefix = "[" + prefix;
        if (!prefix.endsWith("]")) prefix = prefix + "]";

        return new StandardLogger(prefix, defaultColor);
    }

}
