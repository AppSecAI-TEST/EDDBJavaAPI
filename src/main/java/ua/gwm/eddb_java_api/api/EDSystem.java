package ua.gwm.eddb_java_api.api;

import ua.gwm.eddb_java_api.utils.Location;
import ua.gwm.eddb_java_api.EDDBJavaAPI;
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

public class EDSystem {

    public static URL systems_url;
    public static File systems_file;
    private static boolean updated = false;

    static {
        try {
            systems_url = new URL("https://eddb.io/archive/v5/systems.csv");
            systems_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "systems.csv");
            if (!systems_file.exists()) {
                systems_file.createNewFile();
                updateOrDownloadMinorFactions();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDSystem static initialization...");
            e.printStackTrace();
        }
    }

    public static boolean isUpdated() {
        return updated;
    }

    public static void allowUpdate() {
        updated = false;
    }

    public static void updateOrDownloadMinorFactions() {
        if (updated) {
            return;
        }
        try {
            System.out.println("Starting systems download.");
            ReadableByteChannel rbc = Channels.newChannel(systems_url.openStream());
            FileOutputStream fos = new FileOutputStream(systems_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Systems successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading systems...");
            e.printStackTrace();
        }
    }

    public static Set<EDSystem> loadSystems(Predicate<EDSystem> predicate) {
        System.out.println("Starting systems loading.");
        try {
            Set<EDSystem> systems = new HashSet<EDSystem>();
            BufferedReader systems_reader = new BufferedReader(new FileReader(systems_file));
            systems_reader.readLine();
            String system_info;
            while ((system_info = systems_reader.readLine()) != null && !system_info.equals("")) {
                List<String> splited = EDDBJavaAPIUtils.splitCSV(system_info);
                int id = Integer.valueOf(splited.get(0));
                int edsm_id = splited.get(1).equals("") ? -1 : Integer.valueOf(splited.get(1));
                String name = splited.get(2).replace('\"', ' ');
                Location location = new Location(
                        Double.valueOf(splited.get(3)),
                        Double.valueOf(splited.get(4)),
                        Double.valueOf(splited.get(5)));
                long population = splited.get(6).equals("") ? 0 : Long.valueOf(splited.get(6));
                boolean is_populated = splited.get(7).equals("1");
                EDGovernment government = EDGovernment.byName(splited.get(9));
                EDAllegiance allegiance = EDAllegiance.byName(splited.get(11));
                EDState state = EDState.byName(splited.get(13));
                EDSecurity security = EDSecurity.byName(splited.get(15));
                EDEconomy economy = EDEconomy.byName(splited.get(17));
                EDPower power = EDPower.byName(splited.get(18));
                EDPowerState power_state = EDPowerState.byName(splited.get(19));
                boolean needs_permit = splited.get(21).equals("1");
                long updated_at = Long.valueOf(splited.get(22));
                String simbad_ref = splited.get(23);
                int controlling_minor_faction_id = splited.get(24).equals("") ? -1 : Integer.valueOf(splited.get(24));
                String controlling_minor_faction = splited.get(25).equals("") ? null : splited.get(25);
                EDReserveType reserve_type = EDReserveType.byName(splited.get(27));
                EDSystem system = new EDSystem(id, edsm_id, name, location, population, is_populated, government, allegiance, state,
                        security, economy, power, power_state, needs_permit, updated_at, simbad_ref, controlling_minor_faction_id,
                        controlling_minor_faction, reserve_type);
                if (predicate.test(system)) {
                    systems.add(system);
                }
            }
            System.out.println("Systems successfully loaded!");
            return systems;
        } catch (Exception e) {
            System.out.println("Exception on loading systems...");
            e.printStackTrace();
            return new HashSet<EDSystem>();
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
    private final EDEconomy economy;
    private final EDPower power;
    private final EDPowerState power_state;
    private final boolean needs_permit;
    private final long updated_at;
    private final String simbad_ref;
    private final int controlling_minor_faction_id;
    private final String controlling_minor_faction;
    private final EDReserveType reserve_type;

    public EDSystem(int id, int edsm_id, String name, Location location, long population, boolean is_populated, EDGovernment government,
                    EDAllegiance allegiance, EDState state, EDSecurity security, EDEconomy economy, EDPower power,
                    EDPowerState power_state, boolean needs_permit, long updated_at, String simbad_ref,
                    int controlling_minor_faction_id, String controlling_minor_faction, EDReserveType reserve_type) {
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
        this.economy = economy;
        this.power = power;
        this.power_state = power_state;
        this.needs_permit = needs_permit;
        this.updated_at = updated_at;
        this.simbad_ref = simbad_ref;
        this.controlling_minor_faction_id = controlling_minor_faction_id;
        this.controlling_minor_faction = controlling_minor_faction;
        this.reserve_type = reserve_type;
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

    public EDEconomy getEconomy() {
        return economy;
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

    public int getControllingMinorFactionId() {
        return controlling_minor_faction_id;
    }

    public String getControllingMinorFaction() {
        return controlling_minor_faction;
    }

    public EDReserveType getReserveType() {
        return reserve_type;
    }

    @Override
    public String toString() {
        return "EDSystem{" +
                "id=" + id +
                ", edsm_id=" + edsm_id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", population=" + population +
                ", is_populated=" + is_populated +
                ", government=" + government +
                ", allegiance=" + allegiance +
                ", state=" + state +
                ", security=" + security +
                ", economy=" + economy +
                ", power=" + power +
                ", power_state=" + power_state +
                ", needs_permit=" + needs_permit +
                ", updated_at=" + updated_at +
                ", simbad_ref='" + simbad_ref + '\'' +
                ", controlling_minor_faction_id=" + controlling_minor_faction_id +
                ", controlling_minor_faction='" + controlling_minor_faction + '\'' +
                ", reserve_type=" + reserve_type +
                '}';
    }
}
