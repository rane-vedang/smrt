package com.vedang.smrt.graph.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class Station implements Vertex {

    private String name;
    private List<String> codes;
    private Date openingDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

    public Station(String name) throws Exception {
	if (StringUtils.isEmpty(name)) {
	    throw new Exception("name cannot be empty");
	}
	this.codes = new ArrayList<>();
	this.name = name;
    }

    public Station(String code, String name, String openingDateStr) throws Exception {
	if (StringUtils.isEmpty(code) || StringUtils.isEmpty(name)) {
	    throw new Exception("code or name cannot be empty");
	}
	if (this.codes == null) {
	    this.codes = new ArrayList<>();
	}
	this.codes.add(code);

	this.name = name;
	try {
	    this.openingDate = dateFormat.parse(openingDateStr);
	} catch (ParseException pe) {
	    this.openingDate = new SimpleDateFormat("MMMM yyyy").parse(openingDateStr);
	}
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<String> getCodes() {
	return codes;
    }

    public void setCodes(List<String> code) {
	this.codes = code;
    }

    public Date getOpeningDate() {
	return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
	this.openingDate = openingDate;
    }

    public SimpleDateFormat getDateFormat() {
	return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
	this.dateFormat = dateFormat;
    }

    public boolean isOpen() {
	return new Date().after(openingDate);
    }

    public String getCode() {
	return codes.get(0);
    }

    public String getLine() {
	return codes.get(0).substring(0, 2);
    }

    public List<String> getLines() {
	return codes.stream().map(c -> c.substring(0, 2)).collect(Collectors.toList());
    }

    public String getCommonLine(Station otherStation) {
	List<String> otherStationLines = otherStation.getLines();
	for (String line : this.getLines()) {
	    if (otherStationLines.contains(line)) {
		return line;
	    }
	}
	return getLine();
    }

    @Override
    public String toString() {
	return "Station [name=" + name + ", codes=" + codes + ", openingDate=" + openingDate + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Station other = (Station) obj;
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	return true;
    }

}
