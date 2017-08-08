package ua.gwm.eddb_java_api.api;

public enum EDWeaponMode {

    GIMBAL,
    TURRET,
    FIXED,
    UNKNOWN;

    public static EDWeaponMode byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Gimbal": return GIMBAL;
            case "Turret": return TURRET;
            case "Fixed": return FIXED;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case GIMBAL: return "Gimbal";
            case TURRET: return "Turret";
            case FIXED: return "Fixed";
            default: return "UNKNOWN";
        }
    }
}