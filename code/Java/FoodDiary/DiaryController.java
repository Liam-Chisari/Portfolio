import java.time.LocalDate;
import java.util.InputMismatchException;
class DiaryController {

    private DiaryModel model;
    private DiaryView view;
    //First menu option almost always 1, last is exit.
    private final int mainOptionLow = 1;
    private int mainOptionHigh;


    public DiaryController(DiaryView view, DiaryModel model) {
        this.model = model;
        this.view = view;
        mainOptionHigh = model.getMainMenu().getNumMenuItems() + 1;

    }

    public void run() {

        //TODO:Call model.loadData()
        int exitCode = -1;
        while(exitCode < 1) {
            exitCode = mainMenuAction(model.getMainMenu(), 
                    mainOptionLow, mainOptionHigh);
        }


    }


    public int mainMenuAction(Menu mainMenu, int optionMin, int optionMax) {

        int choice = 0;

        while (choice < optionMin || choice > optionMax) {
            try {
                choice = view.showMainMenu(model.getMainMenu());
            }
            catch(InputMismatchException ex) {
                view.print("Error! Please enter a valid selection.");
                view.clearScanner();
            }
        }

        switch (choice) {
            case 1:
                if(createEntry()) view.print("Entry added successfully.");
                break;
            case 2:
                view.print(model.showEntries());
                break;
            case 3:
                //TODO:deleteEntry();
                break;
            case 4:
                //TODO:model.showStatistics();
                break;
            case 5:
                //TODO:saveAndExit("Goodbye!");
                return 1;
        }

        return 0;
    }


    public boolean createEntry(){


        /*Create temporary entry, get data from user, and attempt
        * to add it to meals.*/
        String mealName;
        String foodName;
        double kilojoules;
        double protein;
        double carbohydrates;
        double fat;

        try {
            mealName = view.getString("Enter meal name.");
            foodName = view.getString("Enter food name.");
            kilojoules = view.getDouble("Enter number of kilojoules.");
            protein = view.getDouble("Enter amount of protein in grams.");
            carbohydrates = view.getDouble("Enter amount of carbohydrates in grams.");
            fat = view.getDouble("Enter amount of fat in grams.");
            Meal tempMeal = new Meal(mealName, foodName, kilojoules, protein,
                    carbohydrates, fat);
            model.createEntry(tempMeal, LocalDate.now());
        }
        catch(InputMismatchException ex) {
            view.print("Invalid input! Returning to main menu.");
            view.clearScanner();
            return false;
        }
        return true;




    }




}


