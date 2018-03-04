package testtask.webusage.service;

import testtask.webusage.domain.WebEvent;

public interface WebEventService {

    void saveWebEvent(WebEvent webEvent);

    Integer getUserCountByPeriod(Long from, Long to);

    Integer getUniqueUserCountByPeriod(Long from, Long to);

    Integer getRegularUserCountByPeriod(Long from, Long to);

    Integer getUserCountPerDay();

    Integer getUniqueUserCountPerDay();
}
