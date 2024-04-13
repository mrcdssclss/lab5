package commands;
import managers.*;


public class ExecuteScriptCommand extends Command{

    private final ConsoleManager console;
    int x = 0;

    public ExecuteScriptCommand(ConsoleManager console) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла (в скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме)");
        this.console = console;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new IllegalArgumentException();
            console.println("Выполнение скрипта '" + argument + "'...");
            x++;
            return true;
        } catch (IllegalArgumentException exception) {
            console.printError("Нет аргументов для исполнения команды '" + getName() + "'");
        }
        if (x > 20){
            x = 0;
            System.exit(1);
        }
        return false;
    }
}
