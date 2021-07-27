package com.steps.postsapi.controllers;

import com.steps.postsapi.jsonobjects.Runtime;
import com.steps.postsapi.persistence.User;
import com.steps.postsapi.services.application.CalculateRuntimesApplicationService;
import com.steps.postsapi.services.application.GetTopCreatorsApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("steps/v0")
public class StatisticsController {

    private static final String MILLIS = "Millis";
    private static final String NANOS = "Nanos";

    @Value("${time.unit}")
    private String timeUnit;

    @Autowired
    private GetTopCreatorsApplicationService getTopCreatorsApplicationService;
    @Autowired
    private CalculateRuntimesApplicationService calculateRuntimesApplicationService;

    @GetMapping("/statistics/topcreators")
    @ResponseBody
    public ResponseEntity<List<User>> getTopCreators(){
        List<User> topCreators = getTopCreatorsApplicationService.getTopCreators();
        return ResponseEntity.ok(topCreators);
    }

    @GetMapping("/statistics/runtimes")
    @ResponseBody
    public ResponseEntity<Runtime> runtimes(){
        Long averageRuntime = calculateRuntimesApplicationService.calculate();
        Runtime runtime;
        if (MILLIS.equalsIgnoreCase(timeUnit))
            runtime = new Runtime(averageRuntime, MILLIS);
        else
            runtime = new Runtime(averageRuntime, NANOS);
        return ResponseEntity.ok(runtime);
    }

}
