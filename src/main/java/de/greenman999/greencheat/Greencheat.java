package de.greenman999.greencheat;

import de.greenman999.greencheat.pluginsystem.ConsoleMessageLogger;
import de.greenman999.greencheat.pluginsystem.IPlugin;
import de.greenman999.greencheat.pluginsystem.PluginLoader;
import de.greenman999.greencheat.pluginsystem.PropertyPluginConfigLoader;
import de.greenman999.greencheat.pluginsystem.interfaces.MessageLogger;
import de.greenman999.greencheat.pluginsystem.interfaces.PluginConfigLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class Greencheat implements ModInitializer {

    private static MessageLogger messageLogger = new ConsoleMessageLogger();

    private static File folder = new File(FabricLoader.getInstance().getGameDir() + "/greencheat/plugins");
    private static PluginConfigLoader pluginConfigLoader = new PropertyPluginConfigLoader();

    private static PluginLoader<IPlugin> pluginLoader = new PluginLoader<>(messageLogger, folder, pluginConfigLoader);

    @Override
    public void onInitialize() {
        if(!folder.exists()) folder.mkdirs();
        pluginLoader.load();
        pluginLoader.enable();
        Thread pluginUnload = new Thread(() -> {
            pluginLoader.disable();
            pluginLoader.unload();
        });
        Runtime.getRuntime().addShutdownHook(pluginUnload);
    }
}
