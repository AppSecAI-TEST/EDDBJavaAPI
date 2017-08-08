package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDSettlementSecurity {

    LOW,
    MEDIUM,
    HIGH,
    NONE,
    UNKNOWN;

    public static EDSettlementSecurity byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Low": return LOW;
            case "Medium": return MEDIUM;
            case "HIGH": return HIGH;
            case "None": return NONE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case LOW: return "Low";
            case MEDIUM: return "Medium";
            case HIGH: return "High";
            case NONE: return "None";
            default: return "UNKNOWN";
        }
    }
}
