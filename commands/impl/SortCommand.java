package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "sort".
 * Сортирует коллекцию в естественном порядке (по id).
 */
public class SortCommand extends CollectionCommand{

    public SortCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.sort();
        }

    @Override
    public String getDescription() {
        return "Отсортировать коллекцию в естественном порядке";
    }

    @Override
    public String getName() {
        return "sort";
    }
}
