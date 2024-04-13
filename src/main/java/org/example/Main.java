package org.example;

import commands.*;
import managers.*;

public class Main {
    public static void main(String[] args) {
        var console = new ConsoleManager();
        FileManager fileManager = new FileManager("/Users/lepidodendronnnn/IdeaProjects/lab5_maven_ver/src/main/java/org/example/file_lab5.xml", console);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        collectionManager.loadCollection(fileManager);
        CommandManager commandManager = new CommandManager() {{
            registration("add", new AddCommand(collectionManager, console));
            registration("clear", new ClearCommand(collectionManager));
            registration("count_greater_than_meters_above_sea_level", new CountGreaterCommand(collectionManager, console));
            registration("execute_script", new ExecuteScriptCommand(console));
            registration("exit", new ExitCommand(console));
            registration("filter_contains_name", new FilterCommand(console, collectionManager));
            registration("help", new HelpCommand(console, this));
            registration("history", new HistoryCommand(console, this));
            registration("min_by_Standard_Of_Living", new MinByCommand(console));
            registration("remove_greater", new RemoveGreaterCommand(collectionManager, console));
            registration("remove_head", new RemoveHeadCommand(collectionManager, console));
            registration("save", new SaveCommand(fileManager, collectionManager));
            registration("info", new InfoCommand(console, collectionManager));
            registration("show", new ShowCommand(console, collectionManager));
            registration("update_id", new UpdateIdCommand(console, collectionManager));
        }};
        Runner runner = new Runner(console, commandManager);
        runner.getCommand();
    }
}