package managers;


import commands.Command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    ConsoleManager console;
    CommandManager commandManager;
    ArrayList<String> usedFileNames = new ArrayList<>();

    public Runner(ConsoleManager console, CommandManager commandManager){
        this.console = console;
        this.commandManager = commandManager;
    }

    public void getCommand(){
        try {
            String[] userCommand;
            while (true) {
                userCommand = (console.readln().trim() + " ").split(" ", 2);
                if (userCommand[0].isEmpty()) {
                    console.printError("Команда не введена");
                } else {
                    if (commandManager.getCommand(userCommand[0]) == null) {
                        console.printError("Команда введена некорректно");
                    } else {
                        if (userCommand.length == 1) {
                            userCommand = new String[]{userCommand[0], ""};
                        }
                        userCommand[1] = userCommand[1].trim();
                        commandManager.addToHistory(userCommand[0]);
                        if (userCommand[0].equals("exit")) break;
                        if (userCommand[0].equals("execute_script")){
                            boolean commandStatus = scriptLaunch(userCommand);
                        }
                        boolean commandStatus = commandLaunch(userCommand);

                        if (commandStatus) console.println("Команда выполнена успешно");
                    }
                }
            }

        } catch (NoSuchElementException e) {
            console.printError("Пользовательский ввод не обнаружен");
        } catch (IllegalStateException e) {
            console.printError("Непредвиденная ошибка");
        } catch (Ask.AskBreak e) {
            throw new RuntimeException(e);
        }
    }

    public boolean scriptLaunch(String[] userCommand){
        boolean commandStatus = true;
        if (userCommand.length == 1) {
            console.printError("Введите название файла со скриптом");
            return false;
        }
        String fileName = userCommand[1];
        try{
            usedFileNames.add(fileName);
            String line;
            String[] scriptCommand;
            File file = new File(fileName);
            console.setFileMode();
            console.setScanner(new Scanner(file));
            while (commandStatus && console.getScanner().hasNext() && (line = console.getScanner().nextLine()) != null){
                scriptCommand = (line.trim()+" ").split(" ", 2);
                scriptCommand[1] = scriptCommand[1].trim();
                while (scriptCommand[0].isEmpty() && console.getScanner().hasNext()){
                    line = console.getScanner().nextLine();
                    scriptCommand = (line.trim()+" ").split(" ", 2);
                    scriptCommand[1] = scriptCommand[1].trim();
                }
                if (scriptCommand[0].equals("execute_script")){
                    if (usedFileNames.contains(fileName)){
                        console.printError("Скрипты не вызываются рекурсивно");
                        commandStatus = false;
                    } else {
                        commandStatus = scriptLaunch(new String[]{"execute_script"});
                    }
                } else {
                    commandStatus = commandLaunch(scriptCommand);
                }
            }
        }catch(IOException | Ask.AskBreak e){
            console.printError("Ошибка чтения файла" + e.getMessage());
        }
        console.setFileMode();
        usedFileNames.remove(fileName);
        return commandStatus;
    }

    public boolean commandLaunch(String[] userCommand) throws Ask.AskBreak {
        Command command = commandManager.getCommand(userCommand[0]);
        return command.execute(userCommand[1]);
    }

}

