package com.ah3nan.medicalclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class MedicalClinicApplication {

	public static void main(String[] args) {
		Map<String, String> env  = System.getenv();
		env.entrySet().forEach(System.out::println);

		SpringApplication.run(MedicalClinicApplication.class, args);
	}

}
