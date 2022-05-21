package me.efco.data;

public class DBConnection {
    private static final DBConnection INSTANCE = new DBConnection();
    private final String CONNECTION_STRING;
    private final String USERNAME;
    private final String PASSWORD;

    private DBConnection(){
        CONNECTION_STRING = ConfigLoader.getInstance().loadConfigByName("db_database");
        USERNAME = ConfigLoader.getInstance().loadConfigByName("db_user");
        PASSWORD = ConfigLoader.getInstance().loadConfigByName("db_pass");
    }
}
