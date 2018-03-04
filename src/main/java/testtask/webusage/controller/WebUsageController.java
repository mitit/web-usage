package testtask.webusage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.webusage.controller.converter.WebEventConverter;
import testtask.webusage.domain.dto.ReportDto;
import testtask.webusage.domain.dto.WebEventDto;
import testtask.webusage.service.WebEventService;

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

        final Integer userCountPerDay = webEventService.getUserCountPerDay();
        final Integer uniqueUserCountPerDay = webEventService.getUniqueUserCountPerDay();

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

        final Integer userCountByPeriod = webEventService.getUserCountByPeriod(bt, et);
        final Integer uniqueUserCountByPeriod = webEventService.getUniqueUserCountByPeriod(bt, et);
        final Integer regularUserCountByPeriod = webEventService.getRegularUserCountByPeriod(bt, et);

        return ReportDto
                .builder()
                .userCount(userCountByPeriod)
                .uniqueUserCount(uniqueUserCountByPeriod)
                .regularUserCount(regularUserCountByPeriod)
                .build();
    }
}
