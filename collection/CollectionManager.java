package collection;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.City;
import models.StandardOfLiving;

/**
 * Управляет коллекцией городов (LinkedList).
 * Обеспечивает основные операции добавления, удаления, сортировки и т.д.
 */
public class CollectionManager {
    private LinkedList<City> collection;
    private LocalDateTime initDate;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.collection = new LinkedList<>();
        this.initDate = LocalDateTime.now();
        loadCollection();
    }
    
    private void loadCollection() {
        try {
            LinkedList<City> loaded = fileManager.readCollection();
            this.collection = (loaded != null) ? loaded : new LinkedList<>();
        } catch (Exception e) {
            System.err.println("Ошибка загрузки: " + e.getMessage());
            this.collection = new LinkedList<>();
        }
    }

    public void info() {
        System.out.println("Тип коллекции: " + collection.getClass().getName());
        System.out.println("Дата инициализации: " + initDate);
        System.out.println("Количество элементов: " + collection.size());
    }

    public void show() {
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста");
            return;
        }
        for (City city : collection) {
            System.out.println(city);
        }
    }

    public boolean containsId(long id) {
        return collection.stream().anyMatch(c -> c.getId() == id);
    }

    public void add(City city) {
        collection.add(city);
        System.out.println("Город добавлен с id: " + city.getId());
    }

    public void update(long id, City newCity) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                newCity.setId(id);
                collection.set(i, newCity);
                System.out.println("Город с id " + id + " обновлен");
                return;
            }
        }
        System.out.println("Город с id " + id + " не найден");
    }

    public void removeById(long id) {
        if (collection.removeIf(c -> c.getId() == id)) {
            System.out.println("Город с id " + id + " удален");
        } else {
            System.out.println("Город с id " + id + " не найден");
        }
    }

    public void clear() {
        collection.clear();
        System.out.println("Коллекция очищена");
    }

    public void save() {
        try {
            fileManager.writeCollection(collection);
            System.out.println("Коллекция сохранена в файл");
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public void removeLower(City city) {
        int before = collection.size();
        collection.removeIf(c -> c.compareTo(city) < 0);
        int removed = before - collection.size();
        System.out.println("Удалено элементов: " + removed);
    }

    public void reorder() {
        Collections.reverse(collection);
        System.out.println("Порядок изменен на обратный");
    }

    public void sort() {
        Collections.sort(collection);
        System.out.println("Коллекция отсортирована по id");
    }

    public void removeAnyByStandardOfLiving(StandardOfLiving sol) {
        for (City city : collection) {
            if (sol == null) {
                if (city.getStandardOfLiving() == null) {
                    collection.remove(city);
                    System.out.println("Удален город с null уровнем жизни");
                    return;
                }
            } else if (sol.equals(city.getStandardOfLiving())) {
                collection.remove(city);
                System.out.println("Удален город с уровнем жизни " + sol);
                return;
            }
        }
        System.out.println("Элемент с таким уровнем жизни не найден");
    }

    public void groupCountingByGovernor() {
        Map<Long, Long> groups = collection.stream()
            .collect(Collectors.groupingBy(
                c -> c.getGovernor().getAge(),
                Collectors.counting()
            ));
        
        if (groups.isEmpty()) {
            System.out.println("Коллекция пуста");
            return;
        }
        
        groups.forEach((age, count) -> 
            System.out.println("Возраст " + age + ": " + count + " городов"));
    }

    public void filterStartsWithName(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            System.out.println("Ошибка: укажите непустой префикс");
            return;
        }
        
        List<City> filtered = collection.stream()
            .filter(c -> c.getName().toLowerCase().startsWith(prefix.toLowerCase()))
            .collect(Collectors.toList());
        
        if (filtered.isEmpty()) {
            System.out.println("Городов, начинающихся с '" + prefix + "', не найдено");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    public LinkedList<City> getCollection() {
        return collection;
    }
}
