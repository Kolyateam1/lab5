package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "show".
 * Выводит все элементы коллекции в строковом представлении.
 */
public class ShowCommand extends CollectionCommand{

    public ShowCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.show();
        }

    @Override
    public String getDescription() {
        return "Вывести все элементы коллекции";
    }

    @Override
    public String getName() {
        return "show";
    }
}
