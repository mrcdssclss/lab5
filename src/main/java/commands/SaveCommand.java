package commands;

import managers.CollectionManager;
import managers.FileManager;

public class SaveCommand extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public SaveCommand(FileManager fileManager, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }
    public boolean execute(String argument){
        return collectionManager.saveCollection(fileManager);
    }
}
