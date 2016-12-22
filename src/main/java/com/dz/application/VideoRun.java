package com.dz.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;

import com.dz.annotation.EnableJpaRepositoriesAuto;
@SpringBootApplication
@ComponentScan("com.dz")
@EnableJpaRepositoriesAuto
@EnableCaching(mode = AdviceMode.PROXY)
public class VideoRun {

	public static void main(String[] args) {
		SpringApplication.run(VideoRun.class, args);
	}
}
