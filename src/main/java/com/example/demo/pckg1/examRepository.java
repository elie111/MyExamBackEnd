package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Repository;

@Repository
public class examRepository {

	//1
	/**
	 * 
	 * @return All Meetings
	 */
	public List<Meeting> getAllMeetings(){
		return Randoms.getMeetings();
	}
	
	public int[] getAllhappendMeetingsForDate(Date startDate, Date endDate){
		int all = 1;
		int happend = 0;
		 for (Meeting m: getAllMeetings()) {
			 if (m.getMeetingDateTime().after(startDate) && m.getMeetingDateTime().before(endDate)) {
				 all++;
				 if (m.isCancelled() == false) {
					 happend++;
				 }
			 }
		 }
		 int[] toReturn = {happend, Math.round(100*happend/all)};
		 return toReturn;
	 }
	
	//2 
	public List <Parent> getAllActiveparents (){
		return Randoms.getParents();
	}
	

	 public int[] getNumOfActiveParentsForDate(Date startDate, Date endDate){
			int all = 1;
			int active = 0;
			 for (Parent p: getAllActiveparents()) {
				 if (p.getActiveDate().after(startDate) && p.getActiveDate().before(endDate)) {
					 all++;
					 if (p.getActive() == Status.Active) {
						 active++;
					 }
				 }
			 }
			 int[] toReturn = {active, Math.round(100*active/all)};
			 return toReturn;
	}
	 
	 //3
	 public List<Kid> retrieveAllKids(){
			return Randoms.getKids();
		}
	
		 public int[] getNumOfActiveKidsForDate(Date startDate, Date endDate){
			int all = 1;
			int active = 0;
				for (Kid k: retrieveAllKids()) {
				 if (k.getActiveDate().after(startDate) && k.getActiveDate().before(endDate)) {
					 all++;
					 if (k.getStatus() == Status.Active) {
						 active++;
					 }
				 }
			 }
			 int[] toReturn = {active, Math.round(100*active/all)};
			 return toReturn;
		}
		 
		 //4
		 public List<Category> getAllCategories(){
				return Randoms.getCats();
			}

			public HashMap<String, Integer> getkidsNumInCategory(Date startDate, Date endDate){
				HashMap<String, Integer> catKidsNum = new HashMap<String, Integer>();
				for (Category cat : getAllCategories()) {
					int count = 0;
					for (Course course : getCategoryCourses(cat.getId())) {
						if ((course.getStartDateTime().after(startDate) && course.getStartDateTime().before(endDate))|| (course.getFinishDateTime().after(startDate) && course.getFinishDateTime().before(endDate)) || (course.getStartDateTime().before(startDate) && course.getFinishDateTime().after(endDate))) {
							for (String kidId : course.getKidsIDs()) {
								if (getKidById(kidId).getStatus() == Status.Active) {
									count++;
								}
							}
						}
					}	
					catKidsNum.put(cat.getName(), count);
				}
				return catKidsNum;
			}
				
			public ArrayList<Course> getCategoryCourses(String categoryID) {
				ArrayList<Course> categoryCourses = new ArrayList<Course>();
				for (Course c : Randoms.getCourses())
					if (c.getCategoryId().equals(categoryID)) {
						categoryCourses.add(c);
					}
				return categoryCourses;
			}
			
			public Kid getKidById(String kidId) {
				for (Kid k : Randoms.getKids()) {
					if (k.getId().equals(kidId)) {
						return k;
					}
				}
				return null;
			}
			
			//5
			public HashMap<String, HashMap<Integer, Integer>> getCategoryWeeklytrend(){
				DateTime dateTime = new DateTime();
				HashMap<String, HashMap<Integer, Integer>> trend= new HashMap<String, HashMap<Integer, Integer>>();
				for (Category cat : getAllCategories()) {
					trend.put(cat.getName(), new HashMap<Integer, Integer>());
					for (int i = 0; i < 7; i ++) {
						//System.out.println("--------");
						int count = 0;
						DateTime day = dateTime.minusDays(i);
						//System.out.println( "todays is: " + day);
						for (Course course : getCategoryCourses(cat.getId())) {
							if (new DateTime(course.getStartDateTime()).equals(day)|| new DateTime(course.getFinishDateTime()).equals(day) || (course.getStartDateTime().before(day.toDate()) && course.getFinishDateTime().after(day.toDate()))) {
								//System.out.println(course.getName() + ": " + course.getStartDateTime() + course.getFinishDateTime());
								for (String kidId : course.getKidsIDs()) {
									if (getKidById(kidId).getStatus() == Status.Active) {
										count++;
									}
								}
							}
						}
						//System.out.println((7-i) + "count: " + count);
						//System.out.println("--------");
						trend.get(cat.getName()).put(7 - i, count);
					}
				}
			return trend;
			}
			
			public HashMap<String, HashMap<Integer, Integer>> getCategoryMonthllyYearlytrend(int periods){
				if (periods != 4 && periods != 12) {
					return null;
				}
				DateTime dateTime = new DateTime();
				DateTime lastDay = null;
				DateTime firstDay = null;
				HashMap<String, HashMap<Integer, Integer>> trend= new HashMap<String, HashMap<Integer, Integer>>();
				for (Category cat : getAllCategories()) {
					trend.put(cat.getName(), new HashMap<Integer, Integer>());
					for (int i = 0; i < periods; i ++) {
						//System.out.println("--------");
						int count = 0;
						if (periods == 4) {
							lastDay = dateTime.minusWeeks(i);
							firstDay = dateTime.minusWeeks(i + 1);
						}
						if (periods == 12) {
							lastDay = dateTime.minusMonths(i);
							firstDay = dateTime.minusMonths(i + 1);
						}
						Interval interval = new Interval(firstDay, lastDay);
						//System.out.println( "todays is: " + firstDay + " - " + lastDay);
						for (Course course : getCategoryCourses(cat.getId())) {
							if (interval.contains(new DateTime(course.getStartDateTime()))|| interval.contains(new DateTime(course.getFinishDateTime())) || new Interval(new DateTime(course.getStartDateTime()), new DateTime(course.getFinishDateTime())).contains(interval)) {
								//System.out.println(course.getName() + ": " + course.getStartDateTime() + course.getFinishDateTime());
								for (String kidId : course.getKidsIDs()) {
									if (getKidById(kidId).getStatus() == Status.Active) {
										count++;
									}
								}
							}
						}
						//System.out.println((7-i) + "count: " + count);
						//System.out.println("--------");
						trend.get(cat.getName()).put(periods - i, count);
					}
				}
			return trend;
			}
			
			
			
}
