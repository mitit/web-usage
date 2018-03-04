package testtask.webusage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.webusage.domain.WebEvent;
import testtask.webusage.repository.WebEventRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class WebEventServiceImpl implements WebEventService {

    @Autowired
    private WebEventRepository webEventRepository;

    @Override
    public void saveWebEvent(WebEvent webEvent) {
        webEventRepository.save(webEvent);
    }

    @Override
    public Integer getEventCountByPeriod(Long bt, Long et) {
        final Timestamp from = new Timestamp(bt);
        final Timestamp to = new Timestamp(et);

        return webEventRepository.countByDateBetween(from, to);
    }

    @Override
    public Integer getUniqueUserCountByPeriod(Long bt, Long et) {
        final Timestamp from = new Timestamp(bt);
        final Timestamp to = new Timestamp(et);

        return webEventRepository.countDistinctUsersByPeriod(from, to);
    }

    @Override
    public Integer getRegularUserCountByPeriod(Long bt, Long et) {
        final Timestamp from = new Timestamp(bt);
        final Timestamp to = new Timestamp(et);

        return webEventRepository.countRegularUsersByPeriod(from, to);
    }

    @Override
    public Integer getEventCountPerDay() {
        final LocalDateTime today = LocalDateTime.now();

        final Timestamp from = getTodayTimestampFrom(today);
        final Timestamp to = getTodayTimestampTo(today);

        return webEventRepository.countByDateBetween(from, to);
    }

    @Override
    public Integer getUniqueUserCountPerDay() {
        final LocalDateTime today = LocalDateTime.now();

        final Timestamp from = getTodayTimestampFrom(today);
        final Timestamp to = getTodayTimestampTo(today);

        return webEventRepository.countDistinctUsersByPeriod(from, to);
    }

    private Timestamp getTodayTimestampTo(LocalDateTime today) {
        final LocalDateTime dateTo =
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 23, 59, 59);
        return Timestamp.valueOf(dateTo);
    }

    private Timestamp getTodayTimestampFrom(LocalDateTime today) {
        final LocalDateTime dateFrom =
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 0, 0);
        return Timestamp.valueOf(dateFrom);
    }
}
