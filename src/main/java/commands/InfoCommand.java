package commands;

import managers.CollectionManager;
import managers.ConsoleManager;

public class InfoCommand extends Command {
    private final ConsoleManager console;
    private final CollectionManager collectionManager;

    public InfoCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
        }
        console.println("тип коллекции: " + collectionManager.collectionType()
                + ", дата инициализации: " + collectionManager.getLastInitTime()
                + ", размер: " + collectionManager.collectionSize());
        return true;
    }
}
