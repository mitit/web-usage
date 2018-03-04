package testtask.webusage.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.webusage.domain.WebEvent;


@Repository
public interface WebEventRepository extends JpaRepository<WebEvent, Long> {

//    Integer countDistinctUserId();


}
