package testtask.webusage.service;

import org.springframework.stereotype.Service;
import testtask.webusage.domain.WebEvent;

import java.sql.Timestamp;

public interface WebEventService {

    void saveWebEvent(WebEvent webEvent);

    Integer getUserCountWithPeriod(Timestamp from, Timestamp to);

    Integer getUniqueUserCountWithPeriod(Timestamp from, Timestamp to);

    Integer getRegularUserCountWithPeriod(Timestamp from, Timestamp to);
}
