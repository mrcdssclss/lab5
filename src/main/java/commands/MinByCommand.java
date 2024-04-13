package commands;

import data.City;
import data.StandardOfLiving;
import managers.CollectionManager;
import managers.ConsoleManager;

import java.util.ArrayDeque;



public class MinByCommand extends Command {
    private final ConsoleManager console;

    public MinByCommand(ConsoleManager console) {
        super("min_by_Standard_Of_Living", "вывести любой объект из коллекции, " +
                "значение поля " +
                "Standard_Of_Living которого является минимальным");
        this.console = console;
    }
    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()) {
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        ArrayDeque<City> city = CollectionManager.getCollection();
        if (city == null || city.isEmpty()) {
            console.printError("Коллекция пуста");
            return false;
        }
        boolean found = false;
        for (City el : city) {
            if (el.getStandardOfLiving() == StandardOfLiving.NIGHTMARE) {
                System.out.println(el);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Элемент с уровнем жизни NIGHTMARE не найден в коллекции");
        }
        return true;
    }

}

