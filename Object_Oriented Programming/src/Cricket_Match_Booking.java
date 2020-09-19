// Written by Surya Teja

import java.io.IOException;
import java.util.Scanner;

public class Cricket_Match_Booking {

    private static boolean MainMenu = true;
    private static boolean SubMenu = true;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Seat[] myStadium = new Seat[10];
        myStadium[0] = new Seat();
        myStadium[1] = new Seat();
        myStadium[2] = new Seat();
        myStadium[3] = new Seat();
        myStadium[4] = new Seat();
        myStadium[5] = new Seat();
        myStadium[6] = new Seat();
        myStadium[7] = new Seat();
        myStadium[8] = new Seat();
        myStadium[9] = new Seat();
        int SeatNum = 0;
        initialise(myStadium);
        while (MainMenu) {
            while (SubMenu) {
                final String RESET = "\u001B[0m";
                final String GREEN = "\u001B[32m";
                final String YELLOW = "\u001B[33m";
                final String BLUE = "\u001B[34m";
                System.out.println("******************************************************\n");
                System.out.println(GREEN + "Rajiv Gandhi International Cricket Ground Welcomes you to \n" +
                        "to the" + RESET + BLUE + " India " + RESET + " (vs) " + YELLOW + " Australia " + RESET + GREEN + " ODI Match !! " + RESET);

                System.out.println("\n");
                System.out.println("Please select any one of the following options : \n");
                System.out.println(" Press - A : Book A New Seat.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println(" Press - B : Display Empty Seats.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println(" Press - C : View all Seats.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println(" Press - D : Delete spectator from seat.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println(" Press - E : Find a seat from spectator's name.");
                System.out.println("---------------------------------------------------------------------------------------\n");
                String Selection = input.next();
                Selection = Selection.toUpperCase();
                switch (Selection) {
                    case "A":
                        Book_A_Seat(myStadium);
                        break;
                    case "B":
                        Check_If_Empty(myStadium);
                        break;
                    case "C":
                        View_All_Seats(myStadium);
                        break;
                    case "D":
                        Delete_Spectator_From_Seat(myStadium, SeatNum);
                        break;
                    case "E":
                        Find_Seat_From_Spectator_Name(myStadium);
                        break;
                    default:
                        System.out.println(" Invalid Selection ! ");
                        break;
                }
                System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
                System.out.println(" Would you like to select any other Option\n1.) Yes\n2.) No");
                System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");

                if (input.nextInt() == 1) {
                    SubMenu = true;
                } else {
                    SubMenu = false;
                }
            }
            SubMenu = true;
            System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
            System.out.println(" Would you like to select any other Option\n1.) Yes\n2.) No");
            System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
            if (input.nextInt() == 1) {
                MainMenu = true;
            } else {
                System.out.println("");
                System.exit(0);

            }
        }

    }

    private static void initialise(Seat[] myStadium) {
        for (int x = 0; x < myStadium.length; x++) {
            myStadium[x].setName("Nobody");
        }
    }

    private static void Check_If_Empty(Seat[] myStadium) {
        System.out.println(" The following seats are empty : ");
        for (int x = 0; x < myStadium.length; x++) {
            if (myStadium[x].getName().equals("Nobody")) {
                System.out.println((x + 1));
            }
        }
    }

    private static void Book_A_Seat(Seat[] myStadium) {
        String seatNumber;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the seat number (1-10) : ");
        int SeatNum = input.nextInt() - 1;
        if(SeatNum > 10 || SeatNum < 0) {
            System.out.println(" Plz enter a seat number between 1 to 10 ... ");
            System.exit(1);
        }
        System.out.println("Enter name for seat - " + (SeatNum + 1) + " : ");
        seatNumber = input.next();
        myStadium[SeatNum].setName(seatNumber);
    }

    private static void View_All_Seats(Seat[] myStadium) {
        for (int x = 0; x < myStadium.length; x++) {
            System.out.println(" Seat " + (x + 1) + " has been occupied " + " by ---> " + myStadium[x].getName());
        }
    }

    private static void Delete_Spectator_From_Seat(Seat[] myStadium, int SeatNum) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a seat number to delete(1-10) : ");
        SeatNum = input.nextInt() - 1;
        myStadium[SeatNum].setName("Nobody");
        System.out.println("Entry Deleted : )");
    }

    private static void Find_Seat_From_Spectator_Name(Seat[] myStadium) {
        Scanner input = new Scanner(System.in);
        String seatNumber;
        System.out.println(" Enter a name to search for : ");
        seatNumber = input.next();
        int x;
        boolean Checker = false;
        for (x = 0; x < myStadium.length; x++) {
            if (seatNumber.equals(myStadium[x].getName())) {
                System.out.println(" This name matches with seat number : " + (x+1));
                Checker = true;
            }
        }
        if (Checker == false) {
            System.out.println(" There are no Seats Booked with that name\n (make sure you've used the correct CAP's...) ");
        }
    }

    static class Seat {

        private String mainName;

        public Seat() {
            mainName = "X";

        }

        public void setName(String aName) {
            mainName = aName;
        }

        public String getName() {
            return mainName;
        }
    }
}
