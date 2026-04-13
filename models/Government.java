package models;

/**
 * Перечисление форм правления.
 */
public enum Government {
    KRITARCHY,
    MERITOCRACY,
    NOOCRACY,
    REPUBLIC;

    public static String getNames() {
        StringBuilder sb = new StringBuilder();
        for (Government gov : values()) {
            sb.append(gov.name()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
