package Lab6;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Lab6Solution.CTARoute;
import Lab6Solution.CTAStation;

import java.util.ArrayList;

public class CTAStopApp {
	
	//Parses the user input for an integer and returns an error if one isn't found.
    public static int intPrompt(Scanner input, String prompt) {
        int result = 0;
        String entered = "";
        boolean done = false;
         
        do {
            try {
                System.out.print(prompt);
                entered = input.nextLine();
                result = Integer.parseInt(entered);
                 
                done = true;
            } catch (Exception e) {
                System.out.println("'" + entered + "' is not an integer. You must enter a whole number.");
            }
        } while (!done);
         
        return result;
    }
    
   //Parses the user input for an integer and returns an error if one isn't found. Also checks that the integer is within a range of integers.
    public static int intPrompt(Scanner input, String prompt, int min, int max) {
        int result = 0;
        String entered = "";
        boolean done = false;
         
        do {
            try {
                System.out.print(prompt);
                entered = input.nextLine();
                result = Integer.parseInt(entered);
                 
                if (result>=min && result<=max) {
                    done = true;
                } else {
                    System.out.println(result + " needs to be between " + min + " and " + max + ". Please try again.");
                }
                 
            } catch (Exception e) {
                System.out.println("'" + entered + "' is not an integer. You must enter a whole number.");
            }
        } while (!done);
         
        return result;
    }
    
  //Parses the user input for a double and returns an error if one isn't found.
    public static double doublePrompt(Scanner input, String prompt) {
        double result = 0.0;
        String entered = "";
        boolean done = false;
         
        do {
            try {
                System.out.print(prompt);
                entered = input.nextLine();
                result = Double.parseDouble(entered);
                 
                done = true;
            } catch (Exception e) {
                System.out.println("'" + entered + "' is not an double. You must enter a real number.");
            }
        } while (!done);
         
        return result;
    }
    
    //Parses the user input for a double and returns an error if one isn't found. Also checks that the double is within a range of doubles.
    public static double doublePrompt(Scanner input, String prompt, double min, double max) {
        double result = 0;
        String entered = "";
        boolean done = false;
         
        do {
            try {
                System.out.print(prompt);
                entered = input.nextLine();
                result = Double.parseDouble(entered);
                 
                if (result>=min && result<=max) {
                    done = true;
                } else {
                    System.out.println(result + " needs to be between " + min + " and " + max + ". Please try again.");
                }
                 
            } catch (Exception e) {
                System.out.println("'" + entered + "' is not an double. You must enter a real number.");
            }
        } while (!done);
         
        return result;
    }
    
    //Makes sure the user's input is a yes or a no.
    public static boolean yesNoPrompt(Scanner input, String prompt) {
        boolean result = false;
        String entered = "";
        boolean done = false;
         
        do {
            System.out.print(prompt);
            entered = input.nextLine();
             
            if (entered.equalsIgnoreCase("y")||entered.equalsIgnoreCase("yes")) {
                result = true;
                done = true;
            } else if (entered.equalsIgnoreCase("n")||entered.equalsIgnoreCase("no")) {
                result = false;
                done = true;
            } else {
                System.out.println("'" + entered + "' is not a yes or no. You must enter y (yes) or n (no).");
            }
             
        } while (!done);
         
        return result;
    }
    
    //Makes sure that both of the user's inputs are a yes or a no.
    public static boolean yesNoPromptConfirm(Scanner input, String prompt, String secondaryPrompt) {
        boolean result = false;
        String entered = "";
        boolean done = false;
         
        do {
            System.out.print(prompt);
            entered = input.nextLine();
             
            if (entered.equalsIgnoreCase("y")||entered.equalsIgnoreCase("yes")) {
                result = true;
                done = confirmInput(input, secondaryPrompt);
            } else if (entered.equalsIgnoreCase("n")||entered.equalsIgnoreCase("no")) {
                result = false;
                done = true;
            } else {
                System.out.println("'" + entered + "' is not a yes or no. You must enter y (yes) or n (no).");
            }
             
        } while (!done);
         
        return result;
    }
    
    //Confirmation that user provided their desired answer.
    public static boolean confirmInput(Scanner input, String prompt) {
        return yesNoPrompt(input, prompt);
    }
    
