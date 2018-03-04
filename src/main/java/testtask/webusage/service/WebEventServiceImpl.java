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
    public Integer getUserCountWithPeriod(Timestamp from, Timestamp to) {
        return webEventRepository.countByDateBetween(from, to);
    }

    @Override
    public Integer getUniqueUserCountWithPeriod(Timestamp from, Timestamp to) {
        return webEventRepository.countDistinctUsers(from, to);
    }

    @Override
    public Integer getRegularUserCountWithPeriod(Timestamp from, Timestamp to) {
        return webEventRepository.countRegularUsers(from, to);
    }
}
