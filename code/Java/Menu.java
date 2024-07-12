import java.util.ArrayList;
import java.lang.StringBuilder;

class Menu {

    private String title;
    private ArrayList<MenuItem> menuItems;
    private boolean hasExitOption;

    public Menu(String title) {
        this.title = title;
        this.menuItems = new ArrayList<MenuItem>();
        this.hasExitOption = false;
    }

    public void addItem(String optionText) {
        menuItems.add(new MenuItem(optionText, menuItems.size()+1));
    }

    //Returns a string representation of the menu, ready to be printed.
    public String toString() {
        if(!hasExitOption) {
            addItem("Exit");
            hasExitOption = true;
        }
        StringBuilder menuString = new StringBuilder(this.title);
        menuString.append(":\n");

        for(MenuItem menuItem : menuItems) {
            menuString.append(menuItem);
        }

        return menuString.toString();



    }

    public String getTitle() {
        return title;
    }
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getNumMenuItems() {
        return menuItems.size();
    }


}
