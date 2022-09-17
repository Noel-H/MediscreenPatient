package com.noelh.mediscreenpatient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * MediscreenPatient Application
 */
@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
public class MediscreenPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenPatientApplication.class, args);
    }

}
