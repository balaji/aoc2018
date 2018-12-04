package com.balaji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day4 {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String line;
        String[] input = new String[1200];
        int count=0;
        while(!(line = scanner.nextLine()).equals("")) {
            input[count++] = line;
        }
        Arrays.sort(input, 0, count);

        final String[][] data = new String[count / 2][62];
        for (String[] datum : data) {
            Arrays.fill(datum, "");
        }
        System.out.println(count);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < count; i = i + 3) {
            System.out.println(i);
            data[i/3][0] = input[i].split(" ")[3];
            String sleep = input[i + 1];
            String wakes = input[i + 2];

            Date sleepTime = dateFormat.parse(sleep.substring(1, sleep.indexOf(']')));
            Date wakeUpTime = dateFormat.parse(wakes.substring(1, wakes.indexOf(']')));

            ZonedDateTime sleepDT = sleepTime.toInstant().atZone(ZoneId.of("Z"));
            data[i/3][1] = String.format("%d-%d", sleepDT.getMonthValue(), sleepDT.getDayOfMonth());

            final int v = i/3;
            IntStream.range(sleepDT.getMinute(),
            wakeUpTime.toInstant().atZone(ZoneId.of("Z")).getMinute())
                    .forEach(t -> data[v][t] = "#");
        }

        for (String[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
    }
}
