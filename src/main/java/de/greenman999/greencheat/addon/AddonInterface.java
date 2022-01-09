package de.greenman999.greencheat.addon;

public interface AddonInterface {

    boolean onEnable();
    boolean onDisable();
    void setPluginManager(AddonManager manager);
    boolean onCommand(String cmd, String[] args);
}
