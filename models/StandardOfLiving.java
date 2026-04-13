package models;

/**
 * Перечисление уровней жизни.
 */
public enum StandardOfLiving {
    ULTRA_HIGH,
    VERY_HIGH,
    VERY_LOW,
    NIGHTMARE;

    public static String getNames() {
        StringBuilder sb = new StringBuilder();
        for (StandardOfLiving s : values()) {
            sb.append(s.name()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
