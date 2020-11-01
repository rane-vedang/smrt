package com.vedang.smrt.graph.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.vedang.smrt.graph.entity.Station;

public class DurationServiceUtil {

    public static List<String> PEAK_HOUR_SPECIAL_LINES = Arrays.asList("NS", "NE");
    public static List<String> NON_PEAK_HOUR_SPECIAL_LINES = Arrays.asList("DT", "TE");
    public static List<String> NIGHT_SPECIAL_LINES = Arrays.asList("TE");
    public static List<String> NIGHT_NON_OPERATING_LINES = Arrays.asList("DT", "CG", "CE");

    public static Integer STATION_COST_PEAK = 12;
    public static Integer STATION_COST_DEFAULT_PEAK = 10;

    public static Integer STATION_COST_NON_PEAK = 8;
    public static Integer STATION_COST_DEFAULT_NON_PEAK_10 = 10;

    public static Integer LINE_CHANGE_COST_PEAK = 15;
    public static Integer LINE_CHANGE_COST = 10;

    public static Integer getDuration(LocalDateTime dateTime, Station source, Station destination) {
	String sourceLine = source.getLine();
	String destinationLine = destination.getLine();
	int totalDuration = 0;
	if (isPeak(dateTime)) {
	    // peak period
	    if (!sourceLine.equals(destinationLine)) {
		totalDuration += LINE_CHANGE_COST_PEAK; // add line change cost
	    }
	    if (PEAK_HOUR_SPECIAL_LINES.contains(destinationLine)) {
		totalDuration += STATION_COST_PEAK;
	    } else {
		totalDuration += STATION_COST_DEFAULT_PEAK;
	    }
	} else if (isNight(dateTime)) {

	    // night time
	    if (!sourceLine.equals(destinationLine)) {
		totalDuration += LINE_CHANGE_COST; // add line change cost
	    }
	    if (NIGHT_SPECIAL_LINES.contains(destinationLine)) {
		totalDuration += STATION_COST_NON_PEAK;
	    } else {
		totalDuration += STATION_COST_DEFAULT_NON_PEAK_10;
	    }
	} else {
	    // non peak time
	    if (!sourceLine.equals(destinationLine)) {
		totalDuration += LINE_CHANGE_COST; // add line change cost
	    }
	    if (NON_PEAK_HOUR_SPECIAL_LINES.contains(destinationLine)) {
		totalDuration += STATION_COST_NON_PEAK;
	    } else {

		totalDuration += STATION_COST_DEFAULT_NON_PEAK_10;
	    }
	}
	return totalDuration;
    }

    public static boolean isPeak(LocalDateTime currentTime) {
	DayOfWeek dayOfWeek = currentTime.toLocalDate().getDayOfWeek();
	boolean isPeakDay = dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY
		|| dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.FRIDAY;

	int hour = currentTime.toLocalTime().getHour();
	boolean isPeakTime = (hour > 6 && hour < 9) || (hour > 18 && hour < 21);
	return isPeakDay && isPeakTime;

    }

    public static boolean isNight(LocalDateTime currentTime) {
	int hour = currentTime.toLocalTime().getHour();
	return hour > 22 || hour < 6;
    }

}
