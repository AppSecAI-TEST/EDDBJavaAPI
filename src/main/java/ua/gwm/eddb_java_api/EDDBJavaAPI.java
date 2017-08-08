package ua.gwm.eddb_java_api;

import ua.gwm.eddb_java_api.api.*;
import ua.gwm.eddb_java_api.utils.EDDBJavaAPIOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class EDDBJavaAPI {

    public static EDDBJavaAPIOptions OPTIONS;
    private static Set<Thread> DOWNLOAD_THREADS = new HashSet<Thread>();

    public static void main(String[] args) throws Throwable {
        OPTIONS = EDDBJavaAPIOptions.parseOptions(args);
        if (OPTIONS.isUpdateSystems()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDSystem::updateOrDownloadMinorFactions));
            } else {
                EDSystem.updateOrDownloadMinorFactions();
            }
        }
        if (OPTIONS.isUpdatePopulatedSystems()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDPopulatedSystem::updateOrDownloadSystems));
            } else {
                EDPopulatedSystem.updateOrDownloadSystems();
            }
        }
        if (OPTIONS.isUpdateBodies()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDBody::updateOrDownloadBodies));
            } else {
                EDBody.updateOrDownloadBodies();
            }
        }
        if (OPTIONS.isUpdateStations()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDStation::updateOrDownloadStations));
            } else {
                EDStation.updateOrDownloadStations();
            }
        }
        if (OPTIONS.isUpdateMinorFactions()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDMinorFaction::updateOrDownloadMinorFactions));
            } else {
                EDMinorFaction.updateOrDownloadMinorFactions();
            }
        }
        if (OPTIONS.isUpdatePrices()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDPrice::updateOrDownloadPrices));
            } else {
                EDPrice.updateOrDownloadPrices();
            }
        }
        if (OPTIONS.isUpdateCommodities()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDCommodity::updateOrDownloadPrices));
            } else {
                EDCommodity.updateOrDownloadPrices();
            }
        }
        if (OPTIONS.isUpdateModules()) {
            if (OPTIONS.isAsync()) {
                DOWNLOAD_THREADS.add(new Thread(EDModule::updateOrDownloadPrices));
            } else {
                EDModule.updateOrDownloadPrices();
            }
        }
        if (!DOWNLOAD_THREADS.isEmpty()) {
            DOWNLOAD_THREADS.forEach(Thread::start);
            while (DOWNLOAD_THREADS.size() > 0) {
                DOWNLOAD_THREADS.removeIf(next -> !next.isAlive());
                Thread.sleep(1000);
            }
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        System.out.println("Enter command.");
        while (!(command = reader.readLine()).equals("exit")) {
            onCommand(command);
            System.out.println("Enter next command or \"exit\"!");
        }
        System.out.println("Bye, have a good time! Thank you for using EDDB Java Api created by GWM.");
        Thread.sleep(1337);
    }

    private static void onCommand(String command) {
        System.out.println("Commands dont supported in this version!");
    }
}
