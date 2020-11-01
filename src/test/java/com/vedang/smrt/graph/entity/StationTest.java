package com.vedang.smrt.graph.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

public class StationTest {

	@Test
	public void testConstructorName() throws Exception {
		assertThatThrownBy(() -> {
			new Station("", "Jurong East", "10 05 19900000");
		}).isInstanceOf(Exception.class);
	}
	
	@Test
	public void testConstructorInvalidDate() throws Exception {
		assertThatThrownBy(() -> {
			new Station("NS1", "Jurong East", "10 05 19900000");
		}).isInstanceOf(ParseException.class);
	}

	@Test
	public void testConstructor() throws Exception {
		Station station = new Station("NS1", "Jurong East", "10 March 1990");
		assertThat(station.getName()).isEqualTo("Jurong East");
	}

	@Test
	public void testIsOpenTrue() throws Exception {
		Station station = new Station("NS1", "Jurong East", "10 March 1990");
		assertTrue(station.isOpen());
	}

	@Test
	public void testIsOpenFalse() throws Exception {
		Station station = new Station("NS1", "Jurong East", "10 March 2050");
		assertFalse(station.isOpen());
	}

	@Test
	public void testEquals() throws Exception {
		Station station1 = new Station("NS1", "Jurong East", "10 March 1990");
		Station station2 = new Station("TE2", "Jurong East", "10 March 1990");
		assertTrue(station1.equals(station2));
	}

}
