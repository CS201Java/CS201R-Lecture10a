import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Welcome to classes!");

        //define file values
        ArrayList<Person> people = new ArrayList<>();
        
        //EXAMPLE 1:  CREATE A STUDENT OBJECT USING OVERLOADED CONSTRUCTOR
        Student s1 = new Student('S', "Patrick","Mahomes", 29, 3.5);

        //EXAMPLE 1:  CREATE A STUDENT OBJECT USING DEFAULT CONSTRUCTOR
        Student s2 = new Student();
        s2.setType('S');
        s2.setLName("Kelce");
        s2.setFName("Travis");
        s2.setAge(35);
        s2.setGPA(3.4);

        //EXAMPLE 1:  PRINT OBJECTS 
         System.out.print(s1);
         System.out.print(s2);

        people.add(s1);
        people.add(s2);

        for (Person p : people){
            System.out.print(p);
        }

        //EXAMPLE 2A: POLYMORPHISM: APPLE CLASS
        //testing Apple class
        System.out.println("\n\nEXAMPLE 2:  TESTING APPLE CLASS");
        System.out.println("\tEXAMPLE2a: ");
        Apple a = new Apple();
        System.out.println("\t" + a + "\n");
        
        System.out.println("\tEXAMPLE2b: ");
        GoldenDelicious b = new GoldenDelicious(7);
        System.out.println("\t" + b + "\n");

        System.out.println("\tEXAMPLE2c: ");
        Apple c = new GoldenDelicious(8);
        System.out.println("\t" +c + "\n");

        System.out.println("\tEXAMPLE2d: ");
        Apple d = new GoldenDelicious();
        System.out.println("\t" + d + "\n");
 
        try {
            File inFile = new File("people.txt");
            Scanner scanner = new Scanner(inFile);

            //input values from a file & add to people
            if (loadArrayList(people, scanner) == -1){
                System.out.println("Input is not valid");
            }
            
            System.out.println("\nPRINT ALL PEOPLE USING OBJECT PRINTPERSON");
            for (Person p : people){
                System.out.print(p);
            }
            System.out.println("Total People:   " + Person.totalPeople);
            System.out.println("Total Students: " + Student.totalStudent);

            System.out.println("\nPRINT ALL PEOPLE USING GETTERS");
            for (Person p : people){
                System.out.print(p);
            }

            System.out.println("\nPRINT ONLY STUDENTS");
            //using instanceof to print only
            for (Person p : people){
                if (p instanceof Student)
                    System.out.print(p);
            }

            //deep vs shallow copy
            ArrayList<Person> people2 = people;
            System.out.println("\nEXAMPLE 4A: SHALLOW (SAME REFERENCE) COPY");
            System.out.println("CHANGES MADE TO PEOPLE2 AFFECTS PEOPLE");
            people2.get(1).setLName("Grasshopper");

            for (Person p : people){
                    System.out.print(p);
            }


            System.out.println("\nEXAMPLE 4B: SHALLOW COPY OF OBJECTS");
            System.out.println("CHANGES MADE TO PEOPLE3 OJBECT AFFECTS PEOPLE OBJECT");
            ArrayList<Person> people3 = new ArrayList<>(people);
            people3.get(2).setLName("MooMooCows");

            for (Person p : people){
                System.out.print(p);
            }
            scanner.close();
        }

        catch (FileNotFoundException e){
            System.out.println("Unable to open file");
        }
       
    }

    public static int loadArrayList(ArrayList<Person> people, Scanner input){

        String inputLine, f, l;
        int a;
        double g;
 
        while (input.hasNextLine()){
            //get the next line of input from the file
            inputLine = input.nextLine();
            String[] tokens = inputLine.split(",");

            //check that the number of tokens includes row & colum
            if (tokens[0].equals("P") && tokens.length < 4){
                return -1;
            } 
            if (tokens[0].equals("S") && tokens.length < 5){
                return -1;
            } 
            f = tokens[2];
            l = tokens[1];

            if (tokens[0].equals("P")){
                try{
                    a = Integer.parseInt(tokens[3]);
                    Person newPerson = new Person(f,l,a);
                    people.add(newPerson);
                }
                catch(NumberFormatException e){
                    System.out.println("Error in the line: " + inputLine);
                }
            }
            else if (tokens[0].equals("S")){
                try{
                    a = Integer.parseInt(tokens[3]);
                    g = Double.parseDouble(tokens[4]);
                    //Student newPerson = new Student('S',f,l,a,g);
                    //people.add(newPerson);
                    Person newPerson2 = new Student('S',f,l,a,g);
                    people.add(newPerson2);
                }
                catch(NumberFormatException e){
                    System.out.println("Error in the line: " + inputLine);
                }
            }

       }
                   
       return 1;
    }
      
}

