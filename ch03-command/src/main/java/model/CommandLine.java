package model;

import command.ICommand;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {

    private Map<String, ICommand> commandMap = new HashMap<String, ICommand>();

    public void addCommand(String name, ICommand cmd){
        commandMap.put(name, cmd);
    }

    public void reomve(String name){
        commandMap.remove(name);
    }

    public void input(String str){
        ICommand iCommand = commandMap.get(str);
        if(iCommand == null){
            System.out.println("命令未找到");
            return;
        }
        iCommand.execute();
    }
}
