package de.greenman999.greencheat.addons;

import de.greenman999.greencheat.addon.AddonInterface;
import de.greenman999.greencheat.addon.AddonManager;

public class HelloAddon implements AddonInterface {

    private AddonManager manager;

    @Override
    public boolean onEnable() {
        System.out.println("Addon enabled!");
        return true;
    }

    @Override
    public boolean onDisable() {
        System.out.println("Addon disabled!");
        return true;
    }

    @Override
    public void setPluginManager(AddonManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(String cmd, String[] args) {
        return false;
    }
}
