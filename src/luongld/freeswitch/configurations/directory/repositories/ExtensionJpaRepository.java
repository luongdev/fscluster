package luongld.freeswitch.configurations.directory.repositories;

import luongld.freeswitch.configurations.directory.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionJpaRepository extends JpaRepository<Extension, String> {
}
