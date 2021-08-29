package com.example.demo.pckg1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication//(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class KidiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KidiApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	  public void EventListenerExecute() {
	    System.out.println("Application Ready Event is successfully Started");
	}
}
