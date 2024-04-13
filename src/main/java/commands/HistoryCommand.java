package commands;


import managers.CommandManager;
import managers.ConsoleManager;

import java.util.List;

public class HistoryCommand extends Command {
    private final CommandManager commandManager;
    private final ConsoleManager console;

    public HistoryCommand(ConsoleManager console, CommandManager commandManager) {
        super("history", " вывести последние 12 команд (без их аргументов)");
        this.commandManager = commandManager;
        this.console = console;
    }
    @Override
    public boolean execute(String args) throws IllegalArgumentException {
        if (!args.isBlank()) throw new IllegalArgumentException();
        List<String> history= commandManager.getCommandHistory();
        for (String command:history.subList(Math.max(history.size() - 12, 0), history.size())){
            console.println(command);
        }
        return true;
    }
}
