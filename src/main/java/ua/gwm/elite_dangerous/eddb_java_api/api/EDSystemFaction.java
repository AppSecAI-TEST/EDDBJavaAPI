package ua.gwm.elite_dangerous.eddb_java_api.api;

public class EDSystemFaction {

    private final int minor_faction_id;
    private final double influence;
    private final EDState state;

    public EDSystemFaction(int minor_faction_id, double influence, EDState state) {
        this.minor_faction_id = minor_faction_id;
        this.influence = influence;
        this.state = state;
    }

    public int getMinorFactionId() {
        return minor_faction_id;
    }

    public double getInfluence() {
        return influence;
    }

    public EDState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "EDSystemFaction{" +
                "minor_faction_id=" + minor_faction_id +
                ", influence=" + influence +
                ", state='" + state + '\'' +
                '}';
    }
}
