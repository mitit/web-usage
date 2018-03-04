package testtask.webusage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.webusage.domain.WebEvent;

import java.sql.Timestamp;

@Service
public class WebEventServiceImpl implements WebEventService {

    @Autowired
    WebEventRepository webEventRepository;

    @Override
    public void saveWebEvent(WebEvent webEvent) {
        webEventRepository.save(webEvent);
    }

    @Override
    public Integer getUserCountByPeriod(Timestamp from, Timestamp to) {
        return webEventRepository.countByDateBetween(from, to);
    }

    @Override
    public Integer getUniqueUserCountByPeriod(Timestamp from, Timestamp to) {
        return webEventRepository.countDistinctUsersByPeriod(from, to);
    }

    @Override
    public Integer getRegularUserCountByPeriod(Timestamp from, Timestamp to) {
        return webEventRepository.countRegularUsersByPeriod(from, to);
    }
}
