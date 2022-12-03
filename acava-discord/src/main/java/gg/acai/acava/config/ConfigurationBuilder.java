package gg.acai.acava.config;

/**
 * @author Clouke
 * @since 03.12.2022 15:12
 * © Acava - All Rights Reserved
 */
public interface ConfigurationBuilder {

    /**
     * Sets the directory of the configuration file.
     * @param directory The directory of the configuration file.
     * @return The builder instance.
     */
    ConfigurationBuilder directory(String directory);

    /**
     * Sets the name of the configuration file.
     * @param file The name of the configuration file.
     * @return The builder instance.
     */
    ConfigurationBuilder file(String file);

    /**
     * Builds the configuration.
     * @return The configuration.
     */
    Configuration build();

}
