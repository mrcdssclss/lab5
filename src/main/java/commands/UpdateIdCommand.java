package commands;

import data.City;
import managers.Ask;
import managers.CollectionManager;
import managers.ConsoleManager;
import java.util.Scanner;

public class UpdateIdCommand extends Command {
    private final ConsoleManager console;
    private final CollectionManager collectionManager;
    Scanner scanner;

    public UpdateIdCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("update_id", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) throws Ask.AskBreak {
        Ask ask = new Ask(collectionManager, scanner, console);
        if (argument.isEmpty()) {
            console.printError("Введите id для обновления элемента");
            return false;
        }
        if (!argument.matches("\\d+")){
            console.printError("id должен быть числом");
            return false;
        }
        Integer id = Integer.parseInt(argument);
        if (collectionManager.getCollectionById(id) == null ){
            console.printError("Элемента с таким id не существует");
            return false;
        }
        collectionManager.remove(id);
        if (collectionManager.addById(id, new City(ask.setId(),
                ask.askName(),
                ask.askCoordinates(),
                ask.askCreationDate(),
                ask.askArea(),
                ask.askPopulation(),
                ask.askMetersAboveSeaLevel(),
                ask.askCarCode(),
                ask.askGovernment(),
                ask.askStandardOfLiving(),
                ask.askHuman()))) return true;
        console.printError("Произошла ошибка при обновлении элемента");
        return false;
    }
}