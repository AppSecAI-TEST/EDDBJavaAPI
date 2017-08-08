package ua.gwm.eddb_java_api.utils;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;

public class EDDBJavaAPIOptions {

    private boolean update_systems = false;
    private boolean update_populated_systems = false;
    private boolean update_bodies = false;
    private boolean update_stations = false;
    private boolean update_minor_factions = false;
    private boolean update_prices = false;
    private boolean update_commodities = false;
    private boolean update_modules = false;
    private boolean async = false;
    private Optional<MySQL> mySQL = Optional.empty();
    private File directory;
    private String reference_system_name = "Sol";

    public static EDDBJavaAPIOptions parseOptions(String[] args) {
        EDDBJavaAPIOptions options = new EDDBJavaAPIOptions();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "--update": {
                    options.setUpdateSystems(true);
                    options.setUpdatePopulatedSystems(true);
                    options.setUpdateBodies(true);
                    options.setUpdateStations(true);
                    options.setUpdateMinorFactions(true);
                    options.setUpdatePrices(true);
                    options.setUpdateCommodities(true);
                    options.setUpdateModules(true);
                    break;
                }
                case "--update-systems": {
                    options.setUpdateSystems(true);
                    break;
                }
                case "--update-populated_systems": {
                    options.setUpdatePopulatedSystems(true);
                    break;
                }
                case "--update-bodies": {
                    options.setUpdateBodies(true);
                    break;
                }
                case "--update-stations": {
                    options.setUpdateStations(true);
                    break;
                }
                case "--update-minor_factions": {
                    options.setUpdateMinorFactions(true);
                    break;
                }
                case "--update-prices": {
                    options.setUpdatePrices(true);
                    break;
                }
                case "--update-commodities": {
                    options.setUpdateCommodities(true);
                    break;
                }
                case "--update-modules": {
                    options.setUpdateModules(true);
                    break;
                }
                case "--async_download": {
                    options.setAsync(true);
                    break;
                }
                case "--mysql": {
                    if (i == arg.length() - 1) {
                        throw new ParseException("--mysql values not found!");
                    }
                    try {
                        options.setMySQL(Optional.of(parseMySQLArgs(args, i + 1)));
                    } catch (ParseException e){
                        System.out.println("Exception parsing --mysql parameter!");
                        e.printStackTrace();
                    }
                    break;
                }
                case "--directory": {
                    if (i == arg.length() - 1) {
                        throw new ParseException("--directory value not found!");
                    }
                    Pair<String, Integer> param_and_to_skip = EDDBJavaAPIUtils.getMultiWordParam(args, i + 1);
                    String path = param_and_to_skip.getKey();
                    File directory = new File(path);
                    if (directory.exists()) {
                        if (!directory.isDirectory()) {
                            throw new ParseException("Path \"" + path + "\" is not directory!");
                        }
                    } else {
                        try {
                            directory.mkdirs();
                        } catch (Exception e) {
                            throw new ParseException("Unable to create directory on path \"" + path + "\"!", e);
                        }
                    }
                    options.setDirectory(directory);
                    i += param_and_to_skip.getValue();
                    break;
                }
                case "--reference_system": {
                    if (i == arg.length() - 1) {
                        throw new ParseException("--reference_system value not found!");
                    }
                    Pair<String, Integer> param_and_to_skip = EDDBJavaAPIUtils.getMultiWordParam(args, i + 1);
                    options.setReferenceSystemName(param_and_to_skip.getKey());
                    i += param_and_to_skip.getValue();
                    break;
                }
            }
        }
        return options;
    }

    private static MySQL parseMySQLArgs(String[] args, int start_position) {
        String ip = null;
        int port = -1;
        String db = null;
        String user = null;
        String password = null;
        for (int i = start_position; i < args.length && i < start_position + 10; i += 2) {
            String arg = args[i];
            switch (arg) {
                case "-ip": {
                    ip = args[i + 1];
                    break;
                }
                case "-port": {
                    port = Integer.parseInt(args[i + 1]);
                    break;
                }
                case "-db": {
                    db = args[i + 1];
                    break;
                }
                case "-user": {
                    user = args[i + 1];
                    break;
                }
                case "-password": {
                    password = args[i + 1];
                    break;
                }
                default: {
                    throw new ParseException("Unknown argument \"" + arg + "\" in MySQL values! Allowed arguments: -ip, -port, -db, -user, -password");
                }
            }
        }
        if (ip == null) {
            throw new ParseException("Missing argument \"-port\" for MySQL!");
        }
        if (port < 0) {
            throw new ParseException("Missing argument \"-port\" (or port is lower than 0) for MySQL!");
        }
        if (db == null) {
            throw new ParseException("Missing argument \"-db\" for MySQL!");
        }
        if (user == null) {
            throw new ParseException("Missing argument \"-user\" for MySQL!");
        }
        if (password == null) {
            throw new ParseException("Missing argument \"-password\" for MySQL!");
        }
        try {
            return new MySQL(ip, port, db, user, password);
        } catch (SQLException e) {
            throw new ParseException("SQLException on creating MySQL connection!", e);
        }
    }

    public EDDBJavaAPIOptions() {
        String os = System.getProperty("os.name").toLowerCase();
        String user_name = System.getProperty("user.name");
        if (os.contains("windows")) {
            directory = new File("C:\\Users\\" + user_name + "\\EDDBJavaAPI");
            System.out.println("OS is Windows! Default directory path set to \"" + directory.getAbsolutePath() + "\"!");
        } else if (os.contains("linux")) {
            directory = new File("/home/" + user_name + "/EDDBJavaAPI");
            System.out.println("OS is Linux! Default directory path set to \"" + directory.getAbsolutePath() + "\"!");
        } else {
            System.out.println("Unknown/Unsupported OS. Default directory path set to null!");
        }
    }

    public EDDBJavaAPIOptions(boolean update_systems, boolean update_populated_systems,
                              boolean update_bodies, boolean update_stations,
                              boolean update_minor_factions, boolean update_prices,
                              boolean update_commodities, boolean update_modules, boolean async,
                              Optional<MySQL> mySQL, File directory, String reference_system_name) {
        this.update_systems = update_systems;
        this.update_populated_systems = update_populated_systems;
        this.update_bodies = update_bodies;
        this.update_stations = update_stations;
        this.update_minor_factions = update_minor_factions;
        this.update_prices = update_prices;
        this.update_commodities = update_commodities;
        this.update_modules = update_modules;
        this.async = async;
        this.mySQL = mySQL;
        this.directory = directory;
        this.reference_system_name = reference_system_name;
    }

    public boolean isUpdateSystems() {
        return update_systems;
    }

    public void setUpdateSystems(boolean update_systems) {
        this.update_systems = update_systems;
    }

    public boolean isUpdatePopulatedSystems() {
        return update_populated_systems;
    }

    public void setUpdatePopulatedSystems(boolean update_populated_systems) {
        this.update_populated_systems = update_populated_systems;
    }

    public boolean isUpdateBodies() {
        return update_bodies;
    }

    public void setUpdateBodies(boolean update_bodies) {
        this.update_bodies = update_bodies;
    }

    public boolean isUpdateStations() {
        return update_stations;
    }

    public void setUpdateStations(boolean update_stations) {
        this.update_stations = update_stations;
    }

    public boolean isUpdateMinorFactions() {
        return update_minor_factions;
    }

    public void setUpdateMinorFactions(boolean update_minor_factions) {
        this.update_minor_factions = update_minor_factions;
    }

    public boolean isUpdatePrices() {
        return update_prices;
    }

    public void setUpdatePrices(boolean update_prices) {
        this.update_prices = update_prices;
    }

    public boolean isUpdateCommodities() {
        return update_commodities;
    }

    public void setUpdateCommodities(boolean update_commodities) {
        this.update_commodities = update_commodities;
    }

    public boolean isUpdateModules() {
        return update_modules;
    }

    public void setUpdateModules(boolean update_modules) {
        this.update_modules = update_modules;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public Optional<MySQL> getMySQL() {
        return mySQL;
    }

    public void setMySQL(Optional<MySQL> mySQL) {
        this.mySQL = mySQL;
    }

    public File getDirectory() {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public String getReferenceSystemName() {
        return reference_system_name;
    }

    public void setReferenceSystemName(String reference_system_name) {
        this.reference_system_name = reference_system_name;
    }

    public static class ParseException extends RuntimeException {

        public ParseException(String message) {
            super(message);
        }

        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
