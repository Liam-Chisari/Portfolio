public class Item {

    public String ID;
    public String name;
    public String description;
    public double price;
    public int quantity;
    public double discount;
    public String category;


    public Item (String ID, String name, String description, double price, int quantity,
            double discount, String category){
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.category = category;
    }

}
