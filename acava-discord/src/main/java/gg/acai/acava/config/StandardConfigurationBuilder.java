package gg.acai.acava.config;

/**
 * @author Clouke
 * @since 03.12.2022 15:16
 * © Acava - All Rights Reserved
 */
public class StandardConfigurationBuilder implements ConfigurationBuilder {

    private String directory;
    private String file;

    @Override
    public ConfigurationBuilder directory(String directory) {
        this.directory = directory;
        return this;
    }

    @Override
    public ConfigurationBuilder file(String file) {
        this.file = file;
        return this;
    }

    @Override
    public Configuration build() {
        return new StandardConfiguration(directory, file);
    }
}
