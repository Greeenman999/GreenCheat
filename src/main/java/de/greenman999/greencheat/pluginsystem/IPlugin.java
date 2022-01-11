package de.greenman999.greencheat.pluginsystem;

import de.greenman999.greencheat.Greencheat;
import de.greenman999.greencheat.command.ICommandExecutor;

public interface IPlugin {

    String getPluginIdentity();
    void onEnable();
    void onDisable();

}
