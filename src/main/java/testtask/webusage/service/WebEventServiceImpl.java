package testtask.webusage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.webusage.controller.converter.WebEventConverter;
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
    public Integer getUserCount() {
        return (int) webEventRepository.count();
    }

    @Override
    public Integer getUniqueUserCount() {
        return null;
    }

    @Override
    public Integer getRegularUserCount() {
        return null;
    }

    @Override
    public Integer getUserCountWithPeriod(Timestamp from, Timestamp to) {
        return null;
    }

    @Override
    public Integer getUniqueUserCountWithPeriod(Timestamp from, Timestamp to) {
        return null;
    }

    @Override
    public Integer getRegularUserCountWithPeriod(Timestamp from, Timestamp to) {
        return null;
    }
}
