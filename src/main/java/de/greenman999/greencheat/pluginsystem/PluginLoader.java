package de.greenman999.greencheat.pluginsystem;

import de.greenman999.greencheat.Greencheat;
import de.greenman999.greencheat.command.ICommandExecutor;
import de.greenman999.greencheat.pluginsystem.interfaces.MessageLogger;
import de.greenman999.greencheat.pluginsystem.interfaces.PluginConfigLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.GarbageCollectorMXBean;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipFile;

public class PluginLoader<T extends IPlugin> {

    private final Set<T> plugins = new HashSet<>();
    private final Set<Class<?>> pluginClasses = new HashSet<>();
    private final File folder;
    private MessageLogger messageLogger;
    private PluginConfigLoader pluginConfigLoader;

    public PluginLoader(MessageLogger messageLogger, File folder, PluginConfigLoader pluginConfigLoader) {
        this.messageLogger = messageLogger;
        this.folder = folder;
        this.pluginConfigLoader = pluginConfigLoader;
    }


    public void load() {

        if (folder.exists() && folder.isDirectory()) {



            File[] files = folder.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });

            if (files != null) {
                for (File jar : files) {


                    String mainClass = null;
                    try {
                        ZipFile zipFile = new ZipFile(jar);

                        InputStream is = zipFile.getInputStream(zipFile.getEntry("extension.yml"));
                        mainClass = pluginConfigLoader.getPathToMainPluginClass(is);
                        ClassLoader l = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()}, getClass().getClassLoader());

                        Class<?> clazz = l.loadClass(mainClass);
                        pluginClasses.add(clazz);
                        zipFile.close();
                    } catch (IOException e) {
                        messageLogger.error("Error while loading module file " + jar.getName());
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        messageLogger.error("Class not found! Wrong main defined in extension.yml?: " + jar.getName() + " class: " + mainClass);
                        e.printStackTrace();
                    }

                }
            }

        }
    }

    public void enable() {


        for (Class<?> clazz : pluginClasses) {


            try {
                T plugin = (T) clazz.newInstance();
                plugin.onEnable();
                plugins.add(plugin);
                Greencheat.getCommandLoader().loadCommands();
                messageLogger.info(plugin.getPluginIdentity() + " enabled!");
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }



    }

    public void disable() {

        for (T extension : plugins) {
            extension.onDisable();
            Greencheat.getCommandLoader().loadCommands();
            messageLogger.info(extension.getPluginIdentity() + " disabled!");
        }


    }

    public void unload() {

        plugins.clear();
        pluginClasses.clear();
        System.gc();


    }

    public void onCommand(String cmd, String[] args) {
        for(T plugin : plugins) {
            for(ICommandExecutor cmdExecutor : Greencheat.getCommandLoader().getCommands()) {
                cmdExecutor.onCommand(cmd, args);
            }
        }
    }
}
