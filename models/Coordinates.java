package models;

/**
 * Координаты города (x, y).
 */
public class Coordinates {
    /**
     * Создаёт координаты с заданными значениями.
     * @param x координата X (> -591, не null)
     * @param y координата Y (> -156, не null)
     * @throws IllegalArgumentException если значения некорректны
     */
    private static final double MIN_X = -591.0;
    private static final float MIN_Y = -156.0f;

    private Double x; //Значение поля должно быть больше -591, Поле не может быть null
    private Float y; //Значение поля должно быть больше -156, Поле не может быть null

    public Coordinates(Double x, Float y) {
        setX(x);
        setY(y);
    }

    public Double getX() {return x; }
    public Float getY() {return y; }

    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("X не может быть null");
        }
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("X должен быть числом");
        }
        if (x <= MIN_X) {
            throw new IllegalArgumentException("X должен быть больше " + MIN_X);
        }
        this.x = x;
    }

    public void setY(Float y) {
        if (y == null) {
            throw new IllegalArgumentException("Y не может быть null");
        }
        if (Float.isNaN(y) || Float.isInfinite(y)) {
            throw new IllegalArgumentException("Y должен быть числом");
        }
        if (y <= MIN_Y) {
            throw new IllegalArgumentException("Y должен быть больше " + MIN_Y);
        }
        this.y = y;
    }
}
