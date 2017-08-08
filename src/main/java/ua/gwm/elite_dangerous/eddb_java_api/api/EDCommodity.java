package ua.gwm.elite_dangerous.eddb_java_api.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

public class EDCommodity {

    public static URL commodities_url;
    public static File commodities_file;
    private static boolean updated = false;

    static {
        try {
            commodities_url = new URL("https://eddb.io/archive/v5/commodities.json");
            commodities_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "commodities.json");
            if (!commodities_file.exists()) {
                commodities_file.createNewFile();
                updateOrDownloadPrices();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDCommodity static initialization...");
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
            System.out.println("Starting commodities download.");
            ReadableByteChannel rbc = Channels.newChannel(commodities_url.openStream());
            FileOutputStream fos = new FileOutputStream(commodities_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Commodities successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading commodities...");
            e.printStackTrace();
        }
    }

    public static Set<EDCommodity> loadCommodities(Predicate<EDCommodity> predicate) {
        System.out.println("Starting commodities loading!");
        try {
            Set<EDCommodity> commodities = new HashSet<EDCommodity>();
            JsonArray commodities_array = EDDBJavaAPIUtils.GSON.fromJson(
                    new BufferedReader(new FileReader(commodities_file)).readLine(), JsonArray.class);
            for (JsonElement commodity_element : commodities_array) {
                JsonObject commodity_object = commodity_element.getAsJsonObject();
                int id = commodity_object.get("id").getAsJsonPrimitive().getAsInt();
                String name = commodity_object.get("name").getAsJsonPrimitive().getAsString();
                int average_price = commodity_object.get("average_price").getAsJsonPrimitive().getAsInt();
                boolean is_rare = commodity_object.get("is_rare").getAsJsonPrimitive().getAsBoolean();
                EDCommodityCategory category = EDCommodityCategory.byName(
                        commodity_object.getAsJsonObject("category").get("name").getAsJsonPrimitive().getAsString());
                EDCommodity commodity = new EDCommodity(id, name, average_price, is_rare, category);
                if (predicate.test(commodity)) {
                    commodities.add(commodity);
                }
            }
            System.out.println("Successfully loaded commodities!");
            return commodities;
        } catch (Exception e) {
            System.out.println("Exception on loading commodities!");
            e.printStackTrace();
            return new HashSet<EDCommodity>();
        }
    }

    private final int id;
    private final String name;
    private final int average_price;
    private final boolean is_rare;
    private final EDCommodityCategory category;

    public EDCommodity(int id, String name, int average_price, boolean is_rare, EDCommodityCategory category) {
        this.id = id;
        this.name = name;
        this.average_price = average_price;
        this.is_rare = is_rare;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAveragePrice() {
        return average_price;
    }

    public boolean isRare() {
        return is_rare;
    }

    public EDCommodityCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "EDCommodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", average_price=" + average_price +
                ", is_rare=" + is_rare +
                ", category=" + category +
                '}';
    }
}
