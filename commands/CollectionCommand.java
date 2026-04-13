package commands;

import collection.CollectionManager;
import utils.InputValidator;

/**
 * Управляет коллекцией городов (LinkedList).
 * Обеспечивает основные операции добавления, удаления, сортировки и т.д.
 */
public abstract class CollectionCommand implements Command{
    protected CollectionManager collectionManager;
    protected InputValidator validator;

    public CollectionCommand(CollectionManager collectionManager, InputValidator validator) {
        this.collectionManager = collectionManager;
        this.validator = validator;
    }
}
