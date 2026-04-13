package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.CityFactory;
import utils.InputValidator;

/**
 * Команда "add".
 * Добавляет новый город в коллекцию. Пользователь вводит все поля по очереди.
 */
public class AddCommand extends CollectionCommand{
    private CityFactory cityFactory;

    public AddCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
        this.cityFactory = new CityFactory(validator);
    }
    
    @Override
    public void execute(String args) {
        try {
            collectionManager.add(cityFactory.createCity());
        } catch (Exception e){
            System.out.println("Ошибка при создании города:" + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Добавить в коллекцию новый элемент";
    }

    @Override
    public String getName() {
        return "add";
    }
    
}
