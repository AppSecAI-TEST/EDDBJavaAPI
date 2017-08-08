package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDGovernment {

    ANARCHY,
    CONFEDERACY,
    CORPORATE,
    COOPERATIVE,
    DEMOCRACY,
    DICTATORSHIP,
    FEUDAL,
    PATRONAGE,
    PRISON_COLONY,
    THEOCRACY,
    ENGINEER,
    UNKNOWN;

    public static EDGovernment byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Anarchy": return ANARCHY;
            case "Confederacy": return CONFEDERACY;
            case "Corporate": return CORPORATE;
            case "Cooperative": return COOPERATIVE;
            case "Democracy": return DEMOCRACY;
            case "Dictatorship": return DICTATORSHIP;
            case "Feudal": return FEUDAL;
            case "Patronage": return PATRONAGE;
            case "Prison Colony": return PRISON_COLONY;
            case "Theocracy": return THEOCRACY;
            case "Engineer": return ENGINEER;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ANARCHY: return "Anarchy";
            case CONFEDERACY: return "Confederacy";
            case CORPORATE: return "Corporate";
            case COOPERATIVE: return "Cooperative";
            case DEMOCRACY: return "Democracy";
            case DICTATORSHIP: return "Dictatorship";
            case FEUDAL: return "Feudal";
            case PATRONAGE: return "Patronage";
            case PRISON_COLONY: return "Prison Colony";
            case THEOCRACY: return "Theocracy";
            case ENGINEER: return "Engineer";
            default: return "UNKNOWN";
        }
    }
}

