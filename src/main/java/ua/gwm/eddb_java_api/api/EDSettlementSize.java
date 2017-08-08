package ua.gwm.eddb_java_api.api;

public enum EDSettlementSize {

    SMALL,
    MEDIUM,
    BIG,
    UNKNOWN;

    public static EDSettlementSize byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "+": return SMALL;
            case "++": return MEDIUM;
            case "+++": return BIG;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case SMALL: return "+";
            case MEDIUM: return "++";
            case BIG: return "+++";
            default: return "UNKNOWN";
        }
    }
}
