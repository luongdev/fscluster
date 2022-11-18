package luongld.freeswitch.cluster.repositories;

import luongld.freeswitch.cluster.Instance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstanceRepository extends JpaRepository<Instance, String> {
}
