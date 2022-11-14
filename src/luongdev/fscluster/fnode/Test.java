package luongdev.fscluster.fnode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "cluster.test")
public class Test {

    @Id
    private UUID id = UUID.randomUUID();

    private String content;

    public Test() {
    }

    public Test(String content) {
        this();
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
