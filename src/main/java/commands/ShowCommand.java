package commands;

import managers.CollectionManager;
import managers.ConsoleManager;

public class ShowCommand extends Command {
    private final ConsoleManager console;
    private final CollectionManager collectionManager;

    public ShowCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
        }
        collectionManager.printCollection(console);
        return true;
    }
}
