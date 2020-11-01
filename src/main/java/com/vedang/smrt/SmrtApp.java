package com.vedang.smrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmrtApp {
    public static void main(String[] args) {
	System.out.println("Starting SMRT Application...");
	SpringApplication.run(SmrtApp.class, args);
    }
}