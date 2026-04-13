package models;

/**
 * Перечисление возможных климатических зон.
 */
public enum Climate {
    TROPICAL_SAVANNA,
    OCEANIC,
    MEDITERRANIAN;

    public static String getNames() {
        StringBuilder sb = new StringBuilder();
        for (Climate clim : values()) {
            sb.append(clim.name()).append(", ");
        }
        return sb.substring(0, sb.length()-2);
    }
}
