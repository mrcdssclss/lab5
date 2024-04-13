package managers;

import commands.Command;
import lombok.Getter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CommandManager {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    public void registration(String name, Command command){
        commandMap.put(name,command);
    }

    public Command getCommand(String name){
        return commandMap.get(name);
    }

    public void addToHistory(String line){
        if(line.isBlank()) return;
        this.commandHistory.add(line);
    }


}
