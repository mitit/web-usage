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
    Integer countDistinctUserId(Timestamp from, Timestamp to);

//    Integer countRegularUsers();
}
