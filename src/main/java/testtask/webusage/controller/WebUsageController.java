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
    WebEventConverter webEventConverter;

    @Autowired
    WebEventService webEventService;

    @RequestMapping(method = RequestMethod.POST)
    public ReportDto createWebEvent(@RequestBody WebEventDto eventDto) {
        webEventService.saveWebEvent(webEventConverter.createFromDto(eventDto));

        Integer userCountPerDay = getUserCountPerDay();
        Integer uniqueUserCountPerDay = getUniqueUserCountPerDay();

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

        Integer userCountByPeriod = getUserCountByPeriod(bt, et);
        Integer uniqueUserCountByPeriod = getUniqueUserCountByPeriod(bt, et);
        Integer regularUserCountByPeriod = getRegularUserCountByPeriod(bt, et);

        return ReportDto
                .builder()
                .userCount(userCountByPeriod)
                .uniqueUserCount(uniqueUserCountByPeriod)
                .regularUserCount(regularUserCountByPeriod)
                .build();
    }

    private Integer getUserCountByPeriod(Long bt, Long et) {
        Timestamp from = new Timestamp(bt);
        Timestamp to = new Timestamp(et);

        return webEventService.getUserCountByPeriod(from, to);
    }

    private Integer getUniqueUserCountByPeriod(Long bt, Long et) {
        Timestamp from = new Timestamp(bt);
        Timestamp to = new Timestamp(et);

        return webEventService.getUniqueUserCountByPeriod(from, to);
    }

    private Integer getRegularUserCountByPeriod(Long bt, Long et) {
        Timestamp from = new Timestamp(bt);
        Timestamp to = new Timestamp(et);

        return webEventService.getRegularUserCountByPeriod(from, to);
    }

    private Integer getUserCountPerDay() {
        LocalDateTime today = LocalDateTime.now();

        Timestamp from = getTodayTimestampFrom(today);
        Timestamp to = getTodayTimestampTo(today);

        return webEventService.getUserCountByPeriod(from, to);
    }

    private Integer getUniqueUserCountPerDay() {
        LocalDateTime today = LocalDateTime.now();

        Timestamp from = getTodayTimestampFrom(today);
        Timestamp to = getTodayTimestampTo(today);

        return webEventService.getUniqueUserCountByPeriod(from, to);
    }

    private Timestamp getTodayTimestampTo(LocalDateTime today) {
        LocalDateTime dateTo =
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 0, 0);
        return Timestamp.valueOf(dateTo);
    }

    private Timestamp getTodayTimestampFrom(LocalDateTime today) {
        LocalDateTime dateFrom =
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 23, 59);
        return Timestamp.valueOf(dateFrom);
    }
}
