package testtask.webusage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.webusage.domain.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByPath(String path);
}
