import collection.CollectionManager;
import collection.FileManager;
import commands.CommandExecutor;
import utils.InputValidator;
import java.io.File;

/**
 * Точка входа в программу.
 * Обрабатывает аргумент командной строки (имя файла),
 * инициализирует компоненты и запускает интерактивный режим.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Управление коллекцией городов");

        String filename;
        if (args.length == 0) {
            String baseName = "collection";
            String extension = ".csv";
            int counter = 0;
            File file;
            while (true) {
                String candidate = baseName + (counter == 0 ? "" : String.valueOf(counter)) + extension;
                file = new File(candidate);
                if (!file.exists()) {
                    filename = candidate;
                    break;
                }
                counter++;
            }
            System.out.println("Имя файла не указано. Будет создан файл с названием по умолчанию: " + filename);
        } else {
        filename = args[0];
        System.out.println("Файл данных: " + filename);}
        
        try {
            FileManager fileManager = new FileManager(filename);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            InputValidator validator = new InputValidator();
            CommandExecutor executor = new CommandExecutor(collectionManager, validator);

            System.out.println("Программа запущена. Введите 'help' для списка команд.\n");

            while (true) {
                System.out.print("> ");
                String command = validator.readLine(null);
                executor.execute(command);
            }
            
        } catch (Exception e) {
            System.err.println("Критическая ошибка: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
