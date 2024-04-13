package commands;

import data.City;
import managers.CollectionManager;
import managers.ConsoleManager;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class FilterCommand extends Command{
    private final ConsoleManager console;
    private final CollectionManager collectionManager;
    public FilterCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("filter_by_name ", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new IllegalArgumentException();
            var name = filterByName(argument);
            if (name.isEmpty()) {
                console.println("элементов, которые содержат '" + argument + "' не обнаружено.");
            } else {
                console.println("элементов, которые содержат '" + argument  + "' обнаружено " + name.size() + " шт.\n");
                name.forEach(console::println);
            }
            return true;
        } catch (IllegalArgumentException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
    private ArrayDeque<City> filterByName(String partNumberSubstring) {
        return collectionManager.getCollection().stream()
                .filter(name -> (name.getName() != null && name.getName().contains(partNumberSubstring)))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
