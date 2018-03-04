package testtask.webusage.service;

import testtask.webusage.domain.WebEvent;

public interface WebEventService {

    void saveWebEvent(WebEvent webEvent);

    Integer getEventCountByPeriod(Long from, Long to);

    Integer getUniqueUserCountByPeriod(Long from, Long to);

    Integer getRegularUserCountByPeriod(Long from, Long to);

    Integer getEventCountPerDay();

    Integer getUniqueUserCountPerDay();
}
