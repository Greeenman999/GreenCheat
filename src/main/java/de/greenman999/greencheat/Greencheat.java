package de.greenman999.greencheat;

import de.greenman999.greencheat.addon.AddonManagerImpl;
import net.fabricmc.api.ModInitializer;

public class Greencheat implements ModInitializer {

    AddonManagerImpl addonManager = new AddonManagerImpl();

    @Override
    public void onInitialize() {
        addonManager.start();
        Thread addonStop = new Thread(() -> addonManager.stop());
        Runtime.getRuntime().addShutdownHook(addonStop);
    }
}
