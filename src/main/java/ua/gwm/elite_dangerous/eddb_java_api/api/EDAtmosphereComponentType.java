package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDAtmosphereComponentType {

    WATER,
    METHANE,
    OXYGEN,
    HYDROGEN,
    CARBON_DIOXIDE,
    IRON,
    SILICATES,
    NITROGEN,
    HELIUM,
    AMMONIA,
    NEON,
    SULPHUR_DIOXIDE,
    ARGON,
    UNKNOWN;

    public static EDAtmosphereComponentType byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Water": return WATER;
            case "Methane": return METHANE;
            case "Oxygen": return OXYGEN;
            case "Hydrogen": return HYDROGEN;
            case "Carbon dioxide": return CARBON_DIOXIDE;
            case "Iron": return IRON;
            case "Silicates": return SILICATES;
            case "Nitrogen": return NITROGEN;
            case "Helium": return HELIUM;
            case "Ammonia": return AMMONIA;
            case "Neon": return NEON;
            case "Sulphur dioxide": return SULPHUR_DIOXIDE;
            case "Argon": return ARGON;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case WATER: return "Water";
            case METHANE: return "Methane";
            case OXYGEN: return "Oxygen";
            case HYDROGEN: return "Hydrogen";
            case CARBON_DIOXIDE: return "Carbon dioxide";
            case IRON: return "Iron";
            case SILICATES: return "Silicates";
            case NITROGEN: return "Nitrogen";
            case HELIUM: return "Helium";
            case AMMONIA: return "Ammonia";
            case NEON: return "Neon";
            case SULPHUR_DIOXIDE: return "Sulphur dioxide";
            case ARGON: return "Argon";
            default: return "UNKNOWN";
        }
    }
}