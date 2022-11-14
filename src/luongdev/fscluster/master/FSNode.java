package luongdev.fscluster.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "cluster.nodes")
public class FSNode {

    @Id
    private UUID id;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "db_port", nullable = false)
    private int dbPort = 5432;

    @Column(name = "db_user", nullable = false)
    private String dbUser = "fcluster";

    @Column(name = "db_password", nullable = false)
    private String dbPassword = "fcluster";

    @Column(name = "socket_port", nullable = false)
    private int socketPort = 8021;

    @Column(name = "socket_password", nullable = false)
    private String socketPassword = "ClueCon";

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
