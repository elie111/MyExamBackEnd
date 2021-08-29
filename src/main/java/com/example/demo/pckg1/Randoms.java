package com.example.demo.pckg1;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Randoms {

	private static Random rand = new Random();
	private static String[] firstNames = {"Dina", "Tasneem", "Walaa", "Guy", "Oliver", "Jay", "Yuval", "Eli", "Nastia", "Asad", "Abed", "sheli", "Maya", "Or"};
	private static String[] lastNames = {"Cohen", "Levi", "Biton", "Dahan", "Amar", "Amer", "Katz", "Friedman", "Kaya", "Demir", "Mendis", "Bandara", "Santos"};
	private static String[] category = {"Sport", "Space", "Art", "Math", "Nature"};
	static ZoneId defaultZoneId = ZoneId.systemDefault();
	static LocalDate curDate = LocalDate.now();
	public static List<Parent> parents = new ArrayList<Parent>();
	private static List<Kid> kids = new ArrayList<Kid>();
	public static List<Category> cats = new ArrayList<Category>();
	public static List<Course> courses = new ArrayList<Course>();
	public static List<Meeting> meetings = new ArrayList<Meeting>();
	
	public static List<Category> getCats() {
		return cats;
	}

	public static List<Parent> getParents() {
		return parents;
	}

	public static List<Kid> getKids() {
		return kids;
	}

	public static List<Course> getCourses() {
		return courses;
	}

	public static List<Meeting> getMeetings() {
		return meetings;
	}

	public static List<Parent> createRandomParents() {
		int parNum = 50;
		for(int i = 0; i < parNum; i++) {
			Parent p = new Parent(firstNames[rand.nextInt(firstNames.length)] + " " + lastNames[rand.nextInt(lastNames.length)], phoneNumber(), generateRandomWord() + "@gmail.com", generateRandomPassword());
			p.setId("id" + i);
			p.setActiveDate(new Date(ThreadLocalRandom.current()
                    .nextLong(Date.from(curDate.minusDays(400).atStartOfDay(defaultZoneId).toInstant()).getTime(), new Date().getTime())));
			if (i % (5 + rand.nextInt(15)) == 0) {
				p.setActive(Status.InActive);
			}
			parents.add(p);
		}
		return parents;
	}
	
	public static List<Kid> createRandomkids() {
		int kidNum = 80;
		for(int i = 0; i < kidNum; i++) {
	        Date date = new Date();
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);

	        // Perform subtraction of 12 years
	        c.add(Calendar.YEAR, -12);

	        // Convert calendar back to Date
	        Date currentDateminus12 = c.getTime();

			Date birthDate = new Date(ThreadLocalRandom.current()
                    .nextLong(currentDateminus12.getTime(), new Date().getTime()));
			Kid k = new Kid(firstNames[rand.nextInt(firstNames.length)] + " " + lastNames[rand.nextInt(lastNames.length)], birthDate, Gender.values()[rand.nextInt(Gender.values().length)]);
			k.setActiveDate(new Date(ThreadLocalRandom.current()
                    .nextLong(Date.from(curDate.minusDays(400).atStartOfDay(defaultZoneId).toInstant()).getTime(), new Date().getTime())));
			k.setId("id" + i);
			kids.add(k);
		}
		return kids;
	}
	public static List<Category> createRandomCategories() {
		for(int i = 0; i < category.length; i++) {
			Category c = new Category(category[i], "");
			c.setId("id" + i);
			cats.add(c);
		}
		
		
		return cats;
	}
	
	public static List<Course> createRandomCourses() {
		for(int i = 0; i < 100; i++) {
			Date startDate = new Date(ThreadLocalRandom.current()
                    .nextLong(Date.from(curDate.minusDays(400).atStartOfDay(defaultZoneId).toInstant()).getTime(), new Date().getTime()));
			Date finishDate = new Date(ThreadLocalRandom.current()
                    .nextLong(startDate.getTime(), new Date().getTime()));
			String randomCatId = cats.get(rand.nextInt(cats.size())).getId();
	        Course c = new Course(generateRandomWord(), startDate, finishDate, Day.values()[rand.nextInt(Day.values().length)], randomCatId);
	        c.setID("id" + i);
	        c.setKidsIDs(signRandomKidsToCourses(rand.nextInt(10)));
	        courses.add(c);
	        
		}
		return courses;
	}
	
	public static List<Meeting> createRandomMeetings() {
		for(int i = 0; i < 150; i++) {
			Date meetDate = new Date(ThreadLocalRandom.current()
                    .nextLong(Date.from(curDate.minusDays(400).atStartOfDay(defaultZoneId).toInstant()).getTime(), new Date().getTime()));
			Meeting meet = new Meeting(courses.get(rand.nextInt(courses.size())).getID(), meetDate);
			meet.setId("id" + i);
			if (i % (5 + rand.nextInt(15)) == 0) {
				meet.setCancelled(true);
			}
			meetings.add(meet);
		}
		return meetings;
	}
	
	public static ArrayList<String> signRandomKidsToCourses(int length) {
		ArrayList<String> kidIds = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			kidIds.add(kids.get(rand.nextInt(kids.size())).getId());
		}
		return kidIds;
	}
	
	public static String phoneNumber(){
	    int area = rand.nextInt(900)+100;
	    int mid = rand.nextInt(643)+100;
	    int last = rand.nextInt(9000)+1000;
	 
	    return (area+"-"+mid+"-"+last);
	  }
	
	public static String generateRandomWord() {
		// create a string of all characters
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // specify length of random string
	    int length = rand.nextInt(7);

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = rand.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }
	    String randomString = sb.toString();
	    return randomString;
	}
	
	public static String generateRandomPassword()
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < rand.nextInt(15); i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }


}

