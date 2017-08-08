package ua.gwm.elite_dangerous.eddb_java_api.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ua.gwm.elite_dangerous.eddb_java_api.EDDBJavaAPI;
import ua.gwm.elite_dangerous.eddb_java_api.utils.EDDBJavaAPIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

public class EDMinorFaction {

    public static URL minor_factions_url;
    public static File minor_factions_file;
    private static boolean updated = false;

    static {
        try {
            minor_factions_url = new URL("https://eddb.io/archive/v5/factions.json");
            minor_factions_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "factions.json");
            if (!minor_factions_file.exists()) {
                minor_factions_file.createNewFile();
                updateOrDownloadMinorFactions();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDMinorFaction static initialization...");
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
            System.out.println("Starting minor factions download.");
            ReadableByteChannel rbc = Channels.newChannel(minor_factions_url.openStream());
            FileOutputStream fos = new FileOutputStream(minor_factions_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Minor factions successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading minor factions...");
            e.printStackTrace();
        }
    }

    public static Set<EDMinorFaction> loadMinorFactions(Predicate<EDMinorFaction> predicate) {
        System.out.println("Starting minor factions loading.");
        try {
            Set<EDMinorFaction> minor_factions = new HashSet<EDMinorFaction>();
            StreamSupport.stream(EDDBJavaAPIUtils.GSON.fromJson(
                    new BufferedReader(new FileReader(minor_factions_file)).readLine(), JsonArray.class).
                    spliterator(), false).map(JsonElement::getAsJsonObject).
                    forEach(minor_faction_object -> {
                        int id = minor_faction_object.get("id").getAsJsonPrimitive().getAsInt();
                        String name = minor_faction_object.get("name").getAsJsonPrimitive().getAsString();
                        long updated_at = minor_faction_object.get("updated_at").getAsJsonPrimitive().getAsLong();
                        JsonElement government_element = minor_faction_object.get("government");
                        EDGovernment government = EDGovernment.byName(
                                government_element.isJsonNull() ? null : government_element.getAsJsonPrimitive().getAsString());
                        JsonElement allegiance_element = minor_faction_object.get("allegiance");
                        EDAllegiance allegiance = EDAllegiance.byName(
                                allegiance_element.isJsonNull() ? null : allegiance_element.getAsJsonPrimitive().getAsString());
                        JsonElement state_element = minor_faction_object.get("state");
                        EDState state = EDState.byName(
                                state_element.isJsonNull() ? null : state_element.getAsJsonPrimitive().getAsString());
                        JsonElement home_system_id_element = minor_faction_object.get("home_system_id");
                        int home_system_id = home_system_id_element.isJsonNull() ? -1 : home_system_id_element.getAsJsonPrimitive().getAsInt();
                        boolean is_player_faction = minor_faction_object.get("is_player_faction").getAsJsonPrimitive().getAsBoolean();
                        EDMinorFaction minor_faction = new EDMinorFaction(id, name, updated_at, government, allegiance, state, home_system_id, is_player_faction);
                        if (predicate.test(minor_faction)) {
                            minor_factions.add(minor_faction);
                        }
                    });
            System.out.println("Minor factions successfully loaded!");
            return minor_factions;
        } catch (Exception e) {
            throw new RuntimeException("Exception on loading minor factions...", e);
        }
    }

    private final int id;
    private final String name;
    private final long updated_at;
    private final EDGovernment government;
    private final EDAllegiance allegiance;
    private final EDState state;
    private final int home_system_id;
    private final boolean is_player_faction;

    public EDMinorFaction(int id, String name, long updated_at, EDGovernment government, EDAllegiance allegiance, EDState state,
                          int home_system_id, boolean is_player_faction) {
        this.id = id;
        this.name = name;
        this.updated_at = updated_at;
        this.government = government;
        this.allegiance = allegiance;
        this.state = state;
        this.home_system_id = home_system_id;
        this.is_player_faction = is_player_faction;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getUpdatedAt() {
        return updated_at;
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

    public int getHomeSystemId() {
        return home_system_id;
    }

    public boolean isIsPlayerFaction() {
        return is_player_faction;
    }
}
