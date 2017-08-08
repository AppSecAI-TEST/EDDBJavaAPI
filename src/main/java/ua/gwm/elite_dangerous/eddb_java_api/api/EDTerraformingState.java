package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDTerraformingState {

    TERRAFORMING_COMPLETED,
    CANDIDATE_FOR_TERRAFORMING,
    NOT_TERRAFORMABLE,
    BEING_TERRAFORMED,
    UNKNOWN;

    public static EDTerraformingState byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Terraforming completed": return TERRAFORMING_COMPLETED;
            case "Candidate for terraforming": return CANDIDATE_FOR_TERRAFORMING;
            case "Not terraformable": return NOT_TERRAFORMABLE;
            case "Being terraformed": return BEING_TERRAFORMED;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case TERRAFORMING_COMPLETED: return "Terraforming completed";
            case CANDIDATE_FOR_TERRAFORMING: return "Candidate for terraforming";
            case NOT_TERRAFORMABLE: return "Not terraformable";
            case BEING_TERRAFORMED: return "Being terraformed";
            default: return "UNKNOWN";
        }
    }
}