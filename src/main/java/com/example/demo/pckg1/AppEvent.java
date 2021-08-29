package com.example.demo.pckg1;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppEvent implements ApplicationListener <ApplicationReadyEvent>{

	@Override 
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		Randoms.createRandomParents();
		Randoms.createRandomkids();
		Randoms.createRandomCategories();
		Randoms.createRandomCourses();
		Randoms.createRandomMeetings();
	}
}
