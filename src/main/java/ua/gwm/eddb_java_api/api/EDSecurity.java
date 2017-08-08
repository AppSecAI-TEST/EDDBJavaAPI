package ua.gwm.eddb_java_api.api;

public enum  EDSecurity {

    LOW,
    MEDIUM,
    HIGH,
    ANARCHY,
    LAWLESS,
    UNKNOWN;

    public static EDSecurity byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Low": return LOW;
            case "Medium": return MEDIUM;
            case "High": return HIGH;
            case "Anarchy": return ANARCHY;
            case "Lawless": return LAWLESS;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case LOW: return "Low";
            case MEDIUM: return "Medium";
            case HIGH: return "High";
            case ANARCHY: return "Anarchy";
            case LAWLESS: return "Lawless";
            default: return "UNKNOWN";
        }
    }
}
