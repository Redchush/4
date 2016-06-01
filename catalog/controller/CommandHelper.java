package catalog.controller;

import catalog.command.Command;
import catalog.command.impl.FindNewsCommand;
import catalog.command.impl.SaveNewNewsCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 30.05.2016.
 */
public class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    CommandHelper(){
        commands.put(CommandName.SAVE_NEW_NEWS, new SaveNewNewsCommand());
        commands.put(CommandName.FIND_NEWS, new FindNewsCommand());
    }

    public Command getCommand(String name){
        CommandName commadName = CommandName.valueOf(name);
        Command command = commands.get(commadName);
        return command;
    }
}