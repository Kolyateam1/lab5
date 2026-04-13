package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "clear".
 * Полностью очищает коллекцию.
 */
public class ClearCommand extends CollectionCommand{

    public ClearCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.clear();
        }

    @Override
    public String getDescription() {
        return "Очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }
}