package com.steps.postsapi.services.application;

import com.steps.postsapi.services.domain.RuntimeCalculationService;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculateRuntimesApplicationService {

    @Autowired
    private RuntimeCalculationService runtimeCalculationService;

    public long calculate(){
        return runtimeCalculationService.calculateAverageRuntime();
    }
}
