package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDLandingPadSize {

    MEDIUM,
    LARGE,
    NONE,
    UNKNOWN;

    public static EDLandingPadSize byName(String name) {
        switch (name) {
            case "M": return MEDIUM;
            case "L": return LARGE;
            case "None": return NONE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case MEDIUM: return "M";
            case LARGE: return "L";
            case NONE: return "None";
            default: return "UNKNOWN";
        }
    }
}
