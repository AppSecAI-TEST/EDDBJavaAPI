package ua.gwm.eddb_java_api.api;

public enum EDAllegiance {

    ALLIANCE,
    EMPIRE,
    FEDERATION,
    INDEPENDENT,
    UNKNOWN;

    public static EDAllegiance byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Alliance": return ALLIANCE;
            case "Empire": return EMPIRE;
            case "Federation": return FEDERATION;
            case "Independent": return INDEPENDENT;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ALLIANCE: return "Alliance";
            case EMPIRE: return "Empire";
            case FEDERATION: return "Federation";
            case INDEPENDENT: return "Independent";
            default: return "UNKNOWN";
        }
    }
}
