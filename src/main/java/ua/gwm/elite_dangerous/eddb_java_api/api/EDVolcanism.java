package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDVolcanism {

    WATER_MAGMA,
    IRON_MAGMA,
    SILICATE_MAGMA,
    NITROGEN_MAGMA,
    METHANE_MAGMA,
    AMMONIA_MAGMA,
    WATER_GEYSERS,
    SILICATE_VAPOUR_GEYSERS,
    CARBON_DIOXIDE_GEYSERS,
    NO_VOLCANISM,
    UNKNOWN;

    public static EDVolcanism byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Water magma": return WATER_MAGMA;
            case "Iron magma": return IRON_MAGMA;
            case "Silicate magma": return SILICATE_MAGMA;
            case "Nitrogen magma": return NITROGEN_MAGMA;
            case "Methane magma": return METHANE_MAGMA;
            case "Ammonia magma": return AMMONIA_MAGMA;
            case "Water geysers": return WATER_GEYSERS;
            case "Silicate vapour geysers": return SILICATE_VAPOUR_GEYSERS;
            case "Carbon dioxide geysers": return CARBON_DIOXIDE_GEYSERS;
            case "No volcanism": return NO_VOLCANISM;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case WATER_MAGMA: return "Water magma";
            case IRON_MAGMA: return "Iron magma";
            case SILICATE_MAGMA: return "Silicate magma";
            case NITROGEN_MAGMA: return "Nitrogen magma";
            case METHANE_MAGMA: return "Methane magma";
            case AMMONIA_MAGMA: return "Ammonia magma";
            case WATER_GEYSERS: return "Water geysers";
            case SILICATE_VAPOUR_GEYSERS: return "Silicate vapour geysers";
            case CARBON_DIOXIDE_GEYSERS: return "Carbon dioxide geysers";
            case NO_VOLCANISM: return "No volcanism";
            default: return "UNKNOWN";
        }
    }
}
