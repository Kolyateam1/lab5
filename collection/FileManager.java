package collection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

import models.City;
import models.Climate;
import models.Coordinates;
import models.Government;
import models.Human;
import models.StandardOfLiving;

/**
 * Отвечает за чтение и запись коллекции в CSV-файл.
 * Использует {@link java.io.InputStreamReader} и {@link java.io.PrintWriter}.
 */
public class FileManager {
    private String filename;
    private static final String CSV_DELIMITER = ";";
    private static final int EXPECTED_FIELDS = 12;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public LinkedList<City> readCollection() {
        LinkedList<City> collection = new LinkedList<>();
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Файл не существует, будет создан новый при сохранении");
            return collection;
        }

        if (!file.canRead()) {
            System.err.println("Нет прав на чтение файла");
            return collection;
        }

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
            BufferedReader br = new BufferedReader(reader);
            String line;
            int lineNum = 0;
            
            while ((line = br.readLine()) != null) {
                lineNum++;
                if (line.trim().isEmpty()) {
                    continue; // пропускаем пустые строки
                }
                
                try {
                    City city = parseCity(line);
                    if (city != null) {
                        collection.add(city);
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка в строке " + lineNum + ": " + e.getMessage());
                }
            }
            
            System.out.println("Загружено городов: " + collection.size());
            
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        
        return collection;
    }

    public void writeCollection(LinkedList<City> collection) throws IOException {
        File file = new File(filename);
        
        if (!file.exists()) {
            file.createNewFile();
        }
        
        if (!file.canWrite()) {
            throw new IOException("Нет прав на запись в файл");
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (City city : collection) {
                writer.println(cityToCSV(city));
            }
            writer.flush();
        }
    }

    private City parseCity(String line) {
        String[] parts = line.split(CSV_DELIMITER, -1);
        
        if (parts.length < EXPECTED_FIELDS) {
            throw new IllegalArgumentException("Недостаточно полей");
        }

        try {
            City city = new City();
            
            // id пропускаем - генерируется автоматически
            
            // name
            if (parts[1].isEmpty()) {
                throw new IllegalArgumentException("Пустое имя");
            }
            city.setName(parts[1]);
            
            // coordinates
            if (parts[2].isEmpty() || parts[3].isEmpty()) {
                throw new IllegalArgumentException("Пустые координаты");
            }
            Coordinates coords = new Coordinates(
                Double.parseDouble(parts[2]),
                Float.parseFloat(parts[3])
            );
            city.setCoordinates(coords);
            
            // area
            if (parts[5].isEmpty()) {
                throw new IllegalArgumentException("Пустая площадь");
            }
            city.setArea(Double.parseDouble(parts[5]));
            
            // population
            if (parts[6].isEmpty()) {
                throw new IllegalArgumentException("Пустое население");
            }
            city.setPopulation(Long.parseLong(parts[6]));
            
            // metersAboveSeaLevel
            if (!parts[7].isEmpty()) {
                city.setMetersAboveSeaLevel(Long.parseLong(parts[7]));
            }
            
            // climate
            if (parts[8].isEmpty()) {
                throw new IllegalArgumentException("Пустой климат");
            }
            city.setClimate(Climate.valueOf(parts[8]));
            
            // government
            if (parts[9].isEmpty()) {
                throw new IllegalArgumentException("Пустое правление");
            }
            city.setGovernment(Government.valueOf(parts[9]));
            
            // standardOfLiving
            if (!parts[10].isEmpty()) {
                city.setStandardOfLiving(StandardOfLiving.valueOf(parts[10]));
            }
            
            // governor
            if (parts[11].isEmpty()) {
                throw new IllegalArgumentException("Пустой возраст губернатора");
            }
            Human governor = new Human(Long.parseLong(parts[11]));
            city.setGovernor(governor);
            
            return city;
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числа: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверное значение: " + e.getMessage());
        }
    }

    private String cityToCSV(City city) {
        return String.join(CSV_DELIMITER,
            String.valueOf(city.getId()),
            city.getName(),
            String.valueOf(city.getCoordinates().getX()),
            String.valueOf(city.getCoordinates().getY()),
            city.getCreationDate().toString(),
            String.valueOf(city.getArea()),
            String.valueOf(city.getPopulation()),
            city.getMetersAboveSeaLevel() != null ? String.valueOf(city.getMetersAboveSeaLevel()) : "",
            city.getClimate().name(),
            city.getGovernment().name(),
            city.getStandardOfLiving() != null ? city.getStandardOfLiving().name() : "",
            String.valueOf(city.getGovernor().getAge())
        );
    }
}
