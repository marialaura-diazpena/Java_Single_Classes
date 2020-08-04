
import java.util.*;
import java.io.*;
/**
 * Assignment3 - Environment Canada keeps daily records for all major cities 
 * in Canada. This data includes: date, maximum temperature and minimum 
 * temperature. Your program will allow the user to define the temperature 
 * ranges for the counters. The user will be able to input the number of 
 * ranges as well as the low (inclusive) and high (exclusive) values for each 
 * range.
 * 
 * @author Maria Laura Diaz Pena 
 * @version 4/2/2020
 */
public class EnvCanadaRanges {
    public static void main (String [] args) throws IOException 
    {
        Scanner k = new Scanner (System.in);
        //defining my variables for maximum temperature
        double maxTemp = 0;
        double totalMaxTemp = 0;
        double maxTempYear = 0;
        int numMaxDays = 0;
        int ddHot = 0;
        int mmHot = 0;
        int yyHot = 0;
        //defining my variables for minimum temperature
        double minTemp = 0;
        double totalMinTemp = 0;
        double minTempYear = 0;
        int numMinDays = 0;
        int ddCold = 0;
        int mmCold = 0;
        int yyCold = 0;
        
        int year = 0;
        int month = 0;
        int day = 0;

        System.out.print("Enter data filename: ");
        String filename = k.nextLine();
        File file = new File(filename);

        Scanner inFile = new Scanner(file).useDelimiter(",");
        //creating the array for the temperature ranges
        System.out.print("How many temperature ranges do you want? ");
        int ranges = k.nextInt();
        int[] rangesArray = new int[ranges + 1];

        System.out.println("Establishing bounds enter values for ranges.");
        rangesArray[0]= -999;
        rangesArray [rangesArray.length - 1] = 999;
        //creating the minimum and maximum temperature of days array
        int[] daysMaxA = new int [rangesArray.length];
        int[] daysMinA = new int [rangesArray.length];
        //prompting the user to input the ranges and storing the values in the array
        int i = 1;
        System.out.printf("%d. %d up to: ", i, rangesArray[0]);
        rangesArray[i] = k.nextInt();
        do {
            System.out.printf("%d. %d up to: ", i, rangesArray[i]);
            i++;
            rangesArray[i] = k.nextInt();
        } while (i < rangesArray.length - 2);    

        System.out.println("");
        //printing the ranges table
        System.out.println("Here are the ranges");
        System.out.println("     #         Low       High");
        String divider = "=============================";
        System.out.println(divider);
        //printing the ranges and the values
        i = 1;
        System.out.printf("%7d %10d %10d \n", i, rangesArray[0], rangesArray[i]);
        do {
            if (i == rangesArray.length - 1) { 
                i++;
                System.out.printf("%7d %10d %10d \n", i, rangesArray[i-1], rangesArray [rangesArray.length - 1]);
            }
            else 
            {    
                i++;
                System.out.printf("%7d %10d %10d \n", i, rangesArray[i-1], rangesArray[i]);
            }    
        } while (i<rangesArray.length - 1);

        System.out.println("");
        
        inFile.nextLine();
        while (inFile.hasNext()) {
            // discard 5 columns
            for (i = 0; i < 5; i++) {
                inFile.next();
            }
            // read year, month, day
            year = inFile.nextInt();
            month = inFile.nextInt();
            day = inFile.nextInt();
            // discard data qualifier
            inFile.next();
            // read maximum temperature
            String maxTempS = inFile.next();

            // Check to see if a maximum temperature was present
            if (!maxTempS.equals("")) {
                // temperature present convert to double
                maxTemp = Double.parseDouble(maxTempS);
                //variables that set the condition for maximum temperature
                boolean isHotDay = rangesArray[rangesArray.length - 1] > maxTemp && maxTemp > rangesArray[rangesArray.length - 2];
                boolean higherThanHottestDay = maxTemp > maxTempYear;
                totalMaxTemp += maxTemp;
                numMaxDays++;

                // storing highest temperature data
                if (isHotDay && higherThanHottestDay) {
                    maxTempYear = maxTemp;
                    ddHot = day;
                    mmHot = month;
                    yyHot = year;
                }
                //storing and counting the days in maximum temperature ranges
                for (i = 0; i < rangesArray.length - 1; i++) {
                    if (maxTemp >= rangesArray[i] && maxTemp < rangesArray[i + 1]) {
                        daysMaxA[i]++;
                    }
                }
            }

            inFile.next();
            //read minimum temperature
            String minTempS = inFile.next();

            //check to see if a minimum temperature was present
            if (!minTempS.equals("")) {
                //temperature present convert to double
                minTemp = Double.parseDouble (minTempS);
                //variables that set condition for the minimum temperature
                boolean isColdDay = rangesArray[0] < minTemp && minTemp < rangesArray[1];
                boolean isColderThanColdestDay = minTemp < minTempYear;
                totalMinTemp += minTemp;
                numMinDays++;
                //storing lowest temperature data
                if (isColdDay && isColderThanColdestDay){
                    minTempYear = minTemp;
                    mmCold = month;
                    ddCold = day;
                    yyCold = year;
                }
                //counting and storing the days in minimum temperature ranges
                for (i = 0; i < rangesArray.length - 1; i++) {
                    if (minTemp >= rangesArray[i] && minTemp < rangesArray[i + 1]) {
                        daysMinA[i]++;
                    }
                }
            }

            inFile.nextLine();
        } // loop to process all temperatures

        System.out.println("Temperature report for 2019");
        System.out.printf("Hottest day was (mm/dd/yyyy) %d/%d/%d, highest temperature was %.1f \n",  mmHot, ddHot, yyHot, maxTempYear);
        System.out.printf("Coldest day was (mm/dd/yyyy) %d/%d/%d, lowest temperature was %.1f \n", mmCold, ddCold, yyCold, minTempYear);

        System.out.println("");

        System.out.println("Minimum Temperature Statistics");
        System.out.printf("%15s %15s %10s \n %15s %15s \n", "Min value", "Max value", "# of days", "(Inclusive)", "(Exclusive)");
        //loop for printing the days in the ranges
        for (i = 0; i < rangesArray.length - 1; i++) {
            System.out.printf("%15d %15d %10d\n", rangesArray[i], rangesArray[i + 1], daysMinA[i]);
        }

        System.out.printf("%42d \n", numMinDays);
        //print the max Temperature statistics
        System.out.println("Maximum Temperature Statistics");
        System.out.printf("%15s %15s %10s \n %15s %15s \n", "Min value", "Max value", "# of days", "(Inclusive)", "(Exclusive)");
        //loop for printing the days in the ranges
        for (i = 0; i < rangesArray.length - 1; i++) {
            System.out.printf("%15d %15d %10d\n", rangesArray[i], rangesArray[i + 1], daysMaxA[i]);
        }

        System.out.printf("%42d \n", numMaxDays);
        //defining and printing the average max & min Temperature
        double averageMax = totalMaxTemp/numMaxDays;
        double averageMin = totalMinTemp/numMinDays;
        System.out.println("");
        System.out.printf("Average Maximum was %.2f based on %d days of data \n", averageMax, numMaxDays);
        System.out.printf("Average Minimum was %.2f based on %d days of data \n", averageMin, numMinDays);
    }
}
