package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDShip {

    SIDEWINDER_MK_1,
    EAGLE_MK_2,
    HAULER,
    ADDER,
    IMPERIAL_EAGLE,
    VIPER_MK_3,
    COBRA_MK_3,
    VIPER_MK_4,
    DIAMONDBACK_SCOUT,
    COBRA_MK_4,
    TYPE_6_TRANSPORTER,
    DOLPHIN,
    DIAMONDBACK_EXPLORER,
    IMPERIAL_COURIER,
    KEELBACK,
    ASP_SCOUT,
    VULTURE,
    ASP_EXPLORER,
    FEDERAL_DROPSHIP,
    TYPE_7_TRANSPORTER,
    FEDERAL_ASSAULT_SHIP,
    IMPERIAL_CLIPPER,
    FEDERAL_GUNSHIP,
    ORCA,
    FER_DE_LANCE,
    PYTHON,
    TYPE_9_HEAVY,
    BELUGA_LINER,
    ANACONDA,
    FEDERAL_CORVETTE,
    IMPERIAL_CUTTER,
    UNKNOWN;

    public static EDShip byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Sidewinder Mk. I": return SIDEWINDER_MK_1;
            case "Eagle Mk. II": return EAGLE_MK_2;
            case "Hauler": return HAULER;
            case "Adder": return ADDER;
            case "Imperial Eagle": return IMPERIAL_EAGLE;
            case "Viper Mk III": return VIPER_MK_3;
            case "Cobra Mk. III": return COBRA_MK_3;
            case "Viper MK IV": return VIPER_MK_4;
            case "Diamondback Scout": return DIAMONDBACK_SCOUT;
            case "Cobra MK IV": return COBRA_MK_4;
            case "Type-6 Transporter": return TYPE_6_TRANSPORTER;
            case "Dolphin": return DOLPHIN;
            case "Diamondback Explorer": return DIAMONDBACK_EXPLORER;
            case "Imperial Courier": return IMPERIAL_COURIER;
            case "Keelback": return KEELBACK;
            case "Asp Scout": return ASP_SCOUT;
            case "Vulture": return VULTURE;
            case "Asp Explorer": return ASP_EXPLORER;
            case "Federal Dropship": return FEDERAL_DROPSHIP;
            case "Type-7 Transporter": return TYPE_7_TRANSPORTER;
            case "Federal Assault Ship": return FEDERAL_ASSAULT_SHIP;
            case "Imperial Clipper": return IMPERIAL_CLIPPER;
            case "Federal Gunship": return FEDERAL_GUNSHIP;
            case "Orca": return ORCA;
            case "Fer-de-Lance": return FER_DE_LANCE;
            case "Python": return PYTHON;
            case "Type-9 Heavy": return TYPE_9_HEAVY;
            case "Beluga Liner": return BELUGA_LINER;
            case "Anaconda": return ANACONDA;
            case "Federal Corvette": return FEDERAL_CORVETTE;
            case "Imperial Cutter": return IMPERIAL_CUTTER;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case SIDEWINDER_MK_1: return "Sidewinder Mk. I";
            case EAGLE_MK_2: return "Eagle Mk. II";
            case HAULER: return "Hauler";
            case ADDER: return "Added";
            case IMPERIAL_EAGLE: return "Imperial Eagle";
            case VIPER_MK_3: return "Viper Mk III";
            case COBRA_MK_3: return "Cobra Mk. III";
            case VIPER_MK_4: return "Viper MK IV";
            case DIAMONDBACK_SCOUT: return "Diamondback Scout";
            case COBRA_MK_4: return "Cobra MK IV";
            case TYPE_6_TRANSPORTER: return "Type-6 Transporter";
            case DOLPHIN: return "Dolphin";
            case DIAMONDBACK_EXPLORER: return "Diamondback Explorer";
            case IMPERIAL_COURIER: return "Imperial Courier";
            case KEELBACK: return "Keelback";
            case ASP_SCOUT: return "Asp Scout";
            case VULTURE: return "Vulture";
            case ASP_EXPLORER: return "Asp Explorer";
            case FEDERAL_DROPSHIP: return "Federal Drosphip";
            case TYPE_7_TRANSPORTER: return "Type-7 Transporter";
            case FEDERAL_ASSAULT_SHIP: return "Federal Assault Ship";
            case IMPERIAL_CLIPPER: return "Imperial Clipper";
            case FEDERAL_GUNSHIP: return "Federal Gunship";
            case ORCA: return "Orca";
            case FER_DE_LANCE: return "Fer-de-Lance";
            case PYTHON: return "Python";
            case TYPE_9_HEAVY: return "Type-9 Heavy";
            case BELUGA_LINER: return "Beluga Liner";
            case ANACONDA: return "Anaconda";
            case FEDERAL_CORVETTE: return "Federal Corvette";
            case IMPERIAL_CUTTER: return "Imperial Cutter";
            default: return "UNKNOWN";
        }
    }
}
