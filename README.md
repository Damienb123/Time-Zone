# Time-Zone

## Table of Contents

1. [Overview](#Overview)
2. [Purpose and Functionality](#Purpose-and-Functionality)
   - TimeZone
3. [Usage Instructions](#Usage-Instructions)
4. [Compilation and Execution](#Compilation-an-Execution)
5. [Example Output](#Example-Output)

## Overview

This Java program leverages the ZonedDateTime, ZoneId, Instant, and DateTimeFormatter classes to parse and manipulate timestamps. It accepts user inputs for timestamps and time zones, converts the timestamp to the specified time zone, and prints the result in a detailed format, including the year, month, day, time, milliseconds, and nanoseconds.

## Purpose and Functionality

### TimeZone

The TimeZone class is the main class of the project. It handles user input, parses the timestamp, converts it to the specified time zone, and prints the converted time details.
```
import java.time.Instant;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeZone {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input timestamp from the user
            System.out.println("Enter a timestamp (e.g., 2024-04-09T06:45:30.00-06:00): ");
            String timestamp = scanner.nextLine();

            // Input the time zone from the user
            System.out.println("Enter time zone (e.g., America/New_York): ");
            String timezone = scanner.nextLine();

            try {
                // Define a custom formatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSXXX");

                // Parse the timestamp using the formatter
                Instant inst = Instant.from(formatter.parse(timestamp));

                ZoneId zoneId = ZoneId.of(timezone);
                ZonedDateTime zdt = inst.atZone(zoneId);

                int year = zdt.getYear();
                Month month = zdt.getMonth();
                int day = zdt.getDayOfMonth();
                int hour = zdt.getHour();
                int minute = zdt.getMinute();
                int second = zdt.getSecond();
                int millisecond = zdt.getNano() / 1_000_000; // Convert nanoseconds to milliseconds
                int nanosecond = zdt.getNano() % 1_000_000;  // Remaining nanoseconds

                // Printing out the input in a specific format
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
```

## Usage Instructions

1. **Compile the Java file:** Ensure you have the Java Development Kit (JDK) installed.
2. **Run the main method:** Execute the **TimeZone** class, which will prompt the user for input.

## Compilation and Execution

To compile and run the Java file, follow these steps:

1. **Compile the class:**
```
javac TimeZone.java
```

2. **Run the main method:**
```
java TimeZone
```

## Example Output

### Input Valid (part1)
```
Enter a timestamp (e.g., 2024-04-09T06:45:30.00-06:00): 2024-04-09T06:45:30.00-06:00
Enter time zone (e.g., America/New_York): America/New_York
```

### Output Valid (part1)
```
year=2024
month=APRIL
day=9
time=8:45:30.0 (0 nanoseconds)
```

If the user inputs an invalid timestamp or time zone, the program will catch the exception and print an error message.

### Input Invalid (part2)
```
Enter a timestamp (e.g., 2024-04-09T06:45:30.00-06:00): invalid-timestamp
Enter time zone (e.g., America/New_York): invalid-timezone
```

### Output Invalid (part2)
```
Error: Invalid timestamp or time zone.
java.time.format.DateTimeParseException: Text 'invalid-timestamp' could not be parsed at index 0
	at java.base/java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:2046)
	at java.base/java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1948)
	at java.base/java.time.Instant.from(Instant.java:378)
	at TimeZone.main(TimeZone.java:26)
```
