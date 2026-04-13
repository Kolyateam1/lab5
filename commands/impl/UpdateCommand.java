package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.CityFactory;
import utils.InputValidator;

/**
 * Команда "update id".
 * Обновляет город с указанным id. Пользователь вводит новые значения для всех полей.
 */
public class UpdateCommand extends CollectionCommand{
    private CityFactory cityFactory;

    public UpdateCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
        this.cityFactory = new CityFactory(validator);
    }
    
    @Override
    public void execute(String args) {
        if (args == null || args.trim().isEmpty()) {
            System.out.println("Укажите id");
            return;
        }

        try {
            long id = Long.parseLong(args.trim());

            if (!collectionManager.containsId(id)) {
                System.out.println("Города с id " + id + " не существует");
                return;
            }

            collectionManager.update(id, cityFactory.createCity());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента с данным id";
    }

    @Override
    public String getName() {
        return "update id";
    }
    
}
