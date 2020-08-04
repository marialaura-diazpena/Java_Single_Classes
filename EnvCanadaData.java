import java.util.*;
import java.io.*;

/**
 * EnvCanadaData - Program to process an Environment Canada 
 * data file and calculate the average maximum 
 * 
 * @author Maria Laura Diaz Pena
 * @version March 22 2020
 */
// Standard import for the Scanner class

public class EnvCanadaData {
    public static void main (String [] args) throws IOException {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);
        System.out.print ("Enter the filename: ");
        String filename = in.nextLine();
        File file = new File(filename);
        Scanner inFile = new Scanner(file).useDelimiter (",");
        // Read and discard header line
        inFile.nextLine();

        int numMaxDays = 0; //
        double totalMaxTemp = 0; //
        double maxTemp; //

        int numMinDays = 0; //
        double totalMinTemp = 0; //
        double minTemp; //
        int year, month, day;
        // while more data
        while (inFile.hasNext())  {
            // discard 5 columns
            for (int i = 0; i < 5;i++) {
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
                maxTemp = Double.parseDouble (maxTempS);
                totalMaxTemp += maxTemp;
                numMaxDays++;

            }

            inFile.next();
            //read minimum temperature
            String minTempS = inFile.next();

            //check to see if a minimum temperature was present
            if (!minTempS.equals("")) {
                //temperature present convert to double
                minTemp = Double.parseDouble (minTempS);
                totalMinTemp += minTemp;
                numMinDays++;
            }

            inFile.nextLine();
        } // loop to process all temperatures
        inFile.close();
        double averageMax = totalMaxTemp/numMaxDays;
        double averageMin = totalMinTemp/numMinDays;
        System.out.printf ("# of days reported = %d%n", numMaxDays);
        System.out.printf ("The average maximum temperature is %.2f%n", averageMax);
        System.out.printf ("# of days reported = %d%n", numMinDays);
        System.out.printf ("The average minimum temperature is %.2f%n", averageMin);
    }
}
