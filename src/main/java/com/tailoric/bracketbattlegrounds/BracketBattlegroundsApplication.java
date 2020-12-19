package com.tailoric.bracketbattlegrounds;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BracketBattlegroundsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BracketBattlegroundsApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

}