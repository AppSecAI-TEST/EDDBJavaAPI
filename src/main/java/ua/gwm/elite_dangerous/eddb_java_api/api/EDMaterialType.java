package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDMaterialType {

    ARSENIC,
    VANADIUM,
    IRON,
    TECHNETIUM,
    SULPHUR,
    NIOBIUM,
    TELLURIUM,
    TUNGSTEN,
    SELENIUM,
    CADMIUM,
    RUTHENIUM,
    PHOSPHORUS,
    ANTIMONY,
    CARBON,
    MOLYBDENUM,
    ZINC,
    ZIRCONIUM,
    CHROMIUM,
    TIN,
    GERMANIUM,
    POLONIUM,
    NICKEL,
    YTTRIUM,
    MERCURY,
    MANGANESE,
    UNKNOWN;

    public static EDMaterialType byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Arsenic": return ARSENIC;
            case "Vanadium": return VANADIUM;
            case "Iron": return IRON;
            case "Technetium": return TECHNETIUM;
            case "Sulphur": return SULPHUR;
            case "Niobium": return NIOBIUM;
            case "Tellurium": return TELLURIUM;
            case "Tungsten": return TUNGSTEN;
            case "Selenium": return SELENIUM;
            case "Cadmium": return CADMIUM;
            case "Ruthenium": return RUTHENIUM;
            case "Phosphorus": return PHOSPHORUS;
            case "Antimony": return ANTIMONY;
            case "Carbon": return CARBON;
            case "Molybdenum": return MOLYBDENUM;
            case "Zinc": return ZINC;
            case "Zirconium": return ZIRCONIUM;
            case "Chromium": return CHROMIUM;
            case "Tin": return TIN;
            case "Germanium": return GERMANIUM;
            case "Polonium": return POLONIUM;
            case "Nickel": return NICKEL;
            case "Yttrium": return YTTRIUM;
            case "Mercury": return MERCURY;
            case "Manganese": return MANGANESE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ARSENIC: return "Arsenic";
            case VANADIUM: return "Vanadium";
            case IRON: return "Iron";
            case TECHNETIUM: return "Technetium";
            case SULPHUR: return "Sulphur";
            case NIOBIUM: return "Niobium";
            case TELLURIUM: return "Tellurium";
            case TUNGSTEN: return "Tungsten";
            case SELENIUM: return "Selenium";
            case CADMIUM: return "Cadmium";
            case RUTHENIUM: return "Ruthenium";
            case PHOSPHORUS: return "Phosphorus";
            case ANTIMONY: return "Antimony";
            case CARBON: return "Carbon";
            case MOLYBDENUM: return "Molybdenum";
            case ZINC: return "Zinc";
            case ZIRCONIUM: return "Zirconium";
            case CHROMIUM: return "Chromium";
            case TIN: return "Tin";
            case GERMANIUM: return "Germanium";
            case POLONIUM: return "Polonium";
            case NICKEL: return "Nickel";
            case YTTRIUM: return "Yttrium";
            case MERCURY: return "Mercury";
            case MANGANESE: return "Manganese";
            default: return "UNKNOWN";
        }
    }
}
