/*
 * The ability to parse the input of a timestamp according
 * to the custom format specified. Providing more 
 * flexibility in handling different timestamp formats.
 * 
 * This program is interactive allowing the user to input
 * desired timestamps and timezones. Handles potential errors
 * by catching exceptions and printing an error message.
 * */

//import permissions using the ZonedDateTime
import java.time.Instant;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//class creation for TimeZone
public class TimeZone {
	
	public static void main(String []args) {
		
		
		

		try(Scanner scanner = new Scanner(System.in)){
		
		//input timestamp from the user
		System.out.println("Enter a timestamp (e.g., 2024-04-09T06:45:30.00-06:00): ");
		String timestamp = scanner.nextLine();
		
		//input the time zone from the user
		System.out.println("Enter time zone (e.g., America/New_York): ");
		String timezone = scanner.nextLine();
		
		
		try {
			
			//define a custom formatter
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSXXX");
			
			//parse the timestamp using the formatter
			Instant inst = Instant.from(formatter.parse(timestamp));
			
			ZoneId zoneId = ZoneId.of(timezone);
			ZonedDateTime zdt = inst.atZone(zoneId);
			
			int year = zdt.getYear();
			Month month = zdt.getMonth();
			int day = zdt.getDayOfMonth();
			int hour = zdt.getHour();
			int minute = zdt.getMinute();
			int second = zdt.getSecond();
			int millisecond = zdt.getNano() / 1_000_000; //convert nanoseconds to milliseconds
			int nanosecond = zdt.getNano() % 1_000_000; //remaining nanoseconds
			
			//printing out the input in a specific format
			System.out.println("year=" + year);
            System.out.println("month=" + month);
            System.out.println("day=" + day);
            System.out.println("time=" + hour + ":" + minute + ":" + second + "." + millisecond + " (" + nanosecond + " nanoseconds)");
		} catch (Exception e) {
			System.out.println("Error: Invalid timestamp or time zone.");
			e.printStackTrace();
			
			}
		}
	}
}