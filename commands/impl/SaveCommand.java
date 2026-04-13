package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "save".
 * Сохраняет текущее состояние коллекции в файл (в формате CSV).
 */
public class SaveCommand extends CollectionCommand{
    public SaveCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.save();
        }

    @Override
    public String getDescription() {
        return "Сохранить коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }
}
