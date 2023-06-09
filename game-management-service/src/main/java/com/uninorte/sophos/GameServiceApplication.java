package com.uninorte.sophos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.uninorte.sophos.components.ViewManager;


@SpringBootApplication
public class GameServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GameServiceApplication.class, args);
		ViewManager views = context.getBean(ViewManager.class);
		views.createViews();
	}

}
