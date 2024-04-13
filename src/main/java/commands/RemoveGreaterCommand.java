package commands;

import data.City;
import managers.Ask;
import managers.CollectionManager;
import managers.ConsoleManager;

import java.util.ArrayDeque;
import java.util.Scanner;


public class RemoveGreaterCommand extends Command {
    private final CollectionManager collectionManager;
    private final ConsoleManager console;
    private Scanner scanner;

    public RemoveGreaterCommand(CollectionManager collectionManager, ConsoleManager console) {
        super("remove_greater", " удалить из коллекции все элементы, большие, чем заданный");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    public boolean execute(String agrument) {
        int id;
        Ask ask = new Ask(collectionManager, scanner, console);
        ArrayDeque<City> city = CollectionManager.getCollection();
        if (!agrument.isEmpty()) {
            id = Integer.parseInt(agrument);
        } else {
            id = ask.setId();
        }
        for (City el : city){
            if (String.valueOf(el.getId()).compareTo(String.valueOf(id)) < 0){
                collectionManager.remove(el.getId());
            }
        }
        return true;
    }
}
