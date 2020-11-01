package com.vedang.smrt.graph.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import com.vedang.smrt.graph.entity.Station;

public class DurationServiceUtilTest {

    @Test
    public void testGetDurationPeakTimePeakStation() throws Exception {
	LocalDateTime peakStartTime = LocalDateTime.of(2020, 10, 29, 07, 00);
	Station source = new Station("NS1", "Jurong East", "10 March 1990");
	Station destination = new Station("NS2", "Bukit Batok", "10 March 1990");
	Integer result = DurationServiceUtil.getDuration(peakStartTime, source, destination);
	assertThat(result).isEqualTo(12);

    }

    @Test
    public void testGetDurationPeakTimeNonPeakStation() throws Exception {
	LocalDateTime peakStartTime = LocalDateTime.of(2020, 10, 29, 07, 00);
	Station source = new Station("EW1", "Pasir Rist", "10 March 1990");
	Station destination = new Station("EW2", "Tampines", "10 March 1990");
	Integer result = DurationServiceUtil.getDuration(peakStartTime, source, destination);
	assertThat(result).isEqualTo(10);

    }

    @Test
    public void testGetDurationNightTimeNightStation() throws Exception {
	LocalDateTime nightStartTime = LocalDateTime.of(2020, 10, 29, 23, 00);
	Station source = new Station("TE1", "Jurong East", "10 March 1990");
	Station destination = new Station("TE2", "Bukit Batok", "10 March 1990");
	Integer result = DurationServiceUtil.getDuration(nightStartTime, source, destination);
	assertThat(result).isEqualTo(8);

    }

    @Test
    public void testGetDurationNightTimeNonNightStation() throws Exception {
	LocalDateTime nightStartTime = LocalDateTime.of(2020, 10, 29, 23, 00);
	Station source = new Station("EW1", "Pasir Rist", "10 March 1990");
	Station destination = new Station("EW2", "Tampines", "10 March 1990");
	Integer result = DurationServiceUtil.getDuration(nightStartTime, source, destination);
	assertThat(result).isEqualTo(10);

    }

    @Test
    public void testGetDurationNonPeakTimeSpecialStation() throws Exception {
	LocalDateTime nonPeakStartTime = LocalDateTime.of(2020, 10, 29, 13, 00);
	Station source = new Station("TE1", "Jurong East", "10 March 1990");
	Station destination = new Station("TE2", "Bukit Batok", "10 March 1990");
	Integer result = DurationServiceUtil.getDuration(nonPeakStartTime, source, destination);
	assertThat(result).isEqualTo(8);

    }

    @Test
    public void testGetDurationNonPeakTimeDefaultStation() throws Exception {
	LocalDateTime nonPeakStartTime = LocalDateTime.of(2020, 10, 29, 13, 00);
	Station source = new Station("EW1", "Pasir Rist", "10 March 1990");
	Station destination = new Station("EW2", "Tampines", "10 March 1990");
	Integer result = DurationServiceUtil.getDuration(nonPeakStartTime, source, destination);
	assertThat(result).isEqualTo(10);

    }

    @Test
    public void testIsPeakTrue() throws Exception {

	LocalDateTime peakStartTime = LocalDateTime.of(2020, 10, 29, 07, 00);

	Boolean result = DurationServiceUtil.isPeak(peakStartTime);

	assertTrue(result);
    }

    @Test
    public void testIsPeakFalse() throws Exception {

	LocalDateTime nonPeakStartTime = LocalDateTime.of(2020, 10, 29, 13, 00);

	Boolean result = DurationServiceUtil.isPeak(nonPeakStartTime);

	assertFalse(result);
    }

    @Test
    public void testIsNightTrue() throws Exception {

	LocalDateTime nightStartTime = LocalDateTime.of(2020, 10, 29, 23, 00);

	Boolean result = DurationServiceUtil.isNight(nightStartTime);

	assertTrue(result);
    }

    @Test
    public void testIsNightFalse() throws Exception {

	LocalDateTime nonNightStartTime = LocalDateTime.of(2020, 10, 29, 13, 00);

	Boolean result = DurationServiceUtil.isNight(nonNightStartTime);

	assertFalse(result);
    }
}
