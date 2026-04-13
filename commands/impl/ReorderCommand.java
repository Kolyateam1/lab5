package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "reorder".
 * Изменяет порядок элементов коллекции на обратный (reverse).
 */
public class ReorderCommand extends CollectionCommand{

    public ReorderCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.reorder();
        }

    @Override
    public String getDescription() {
        return "Отсортировать коллекцию в обратном порядке";
    }

    @Override
    public String getName() {
        return "reorder";
    }
}
