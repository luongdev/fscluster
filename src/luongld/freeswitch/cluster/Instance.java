package luongld.freeswitch.cluster;

import javax.persistence.*;

@Entity
@Table(name = "cluster.instances")
public class Instance {

    @Id
    private String name;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "db_port", columnDefinition = "int2", nullable = false)
    private int dbPort;

    @Column(name = "db_user", nullable = false)
    private String dbUser;

    @Column(name = "db_password", nullable = false)
    private String dbPassword;

    @Column(name = "socket_port", columnDefinition = "int2", nullable = false)
    private int socketPort;

    @Column(name = "socket_password", nullable = false)
    private String socketPassword;

    @Column(name = "description", length = 510)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public int getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(int socketPort) {
        this.socketPort = socketPort;
    }

    public String getSocketPassword() {
        return socketPassword;
    }

    public void setSocketPassword(String socketPassword) {
        this.socketPassword = socketPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
