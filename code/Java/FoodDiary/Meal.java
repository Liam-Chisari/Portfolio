import java.lang.StringBuilder;


class Meal {

    private String mealName;
    private String foodName;
    private double kilojoules;
    private double protein;
    private double carbohydrates;
    private double fat;
    

    public Meal(String mealName, String foodName, double kilojoules,
            double protein, double carbohydrates, double fat) {

        this.mealName = mealName;
        this.foodName = foodName;
        this.kilojoules = kilojoules;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }


    public String toString() {


        StringBuilder outputString = new StringBuilder();

        outputString.append(Shared.HOR_BORDER);
        outputString.append(System.lineSeparator());
        outputString.append("Meal: ");
        outputString.append(mealName);
        outputString.append(System.lineSeparator());
        outputString.append("Food: ");
        outputString.append(foodName);
        outputString.append(System.lineSeparator());
        outputString.append("Kilojoules: ");
        outputString.append(String.valueOf(kilojoules));
        outputString.append(System.lineSeparator());
        outputString.append("Protein: ");
        outputString.append(String.valueOf(protein));
        outputString.append(System.lineSeparator());
        outputString.append("Carbohydrates: ");
        outputString.append(String.valueOf(carbohydrates));
        outputString.append(System.lineSeparator());
        outputString.append("Fat: ");
        outputString.append(String.valueOf(fat));
        outputString.append(System.lineSeparator());

        return outputString.toString();


    }

    //TODO: getters & setters

}
