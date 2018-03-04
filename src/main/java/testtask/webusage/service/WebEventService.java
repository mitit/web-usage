package testtask.webusage.service;

import testtask.webusage.domain.WebEvent;

import java.sql.Timestamp;

public interface WebEventService {

    void saveWebEvent(WebEvent webEvent);

    Integer getUserCountByPeriod(Timestamp from, Timestamp to);

    Integer getUniqueUserCountByPeriod(Timestamp from, Timestamp to);

    Integer getRegularUserCountByPeriod(Timestamp from, Timestamp to);
}
