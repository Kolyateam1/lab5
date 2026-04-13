package commands.impl;

import commands.*;
import collection.CollectionManager;
import utils.*;
import java.util.Map;

/**
 * Команда "help".
 * Выводит список всех доступных команд с кратким описанием.
 */
public class HelpCommand extends CollectionCommand{
    private Map<String, commands.Command> commands;

    public HelpCommand(CollectionManager collectionManager, InputValidator validator,
            Map<String, commands.Command> commands) {
        super(collectionManager, validator);
        this.commands = commands;
    }

    @Override
    public void execute(String args) {
        System.out.println("Доступные комманды:");
        commands.values().stream().distinct()
            .forEach(cmd -> System.out.printf("  %-30s - %s%n", cmd.getName(), cmd.getDescription()));
    }

    @Override
    public String getDescription() {
        return "Вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }
    
}
