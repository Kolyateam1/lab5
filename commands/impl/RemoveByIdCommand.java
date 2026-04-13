package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда "remove_by_id id".
 * Удаляет город из коллекции по его уникальному идентификатору.
 */
public class RemoveByIdCommand extends CollectionCommand{

    public RemoveByIdCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        if (args == null || args.trim().isEmpty()) {
            System.out.println("Укажите id");
            return;
        }

        try {
            long id = Long.parseLong(args.trim());
            collectionManager.removeById(id);
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Удалить элемент по id";
    }

    @Override
    public String getName() {
        return "remove_by_id id";
    }
}
