import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.StringBuilder;

class Day {

    private LocalDate date;
    private ArrayList<Meal> meals;
    private double totalKilojoules;
    private int totalMeals;


    public Day(LocalDate date) {
        this.date = date;
        this.meals = new ArrayList<Meal>();
    }

    public boolean addMeal(Meal newMeal){

        meals.add(newMeal);
        return true;

    }

    public String toString() {

        StringBuilder outputString = new StringBuilder();


        for(Meal meal : meals) {
            outputString.append(meal.toString());
        }

        return outputString.toString();


    }
        

    public LocalDate getDate() {
        return date;
    }
    public ArrayList<Meal> getMeals() {
        return meals;
    }
    public double getTotalKilojoules() {
        return totalKilojoules;
    }
    public int getTotalMeals() {
        return totalMeals;
    }


}
