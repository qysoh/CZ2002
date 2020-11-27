package assignment;

import java.text.*;
import java.util.*;

/**
 * Manages the conversion of date and time to string and vice versa using the Calendar object, 
 * and ensures that user input for date and time are in the correct format.
 * @version 1.0
 * @since 2020-11-24
 */
public class CalendarMgr{
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    static SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm");
    
    /**
     * Converts date and time to string
     * @param cal Calendar
     * @return String as dd/MM/yyyy hh:mm 
     */
    //convert date and time to string
    public static String dateTimeStr(Calendar cal){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return String.format("%02d/%02d/%4d %02d:%02d", day, month + 1, year, hour, min);
    }
    
    /**
     * Converts time to string
     * @param cal Calendar
     * @return String as hh:mm 
     */
    //convert time to string
    public static String timeStr(Calendar cal){
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return String.format("%02d:%02d", hour, min);
    }
    
    /**
     * Converts string to date and time
     * @param s String
     * @return Date and Time in dd/MM/yyyy hh:mm format
     * @throws ParseException If string is not in right format
     */
    //convert string to date and time
    public static Calendar strDateTime(String s) throws ParseException{
        Date date = dateFormat.parse(s);
        Calendar dateTime = Calendar.getInstance();
        dateTime.setTime(date);
        return dateTime;
    }
    
    /**
     * Converts string to time
     * @param s String
     * @return Time in dd/MM/yyyy hh:mm format
     * @throws ParseException If string is not in right format
     */
    //convert string to time
    public static Calendar strTime(String s) throws ParseException {
    	Date time = dateFormat1.parse(s);    
    	Calendar dateTime = Calendar.getInstance();
    	dateTime.setTime(time);
    	return dateTime;
    }
    
    /**
     * Prompts user to give date and time in correct format
     * @param md String
     * @return Valid date time
     * @exception ParseException If string input is not in correct format
     */
    //check if format for date and time is correct
    public static Calendar correctDateTime(String md){
        String date;
        Date parsedDate; 
        boolean isValid = false;
            Calendar newDate = Calendar.getInstance();
            do{
                System.out.print("Enter " + md + " (dd/MM/yyyy hh:mm): ");
                date = sc.nextLine();
                dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                try{
                    parsedDate = dateFormat.parse(date);
                } catch (ParseException e){
                    System.out.println("Date is in incorrect format! Please try again.");
                    continue;
                }
                newDate.setTime(parsedDate);
                isValid = true;
            } while(!isValid);
            return newDate;
    }
    /**
     * Prompts user to give time in correct format
     * @param md String
     * @return Valid time
     * @exception ParseException If string input is not in correct format
     */
  //check if format for time is correct
    public static Calendar correctTime(String md){
        String time;
        Date parsedTime; 
        boolean isValid = false;
            Calendar newTime = Calendar.getInstance();
            do{
                System.out.print("Enter " + md + " (hh:mm): ");
                time = sc.nextLine();
                dateFormat1 = new SimpleDateFormat("hh:mm");
                try{
                    parsedTime = dateFormat1.parse(time);
                } catch (ParseException e){
                    System.out.println("Time is in incorrect format! Please try again.");
                    continue;
                }
                newTime.setTime(parsedTime);
                isValid = true;
            } while(!isValid);
            return newTime;
    }
    
}