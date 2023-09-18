package com.clock.api.service;

import org.springframework.stereotype.Service;

@Service
public class TimeConversionService {

    public String convertToWords(String time) {
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
}

