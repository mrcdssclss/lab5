package commands;

import managers.CollectionManager;

public class ClearCommand extends Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager){
        super("clear", " очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        collectionManager.clear();
        return true;
    }
}
