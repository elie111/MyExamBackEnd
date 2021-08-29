package com.example.demo.pckg1;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiddleExamController {

	@Autowired
	private examRepository repository;

	
	
	//1 - Courses statistics
	@GetMapping("/GetMeetingsStat/{time}") 
	public int[] getCoursesForDate(@PathVariable int time) {
		int[] mettingStat = new int[2];
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate curDate = LocalDate.now();
		Date date = new Date();
		switch(time) {
		  case 1:
			  mettingStat = repository.getAllhappendMeetingsForDate(Date.from(curDate.minusDays(7).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 2:
			  mettingStat = repository.getAllhappendMeetingsForDate(Date.from(curDate.minusDays(35).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 3:
			  mettingStat = repository.getAllhappendMeetingsForDate(Date.from(curDate.minusDays(365).atStartOfDay(defaultZoneId).toInstant()), date);
			break;
		}
		return mettingStat;
	}
	//2 - Parents Statistics
	/**
	 * get data according to the chosen time
	 * * @param course
	 * @return the kid if found, or null
	 */
	@GetMapping("/GetParentsStat/{time}") 
	public int[] getParentsForDate(@PathVariable int time) {
		int[] parNum = new int[2];
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate curDate = LocalDate.now();
		Date date = new Date();
		switch(time) {
		  case 1:
			  parNum = repository.getNumOfActiveParentsForDate(Date.from(curDate.minusDays(7).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 2:
			  parNum = repository.getNumOfActiveParentsForDate(Date.from(curDate.minusDays(35).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 3:
			  parNum = repository.getNumOfActiveParentsForDate(Date.from(curDate.minusDays(365).atStartOfDay(defaultZoneId).toInstant()), date);
			break;
		}
		return parNum;
	}
	
	//3 - Kids statistics
	@GetMapping("/GetActiveKidsStat/{time}") 
	public int[] getKidsForDate(@PathVariable int time) {
		int[] kidNum = new int[2];
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate curDate = LocalDate.now();
		Date date = new Date();
		switch(time) {
		  case 1:
			  kidNum = repository.getNumOfActiveKidsForDate(Date.from(curDate.minusDays(7).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 2:
			  kidNum = repository.getNumOfActiveKidsForDate(Date.from(curDate.minusDays(35).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 3:
			  kidNum = repository.getNumOfActiveKidsForDate(Date.from(curDate.minusDays(365).atStartOfDay(defaultZoneId).toInstant()), date);
			break;
		}
		return kidNum;
	}
	
	//4 - Categories statistics
	
	@GetMapping("/GetCategoriesInfo/{time}") 
	public HashMap<String, Integer> getCategoriesPercentage(@PathVariable int time) {
		HashMap<String, Integer> categoriesKids = new HashMap<String, Integer>();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate curDate = LocalDate.now();
		Date date = new Date();
		switch(time) {
		  case 1:
			  categoriesKids = repository.getkidsNumInCategory(Date.from(curDate.minusDays(7).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 2:
			  categoriesKids = repository.getkidsNumInCategory(Date.from(curDate.minusDays(35).atStartOfDay(defaultZoneId).toInstant()), date);
		    break;
		  case 3:
			  categoriesKids = repository.getkidsNumInCategory(Date.from(curDate.minusDays(365).atStartOfDay(defaultZoneId).toInstant()), date);
			  System.out.println(categoriesKids.keySet().size());
			break;
		}
		return categoriesKids;
		
	}
	
	//5 - Annual trends
	
	@GetMapping("/GetCategoriesTrend/{time}") 
	public HashMap<String, HashMap<Integer, Integer>> getTrend(@PathVariable int time) {
		HashMap<String, HashMap<Integer, Integer>> trend = new HashMap<String, HashMap<Integer, Integer>>();
		switch(time) {
		  case 1:
			  trend = repository.getCategoryWeeklytrend();
		    break;
		  case 2:
			  trend = repository.getCategoryMonthllyYearlytrend(4);
		    break;
		  case 3:
			  trend = repository.getCategoryMonthllyYearlytrend(12);
			 break;
		}
		return trend;
	}
	
}
