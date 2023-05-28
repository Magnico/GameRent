package com.uninorte.sophos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.uninorte.sophos.components.TriggerManager;

import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableScheduling
public class RentServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RentServiceApplication.class, args);
		TriggerManager triggers = context.getBean(TriggerManager.class);
        triggers.createTriggers();
        triggers.executePopulateSqlFile();
	}

}
