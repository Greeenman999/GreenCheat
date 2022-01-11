package de.greenman999.greencheat.command;

import de.greenman999.greencheat.commands.ReloadCommand;

import java.util.HashSet;
import java.util.Set;

public class CommandLoader {

    private Set<ICommandExecutor> commands = new HashSet<>();

    public void loadCommands() {
        commands.add(new ReloadCommand());

    }

    public void unloadCommands() {
        commands.clear();
    }

    public Set<ICommandExecutor> getCommands() {
        return commands;
    }

    public void addCommand(ICommandExecutor cmd) {
        commands.remove(cmd);
        commands.add(cmd);
    }

}
