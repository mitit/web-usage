package testtask.webusage.service;

import org.apache.tomcat.jni.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import testtask.webusage.domain.WebEvent;

import java.sql.Timestamp;


@Repository
public interface WebEventRepository extends JpaRepository<WebEvent, Long> {

    Integer countByDateBetween(Timestamp from, Timestamp to);

    @Query("SELECT DISTINCT COUNT(we.user) FROM WebEvent we " +
            "WHERE we.date BETWEEN ?1 AND ?2")
    Integer countDistinctUsers(Timestamp from, Timestamp to);

    @Query(value = "SELECT COUNT(*) FROM " +
            "(SELECT user_id u, COUNT(DISTINCT url_path) uc FROM web_events " +
            "WHERE date BETWEEN ?1 AND ?2 GROUP BY user_id) tmp " +
            "WHERE tmp.uc > 10", nativeQuery = true)
    Integer countRegularUsers(Timestamp from, Timestamp to);
}
