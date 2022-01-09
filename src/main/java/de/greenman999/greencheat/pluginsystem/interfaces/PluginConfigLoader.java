package de.greenman999.greencheat.pluginsystem.interfaces;

import java.io.IOException;
import java.io.InputStream;

public interface PluginConfigLoader {
    String getPathToMainPluginClass(InputStream inputStream) throws IOException;
}
