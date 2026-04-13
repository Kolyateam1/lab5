package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import utils.InputValidator;

/**
 * Команда exit
 * Завершает работу программы. Коллекция при этом не сохраняется автоматически.
 */

public class ExitCommand extends CollectionCommand {
    public ExitCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        System.out.println("Завершение программы...");
        System.exit(0);
        }

    @Override
    public String getDescription() {
        return "Завершить программу без сохранения";
    }

    @Override
    public String getName() {
        return "exit";
    }
}