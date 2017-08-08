package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDAtmosphere {

    NITROGEN,
    METALLIC_VAPOUR,
    SUITABLE_FOR_WATER_BASED_LIFE,
    ARGON_RICH,
    HELIUM,
    SULPHUR_DIOXIDE,
    WATER,
    CARBON_DIOXIDE,
    METHANE_RICH,
    NEON_RICH,
    METHANE,
    SILLICATE_VAPOUR,
    AMMONIA,
    CARBON_DIOXIDE_RICH,
    AMMONIA_RICH,
    ARGON,
    WATER_RICH,
    OXYGEN,
    NEON,
    AMMONIA_AND_OXYGEN,
    NITROGEN_RICH,
    UNKNOWN_ATMOSPHERE,
    NO_ATMOSPHERE,
    UNKNOWN;

    public static EDAtmosphere byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Nitrogen": return NITROGEN;
            case "Metallic vapour": return METALLIC_VAPOUR;
            case "Suitable for water based life": return SUITABLE_FOR_WATER_BASED_LIFE;
            case "Argon-rich": return ARGON_RICH;
            case "Helium": return HELIUM;
            case "Sulphur dioxide": return SULPHUR_DIOXIDE;
            case "Water": return WATER;
            case "Carbon dioxide": return CARBON_DIOXIDE;
            case "Methane-rich": return METHANE_RICH;
            case "Neon-rich": return NEON_RICH;
            case "Methane": return METHANE;
            case "Silicate vapour": return SILLICATE_VAPOUR;
            case "Ammonia": return AMMONIA;
            case "Carbon dioxide-rich": return CARBON_DIOXIDE_RICH;
            case "Ammonia-rich": return AMMONIA_RICH;
            case "Argon": return ARGON;
            case "Water-rich": return WATER_RICH;
            case "Oxygen": return OXYGEN;
            case "Neon": return NEON;
            case "Ammonia and oxygen": return AMMONIA_AND_OXYGEN;
            case "Nitrogen-rich": return NITROGEN_RICH;
            case "Unknown atmosphere": return UNKNOWN_ATMOSPHERE;
            case "No atmosphere": return NO_ATMOSPHERE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NITROGEN: return "Nitrogen";
            case METALLIC_VAPOUR: return "Metallic vapour";
            case SUITABLE_FOR_WATER_BASED_LIFE: return "Suitable for water based life";
            case ARGON_RICH: return "Argon-rich";
            case HELIUM: return "Helium";
            case SULPHUR_DIOXIDE: return "Sulphur dioxide";
            case WATER: return "Water";
            case CARBON_DIOXIDE: return "Carbon dioxide";
            case METHANE_RICH: return "Methane-rich";
            case NEON_RICH: return "Neon-rich";
            case METHANE: return "Methane";
            case SILLICATE_VAPOUR: return "Silicate vapour";
            case AMMONIA: return "Ammonia";
            case CARBON_DIOXIDE_RICH: return "Carbon dioxide-rich";
            case AMMONIA_RICH: return "Ammonia-rich";
            case ARGON: return "Argon";
            case WATER_RICH: return "Water-rich";
            case OXYGEN: return "Oxygen";
            case NEON: return "Neon";
            case AMMONIA_AND_OXYGEN: return "Ammonia and oxygen";
            case NITROGEN_RICH: return "Nitrogen-rich";
            case UNKNOWN_ATMOSPHERE: return "Unknown atmosphere";
            case NO_ATMOSPHERE: return "No atmosphere";
            default: return "UNKNOWN";
        }
    }
}
