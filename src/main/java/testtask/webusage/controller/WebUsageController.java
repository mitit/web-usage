package testtask.webusage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.webusage.controller.converter.WebEventConverter;
import testtask.webusage.domain.dto.ReportDto;
import testtask.webusage.domain.dto.WebEventDto;
import testtask.webusage.service.WebEventService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/1/web_event")
public class WebUsageController {


    @Autowired
    private WebEventConverter webEventConverter;

    @Autowired
    private WebEventService webEventService;

    @RequestMapping(method = RequestMethod.POST)
    public ReportDto createWebEvent(@RequestBody WebEventDto eventDto) {
        webEventService.saveWebEvent(webEventConverter.createFromDto(eventDto));

        final Integer userCountPerDay = getUserCountPerDay();
        final Integer uniqueUserCountPerDay = getUniqueUserCountPerDay();

        return ReportDto
                .builder()
                .userCount(userCountPerDay)
                .uniqueUserCount(uniqueUserCountPerDay)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ReportDto getReport(
            @RequestParam(value = "begin-time") Long bt,
            @RequestParam(value = "end-time") Long et) {

        final Integer userCountByPeriod = getUserCountByPeriod(bt, et);
        final Integer uniqueUserCountByPeriod = getUniqueUserCountByPeriod(bt, et);
        final Integer regularUserCountByPeriod = getRegularUserCountByPeriod(bt, et);

        return ReportDto
                .builder()
                .userCount(userCountByPeriod)
                .uniqueUserCount(uniqueUserCountByPeriod)
                .regularUserCount(regularUserCountByPeriod)
                .build();
    }

    private Integer getUserCountByPeriod(Long bt, Long et) {
        final Timestamp from = new Timestamp(bt);
        final Timestamp to = new Timestamp(et);

        return webEventService.getUserCountByPeriod(from, to);
    }

    private Integer getUniqueUserCountByPeriod(Long bt, Long et) {
        final Timestamp from = new Timestamp(bt);
        final Timestamp to = new Timestamp(et);

        return webEventService.getUniqueUserCountByPeriod(from, to);
    }

    private Integer getRegularUserCountByPeriod(Long bt, Long et) {
        final Timestamp from = new Timestamp(bt);
        final Timestamp to = new Timestamp(et);

        return webEventService.getRegularUserCountByPeriod(from, to);
    }

    private Integer getUserCountPerDay() {
        final LocalDateTime today = LocalDateTime.now();

        final Timestamp from = getTodayTimestampFrom(today);
        final Timestamp to = getTodayTimestampTo(today);

        return webEventService.getUserCountByPeriod(from, to);
    }

    private Integer getUniqueUserCountPerDay() {
        final LocalDateTime today = LocalDateTime.now();

        final Timestamp from = getTodayTimestampFrom(today);
        final Timestamp to = getTodayTimestampTo(today);

        return webEventService.getUniqueUserCountByPeriod(from, to);
    }

    private Timestamp getTodayTimestampTo(LocalDateTime today) {
        final LocalDateTime dateTo =
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 23, 59);
        return Timestamp.valueOf(dateTo);
    }

    private Timestamp getTodayTimestampFrom(LocalDateTime today) {
        final LocalDateTime dateFrom =
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 0, 0);
        return Timestamp.valueOf(dateFrom);
    }
}
