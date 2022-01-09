package de.greenman999.greencheat.pluginsystem;

import de.greenman999.greencheat.pluginsystem.interfaces.PluginConfigLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyPluginConfigLoader implements PluginConfigLoader {
    @Override
    public String getPathToMainPluginClass(InputStream inputStream) throws IOException {
        Properties config = new Properties();
        config.load(inputStream);
        return config.getProperty("main");
    }
}
