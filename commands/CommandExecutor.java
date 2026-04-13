package commands;

import java.util.HashMap;
import java.util.Map;

import collection.CollectionManager;
import commands.impl.AddCommand;
import commands.impl.ClearCommand;
import commands.impl.ExecuteScriptCommand;
import commands.impl.ExitCommand;
import commands.impl.FilterStartsWithNameCommand;
import commands.impl.GroupCountingByGovernorCommand;
import commands.impl.HelpCommand;
import commands.impl.InfoCommand;
import commands.impl.RemoveAnyByStandardOfLivingCommand;
import commands.impl.RemoveByIdCommand;
import commands.impl.RemoveLowerCommand;
import commands.impl.ReorderCommand;
import commands.impl.SaveCommand;
import commands.impl.ShowCommand;
import commands.impl.SortCommand;
import commands.impl.UpdateCommand;
import utils.InputValidator;

/**
 * Исполнитель команд (Invoker в Command Pattern). Регистрирует все команды и вызывает их по имени.
 */
public class CommandExecutor {
    private Map<String, Command> commands;
    private CollectionManager collectionManager;
    private InputValidator validator;

    public CommandExecutor(CollectionManager collectionManager, InputValidator validator) {
        this.collectionManager = collectionManager;
        this.validator = validator;
        this.commands = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(collectionManager, validator, this);

        commands.put("help", new HelpCommand(collectionManager, validator, commands));
        commands.put("info", new InfoCommand(collectionManager, validator));
        commands.put("show", new ShowCommand(collectionManager, validator));
        commands.put("add", new AddCommand(collectionManager, validator));
        commands.put("update", new UpdateCommand(collectionManager, validator));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, validator));
        commands.put("clear", new ClearCommand(collectionManager, validator));
        commands.put("save", new SaveCommand(collectionManager, validator));
        commands.put("execute_script", executeScriptCommand);
        commands.put("exit", new ExitCommand(collectionManager, validator));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, validator));
        commands.put("reorder", new ReorderCommand(collectionManager, validator));
        commands.put("sort", new SortCommand(collectionManager, validator));
        commands.put("remove_any_by_standard_of_living", new RemoveAnyByStandardOfLivingCommand(collectionManager, validator));
        commands.put("group_counting_by_governor", new GroupCountingByGovernorCommand(collectionManager, validator));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager, validator));
    }

    public void execute(String commandLine) {
        if (commandLine == null || commandLine.trim().isEmpty()) {
            return;
        }

        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        Command command = commands.get(commandName);

        if (command != null) {
            try {
                command.execute(arguments);
            } catch (Exception e) {
                System.out.println("Ошибка при выполнении команды: " + e.getMessage());
            }
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для справки.");
        }
    }

    public void executeCommand(String commandLine) {
        execute(commandLine);
    }

}
