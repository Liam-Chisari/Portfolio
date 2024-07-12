import java.lang.StringBuilder;

class MenuItem {

    private String optionText;
    private int optionNumber;

    public MenuItem(String optionText, int optionNumber) {
        this.optionText = optionText;
        this.optionNumber = optionNumber;
    }

    public String toString() {
        StringBuilder itemString = new StringBuilder();
        itemString.append(String.valueOf(optionNumber));
        itemString.append(") ");
        itemString.append(optionText);
        itemString.append("\n");

        return itemString.toString();
    }

    public String getOptionText() {
        return optionText;
    }
    public int getOptionNumber() {
        return optionNumber;
    }
    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

}
