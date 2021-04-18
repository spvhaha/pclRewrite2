package ca.ontario.health.hns.controller;

import ca.ontario.health.hns.domain.SCPRejection;
import ca.ontario.health.hns.service.SCPRejectionService;
import net.sf.jasperreports.data.http.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

@RequestMapping("/api")
public class SCPRejectionController {


    @Autowired
    private SCPRejectionService scpRejectionService;

    @RequestMapping("/scpRejections")
    public List<SCPRejection> getAllSCPRejection() {
        return scpRejectionService.getAllSCPRejections();

    }
    @RequestMapping("/scpRejections/{appNum}")
    public SCPRejection getSCPRejection(@PathVariable String appNum) {
        return scpRejectionService.getSCPRejection(appNum);
    }


    @RequestMapping(method = org.springframework.web.bind.annotation.RequestMethod.POST, value = "/scpRejections")
    public void addSCPRejection(@RequestBody SCPRejection scpRejection) {

        scpRejectionService.addSCPRejection(scpRejection);

    }


}
