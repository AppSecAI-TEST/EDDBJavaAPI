package ua.gwm.eddb_java_api.api;

public enum EDBodyType {

    EARTH_LIKE_WORLD,
    AMMONIA_WORLD,
    HIGH_METAL_CONTENT_WORLD,
    METAL_RICH_BODY,
    ROCKY_BODY,
    ICY_BODY,
    ROCKY_ICE_WORLD,
    WATER_WORLD,
    WATER_GIANT,
    CLASS_1_GAS_GIANT,
    CLASS_2_GAS_GIANT,
    CLASS_3_GAS_GIANT,
    CLASS_4_GAS_GIANT,
    CLASS_5_GAS_GIANT,
    GAS_GIANT_WITH_WATER_BASED_LIFE,
    GAS_GIANT_WITH_AMMONIA_BASED_LIFE,
    HELIUM_RICH_GAS_GIANT,
    NEUTRON_STAR,
    BLACK_HOLE,
    SUPERMASSIVE_BLACK_HOLE,
    UNKNOWN;

    public static EDBodyType byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Earth-like world": return EARTH_LIKE_WORLD;
            case "Ammonia world": return AMMONIA_WORLD;
            case "High metal content world": return HIGH_METAL_CONTENT_WORLD;
            case "Metal-rich body": return METAL_RICH_BODY;
            case "Rocky body": return ROCKY_BODY;
            case "Icy body": return ICY_BODY;
            case "Rocky ice body": return ROCKY_ICE_WORLD;
            case "Water world": return WATER_WORLD;
            case "Water giant": return WATER_GIANT;
            case "Class I gas giant": return CLASS_1_GAS_GIANT;
            case "Class II gas giant": return CLASS_2_GAS_GIANT;
            case "Class III gas giant": return CLASS_3_GAS_GIANT;
            case "Class IV gas giant": return CLASS_4_GAS_GIANT;
            case "Class V gas giant": return CLASS_5_GAS_GIANT;
            case "Gas giant with water-based life": return GAS_GIANT_WITH_WATER_BASED_LIFE;
            case "Gas giant with ammonia-based life": return GAS_GIANT_WITH_AMMONIA_BASED_LIFE;
            case "Helium-rich gas giant": return HELIUM_RICH_GAS_GIANT;
            case "Neutron star": return NEUTRON_STAR;
            case "Black hole": return BLACK_HOLE;
            case "Supermassive black hole": return SUPERMASSIVE_BLACK_HOLE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case EARTH_LIKE_WORLD: return "Earth-like world";
            case AMMONIA_WORLD: return "Ammonia world";
            case HIGH_METAL_CONTENT_WORLD: return "High metal content world";
            case METAL_RICH_BODY: return "Metal-rich body";
            case ROCKY_BODY: return "Rocky body";
            case ICY_BODY: return "Icy body";
            case ROCKY_ICE_WORLD: return "Rocky ice body";
            case WATER_WORLD: return "Water world";
            case WATER_GIANT: return "Water giant";
            case CLASS_1_GAS_GIANT: return "Class I gas giant";
            case CLASS_2_GAS_GIANT: return "Class II gas giant";
            case CLASS_3_GAS_GIANT: return "Class III gas giant";
            case CLASS_4_GAS_GIANT: return "Class IV gas giant";
            case CLASS_5_GAS_GIANT: return "Class V gas giant";
            case GAS_GIANT_WITH_WATER_BASED_LIFE: return "Gas giant with water-based life";
            case GAS_GIANT_WITH_AMMONIA_BASED_LIFE: return "Gas giant with ammonia-based life";
            case HELIUM_RICH_GAS_GIANT: return "Helium-rich gas giant";
            case NEUTRON_STAR: return "Neutron star";
            case BLACK_HOLE: return "Black hole";
            case SUPERMASSIVE_BLACK_HOLE: return "Supermassive black hole";
            default: return "UNKNOWN";
        }
    }
}