    //Reads and stores the file
    public static CTARoute[] read(String filename) throws IOException{
        File file = new File("src/Lab6/CTAstops.csv");  //Reads file.
        Scanner input = new Scanner(file);
        
        //Make CTARoutes for each CTA line.
        String[] header = input.nextLine().split(","); //Create string array of first line split along commas.
        int numRoutes = header.length - 5;  //Create integer variable that is the five less than the length of the array, which how many CTA lines there are.
        CTARoute[] routes = new CTARoute[numRoutes];  //Create array of CTARoutes.
        for (int i=0; i<numRoutes; i++) {  //For each CTA line,
            routes[i] = new CTARoute(header[i+5]);  //create a CTARoute.
        }
        
        //Create ArrayList to hold CTAStations.
        ArrayList<CTAStation> rawStops = new ArrayList<CTAStation>();
 
        while(input.hasNextLine()) {  //Loop as long as there is something more in the file.
            String[] data = input.nextLine().split(",");  //Create string array split along commas.
            CTAStation station = new CTAStation();  //Create CTAStation.
            station.setName(data[0]);  //Set the name of the station based on what is in the first column of the file.
            station.setLatitude(Double.parseDouble(data[1]));  //Set the latitude of the station based on what is in the second column of the file.
            station.setLongitude(Double.parseDouble(data[2]));  //Set the longitude of the station based on what is in the third column of the file.
            station.setLocation(data[3]);  //Set the location of the station based on what is in the fourth column of the file.
            station.setWheelchair(Boolean.parseBoolean(data[4]));  //Set whether the station has wheelchair access based on what is in the fifth column of the file.
             
            //Create an integer array with a length of the number of CTARoutes.
            int[] i = new int[numRoutes];
            
            for (int j=0; j<i.length; j++) {  //For as many times as there are routes, 
                i[j] = Integer.parseInt(data[j+5]);  //assign each array slot the integer that is in the CTA line columns of the file.
            }
             
            station.setIndex(i);  //Make the above integer array the index of the station.
             
            rawStops.add(station);  //Add the station to the ArrayList of CTARoutes.
        }
        
        //Add stations to their routes.
        for (CTAStation station : rawStops) {  //Loop through all the stations in the ArrayList.
            int[] pos = station.getIndex();  //Create integer array to hold the values of the index of each station.
            for (int i=0; i<pos.length; i++) {
                if (pos[i] != -1) {  //If the value in the index is positive,
                    boolean added = false;  //create false boolean variable.
                    for (int j=0; j<routes[i].size(); j++) {  //For each CTARoute,
                        if (pos[i] < routes[i].get(j).getIndex()[i]) {  //If the value in the index is less than the value in the route's index,
                            routes[i].insertStation(j, station); //add the station to the route at that index.
                            added = true;  //Set the added variable to true.
                            break;
                        }
                    }
                     
                    if (!added) {  //If the value in the index is greater than the value in the route's index,
                        routes[i].addStation(station);  //add the station to end of the route.
                    }
                }
            }
        }
         
        input.close();
         
        return routes;
    }
    
    //Method to display all station names.
    public static void displayStationNames(CTARoute[] routes) {
        System.out.println();
        for (int i=0; i<routes.length; i++) {  //Loop over all routes.
            System.out.println(routes[i].toString());  //Print the stations.
        }
        System.out.println();
    }
    
    //Method to display whether stations have wheelchair access.
    public static void displaybyWheelchair(CTARoute[] routes, Scanner input) {
        System.out.println();
         
        boolean answer = yesNoPrompt(input, "Do you want wheelchair access? (y/n): ");  //Stores user's answer as a boolean.
        System.out.println();
        for (CTARoute route : routes) {
            int count = 0;
            System.out.println(route.getName());
            ArrayList<CTAStation> stations = route.getStops();  //Create CTAStation ArrayList of stations in the routes.
            for (CTAStation station : stations) {  //Loop over all the stations.
                if (station.isWheelchair()==answer) {  //If the user's answer corresponds with the station's value for wheelchair access,
                    System.out.println(" " + station.getName());  //print the name of the station.
                    count++;
                }
            }
             
            if (count == 0) {
                System.out.println(" No stations with" + ((!answer)?"out":"") + " wheelchair access");
            }
             
            System.out.println();
        }
    }
    
    //Method to find the nearest station.
    public static void nearestStation(CTARoute[] routes, Scanner input) {
        System.out.println();
         
        double latitude = doublePrompt(input, "Please enter your latitude: ", -90, 90);
        double longitude = doublePrompt(input, "Please enter your longitude: ", -180, 180);
         
        CTAStation nearest = routes[0].nearestStation(latitude, longitude);  //Make the first station the nearest one.
        
        for (int i=1; i<routes.length; i++) {  //Loop over all the routes.
            CTAStation other = routes[i].nearestStation(latitude, longitude);  //Create station with user's location.
            if (nearest.calcDistance(latitude, longitude)>other.calcDistance(latitude, longitude)){  //If the station is closer to the user's than the first station,
                nearest = other;  //make it the nearest one.
            }
        }
         
        System.out.println("The closest station is " + nearest.toString());
         
        System.out.println();
    }
    
    //Method to search for a station by name.
    public static void searchStation(CTARoute[] routes, Scanner input) {
        System.out.println();
         
        System.out.print("What is the name of the station you wish to search for? ");
        String name = input.nextLine();
         
        CTAStation search = null;  //Create empty station.
        for (CTARoute route : routes) {  //Loop over routes.
            CTAStation found = route.lookupStation(name);  //Create station with information from station matching user input.
            if (found!=null) {  //If such a station was created, 
                search = found;  //assign that station to the empty one.
            }
        }
         
        if (search == null) {  //If such a station was not created, 
            System.out.println("No station found called " + name);  //print message saying no station can be found.
        } else {
            System.out.println(search.toStringFull());  //Print the of the information of the station that was found.
        }
         
        System.out.println();
    }
    
