package ua.gwm.elite_dangerous.eddb_java_api.api;

import java.math.BigDecimal;

public class EDRing {

    private final int id;
    private final long created_at;
    private final long updated_at;
    private final String name;
    private final BigDecimal semi_major_axis;
    private final long ring_mass;
    private final int ring_inner_radius;
    private final int ring_outer_radius;
    private final EDRingType type;

    public EDRing(int id, long created_at, long updated_at, String name, BigDecimal semi_major_axis,
                  long ring_mass, int ring_inner_radius, int ring_outer_radius, EDRingType type) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.semi_major_axis = semi_major_axis;
        this.ring_mass = ring_mass;
        this.ring_inner_radius = ring_inner_radius;
        this.ring_outer_radius = ring_outer_radius;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public long getCreatedAt() {
        return created_at;
    }

    public long getUpdatedAt() {
        return updated_at;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSemiMajorAxis() {
        return semi_major_axis;
    }

    public long getRingMass() {
        return ring_mass;
    }

    public int getRingInnerRadius() {
        return ring_inner_radius;
    }

    public int getRingOuterRadius() {
        return ring_outer_radius;
    }

    public EDRingType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "EDRing{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", name='" + name + '\'' +
                ", semi_major_axis=" + semi_major_axis +
                ", ring_mass=" + ring_mass +
                ", ring_inner_radius=" + ring_inner_radius +
                ", ring_outer_radius=" + ring_outer_radius +
                ", type=" + type +
                '}';
    }
}
