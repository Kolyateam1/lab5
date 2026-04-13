package commands.impl;

import collection.CollectionManager;
import commands.CollectionCommand;
import models.StandardOfLiving;
import utils.InputValidator;

/**
 * Команда "remove_any_by_standard_of_living standardOfLiving".
 * Удаляет один любой элемент коллекции, у которого значение поля standardOfLiving равно заданному.
 */
public class RemoveAnyByStandardOfLivingCommand extends CollectionCommand{

    public RemoveAnyByStandardOfLivingCommand(CollectionManager collectionManager, InputValidator validator) {
        super(collectionManager, validator); 
    }
    
    @Override
    public void execute(String args) {
        if (args == null || args.trim().isEmpty()) {
            System.out.println("Укажите уровень жизни");
            System.out.println("Доступные значения:" + StandardOfLiving.getNames() + ", null");
            return;
        }

        String upperArg = args.trim().toUpperCase();

        if (upperArg.equals("NULL")) {
            collectionManager.removeAnyByStandardOfLiving(null);
            return;
        } 

        try {
            StandardOfLiving sol = StandardOfLiving.valueOf(upperArg);
            collectionManager.removeAnyByStandardOfLiving(sol);
        } catch (IllegalArgumentException e) {
            System.out.println("Введите одно из:" + StandardOfLiving.getNames() + ", null");
        }
    }

    @Override
    public String getDescription() {
        return "Удалить один элемент по уровню жизни";
    }

    @Override
    public String getName() {
        return "remove_any_by_standart_of_living sol";
    }
    
}
