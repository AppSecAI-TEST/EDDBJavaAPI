package ua.gwm.elite_dangerous.eddb_java_api.api;

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
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class EDPrice {

    public static URL prices_url;
    public static File prices_file;
    private static boolean updated = false;

    static {
        try {
            prices_url = new URL("https://eddb.io/archive/v5/listings.csv");
            prices_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "listings.csv");
            if (!prices_file.exists()) {
                prices_file.createNewFile();
                updateOrDownloadPrices();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDPrice static initialization...");
            e.printStackTrace();
        }
    }

    public static boolean isUpdated() {
        return updated;
    }

    public static void allowUpdate() {
        updated = false;
    }

    public static void updateOrDownloadPrices() {
        if (updated) {
            return;
        }
        try {
            System.out.println("Starting prices download.");
            ReadableByteChannel rbc = Channels.newChannel(prices_url.openStream());
            FileOutputStream fos = new FileOutputStream(prices_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Prices successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading prices...");
            e.printStackTrace();
        }
    }

    public static Set<EDPrice> loadPrices(Predicate<EDPrice> predicate) {
        System.out.println("Starting price loading!");
        try {
            Set<EDPrice> prices = new HashSet<EDPrice>();
            BufferedReader prices_reader = new BufferedReader(new FileReader(prices_file));
            prices_reader.readLine();
            String price_info;
            while ((price_info = prices_reader.readLine()) != null && !price_info.equals("")) {
                List<String> splited = EDDBJavaAPIUtils.splitCSV(price_info);
                int id = Integer.valueOf(splited.get(0));
                int station_id = Integer.valueOf(splited.get(1));
                int commodity_id = Integer.valueOf(splited.get(2));
                int supply = Integer.valueOf(splited.get(3));
                int buy_price = Integer.valueOf(splited.get(4));
                int sell_price = Integer.valueOf(splited.get(5));
                int demand = Integer.valueOf(splited.get(6));
                long collected_at = Long.valueOf(splited.get(7));
                EDPrice price = new EDPrice(id, station_id, commodity_id, supply, buy_price, sell_price, demand, collected_at);
                if (predicate.test(price)) {
                    prices.add(price);
                }
            }
            System.out.println("Prices successfully loaded!");
            return prices;
        } catch (Exception e) {
            System.out.println("Exception on loading prices...");
            e.printStackTrace();
            return new HashSet<EDPrice>();
        }
    }

    private final int id;
    private final int station_id;
    private final int commodity_id;
    private final int supply;
    private final int buy_price;
    private final int sell_price;
    private final int demand;
    private final long collected_at;

    public EDPrice(int id, int station_id, int commodity_id, int supply, int buy_price, int sell_price, int demand,
                   long collected_at) {
        this.id = id;
        this.station_id = station_id;
        this.commodity_id = commodity_id;
        this.supply = supply;
        this.buy_price = buy_price;
        this.sell_price = sell_price;
        this.demand = demand;
        this.collected_at = collected_at;
    }

    public int getId() {
        return id;
    }

    public int getStationId() {
        return station_id;
    }

    public int getCommodityId() {
        return commodity_id;
    }

    public int getSupply() {
        return supply;
    }

    public int getBuyPrice() {
        return buy_price;
    }

    public int getSellPrice() {
        return sell_price;
    }

    public int getDemand() {
        return demand;
    }

    public long getCollectedAt() {
        return collected_at;
    }

    @Override
    public String toString() {
        return "EDPrice{" +
                "id=" + id +
                ", station_id=" + station_id +
                ", commodity_id=" + commodity_id +
                ", supply=" + supply +
                ", buy_price=" + buy_price +
                ", sell_price=" + sell_price +
                ", demand=" + demand +
                ", collected_at=" + collected_at +
                '}';
    }
}
