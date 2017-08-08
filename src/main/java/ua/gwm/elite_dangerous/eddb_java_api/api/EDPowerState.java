package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDPowerState {

    CONTROL,
    EXPLOITED,
    UNKNOWN;

    public static EDPowerState byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Control": return CONTROL;
            case "Exploited": return EXPLOITED;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case CONTROL: return "Control";
            case EXPLOITED: return "Exploited";
            default: return "UNKNOWN";
        }
    }
}