    //Method to display station information.
    public static void displayStationInformation(CTARoute[] routes) {
        System.out.println();
        for (CTARoute route : routes) {  //Loop over stations.
            System.out.println(route.getName());  //Print out the name of the route.
            ArrayList<CTAStation> stops = route.getStops();  //Create CTAStation ArrayList with the stations on the routes.
            for (CTAStation stop : stops) {  //Loop over the stations.
                System.out.println(" " + stop.toStringFull());  //Print the information for the stations.
            }
            System.out.println();
        }
    }
    
    //Method to add station.
    public static CTARoute[] addStation(CTARoute[] routes, Scanner input) {
        System.out.println();
         
        CTAStation add = new CTAStation();
         
        System.out.print("Enter the name of the new station: ");
        String name = input.nextLine();
        add.setName(name);
         
        System.out.print("Enter the location of " + name + ": ");
        add.setLocation(input.nextLine());
         
        add.setWheelchair(yesNoPrompt(input, "Does " + name + " have wheelchair access? (y/n): "));
         
        add.setLatitude(doublePrompt(input, "What is the latitude of " + name + "? ", -90, 90));
        add.setLongitude(doublePrompt(input, "What is the longitude of " + name + "? ", -90, 90));
         
        for (CTARoute route : routes) {
            if (yesNoPrompt(input, "Is " + name + " on the " + route.getName() + " line? (y/n): ")) {  //Find route the new station is on.
                System.out.print("What is the name of the previous station? (Enter 'None' if there is no station): ");
                String prevName = input.nextLine();
                if (prevName.equalsIgnoreCase("none")) {
                    route.insertStation(0, add);
                } else {
                    System.out.print("What is the name of the following station? (Enter 'None' if there is no station): ");
                    String followName = input.nextLine();
                     
                    int index = route.findStation(prevName);  //Find the index of the previous station.
                    if (index == -1) {  //If the previous station is not on the line.
                        if (followName.equalsIgnoreCase("none")) {  //If there is no station after,
                            route.addStation(add);  //add the station to the end.
                        } else {
                            index = route.findStation(followName);
                            if (index == -1) {  //If the following station is not on the line, neither adjacent stations could be found.
                                System.out.println("Neither " + prevName + " nor " + followName + " where found. Moving on...");
                            } else {
                                route.insertStation(index, add);  //Insert the station with an index of -1.
                            }
                        }
                    } else {
                        route.insertStation(index + 1, add);  //Insert the new station one after the previous station.
                    }
                     
                }
            }
        }
         
        System.out.println();
         
        return routes;
    }
    
    //Method to delete station.
    public static CTARoute[] deleteStation(CTARoute[] routes, Scanner input) {
        System.out.println();
         
        System.out.print("What is the name of the station you want to delete? ");
        String name = input.nextLine();
        System.out.println();
        boolean deleted = false;
         
        for (CTARoute route : routes) {  //Loop over routes.
            CTAStation station = route.lookupStation(name);  //Find station with the name of the user input.
            if (station != null && yesNoPromptConfirm(input,  //Make sure the user wants to remove the station.
                    "Do you want to remove " + name + " from the " + route.getName() + " line? (y/n): ",
                    "Are you sure you want to delete " + name + " from the " + route.getName() + " line? (y/n): ")) {
                route.removeStation(station);
                deleted = true;
                System.out.println();
            } else {
                System.out.println();
            }
        }
         
        if (!deleted) {
            System.out.println("No station called '" + name + "' was found.");
        }
         
        return routes;
    }
    
    //Print the user menu.
    public static void printMenu() {
        System.out.println("1. Display the names of all stations");
        System.out.println("2. Display the stations with wheelchair access");
        System.out.println("3. Display the nearest station to a location");
        System.out.println("4. Display information for a station with a specific name");
        System.out.println("5. Display information for all stations");
        System.out.println("6. Add a new station");
        System.out.println("7. Delete an existing station");
        System.out.println("8. Exit");
    }
    
    //Do what the user selected from the menu.
    public static void menu(CTARoute[] routes) {
        Scanner input = new Scanner(System.in);
         
        boolean done = false;
         
        do {
             
            printMenu();
             
            int choice = intPrompt(input, "Enter you choice: ", 1, 8);
             
            switch(choice) {
                case 1:
                    displayStationNames(routes);
                    break;
                case 2:
                    displaybyWheelchair(routes, input);
                    break;
                case 3:
                    //find nearest
                    nearestStation(routes, input);
                    break;
                case 4:
                    //search
                    searchStation(routes, input);
                    break;
                case 5:
                    //all information
                    displayStationInformation(routes);
                    break;
                case 6:
                    //add
                    routes = addStation(routes, input);
                    break;
                case 7:
                    //delete
                    routes = deleteStation(routes, input);
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    done = true;
                    break;
                default:
                    System.out.println("I didn't understand that.");
            }
             
        } while (!done);
         
        input.close();
    }
    
    public static void main(String[] args) {
        
        CTARoute[] routes;
         
        try {
            routes = read("CTAStops.csv");
             
            menu(routes);
             
        } catch(IOException e) {
            System.out.println("File Not Found");
        }
         
    }
 
}