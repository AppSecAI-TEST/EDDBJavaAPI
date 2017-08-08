package ua.gwm.eddb_java_api.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ua.gwm.eddb_java_api.EDDBJavaAPI;
import ua.gwm.eddb_java_api.utils.Location;
import ua.gwm.eddb_java_api.utils.EDDBJavaAPIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

public class EDPopulatedSystem {

    public static URL populated_systems_url;
    public static File populated_systems_file;
    private static boolean updated = false;

    static {
        try {
            populated_systems_url = new URL("https://eddb.io/archive/v5/systems_populated.json");
            populated_systems_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "systems_populated.json");
            if (!populated_systems_file.exists()) {
                populated_systems_file.createNewFile();
                updateOrDownloadSystems();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDPopulatedSystem static initialization...");
            e.printStackTrace();
        }
    }

    public static boolean isUpdated() {
        return updated;
    }

    public static void allowUpdate() {
        updated = false;
    }

    public static void updateOrDownloadSystems() {
        if (updated) {
            return;
        }
        try {
            System.out.println("Starting populated systems download.");
            ReadableByteChannel rbc = Channels.newChannel(populated_systems_url.openStream());
            FileOutputStream fos = new FileOutputStream(populated_systems_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Populated systems successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading populated systems...");
            e.printStackTrace();
        }
    }

    public static Set<EDPopulatedSystem> loadSystems(Predicate<EDPopulatedSystem> predicate) {
        System.out.println("Starting populated systems loading.");
        try {
            Set<EDPopulatedSystem> populated_systems = new HashSet<EDPopulatedSystem>();
            StreamSupport.stream(EDDBJavaAPIUtils.GSON.fromJson(
                    new BufferedReader(new FileReader(populated_systems_file)).readLine(), JsonArray.class).
                    spliterator(), false).map(JsonElement::getAsJsonObject).
                    forEach(populated_system_object -> {
                        int id = populated_system_object.get("id").getAsJsonPrimitive().getAsInt();
                        JsonElement edsm_id_element = populated_system_object.get("edsm_id");
                        int edsm_id = edsm_id_element.isJsonNull() ? -1 : edsm_id_element.getAsJsonPrimitive().getAsInt();
                        String name = populated_system_object.get("name").getAsJsonPrimitive().getAsString();
                        Location location = new Location(
                                populated_system_object.get("x").getAsJsonPrimitive().getAsDouble(),
                                populated_system_object.get("y").getAsJsonPrimitive().getAsDouble(),
                                populated_system_object.get("z").getAsJsonPrimitive().getAsDouble());
                        JsonElement population_element = populated_system_object.get("population");
                        long population = population_element.isJsonNull() ? 0 : population_element.getAsJsonPrimitive().getAsLong();
                        boolean is_populated = populated_system_object.get("is_populated").getAsJsonPrimitive().getAsBoolean();
                        JsonElement government_element = populated_system_object.get("government");
                        EDGovernment government = EDGovernment.byName(
                                government_element.isJsonNull() ? null : government_element.getAsJsonPrimitive().getAsString());
                        JsonElement allegiance_element = populated_system_object.get("allegiance");
                        EDAllegiance allegiance = EDAllegiance.byName(
                                allegiance_element.isJsonNull() ? null : allegiance_element.getAsJsonPrimitive().getAsString());
                        JsonElement state_element = populated_system_object.get("state");
                        EDState state = EDState.byName(
                                state_element.isJsonNull() ? null : state_element.getAsJsonPrimitive().getAsString());
                        JsonElement security_element = populated_system_object.get("security");
                        EDSecurity security = EDSecurity.byName
                                (security_element.isJsonNull() ? null : security_element.getAsString());
                        JsonElement primary_economy_element = populated_system_object.get("primary_economy");
                        EDEconomy primary_economy = EDEconomy.byName(
                                primary_economy_element.isJsonNull() ? null : primary_economy_element.getAsJsonPrimitive().getAsString());
                        JsonElement power_json_element = populated_system_object.get("power");
                        EDPower power = EDPower.byName(
                                power_json_element.isJsonNull() ? null : power_json_element.getAsJsonPrimitive().getAsString());
                        JsonElement power_state_json_element = populated_system_object.get("power_state");
                        EDPowerState power_state = EDPowerState.byName(
                                power_json_element.isJsonNull() ? null : power_state_json_element.getAsJsonPrimitive().getAsString());
                        boolean needs_permit = populated_system_object.get("needs_permit").getAsJsonPrimitive().getAsBoolean();
                        long updated_at = populated_system_object.get("updated_at").getAsJsonPrimitive().getAsLong();
                        JsonElement simbad_ref_element = populated_system_object.get("simbad_ref");
                        String simbad_ref = simbad_ref_element.isJsonNull() ? null : simbad_ref_element.getAsJsonPrimitive().getAsString();
                        JsonElement reserve_type_element = populated_system_object.get("reserve_type");
                        EDReserveType reserve_type = EDReserveType.byName(
                                reserve_type_element.isJsonNull() ? null : reserve_type_element.getAsJsonPrimitive().getAsString());
                        JsonArray minor_faction_presences_array = populated_system_object.get("minor_faction_presences").getAsJsonArray();
                        List<EDSystemFaction> minor_faction_presences = new ArrayList<EDSystemFaction>();
                        for (JsonElement minor_faction_element : minor_faction_presences_array) {
                            JsonElement minor_faction_id_element = minor_faction_element.getAsJsonObject().get("minor_faction_id");
                            int minor_faction_id = minor_faction_id_element.isJsonNull() ? -1 : minor_faction_id_element.getAsJsonPrimitive().getAsInt();
                            JsonElement influence_element = minor_faction_element.getAsJsonObject().get("influence");
                            double influence = influence_element.isJsonNull() ? Double.NaN : influence_element.getAsJsonPrimitive().getAsDouble();
                            JsonElement minor_faction_station_element = minor_faction_element.getAsJsonObject().get("state");
                            EDState minor_faction_state = EDState.byName(
                                    minor_faction_station_element.isJsonNull() ? null : minor_faction_station_element.getAsJsonPrimitive().getAsString());
                            minor_faction_presences.add(new EDSystemFaction(minor_faction_id, influence, minor_faction_state));
                        }
                        JsonElement controlling_minor_faction_id_element = populated_system_object.get("controlling_minor_faction_id");
                        int controlling_minor_faction_id = controlling_minor_faction_id_element.isJsonNull() ? -1 : controlling_minor_faction_id_element.getAsJsonPrimitive().getAsInt();
                        EDSystemFaction controlling_minor_faction = null;
                        for (EDSystemFaction system_faction : minor_faction_presences) {
                            if (controlling_minor_faction_id == system_faction.getMinorFactionId()) {
                                controlling_minor_faction = system_faction;
                                break;
                            }
                        }
                        EDPopulatedSystem populated_system = new EDPopulatedSystem(id, edsm_id, name, location, population, is_populated, government, allegiance, state, security,
                                primary_economy, power, power_state, needs_permit, updated_at, simbad_ref,
                                controlling_minor_faction, reserve_type, minor_faction_presences);
                        if (predicate.test(populated_system)) {
                            populated_systems.add(populated_system);
                        }

                    });
            System.out.println("Populated systems successfully loaded!");
            return populated_systems;
        } catch (Exception e) {
            System.out.println("Exception on loading populated systems...");
            e.printStackTrace();
            return new HashSet<EDPopulatedSystem>();
        }
    }

    private final int id;
    private final int edsm_id;
    private final String name;
    private final Location location;
    private final long population;
    private final boolean is_populated;
    private final EDGovernment government;
    private final EDAllegiance allegiance;
    private final EDState state;
    private final EDSecurity security;
    private final EDEconomy primary_economy;
    private final EDPower power;
    private final EDPowerState power_state;
    private final boolean needs_permit;
    private final long updated_at;
    private final String simbad_ref;
    private final EDSystemFaction controlling_minor_faction;
    private final EDReserveType reserve_type;
    private final List<EDSystemFaction> minor_faction_presences;

    public EDPopulatedSystem(int id, int edsm_id, String name, Location location, long population, boolean is_populated,
                             EDGovernment government, EDAllegiance allegiance, EDState state, EDSecurity security,
                             EDEconomy primary_economy, EDPower power, EDPowerState power_state, boolean needs_permit,
                             long updated_at, String simbad_ref, EDSystemFaction controlling_minor_faction,
                             EDReserveType reserve_type, List<EDSystemFaction> minor_faction_presences) {
        this.id = id;
        this.edsm_id = edsm_id;
        this.name = name;
        this.location = location;
        this.population = population;
        this.is_populated = is_populated;
        this.government = government;
        this.allegiance = allegiance;
        this.state = state;
        this.security = security;
        this.primary_economy = primary_economy;
        this.power = power;
        this.power_state = power_state;
        this.needs_permit = needs_permit;
        this.updated_at = updated_at;
        this.simbad_ref = simbad_ref;
        this.controlling_minor_faction = controlling_minor_faction;
        this.reserve_type = reserve_type;
        this.minor_faction_presences = minor_faction_presences;
    }

    public int getId() {
        return id;
    }

    public int getEdsmId() {
        return edsm_id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public long getPopulation() {
        return population;
    }

    public boolean isPopulated() {
        return is_populated;
    }

    public EDGovernment getGovernment() {
        return government;
    }

    public EDAllegiance getAllegiance() {
        return allegiance;
    }

    public EDState getState() {
        return state;
    }

    public EDSecurity getSecurity() {
        return security;
    }

    public EDEconomy getPrimaryEconomy() {
        return primary_economy;
    }

    public EDPower getPower() {
        return power;
    }

    public EDPowerState getPowerState() {
        return power_state;
    }

    public boolean isNeedsPermit() {
        return needs_permit;
    }

    public long getUpdatedAt() {
        return updated_at;
    }

    public String getSimbadRef() {
        return simbad_ref;
    }

    public EDSystemFaction getControllingMinorFaction() {
        return controlling_minor_faction;
    }

    public EDReserveType getReserveType() {
        return reserve_type;
    }

    public List<EDSystemFaction> getMinorFactionPresences() {
        return minor_faction_presences;
    }

    @Override
    public String toString() {
        return "EDPopulatedSystem{" +
                "id=" + id +
                ", edsm_id=" + edsm_id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", population=" + population +
                ", is_populated=" + is_populated +
                ", government='" + government + '\'' +
                ", allegiance='" + allegiance + '\'' +
                ", state='" + state + '\'' +
                ", security='" + security + '\'' +
                ", primary_economy='" + primary_economy + '\'' +
                ", power='" + power + '\'' +
                ", power_state='" + power_state + '\'' +
                ", needs_permit=" + needs_permit +
                ", updated_at=" + updated_at +
                ", simbad_ref='" + simbad_ref + '\'' +
                ", controlling_minor_faction='" + controlling_minor_faction + '\'' +
                ", reserve_type='" + reserve_type + '\'' +
                ", minor_faction_presences=" + Arrays.toString(minor_faction_presences.toArray()) +
                '}';
    }
}
