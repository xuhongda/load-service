package com.xu.loadservicewebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author xuhongda
 */
@SpringBootApplication
@EnableScheduling
public class LoadServiceWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadServiceWebsocketApplication.class, args);
	}

	@Bean
	public TaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		return threadPoolTaskScheduler;
	}
}
