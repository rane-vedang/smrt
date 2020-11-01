package com.vedang.smrt.result.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vedang.smrt.graph.entity.Vertex;

@JsonInclude(Include.NON_NULL)
public class Result<T extends Vertex> {

    boolean success;
    String description;

    public Result(String description) {
	this.success = true;
	this.description = description;
    }

    public Result(Exception e) {
	this.success = false;
	this.description = e.getMessage();
    }

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

}
