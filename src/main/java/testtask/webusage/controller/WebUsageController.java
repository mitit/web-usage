package testtask.webusage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.webusage.controller.converter.WebEventConverter;
import testtask.webusage.domain.WebEvent;
import testtask.webusage.domain.dto.ReportDto;
import testtask.webusage.domain.dto.WebEventDto;
import testtask.webusage.service.WebEventService;
import testtask.webusage.service.WebEventServiceImpl;

@RestController
@RequestMapping("/api/1/web_event")
public class WebUsageController {


    @Autowired
    WebEventConverter webEventConverter;

    @Autowired
    WebEventService webEventService;

    @RequestMapping(method = RequestMethod.POST)
    public ReportDto createWebEvent(@RequestBody WebEventDto eventDto) {

        webEventService.saveWebEvent(WebEvent.builder().build());
        return ReportDto.builder().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ReportDto getReport(
            @RequestParam("begin-time") long bt,
            @RequestParam("end-time") long et) {


        return ReportDto.builder().build();
    }
}
