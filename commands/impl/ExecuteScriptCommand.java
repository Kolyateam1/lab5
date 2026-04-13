package commands.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import collection.CollectionManager;
import commands.CollectionCommand;
import commands.CommandExecutor;
import utils.InputValidator;

/**
 * Команда "execute_script file_name".
 * Выполняет команды из указанного файла скрипта. Поддерживает вложенные скрипты с защитой от рекурсии.
 */
public class ExecuteScriptCommand extends CollectionCommand{
    private CommandExecutor executor;
    private java.util.Set<String> scriptStack;
    private static final int MAX_SCRIPT_DEPH = 10;

    public ExecuteScriptCommand(CollectionManager collectionManager, 
                                InputValidator validator,
                                CommandExecutor executor) {
        super(collectionManager, validator);
        this.executor = executor;
        this.scriptStack = new java.util.HashSet<>();
    }
    
    @Override
    public void execute(String args) {
        if (args == null || args.trim().isEmpty()) {
            System.out.println("Укажите имя файла");
            return;
        }

        String filename = args.trim();
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Такого файла не существует");
            return;
        }

        if (!file.canRead()) {
            System.out.println("Нет прав на чтение файла");
            return;
        }

        if (scriptStack.contains(filename)) {
            System.out.println("В файле не должно быть рекурсий");
            return;
        }

        if (scriptStack.size() >= MAX_SCRIPT_DEPH) {
            System.out.println("Слишком много команд");
            return;
        }

        scriptStack.add(filename);
        boolean oldFileMode = validator.isFileMode();
        validator.setFileMode(true);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;

            while ((line = br.readLine()) != null) {
                lineNum++;
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }

                System.out.println("[" + filename + ":" + lineNum + "] ");
                System.out.println(line);
                executor.executeCommand(line);
            }

            System.out.println("Скрипт " + filename + " выполнен");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении скрипта: " + e.getMessage());
        } finally {
            validator.setFileMode(oldFileMode);
            scriptStack.remove(filename);
        }
    }

    @Override
    public String getDescription() {
        return "выполнить скрипт из файла";
    }

    @Override
    public String getName() {
        return "execute_script file_name";
    }
}
