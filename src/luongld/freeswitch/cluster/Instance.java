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
}
