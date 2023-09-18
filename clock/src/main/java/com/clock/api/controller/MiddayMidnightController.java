package com.clock.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MiddayMidnightController {

    @GetMapping("/midday-midnight/{inputTime}")
    public String checkMiddayMidnight(@PathVariable String inputTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());

        if ("12:00".equals(inputTime)) {
            return "It's midday";
        } else if ("00:00".equals(inputTime)) {
            return "It's midnight";
        } else {
            return "Input time is not midday or midnight.";
        }
    }
}

