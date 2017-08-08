package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDState {

    BOOM,
    BUST,
    FAMINE,
    CIVIL_UNREST,
    CIVIL_WAR,
    ELECTION,
    EXPANSION,
    LOCKDOWN,
    OUTBREAK,
    WAR,
    RETREAT,
    INVESTMENT,
    NONE,
    UNKNOWN;

    public static EDState byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Boom": return BOOM;
            case "Bust": return BUST;
            case "Famine": return FAMINE;
            case "Civil Unrest": return CIVIL_UNREST;
            case "Civil War": return CIVIL_WAR;
            case "Election": return ELECTION;
            case "Expansion": return EXPANSION;
            case "Lockdown": return LOCKDOWN;
            case "Outbreak": return OUTBREAK;
            case "War": return WAR;
            case "Retreat": return RETREAT;
            case "Investment": return INVESTMENT;
            case "None": return NONE;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case BOOM: return "Boom";
            case BUST: return "Bust";
            case FAMINE: return "Famine";
            case CIVIL_UNREST: return "Civil Unrest";
            case CIVIL_WAR: return "Civil War";
            case ELECTION: return "Election";
            case EXPANSION: return "Expansion";
            case LOCKDOWN: return "Lockdown";
            case OUTBREAK: return "Outbreak";
            case WAR: return "War";
            case RETREAT: return "Retreat";
            case INVESTMENT: return "Investment";
            case NONE: return "None";
            default: return "UNKNOWN";
        }
    }
}
