package ua.gwm.eddb_java_api.api;

import ua.gwm.eddb_java_api.EDDBJavaAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class EDModule {

    public static URL modules_url;
    public static File modules_file;
    private static boolean updated = false;

    static {
        try {
            modules_url = new URL("https://eddb.io/archive/v5/modules.json");
            modules_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "modules.json");
            if (!modules_file.exists()) {
                modules_file.createNewFile();
                updateOrDownloadPrices();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDModules static initialization...");
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
            System.out.println("Starting modules download.");
            ReadableByteChannel rbc = Channels.newChannel(modules_url.openStream());
            FileOutputStream fos = new FileOutputStream(modules_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Modules successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading modules...");
            e.printStackTrace();
        }
    }

    public static Set<EDModule> loadModules(Predicate<EDModule> predicate) {
        return new HashSet<>();
    }
}
