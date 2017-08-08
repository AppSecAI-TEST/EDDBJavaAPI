package ua.gwm.eddb_java_api.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found!");
        }
    }

    private ConnectionCreator connection_creator;
    private Connection connection;

    public MySQL(String ip, int port, String db, String user, String password) throws SQLException {
        connection_creator = new ConnectionCreator(ip, port, db, user, password);
        reconnect();
    }

    public void reconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = connection_creator.createConnection();
    }

    public ConnectionCreator getConnectionCreator() {
        return connection_creator;
    }

    public void setConnectionCreator(ConnectionCreator connection_creator) {
        this.connection_creator = connection_creator;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public class ConnectionCreator {

        private String ip;
        private int port;
        private String db;
        private String user;
        private String password;

        public ConnectionCreator(String ip, int port, String db, String user, String password) {
            this.ip = ip;
            this.port = port;
            this.db = db;
            this.user = user;
            this.password = password;
        }

        public Connection createConnection() throws SQLException {
            return DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":" + port + "/" + db + "?useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}