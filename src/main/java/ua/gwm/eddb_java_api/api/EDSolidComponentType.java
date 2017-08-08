package ua.gwm.eddb_java_api.api;

public enum EDSolidComponentType {

    ROCK,
    ICE,
    METAL,
    UNKNOWN;

    public static EDSolidComponentType byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Rock": return ROCK;
            case "Ice": return ICE;
            case "Metal": return METAL;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ROCK: return "Rock";
            case ICE: return "Ice";
            case METAL: return "Metal";
            default: return "UNKNOWN";
        }
    }
}