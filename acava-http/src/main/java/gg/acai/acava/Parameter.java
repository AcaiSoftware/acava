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

    /**
     * Delegates a map to this parameter.
     *
     * @param parameters The map to delegate.
     */
    public Parameter delegate(Map<String, String> parameters) {
        Objects.requireNonNull(parameters, "parameters cannot be null");
        if (this.parameters == null) {
            this.parameters = parameters;
            return this;
        }
        this.parameters.putAll(parameters);
        return this;
    }

    /**
     * Adds a parameter to the parameter.
     *
     * @param key The key of the parameter.
     * @param value The value of the parameter.
     */
    public Parameter add(String key, String value) {
        if (this.parameters == null) {
            this.parameters = new HashMap<>();
        }
        this.parameters.put(key, value);
        return this;
    }

    /**
     * @return Returns the formatted parameters as a string.
     */
    @Override
    public String toString() {
        if (parameters == null || parameters.isEmpty()) return "";
        StringBuilder builder = new StringBuilder()
                .append('?');

        parameters.forEach((key, value) -> builder.append(key)
                .append('=')
                .append(value)
                .append('&'));

        return builder.toString();
    }
}
