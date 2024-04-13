package commands;
import data.City;
import managers.CollectionManager;
import managers.ConsoleManager;

import java.util.ArrayDeque;


public class RemoveHeadCommand extends Command{
    private final CollectionManager collectionManager;
    private final ConsoleManager console;

    public RemoveHeadCommand(CollectionManager collectionManager, ConsoleManager console) {
        super("remove_head", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new IllegalArgumentException();
            collectionManager.printCollection(console);
            collectionManager.removeFirst();
            console.println("элемент был успешно удален");
            return true;
        } catch (NumberFormatException e) {
            console.printError("Поле должно быть int");
        } catch (ArrayIndexOutOfBoundsException exception){
            console.printError("Недопустимый индекс");
        } catch (IllegalArgumentException e){
            console.printError("Использование аргумента '" + argument + "' в команде '" + getName() + "'");
        }
        return false;
    }
}
