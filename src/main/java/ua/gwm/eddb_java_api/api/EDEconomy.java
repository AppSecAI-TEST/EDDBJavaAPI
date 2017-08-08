package ua.gwm.eddb_java_api.api;

public enum EDEconomy {

    AGRICULTURE,
    EXTRACTION,
    HIGH_TECH,
    INDUSTRIAL,
    MILITARY,
    REFINERY,
    SERVICE,
    TERRAFORMING,
    TOURISM,
    COLONY,
    NONE,
    UNKNOWN;

    public static EDEconomy byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Agriculture": return AGRICULTURE;
            case "Extraction": return EXTRACTION;
            case "High Tech": return HIGH_TECH;
            case "Industrial": return INDUSTRIAL;
            case "Military": return MILITARY;
            case "Refinery": return REFINERY;
            case "Service": return SERVICE;
            case "Terraforming": return TERRAFORMING;
            case "Tourism": return TOURISM;
            case "Colony": return COLONY;
            case "None": return NONE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case AGRICULTURE: return "Agriculture";
            case EXTRACTION: return "Extraction";
            case HIGH_TECH: return "High Tech";
            case INDUSTRIAL: return "Industrial";
            case MILITARY: return "Military";
            case REFINERY: return "Refinery";
            case SERVICE: return "Service";
            case TERRAFORMING: return "Terraforming";
            case TOURISM: return "Tourism";
            case COLONY: return "Colony";
            case NONE: return "None";
            default: return "UNKNOWN";
        }
    }
}
