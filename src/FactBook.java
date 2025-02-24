//
// Project: FactBook
// TODO 1
// Author: [Natalia Rodriguez]
//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FactBook {

    // TODO 2 Define Data Size
    //Comment: Define Arrays to Store Data
    //number of countries (rows) in input file
    static final int COUNTRY_COUNT = 263;
    // number of attributes (columns) in the input file
    static final int ATTRIBUTE_COUNT = 44;
    // TODO 3
    //Comment: Define Arrays to Store Data
    // list of country names
    static String[] countries = new String[COUNTRY_COUNT];
    // list of country attributes
    static double[][] attributes = new double[COUNTRY_COUNT][ATTRIBUTE_COUNT];

    static int AREA_COLUMN_INDEX = 0;
    static int UNEMPLOYMENT_COLUMN_INDEX = 43;
    static int HIV_AIDS_DEATHS_COLUMN_INDEX = 12;
    // TODO 4

    //Load data into the arrays: countries, attributes
    // from local copy of factbook.csv
    // ignore the first 2 lines in factbook.csv
    public static void loadData() throws FileNotFoundException {
        int row = 0;
        Scanner scnr = new Scanner(new File("C:\\Users\\em1li\\Downloads\\factbook.csv"));
        scnr.nextLine(); // ROW 0
        scnr.nextLine(); // ROW 1
        while (scnr.hasNextLine() && row < COUNTRY_COUNT) {
            String line = scnr.nextLine();
            String[] splittedLine = line.split(";", ATTRIBUTE_COUNT+1);
            countries[row] = splittedLine[0];
            for (int col = 0; col < ATTRIBUTE_COUNT ; col++) {
                String currentData = splittedLine[col+1];
                try {
                    attributes[row][col] = Double.parseDouble(currentData);
                } catch(Exception error) {
                    attributes[row][col] = 0;
                }
            }
            row++;
        }

        scnr.close();
    }



    // TODO 5
    // Returns country with largest Area
    public static String largestCountry() {
        double maxArea = Double.MIN_VALUE;
        String countryWithMaxArea = "";

        for (int i = 0; i < COUNTRY_COUNT; i++) {
            double area = attributes[i][AREA_COLUMN_INDEX];

            if (area > maxArea) {
                maxArea = area;
                countryWithMaxArea = countries[i];
            }
        }

        return countryWithMaxArea;
    }


    // TODO 6
    // Returns number of countries reporting unemployment higher than rate
    public static int unemploymentCount(int rate) {
        int count = 0;
        for (int i = 0; i < COUNTRY_COUNT; i++) {
            if (attributes[i][UNEMPLOYMENT_COLUMN_INDEX] > rate) {
                count++;
            }
        }
        return count;
    }

    // TODO 7
    // Returns total HIV/AIDS deaths reported
    public static int hivDeaths() {
        double totalDeaths = 0;

        for (int i = 0; i < COUNTRY_COUNT; i++) {
            totalDeaths += attributes[i][HIV_AIDS_DEATHS_COLUMN_INDEX];
        }

        return (int) totalDeaths;
    }

    public static String CountryWithMostDeaths(){
        double MostDeaths = Double.MIN_VALUE;
        String CountryMostDeaths = "";

        for (int i = 0; i < COUNTRY_COUNT; i++) {
            double deaths = attributes[i][HIV_AIDS_DEATHS_COLUMN_INDEX];

            if (deaths > MostDeaths) {
                MostDeaths = deaths;
                CountryMostDeaths = countries[i];
            }
        }
        return CountryMostDeaths;
    }

    public static String SmallestCountry(){
        double SmallestArea = Double.MAX_VALUE;
        String CountryWithSmallestArea = "";

        for (int i = 0; i < COUNTRY_COUNT ; i++){
            double area = attributes[i][AREA_COLUMN_INDEX];

            if (area < SmallestArea) {
                SmallestArea = area;
                CountryWithSmallestArea = countries[i];
            }
        }
        return CountryWithSmallestArea;
    }

    public static void main(String[] args) throws FileNotFoundException {
        /* --- do not modify below --- */
        loadData();
        System.out.println("The largest country is "+largestCountry());
        System.out.println(unemploymentCount(50)+" countries have unemployment rate > 50%");
        System.out.println(unemploymentCount(40)+" countries have unemployment rate > 40%");
        System.out.println(unemploymentCount(30)+" countries have unemployment rate > 30%");
        System.out.println("Total HIV/AIDS deaths were "+hivDeaths());
        /* --- do not modify above --- */

        // TODO 8
        // make up your own interesting question and answer using the data
        System.out.println("What's the country with most HIV deaths?");
        System.out.println("The country with most deaths for HIV is " + CountryWithMostDeaths());


        // TODO 9
        System.out.println("What's the smallest country?");
        System.out.println("Smallest country is " + SmallestCountry());
    }
}

// TODO 10
/*

43
0
12
The largest country is Russia
5 countries have unemployment rate > 50%
13 countries have unemployment rate > 40%
23 countries have unemployment rate > 30%
Total HIV/AIDS deaths were 2835808
What's the country with most HIV deaths?
The country with most deaths for HIV is South Africa
What's the smallest country?
Smallest country is Bassas da India


*/
