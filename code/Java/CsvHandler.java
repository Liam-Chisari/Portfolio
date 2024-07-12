import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class CsvHandler {


    public boolean loadFromCsv(List<Booking> bookings, String filename) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            //Read file here.
            String currentLine = reader.readLine();
            String[] attributes;
            while(currentLine != null) {
                attributes = currentLine.split(",");

              bookings.add(new Booking(Integer.parseInt(attributes[0]), attributes[1],
                          attributes[2],Integer.parseInt(attributes[3]), 
                       LocalDate.parse(attributes[4], BookingSystem.DATE_FORMAT),
                       LocalDate.parse(attributes[5], BookingSystem.DATE_FORMAT),
                       attributes[6]));

                currentLine = reader.readLine();
            }
        }
        catch(IOException e) {
            System.out.println("Error reading file." +
                    " Check file name and contents.");
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }

    public boolean saveToCsv(List<Booking> bookings, String filename) {

        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));

            //Save each booking object as a new line.
            StringBuilder outputLine = new StringBuilder();

            for(Booking booking : bookings) {

                outputLine.append(String.valueOf(booking.getId()));
                outputLine.append(",");
                outputLine.append(booking.getName());
                outputLine.append(",");
                outputLine.append(booking.getPhoneNumber());
                outputLine.append(",");
                outputLine.append(String.valueOf(booking.getNumGuests()));
                outputLine.append(",");
                outputLine.append(booking.getCheckInDate().format(BookingSystem.DATE_FORMAT));
                outputLine.append(",");
                outputLine.append(booking.getCheckOutDate().format(BookingSystem.DATE_FORMAT));
                outputLine.append(",");
                outputLine.append(booking.getNotes());
                outputLine.append(System.lineSeparator());

                }

            fileWriter.write(outputLine.toString());
            fileWriter.close();


        }
        catch(IOException e) {
            System.out.println("Error writing to file.");
            System.out.println(e.getMessage());
            return false;
        }

        
        return true;


    }


}
