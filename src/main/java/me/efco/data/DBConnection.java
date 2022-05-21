package me.efco.data;

import me.efco.entities.ServerInfo;

import javax.annotation.Nullable;
import java.sql.*;

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

    public ServerInfo getServerInfo(long serverId) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + CONNECTION_STRING,USERNAME,PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_info WHERE server_id=?")){
            statement.setLong(1, serverId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new ServerInfo(serverId, result.getLong("role_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    public void insertServerInfo(long serverId, long roleId) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + CONNECTION_STRING,USERNAME,PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO server_info (server_id,role_id) VALUES (?,?);")){
            statement.setLong(1, serverId);
            statement.setLong(2, roleId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(long serverId, long roleId) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + CONNECTION_STRING,USERNAME,PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE server_info SET role_id=? WHERE server_id=?;")){
            statement.setLong(1, roleId);
            statement.setLong(2, serverId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        return INSTANCE;
    }
}
