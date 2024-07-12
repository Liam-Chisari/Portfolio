import java.time.LocalDate;
import java.lang.StringBuilder;
class Booking {
    int bookingId;
    String name;
    String phoneNumber;
    int numGuests;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String notes;

    public Booking(int bookingId, String name, String phoneNumber, 
            int numGuests, LocalDate checkInDate, LocalDate checkOutDate,
            String notes){
        this.bookingId = bookingId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.numGuests = numGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.notes = notes;


    }

    public String toString() {

        StringBuilder bookingString = new StringBuilder();
        bookingString.append("Booking ID: " + String.valueOf(bookingId));
        bookingString.append("\nCustomer Name: " + name);
        bookingString.append("\nPhone Number: " + phoneNumber);
        bookingString.append("\nNumber of Guests: " + String.valueOf(numGuests));
        bookingString.append("\nCheck-in Date: " +
                checkInDate.format(BookingSystem.DATE_FORMAT));
        bookingString.append("\nCheck-out Date: " + 
                checkOutDate.format(BookingSystem.DATE_FORMAT));
        bookingString.append("\nNotes: " + notes);
        bookingString.append("\n\n");

        return bookingString.toString();

    }

    public int getId() {
        return bookingId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
  

}
