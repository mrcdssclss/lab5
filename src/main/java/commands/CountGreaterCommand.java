package commands;

import managers.CollectionManager;
import managers.ConsoleManager;

import java.util.Objects;

public class CountGreaterCommand extends Command{
    private final CollectionManager collectionManager;
    private final ConsoleManager console;

    public  CountGreaterCommand(CollectionManager collectionManager, ConsoleManager console){
        super("count_greater_than_meters_above_sea_level", " meters_above_sea_level  вывести количество элементов, значение поля meters_above_sea_level которых равно заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    public boolean execute(String arguments){
        if (arguments.isBlank()) throw new IllegalArgumentException();
        try {
            double args = Double.parseDouble(arguments.trim());
            console.print("Количество элементов, с большим значением поля meters_above_sea_level: ");
            console.println(String.valueOf(CollectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(s -> s.getMetersAboveSeaLevel() > args)
                    .map(Objects::toString)
                    .count()));

        } catch (NumberFormatException exception) {
            console.printError("meters_above_sea_level должно быть числом типа double");
        }
        return true;
    }
}
