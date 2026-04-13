package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "group_counting_by_governor".
 * Группирует элементы коллекции по возрасту губернатора и выводит количество городов в каждой группе.
 */
public class GroupCountingByGovernorCommand extends CollectionCommand{

    public GroupCountingByGovernorCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.groupCountingByGovernor();
        }

    @Override
    public String getDescription() {
        return "Сгруппировать элементы по губернатору";
    }

    @Override
    public String getName() {
        return "group_counting_by_governor";
    }
}