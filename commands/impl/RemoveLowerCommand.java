package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.CityFactory;
import utils.InputValidator;

/**
 * Команда remove_lower - удалить все элементы, меньшие чем заданный
 */

public class RemoveLowerCommand extends CollectionCommand{
    private CityFactory cityFactory;

    public RemoveLowerCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
        this.cityFactory = new CityFactory(validator);
    }
    
    @Override
    public void execute(String args) {
        try {
            System.out.println("Введите город для сравнения:");
            models.City city = cityFactory.createCity();
            collectionManager.removeLower(city);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}
