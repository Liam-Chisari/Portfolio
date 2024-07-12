import java.util.InputMismatchException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;

class BookingController {

    private BookingModel model;
    private BookingView view;

    public BookingController(BookingModel model, BookingView view) {

        this.model = model;
        this.view = view;

    }

    public void run() {

        model.loadData();

        int exitCode = 0;
        while(exitCode == 0) {
            exitCode = mainMenuAction(1, 5);
        }

    }


    public int mainMenuAction(int min, int max) {

        //Call view.mainMenuLoop() here
        int choice = 0;


        while (choice < min || choice > max) {
            try {
                choice = view.showMainMenu(model.mainMenu);
            }
            catch(InputMismatchException ex) {
                view.print("Error! Please enter a valid selection.");
            }
        }

        switch (choice) {
            case 1:
                createBooking();
                view.print("Booking addedd successfully.");
                break;
            case 2:
                view.print(model.viewBookings());
                break;
            case 3:
                deleteBooking();
                break;
            case 4:
                model.showStatistics();
                break;
            case 5:
                saveAndExit("Goodbye!");
                return 1;
        }

        return 0;

    }


    public void createBooking() {

       //Create temporary attributes while the data is collected from the user.
       Booking tempBooking = null;
       String tempName;
       String tempPhoneNumber;
       int tempNumGuests;
       LocalDate tempCheckInDate;
       LocalDate tempCheckOutDate;
       String tempNotes;
       boolean isValid = false;

       while(!isValid) {

           tempName = null;
           tempPhoneNumber = null;
           tempNumGuests = -1;
           tempCheckInDate = null;
           tempCheckOutDate = null;
           tempNotes = null;

           tempName = view.getString("Enter customer name:");
           tempPhoneNumber = view.getString("Enter customer phone number:");
           tempNumGuests = view.getInt("Enter number of guests:");
           tempCheckInDate = model.getDateFromUser("Enter check in date " +
                   "with format dd-mm-yyyy:");
           tempCheckOutDate = model.getDateFromUser("Enter check out date " +
                   "with format dd-mm-yyyy:");
               if(tempCheckOutDate != null && tempCheckOutDate.isBefore(tempCheckInDate)) {
                   view.print("Check out date cannot be before check in date!");
                   tempCheckOutDate = null;
               }
           tempNotes = view.getString("Enter notes:");

          try {
              tempBooking = new Booking(model.bookings.size()+1,tempName, tempPhoneNumber,
                          tempNumGuests, tempCheckInDate, tempCheckOutDate, tempNotes);
              isValid = true;
          }
          catch(Exception ex) {
              view.print("Invalid booking, please try again.");
          }

       }

        model.createBooking(tempBooking);


    }


    public void deleteBooking() {

       int targetBookingId = -1;

       while(targetBookingId <= 0) {
           try { 
               targetBookingId = view.getInt("Enter the ID of the booking to delete.");
           }
           catch(InputMismatchException ex) {
               view.print("Error! Please enter a number.");
           }
       }

       model.deleteBooking(targetBookingId);

    }


    public void saveAndExit(String exitMessage) {

    if(model.saveData()) {
    view.print("Saved bookings to:" +  model.csvFilename);
    }
    else {
    view.print("Failed to save bookings.");
    }

    view.print(exitMessage);

    }




}

