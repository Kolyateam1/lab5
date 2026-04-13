package models;

/**
 * Губернатор города (только возраст).
 */
public class Human {
    private long age; //Значение поля должно быть больше 0

    public Human(long age) {
        setAge(age);
    }

    public long getAge(){
        return age;
    }

    public void setAge(long age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Не родившихся детей мы не учитываем:)");
        }
        this.age = age;
    }
}
