package ua.gwm.eddb_java_api.api;

public enum EDBodyGroup {

    STAR,
    COMPACT_STAR,
    PLANET,
    BELT,
    UNKNOWN;

    public static EDBodyGroup byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Star": return STAR;
            case "Compact Star": return COMPACT_STAR;
            case "Planet": return PLANET;
            case "Belt": return BELT;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case STAR: return "Star";
            case COMPACT_STAR: return "Compact Star";
            case PLANET: return "Planet";
            case BELT: return "Belt";
            default: return "UNKNOWN";
        }
    }
}
