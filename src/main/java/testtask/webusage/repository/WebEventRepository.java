package testtask.webusage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import testtask.webusage.domain.WebEvent;

import java.sql.Timestamp;


@Repository
public interface WebEventRepository extends JpaRepository<WebEvent, Long> {

    Integer countByDateBetween(Timestamp from, Timestamp to);

    @Query("SELECT COUNT(DISTINCT we.user) FROM WebEvent we " +
            "WHERE we.date BETWEEN ?1 AND ?2")
    Integer countDistinctUsersByPeriod(Timestamp from, Timestamp to);

    @Query(value = "SELECT COUNT(*) FROM " +
            "(SELECT user_name u, COUNT(DISTINCT url_path) pc FROM web_events " +
            "WHERE date BETWEEN ?1 AND ?2 GROUP BY user_name) tmp " +
            "WHERE tmp.pc > 10", nativeQuery = true)
    Integer countRegularUsersByPeriod(Timestamp from, Timestamp to);
}
