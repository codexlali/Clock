package com.clock.api.controller;

import com.clock.api.entity.TimeResponse;
import com.clock.api.exceptions.TimeConversionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TimeController {

    @GetMapping("/time")
    public TimeResponse getCurrentTimeInWords() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());

        String timeInWords = convertToWords(currentTime);
        TimeResponse response = new TimeResponse();
        response.setMessage("It's " + timeInWords);
        return response;
    }

    @GetMapping("/time/{inputTime}")
    public TimeResponse convertUserInputToWords(@PathVariable String inputTime) {
        String timeInWords = convertToWords(inputTime);
        TimeResponse response = new TimeResponse();
        response.setMessage("It's " + timeInWords);
        return response;
    }

    private String convertToWords(String time) {
        if (time == null || !isValidTimeFormat(time)) {
            throw new TimeConversionException("Invalid time format");
        }
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        String[] numbers = {
                "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
                "nineteen", "twenty"
        };

        if (hours == 12 && minutes == 0) {
            return "midday";
        } else if (hours == 0 && minutes == 0) {
            return "midnight";
        } else {
            String hourInWords = "";
            if (hours < 20) {
                hourInWords = numbers[hours];
            } else {
                int tens = hours / 10;
                int ones = hours % 10;
                hourInWords = "twenty";
                if (ones > 0) {
                    hourInWords += " " + numbers[ones];
                }
            }

            String minuteInWords = "";
            if (minutes == 0) {
                minuteInWords = "o'clock";
            } else if (minutes < 10) {
                minuteInWords = "oh " + numbers[minutes];
            } else if (minutes < 20) {
                minuteInWords = numbers[minutes];
            } else {
                int tens = minutes / 10;
                int ones = minutes % 10;
                minuteInWords = "twenty";
                if (ones > 0) {
                    minuteInWords += " " + numbers[ones];
                }
            }

            return hourInWords + " " + minuteInWords;
        }
    }

    private boolean isValidTimeFormat(String time) {
        return false;
    }


}
