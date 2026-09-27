/*--------------------------------------------
Program 5: MPLS Dog Management System

    Course: COMP 170, Fall 26
    System: Visual Studio Code, Windows 11
    Author: G. Bolles
*/

import java.util.Scanner; //Importing Scanner Class
public class DogManagement {
    /*
     * Global Declaration for parallel arrays and Scanner Object
     */
    //DECLARING PARALEL ARRAYS OUTSIDE OF MAIN METHOD TO HOLD DOG DATA use the static keyword
    //Hard code each parallel array to length 12
    //Dog ID = array position/index, no need to store as a separate array
    static String[] names= new String[12];
    static String[] breeds= new String[12];
    static int[] weights= new int[12];
    static int[] ages= new int[12];
    static int currentIndex=0; //running index within the arrays (current dog ID, in other words)
    static int sentinelVal=-1;
    //DECLARING SCANNER OBJECT
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        
        welcome();
        boolean exit = false;
        int userChoice=0;
        while(exit==false){
            userChoice = displayPrompt();
            if(userChoice==1){
                CreateRecord();
            }
            else if(userChoice==2){
            }
            else if(userChoice==3){
            }
            else if(userChoice==4||userChoice==sentinelVal){
                exit = true;
            }
            else{
                System.out.println("Invalid menu option; please try again.");
            }
        }
        

    }

    //Welcome method that outputs introductory text explaining program
    public static void welcome(){
        System.out.println("Welcome, this program allows for a care attendant to be able to create, retrieve and update a dog record from the system.");
    }

    //Method to display prompt and return integer values
    public static int displayPrompt(){
        //Local Variables
        int menuOption;

        System.out.println("\nSelect a menu option:");
        System.out.println("\t1) Create a dog record");
        System.out.println("\t2) Display dog record");
        System.out.println("\t3) Update dog record");
        System.out.println("\t4) Exit Program");
        
        System.out.print("Enter selection here --> ");
        //INPUT
        menuOption = Integer.parseInt(scn.nextLine());

        return menuOption;
    }

    public static void CreateRecord() {
       String prior = names[currentIndex];
       boolean create=true;
       if(String.equals(prior, "")==false){
            int check=PromptForOverride();
            if(check==0){
                System.out.println("Record creation cancelled.");
                create=false;
            }
            else{
                //all good
            }
       }
       if(create==true){
            String collectName = ValidateStringInput("Please enter the dog's name: ");
            if(String.parseInt(collectName)==sentinelVal){
                System.out.println("Record creation cancelled.");
                return;
            }
            String collectBreed = ValidateStringInput("Please enter the dog's breed: ");
            if(String.parseInt(collectBreed)==sentinelVal){
                System.out.println("Record creation cancelled.");
                return;
            }
            int collectWeight = ValidateIntInput("Please enter the dog's weight: ");
            if(collectWeight==sentinelVal){
                System.out.println("Record creation cancelled.");
                return;
            }
            int collectAge = ValidateIntInput("Please enter the dog's age: ");
            if(collectAge==sentinelVal){
                System.out.println("Record creation cancelled.");
                return;
            }
            //Record creation wasn't cancelled. Store and increment.
            names[currentIndex] = collectName;
            breeds[currentIndex] = collectBreed;
            weights[currentIndex] = collectWeight;
            ages[currentIndex] = collectAge;
            System.out.println("Record creation successful.");
            currentIndex++;
            if(currentIndex >= names.length){
                currentIndex=0; //Circle back to 0. 
            }
        }
    }

public static String ValidateStringInput(String promptText){
    String output="";
    //Only need to validate that the input is not empty.
    while(String.equals(output,"")==true){
        System.out.println(promptText);
        output = scn.nextLine();
    }
    return output;
}
public static int ValidateIntInput(String promptText){
    int output=0;
    boolean exit=false;
    while(exit==false){
        System.out.println(promptText);
        String attemptOutput= scn.nextLine();
        try{
            output = Integer.parseInt(attemptOutput);
            if(output==sentinelVal || output>0){
                exit=true; //sentinel entered or output is valid
            } 
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid whole number.");
            exit=false; //should be redundant
        }
    }
    return output;
}
public static int PromptForOverride(){
    boolean exit=false;
    System.out.println("The record keeping system is full.");
    int outSignal=-1;
    while(exit==false){
        String response = scn.nextLine();
        if(String.equals(response,"Y")==true){
            exit=true;
            outSignal=1;
        }
        else if(String.equals(response,"N")==true){
            exit=true;
            outSignal=0;
        }
        else{
            System.out.println("Invalid input. Please enter Y or N.");
        }
    }
    return outSignal;
}
    

}
