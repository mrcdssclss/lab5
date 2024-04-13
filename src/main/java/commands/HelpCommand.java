package commands;

import managers.CommandManager;
import managers.ConsoleManager;


public class HelpCommand extends Command {
    private final ConsoleManager console;
    private final CommandManager commandManager;
    public HelpCommand(ConsoleManager console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.console = console;
        this.commandManager = commandManager;
    }
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        commandManager.getCommandMap().values().forEach(command ->
            console.println(command.getName() + " " + command.getDescription()));
        return true;
    }
}
