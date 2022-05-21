package me.efco.entities;

public class ServerInfo {
    private final long serverId;
    private final long roleId;


    public ServerInfo(long serverId, long roleId) {
        this.serverId = serverId;
        this.roleId = roleId;
    }

    public long getServerId() {
        return serverId;
    }

    public long getRoleId() {
        return roleId;
    }
}
