import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;

public class BookingSystem {

    private static List<Booking> bookings = new ArrayList<>();
    private static final String HORIZONTAL_BORDER = "--------------------";
    public static final DateTimeFormatter DATE_FORMAT = 
       DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final CsvHandler csvHandler = new CsvHandler();
    private static final String csvFilename = "bookings.csv";

    public static void main(String[] args) {

        if(!csvHandler.loadFromCsv(bookings, csvFilename)) {
            System.out.printf("Could not load file %s. Proceeding without any bookings.%n",
                        csvFilename);
        }






        int choice = -1;
        Menu mainMenu = new Menu("Main Menu");
        mainMenu.addItem("Create Booking");
        mainMenu.addItem("View Bookings");
        mainMenu.addItem("Delete Booking");
        mainMenu.addItem("Statistics");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(mainMenu.toString());
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createBooking(scanner);
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    deleteBooking(scanner);
                    break;
                case 4:
                    showStatistics();
                    break;
                case 5:
                    saveAndExit("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

   private static boolean createBooking(Scanner scanner){ 

       //Create temporary attributes while the data is collected from the user.
       String tempName;
       String tempPhoneNumber;
       int tempNumGuests = -1;
       LocalDate tempCheckInDate = null;
       LocalDate tempCheckOutDate = null;
       String tempNotes;

       System.out.println("Enter customer name:");
       tempName = scanner.nextLine();
       System.out.println("Enter customer phone number:");
       tempPhoneNumber = scanner.nextLine();
       System.out.println("Enter number of guests:");
       tempNumGuests = scanner.nextInt();
       scanner.nextLine();
       while(tempCheckInDate == null) {
           tempCheckInDate = getDateFromUser(scanner, "Enter check in date " +
                   "with format dd-mm-yyyy:");
       }
       while(tempCheckOutDate == null) {
           tempCheckOutDate = getDateFromUser(scanner, "Enter check out date " +
                   "with format dd-mm-yyyy:");
           if(tempCheckOutDate != null && tempCheckOutDate.isBefore(tempCheckInDate)) {
               System.out.println("Check out date cannot be before check in date!");
               tempCheckOutDate = null;
           }
       }

       System.out.println("Enter notes:");
       tempNotes = scanner.nextLine();

      bookings.add(new Booking(bookings.size()+1,tempName, tempPhoneNumber,
                  tempNumGuests, tempCheckInDate, tempCheckOutDate, tempNotes));
      System.out.println("Added booking successfully.");
      return true;

      
   }
   
   private static void viewBookings() {

       System.out.println("Current Bookings:");
       System.out.println(HORIZONTAL_BORDER);



       for(Booking booking : bookings) {
           System.out.println(booking.toString());
       }

       System.out.println(HORIZONTAL_BORDER);

   }

   private static boolean deleteBooking(Scanner scanner) {

       int targetBookingId = -1;

       System.out.print("Enter the ID of the booking to delete.");
       targetBookingId = scanner.nextInt();

       for(Booking booking : bookings) {
           if(targetBookingId == booking.getId()){ 
               bookings.remove(booking);
               System.out.println("Booking deleted.\n");
               return true;
           }

       }

       System.out.println("No matching booking found.\n");
       return false;
   }

   private static void showStatistics() {

       Booking tempBooking;

       if(bookings.size() < 1) {
           System.out.println("There are no bookings recorded.\n");
       }
       else {

           System.out.printf("Total number of bookings:%d%n", bookings.size());
           tempBooking = bookings.get(0);

           for(Booking booking : bookings) {
               if(booking.getNumGuests() > tempBooking.getNumGuests()) {
                   tempBooking = booking;
               }
           }

           System.out.printf("Booking with the highest number of guests:%n%s%n", 
                   tempBooking.toString());
       }

   }


   private static LocalDate getDateFromUser(Scanner scanner, String prompt) {

       String userInput;
       LocalDate newDate = LocalDate.now();
       System.out.print(prompt);

       userInput = scanner.nextLine();

       try {
           newDate = LocalDate.parse(userInput, DATE_FORMAT);
       }
       catch(DateTimeParseException e) {
           System.out.println("Error! Invalid date. Please try again.");
           return null;
       }

       return newDate;

   }


   public static void saveAndExit(String exitMessage) {

       if(csvHandler.saveToCsv(bookings, csvFilename)) {
           System.out.printf("Saved bookings to %s %n", csvFilename);
       }
       else {
           System.out.println("Failed to save bookings.");
       }

       System.out.println(exitMessage);


   }


}
