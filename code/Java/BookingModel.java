import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;
import java.lang.StringBuilder;

class BookingModel {


    List<Booking> bookings;
    private final String HORIZONTAL_BORDER = "--------------------";
    private final CsvHandler csvHandler = new CsvHandler();
    final String csvFilename = "bookings.csv";
    final Menu mainMenu;
    private BookingView view;

    public BookingModel(BookingView view) {

        this.view = view;
        

        bookings = new ArrayList<>();



        mainMenu = new Menu("Main Menu");
        mainMenu.addItem("Create Booking");
        mainMenu.addItem("View Bookings");
        mainMenu.addItem("Delete Booking");
        mainMenu.addItem("Statistics");
    }



   void createBooking(Booking booking){ 

       bookings.add(booking);


   }

   String viewBookings() {

       StringBuilder outputString = new StringBuilder();
       outputString.append("Current Bookings:");
       outputString.append(System.lineSeparator());
       outputString.append(HORIZONTAL_BORDER);



       for(Booking booking : bookings) {
           outputString.append(booking.toString());
       }

       outputString.append(HORIZONTAL_BORDER);

       return outputString.toString();

   }

   boolean deleteBooking(int targetBookingId) {


       for(Booking booking : bookings) {
           if(targetBookingId == booking.getId()){ 
               bookings.remove(booking);
               return true;
           }

       }
       return false;
   }

   String showStatistics() {

       Booking tempBooking;
       StringBuilder outputString = new StringBuilder();

       if(bookings.size() < 1) {
           outputString.append(("There are no bookings recorded.\n"));
       }
       else {

           tempBooking = bookings.get(0);
           outputString.append("Total number of bookings:");
           outputString.append(String.valueOf(bookings.size()));

           for(Booking booking : bookings) {
               if(booking.getNumGuests() > tempBooking.getNumGuests()) {
                   tempBooking = booking;
               }
           }

           outputString.append("Booking with the highest number of guests:\n");
           outputString.append(tempBooking.toString());
       }

       return outputString.toString();

   }




   public boolean saveData() {

       if(csvHandler.saveToCsv(bookings, csvFilename)) {
           return true;
       }
       return false;

   }



   LocalDate getDateFromUser(String prompt) {


       String userInput;
       LocalDate newDate = LocalDate.now();
       userInput = view.getString(prompt);

       try {
           newDate = LocalDate.parse(userInput, Booking.DATE_FORMAT);
       }
       catch(DateTimeParseException e) {
           return null;
       }

       return newDate;

   }


   public void loadData() {


       if(!csvHandler.loadFromCsv(bookings, csvFilename)) {
                System.out.printf("Could not load file %s. Proceeding without any bookings.%n",
                            csvFilename);
        }

   }
 

    public Menu getMainMenu() {
        return mainMenu;
    }



}





