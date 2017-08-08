package ua.gwm.eddb_java_api.api;

public enum EDReserveType {

    PRISTINE,
    MAJOR,
    COMMON,
    LOW,
    DEPLETED,
    UNKNOWN;

    public static EDReserveType byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Pristine": return PRISTINE;
            case "Major": return MAJOR;
            case "Common": return COMMON;
            case "Low": return LOW;
            case "Depleted": return DEPLETED;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PRISTINE: return "Pristine";
            case MAJOR: return "Major";
            case COMMON: return "Common";
            case LOW: return "Low";
            case DEPLETED: return "Depleted";
            default: return "UNKNOWN";
        }
    }
}
