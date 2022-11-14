package luongdev.fscluster.master;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FSNodeRepository extends JpaRepository<FSNode, UUID> {
}
