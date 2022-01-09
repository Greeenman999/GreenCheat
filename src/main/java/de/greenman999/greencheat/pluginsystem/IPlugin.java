package de.greenman999.greencheat.pluginsystem;

public interface IPlugin {

    String getPluginIdentity();
    void onEnable();
    void onDisable();
    boolean onCommand();

}
