package luongld.freeswitch.configurations.directory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "extensions")
public class Extension {

    @Id
    private UUID id;

}
