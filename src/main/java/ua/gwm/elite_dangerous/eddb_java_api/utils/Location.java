package ua.gwm.elite_dangerous.eddb_java_api.utils;

public class Location {

    private double x;
    private double y;
    private double z;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Location location) {
        return Math.sqrt(Math.pow(location.getX() - x, 2) + Math.pow(location.getY() - y, 2) + Math.pow(location.getZ() - z, 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
