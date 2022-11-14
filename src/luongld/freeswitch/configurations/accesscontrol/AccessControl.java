package luongld.freeswitch.configurations.accesscontrol;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "access_controls", uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = "name"))
public class AccessControl {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_allow", nullable = false)
    private boolean allow = false;

    @Column(name = "description", length = 510)
    private String description;

    @MapKey(name = "id")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accessControl", fetch = FetchType.LAZY)
    private final Map<UUID, AccessControlDetail> accessControlDetails = new HashMap<>();

    public AccessControl() {
        this.id = UUID.randomUUID();
    }

    public AccessControl(String name, boolean allow, String description) {
        this();
        assert StringUtils.isNotEmpty(name);

        this.name = name;
        this.allow = allow;
        this.description = description;
    }

    public void addDetail(String cidr, String domain, boolean isAllow, String description) {
        var detail = new AccessControlDetail(cidr, domain, isAllow, description);
        detail.setAccessControl(this);

        this.accessControlDetails.put(detail.getId(), detail);
    }

    public void removeDetail(UUID id) {
        if (!this.accessControlDetails.containsKey(id)) return;

        this.accessControlDetails.remove(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<UUID, AccessControlDetail> getAccessControlDetails() {
        return accessControlDetails;
    }
}
