import java.time.LocalDate;
public class DateComp {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.parse("2024-06-09");

        System.out.printf("Result of date1.compareTo(date2):%d%n", 
                date1.compareTo(date2));

        
        /*LocalDate newDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newDate = LocalDate.parse("15-05-2024", dateFormat);
        */

        


        //System.out.println(newDate.format(dateFormat));


    }



}
