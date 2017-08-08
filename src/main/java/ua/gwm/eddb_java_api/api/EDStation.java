package ua.gwm.eddb_java_api.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EDStation {

    public static URL stations_url;
    public static File stations_file;
    private static boolean updated = false;

    static {
        try {
            stations_url = new URL("https://eddb.io/archive/v5/stations.json");
            stations_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "stations.json");
            if (!stations_file.exists()) {
                stations_file.createNewFile();
                updateOrDownloadStations();
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

    public static void updateOrDownloadStations() {
        if (updated) {
            return;
        }
        try {
            System.out.println("Starting stations download.");
            ReadableByteChannel rbc = Channels.newChannel(stations_url.openStream());
            FileOutputStream fos = new FileOutputStream(stations_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Stations successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading stations...");
            e.printStackTrace();
        }
    }

    public static Set<EDStation> loadStations(Predicate<EDStation> predicate) {
        System.out.println("Starting loading stations!");
        try {
            Set<EDStation> stations = new HashSet<EDStation>();
            StreamSupport.stream(EDDBJavaAPIUtils.GSON.fromJson(
                    new BufferedReader(new FileReader(stations_file)).readLine(), JsonArray.class).
                    spliterator(), false).map(JsonElement::getAsJsonObject).
                    forEach(station_object -> {
                        int id = station_object.get("id").getAsJsonPrimitive().getAsInt();
                        String name = station_object.get("name").getAsJsonPrimitive().getAsString();
                        int system_id = station_object.get("system_id").getAsJsonPrimitive().getAsInt();
                        long updated_at = station_object.get("updated_at").getAsJsonPrimitive().getAsLong();
                        JsonElement max_landing_pad_size_element = station_object.get("max_landing_pad_size");
                        EDLandingPadSize max_landing_pad_size = max_landing_pad_size_element.isJsonNull() ?
                                EDLandingPadSize.UNKNOWN :
                                EDLandingPadSize.byName(max_landing_pad_size_element.getAsJsonPrimitive().getAsString());
                        JsonElement distance_to_star_element = station_object.get("distance_to_star");
                        int distance_to_star = distance_to_star_element.isJsonNull() ? -1 :
                                distance_to_star_element.getAsJsonPrimitive().getAsInt();
                        JsonElement government_element = station_object.get("government");
                        EDGovernment government = government_element.isJsonNull() ? EDGovernment.UNKNOWN :
                                EDGovernment.byName(government_element.getAsJsonPrimitive().getAsString());
                        JsonElement allegiance_element = station_object.get("allegiance");
                        EDAllegiance allegiance = allegiance_element.isJsonNull() ? EDAllegiance.UNKNOWN :
                                EDAllegiance.byName(allegiance_element.getAsJsonPrimitive().getAsString());
                        JsonElement state_element = station_object.get("state");
                        EDState state = state_element.isJsonNull() ? EDState.UNKNOWN :
                                EDState.byName(state_element.getAsJsonPrimitive().getAsString());
                        JsonElement type_element = station_object.get("type");
                        EDStationType type = type_element.isJsonNull() ? EDStationType.UNKNOWN :
                                EDStationType.byName(type_element.getAsJsonPrimitive().getAsString());
                        boolean has_blackmarket = station_object.get("has_blackmarket").getAsJsonPrimitive().getAsBoolean();
                        boolean has_market = station_object.get("has_market").getAsJsonPrimitive().getAsBoolean();
                        boolean has_refuel = station_object.get("has_refuel").getAsJsonPrimitive().getAsBoolean();
                        boolean has_repair = station_object.get("has_repair").getAsJsonPrimitive().getAsBoolean();
                        boolean has_rearm = station_object.get("has_rearm").getAsJsonPrimitive().getAsBoolean();
                        boolean has_outfitting = station_object.get("has_outfitting").getAsJsonPrimitive().getAsBoolean();
                        boolean has_shipyard = station_object.get("has_shipyard").getAsJsonPrimitive().getAsBoolean();
                        boolean has_docking = station_object.get("has_docking").getAsJsonPrimitive().getAsBoolean();
                        boolean has_commodities = station_object.get("has_commodities").getAsJsonPrimitive().getAsBoolean();
                        List<String> import_commodities = StreamSupport.stream(
                                station_object.get("import_commodities").getAsJsonArray().spliterator(), false).
                                map(commodity_element -> commodity_element.getAsJsonPrimitive().getAsString()).
                                collect(Collectors.toList());
                        List<String> export_commodities = StreamSupport.stream(
                                station_object.get("export_commodities").getAsJsonArray().spliterator(), false).
                                map(commodity_element -> commodity_element.getAsJsonPrimitive().getAsString()).
                                collect(Collectors.toList());
                        List<String> prohibited_commodities = StreamSupport.stream(
                                station_object.get("prohibited_commodities").getAsJsonArray().spliterator(), false).
                                map(commodity_element -> commodity_element.getAsJsonPrimitive().getAsString()).
                                collect(Collectors.toList());
                        List<EDEconomy> economies = StreamSupport.stream(
                                station_object.get("economies").getAsJsonArray().spliterator(), false)
                                .map(economy_element -> EDEconomy.byName(economy_element.getAsJsonPrimitive().getAsString())).
                                        collect(Collectors.toList());
                        JsonElement shipyard_updated_at_element = station_object.get("shipyard_updated_at");
                        long shipyard_updated_at = shipyard_updated_at_element.isJsonNull() ? -1 :
                                shipyard_updated_at_element.getAsJsonPrimitive().getAsLong();
                        JsonElement outfitting_updated_at_element = station_object.get("outfitting_updated_at");
                        long outfitting_updated_at = outfitting_updated_at_element.isJsonNull() ? -1 :
                                outfitting_updated_at_element.getAsJsonPrimitive().getAsLong();
                        JsonElement market_updated_at_element = station_object.get("market_updated_at");
                        long market_updated_at = market_updated_at_element.isJsonNull() ? -1 :
                                market_updated_at_element.getAsJsonPrimitive().getAsLong();
                        boolean is_planetary = station_object.get("is_planetary").getAsJsonPrimitive().getAsBoolean();
                        List<EDShip> selling_ships = StreamSupport.stream(
                                station_object.get("selling_ships").getAsJsonArray().spliterator(), false).
                                map(selling_ship_element -> EDShip.byName(selling_ship_element.getAsJsonPrimitive().getAsString())).
                                collect(Collectors.toList());
                        List<Integer> selling_modules = StreamSupport.stream(
                                station_object.get("selling_modules").getAsJsonArray().spliterator(), false).
                                map(module_element -> module_element.getAsJsonPrimitive().getAsInt()).
                                collect(Collectors.toList());
                        JsonElement settlement_size_element = station_object.get("settlement_size");
                        EDSettlementSize settlement_size = settlement_size_element.isJsonNull() ?
                                EDSettlementSize.UNKNOWN :
                                EDSettlementSize.byName(settlement_size_element.getAsJsonPrimitive().getAsString());
                        JsonElement settlement_security_element = station_object.get("settlement_security");
                        EDSettlementSecurity settlement_security = settlement_security_element.isJsonNull() ?
                                EDSettlementSecurity.UNKNOWN :
                                EDSettlementSecurity.byName(settlement_security_element.getAsJsonPrimitive().getAsString());
                        JsonElement body_id_element = station_object.get("body_id");
                        int body_id = body_id_element.isJsonNull() ? -1 : body_id_element.getAsJsonPrimitive().getAsInt();
                        JsonElement controlling_minor_faction_id_element = station_object.get("controlling_minor_faction_id");
                        int controlling_minor_faction_id = controlling_minor_faction_id_element.isJsonNull() ? -1 : controlling_minor_faction_id_element.getAsJsonPrimitive().getAsInt();
                        EDStation station = new EDStation(id, name, system_id, updated_at, max_landing_pad_size, distance_to_star,
                                government, allegiance, state, type, has_blackmarket, has_market, has_refuel, has_repair,
                                has_rearm, has_outfitting, has_shipyard, has_docking, has_commodities, import_commodities,
                                export_commodities, prohibited_commodities, economies, shipyard_updated_at, outfitting_updated_at,
                                market_updated_at, is_planetary, selling_ships, selling_modules, settlement_size,
                                settlement_security, body_id, controlling_minor_faction_id);
                        if (predicate.test(station)) {
                            stations.add(station);
                        }
                    });
            System.out.println("Stations successfully loaded!");
            return stations;
        } catch (Exception e) {
            System.out.println("Exception on loading stations...");
            e.printStackTrace();
            return new HashSet<EDStation>();
        }
    }

    private final int id;
    private final String name;
    private final int system_id;
    private final long updated_at;
    private final EDLandingPadSize max_landing_pad_size;
    private final int distance_to_star;
    private final EDGovernment government;
    private final EDAllegiance allegiance;
    private final EDState state;
    private final EDStationType type;
    private final boolean has_blackmarket;
    private final boolean has_market;
    private final boolean has_refuel;
    private final boolean has_repair;
    private final boolean has_rearm;
    private final boolean has_outfitting;
    private final boolean has_shipyard;
    private final boolean has_docking;
    private final boolean has_commodities;
    private final List<String> import_commodities;
    private final List<String> export_commodities;
    private final List<String> prohibited_commodities;
    private final List<EDEconomy> economies;
    private final long shipyard_updated_at;
    private final long outfitting_updated_at;
    private final long market_updated_at;
    private final boolean is_planetary;
    private final List<EDShip> selling_ships;
    private final List<Integer> selling_modules;
    private final EDSettlementSize settlement_size;
    private final EDSettlementSecurity settlement_security;
    private final int body_id;
    private final int controlling_minor_faction_id;

    public EDStation(int id, String name, int system_id, long updated_at, EDLandingPadSize max_landing_pad_size,
                     int distance_to_star, EDGovernment government, EDAllegiance allegiance, EDState state,
                     EDStationType type, boolean has_blackmarket, boolean has_market, boolean has_refuel, boolean has_repair,
                     boolean has_rearm, boolean has_outfitting, boolean has_shipyard, boolean has_docking,
                     boolean has_commodities, List<String> import_commodities, List<String> export_commodities,
                     List<String> prohibited_commodities, List<EDEconomy> economies, long shipyard_updated_at,
                     long outfitting_updated_at, long market_updated_at, boolean is_planetary, List<EDShip> selling_ships,
                     List<Integer> selling_modules, EDSettlementSize settlement_size,
                     EDSettlementSecurity settlement_security, int body_id, int controlling_minor_faction_id) {
        this.id = id;
        this.name = name;
        this.system_id = system_id;
        this.updated_at = updated_at;
        this.max_landing_pad_size = max_landing_pad_size;
        this.distance_to_star = distance_to_star;
        this.government = government;
        this.allegiance = allegiance;
        this.state = state;
        this.type = type;
        this.has_blackmarket = has_blackmarket;
        this.has_market = has_market;
        this.has_refuel = has_refuel;
        this.has_repair = has_repair;
        this.has_rearm = has_rearm;
        this.has_outfitting = has_outfitting;
        this.has_shipyard = has_shipyard;
        this.has_docking = has_docking;
        this.has_commodities = has_commodities;
        this.import_commodities = import_commodities;
        this.export_commodities = export_commodities;
        this.prohibited_commodities = prohibited_commodities;
        this.economies = economies;
        this.shipyard_updated_at = shipyard_updated_at;
        this.outfitting_updated_at = outfitting_updated_at;
        this.market_updated_at = market_updated_at;
        this.is_planetary = is_planetary;
        this.selling_ships = selling_ships;
        this.selling_modules = selling_modules;
        this.settlement_size = settlement_size;
        this.settlement_security = settlement_security;
        this.body_id = body_id;
        this.controlling_minor_faction_id = controlling_minor_faction_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSystemId() {
        return system_id;
    }

    public long getUpdatedAt() {
        return updated_at;
    }

    public EDLandingPadSize getMaxLandingPadSize() {
        return max_landing_pad_size;
    }

    public int getDistanceToStar() {
        return distance_to_star;
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

    public EDStationType getType() {
        return type;
    }

    public boolean isHasBlackmarket() {
        return has_blackmarket;
    }

    public boolean isHasMarket() {
        return has_market;
    }

    public boolean isHasRefuel() {
        return has_refuel;
    }

    public boolean isHasRepair() {
        return has_repair;
    }

    public boolean isHasRearm() {
        return has_rearm;
    }

    public boolean isHasOutfitting() {
        return has_outfitting;
    }

    public boolean isHasShipyard() {
        return has_shipyard;
    }

    public boolean isHasDocking() {
        return has_docking;
    }

    public boolean isHasCommodities() {
        return has_commodities;
    }

    public List<String> getImportCommodities() {
        return import_commodities;
    }

    public List<String> getExportCommodities() {
        return export_commodities;
    }

    public List<String> getProhibitedCommodities() {
        return prohibited_commodities;
    }

    public List<EDEconomy> getEconomies() {
        return economies;
    }

    public long getShipyardUpdatedAt() {
        return shipyard_updated_at;
    }

    public long getOutfittingUpdatedAt() {
        return outfitting_updated_at;
    }

    public long getMarketUpdatedAt() {
        return market_updated_at;
    }

    public boolean isIsPlanetary() {
        return is_planetary;
    }

    public List<EDShip> getSellingShips() {
        return selling_ships;
    }

    public List<Integer> getSellingModules() {
        return selling_modules;
    }

    public EDSettlementSize getSettlementSize() {
        return settlement_size;
    }

    public EDSettlementSecurity getSettlementSecurity() {
        return settlement_security;
    }

    public int getBodyId() {
        return body_id;
    }

    public int getControllingMinorFactionId() {
        return controlling_minor_faction_id;
    }

    @Override
    public String toString() {
        return "EDStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", system_id=" + system_id +
                ", updated_at=" + updated_at +
                ", max_landing_pad_size=" + max_landing_pad_size +
                ", distance_to_star=" + distance_to_star +
                ", government=" + government +
                ", allegiance=" + allegiance +
                ", state=" + state +
                ", type=" + type +
                ", has_blackmarket=" + has_blackmarket +
                ", has_market=" + has_market +
                ", has_refuel=" + has_refuel +
                ", has_repair=" + has_repair +
                ", has_rearm=" + has_rearm +
                ", has_outfitting=" + has_outfitting +
                ", has_shipyard=" + has_shipyard +
                ", has_docking=" + has_docking +
                ", has_commodities=" + has_commodities +
                ", import_commodities=" + import_commodities +
                ", export_commodities=" + export_commodities +
                ", prohibited_commodities=" + prohibited_commodities +
                ", economies=" + economies +
                ", shipyard_updated_at=" + shipyard_updated_at +
                ", outfitting_updated_at=" + outfitting_updated_at +
                ", market_updated_at=" + market_updated_at +
                ", is_planetary=" + is_planetary +
                ", selling_ships=" + selling_ships +
                ", selling_modules=" + selling_modules +
                ", settlement_size=" + settlement_size +
                ", settlement_security=" + settlement_security +
                ", body_id=" + body_id +
                ", controlling_minor_faction_id=" + controlling_minor_faction_id +
                '}';
    }
}
