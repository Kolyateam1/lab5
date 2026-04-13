package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "info".
 * Выводит информацию о коллекции: тип, дата инициализации, количество элементов.
 */
public class InfoCommand extends CollectionCommand{

    public InfoCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        collectionManager.info();
        }

    @Override
    public String getDescription() {
        return "Вывести информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }
}
