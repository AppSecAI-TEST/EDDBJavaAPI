package ua.gwm.eddb_java_api.api;

public enum EDStationType {

    ORBIS_STARPORT,
    CORIOLIS_STARPORT,
    OCELLUS_STARPORT,
    UNKNOWN_STARPORT,
    ASTEROID_BASE,
    COMMERCIAL_OUTPOST,
    INDUSTRIAL_OUTPOST,
    MINING_OUTPOST,
    SCIENTIFIC_OUTPOST,
    MILITARY_OUTPOST,
    CIVILIAN_OUTPOST,
    UNKNOWN_OUTPOST,
    MEGASHIP,
    PLANETARY_PORT,
    PLANETARY_OUTPOST,
    PLANETARY_ENGINEER_BASE,
    PLANETARY_SETTLEMENT,
    UNKNOWN_PLANETARY,
    UNKNOWN;

    public static EDStationType byName(String name) {
        switch (name) {
            case "Orbis Starport": return ORBIS_STARPORT;
            case "Coriolis Starport": return CORIOLIS_STARPORT;
            case "Ocellus Starport": return OCELLUS_STARPORT;
            case "Unknown Starport": return UNKNOWN_STARPORT;
            case "Asteroid Base": return ASTEROID_BASE;
            case "Commercial Outpost": return COMMERCIAL_OUTPOST;
            case "Industrial Outpost": return INDUSTRIAL_OUTPOST;
            case "Mining Outpost": return MINING_OUTPOST;
            case "Scientific Outpost": return SCIENTIFIC_OUTPOST;
            case "Military Outpost": return MILITARY_OUTPOST;
            case "Civilian Outpost": return CIVILIAN_OUTPOST;
            case "Unknown Outpost": return UNKNOWN_OUTPOST;
            case "Megaship": return MEGASHIP;
            case "Planetary Port": return PLANETARY_PORT;
            case "Planetary Outpost": return PLANETARY_OUTPOST;
            case "Planetary Engineer Base": return PLANETARY_ENGINEER_BASE;
            case "Planetary Settlement": return PLANETARY_SETTLEMENT;
            case "Unknown Planetary": return UNKNOWN_PLANETARY;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ORBIS_STARPORT: return "Orbis Starport";
            case CORIOLIS_STARPORT: return "Coriolis Starport";
            case OCELLUS_STARPORT: return "Ocellus Starport";
            case UNKNOWN_STARPORT: return "Unknown Startport";
            case ASTEROID_BASE: return "Asteroid Base";
            case COMMERCIAL_OUTPOST: return "Commercial Outpost";
            case INDUSTRIAL_OUTPOST: return "Industrial Outpost";
            case MINING_OUTPOST: return "Mining Outpost";
            case SCIENTIFIC_OUTPOST: return "Scientific Outpost";
            case MILITARY_OUTPOST: return "Military Outpost";
            case CIVILIAN_OUTPOST: return "Civilian Outpost";
            case UNKNOWN_OUTPOST: return "Unknown Outpost";
            case MEGASHIP: return "Megaship";
            case PLANETARY_PORT: return "Planetary Port";
            case PLANETARY_OUTPOST: return "Planetary Outpost";
            case PLANETARY_ENGINEER_BASE: return "Planetary Engineer Base";
            case PLANETARY_SETTLEMENT: return "Planetary Settlement";
            case UNKNOWN_PLANETARY: return "Unknown Planetary";
            default: return "UNKNOWN";
        }
    }
}
