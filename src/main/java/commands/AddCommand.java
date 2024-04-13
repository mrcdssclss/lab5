package commands;

import data.City;
import managers.Ask;
import managers.CollectionManager;
import managers.ConsoleManager;

import java.util.Scanner;

public class AddCommand extends Command {
    private final CollectionManager collectionManager;
    private final ConsoleManager console;
    private Scanner scanner;

    public AddCommand(CollectionManager collectionManager, ConsoleManager console) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument) {
        try{
            Ask ask = new Ask(collectionManager, scanner, console);
            if(!argument.isEmpty()) throw new IllegalArgumentException();
            collectionManager.add(new City(
                    ask.setId(),
                    ask.askName(),
                    ask.askCoordinates(),
                    ask.askCreationDate(),
                    ask.askArea(),
                    ask.askPopulation(),
                    ask.askMetersAboveSeaLevel(),
                    ask.askCarCode(),
                    ask.askGovernment(),
                    ask.askStandardOfLiving(),
                    ask.askHuman()
            ));
            console.println("коллекция была создана успешно");
            return true;
        } catch (IllegalArgumentException e){
            console.printError("Использование аргумента '" + argument + "' в команде '" + getName() + "'");
        } catch (Ask.AskBreak e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
