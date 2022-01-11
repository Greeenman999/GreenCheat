package de.greenman999.greencheat.command;

public interface ICommandExecutor {
    boolean onCommand(String cmd, String[] args);

    String getDescription();
    String getSyntax();
    String getCmdName();

}
