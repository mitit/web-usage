package testtask.webusage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.webusage.controller.converter.WebEventConverter;
import testtask.webusage.domain.dto.ReportDto;
import testtask.webusage.domain.dto.WebEventDto;
import testtask.webusage.service.WebEventService;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/1/web_event")
public class WebUsageController {


    @Autowired
    WebEventConverter webEventConverter;

    @Autowired
    WebEventService webEventService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
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

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public ReportDto getReport(
            @RequestParam(value = "begin-time", required = false) Long bt,
            @RequestParam(value = "end-time", required = false) Long et) {

        Integer userCountWithPeriod = getUserCountWithPeriod(bt, et);
        Integer uniqueUserCountWithPeriod = getUniqueUserCountWithPeriod(bt, et);
        Integer regularUserCountWithPeriod = getRegularUserCountWithPeriod(bt, et);

        return ReportDto
                .builder()
                .userCount(userCountWithPeriod)
                .uniqueUserCount(uniqueUserCountWithPeriod)
                .regularUserCount(regularUserCountWithPeriod)
                .build();
    }

    private Integer getUserCountWithPeriod(Long bt, Long et) {
        Timestamp from = new Timestamp(bt);
        Timestamp to = new Timestamp(et);

        return webEventService.getUserCountWithPeriod(from, to);
    }

    private Integer getUniqueUserCountWithPeriod(Long bt, Long et) {
        Timestamp from = new Timestamp(bt);
        Timestamp to = new Timestamp(et);

        return webEventService.getUniqueUserCountWithPeriod(from, to);
    }

    private Integer getRegularUserCountWithPeriod(Long bt, Long et) {
        Timestamp from = new Timestamp(bt);
        Timestamp to = new Timestamp(et);

        return webEventService.getRegularUserCountWithPeriod(from, to);
    }

    private Integer getUserCountPerDay() {
        LocalDateTime today = LocalDateTime.now();

        Timestamp from = getTodayTimestampFrom(today);
        Timestamp to = getTodayTimestampTo(today);

        return webEventService.getUserCountWithPeriod(from, to);
    }

    private Integer getUniqueUserCountPerDay() {
        LocalDateTime today = LocalDateTime.now();

        Timestamp from = getTodayTimestampFrom(today);
        Timestamp to = getTodayTimestampTo(today);

        return webEventService.getUniqueUserCountWithPeriod(from, to);
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
