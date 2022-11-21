package luongld.freeswitch.domain.repositories;

import luongld.freeswitch.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainJpaRepository extends JpaRepository<Domain, String> {
}
