package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDPower {

    AISLING_DUVAL,
    ARISSA_LAVIGNY_DUVAL,
    DENTON_PATREUS,
    ZEMINA_TORVAL,
    ZACHARY_HUDSON,
    FELICIA_WINTERS,
    EDMUND_MAHON,
    LI_YONG_RUI,
    ARCHON_DELAINE,
    PRANAV_ANTAL,
    YURI_GROM,
    UNKNOWN;

    public static EDPower byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Aisling Duval": return AISLING_DUVAL;
            case "Arissa Lavigny-Duval": return ARISSA_LAVIGNY_DUVAL;
            case "Denton Patreus": return DENTON_PATREUS;
            case "Zemina Torval": return ZEMINA_TORVAL;
            case "Zachary Hudson": return ZACHARY_HUDSON;
            case "Felecia Winters": return FELICIA_WINTERS;
            case "Edmund Mahon": return EDMUND_MAHON;
            case "Li Yong-Rui": return LI_YONG_RUI;
            case "Archon Delaine": return ARCHON_DELAINE;
            case "Pranav Antal": return PRANAV_ANTAL;
            case "Yuri Grom": return YURI_GROM;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case AISLING_DUVAL: return "Aisling Duval";
            case ARISSA_LAVIGNY_DUVAL: return "Arissa Lavigny-Duval";
            case DENTON_PATREUS: return "Denton Patreus";
            case ZEMINA_TORVAL: return "Zemina Torval";
            case ZACHARY_HUDSON: return "Zachary Hudson";
            case FELICIA_WINTERS: return "Felecia Winters";
            case EDMUND_MAHON: return "Edmund Mahon";
            case LI_YONG_RUI: return "Li Yong-Rui";
            case ARCHON_DELAINE: return "Archon Delaine";
            case PRANAV_ANTAL: return "Pranav Antal";
            case YURI_GROM: return "Yuri Grom";
            default: return "UNKNOWN";
        }
    }

    public EDAllegiance getAllegiance() {
        switch (this) {
            case AISLING_DUVAL:
            case ARISSA_LAVIGNY_DUVAL:
            case DENTON_PATREUS:
            case ZEMINA_TORVAL: return EDAllegiance.EMPIRE;
            case ZACHARY_HUDSON:
            case FELICIA_WINTERS: return EDAllegiance.FEDERATION;
            case EDMUND_MAHON: return EDAllegiance.ALLIANCE;
            case LI_YONG_RUI:
            case ARCHON_DELAINE:
            case PRANAV_ANTAL:
            case YURI_GROM: return EDAllegiance.INDEPENDENT;
            default: return EDAllegiance.UNKNOWN;
        }
    }
}
