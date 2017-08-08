package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDRingType {

    ROCKY,
    METALLIC,
    METAL_RICH,
    ICY,
    UNKNOWN;

    public static EDRingType byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Rocky": return ROCKY;
            case "Metallic": return METALLIC;
            case "Metal Rich": return METAL_RICH;
            case "Icy": return ICY;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ROCKY: return "Rocky";
            case METALLIC: return "Metallic";
            case METAL_RICH: return "Metal Rich";
            case ICY: return "Icy";
            default: return "UNKNOWN";
        }
    }
}