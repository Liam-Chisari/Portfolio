import java.util.ArrayList;
import java.time.LocalDate;
class DiaryModel {

    private DiaryController controller;
    private DiaryView view;
    private Menu mainMenu;
    private ArrayList<Day> days;


    public DiaryModel(DiaryView view) {

        this.view = view;


        days = new ArrayList<Day>();

        mainMenu = new Menu("Main Menu");
        mainMenu.addItem("Add Entry");
        mainMenu.addItem("View Diary");
        mainMenu.addItem("Delete Entry");
        mainMenu.addItem("Statistics");

        days.add(new Day(LocalDate.now()));

    }


    


    //Returns true if matching date found, false otherwise.
    boolean createEntry(Meal newMeal, LocalDate date) {

        //Match the LocalDate passed in with an object from days
        for(Day day : days) {
            if(date.compareTo(day.getDate()) == 0) {

                day.addMeal(newMeal);
                return true;
            }
        }
        return false;

    }


    public String showEntries() {

        StringBuilder outputString = new StringBuilder();
        //TODO: Call the Day.toString() method for each Day in days
        for(Day day : days) {
            outputString.append(day.toString());
        }
        return outputString.toString();
    }



    public Menu getMainMenu() {
        return mainMenu;
    }

}


