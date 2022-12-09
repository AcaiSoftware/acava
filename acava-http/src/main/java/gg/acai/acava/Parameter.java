package gg.acai.acava;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Clouke
 * @since 09.12.2022 13:49
 * © Acava - All Rights Reserved
 */
public class Parameter {

    private Map<String, String> parameters;

    public Parameter delegate(Map<String, String> parameters) {
        Objects.requireNonNull(parameters, "parameters cannot be null");
        if (this.parameters == null) {
            this.parameters = parameters;
            return this;
        }
        this.parameters.putAll(parameters);
        return this;
    }

    public Parameter add(String key, String value) {
        if (this.parameters == null) {
            this.parameters = new HashMap<>();
        }
        this.parameters.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        if (parameters.isEmpty()) return "";
        StringBuilder builder = new StringBuilder()
                .append('?');

        parameters.forEach((key, value) -> builder.append(key)
                .append('=')
                .append(value)
                .append('&'));

        return builder.toString();
    }
}
