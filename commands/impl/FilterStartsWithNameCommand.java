package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "filter_starts_with_name name".
 * Выводит все элементы, название которых начинается с заданной подстроки (без учёта регистра).
 */
public class FilterStartsWithNameCommand extends CollectionCommand {

    public FilterStartsWithNameCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator);
    }

    @Override
    public void execute(String args) {
        if (args == null || args.trim().isEmpty()) {
            System.out.println("укажите начало имени");
            return;
        }
        collectionManager.filterStartsWithName(args.trim());
    }

    @Override
    public String getDescription() {
        return "вывести элементы, имя которых начинается с подстроки";
    }

    @Override
    public String getName() {
        return "filter_starts_with_name name";
    }
    
}
