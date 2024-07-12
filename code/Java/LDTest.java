import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class LDTest {
    public static void main(String[] args) {

        //LocalDate newDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newDate = LocalDate.parse("15-05-2024", dateFormat);


        System.out.println(newDate.format(dateFormat));



    }


}
