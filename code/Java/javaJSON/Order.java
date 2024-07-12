import java.util.ArrayList;

public class Order {
    private String orderID;
    private String shippingAddress;
    private String customerID;
    private ArrayList<Item> items;


    public Order() {
        this.orderID = null;
        this.shippingAddress = null;
        this.customerID = null;
        this.items = new ArrayList<Item>();

    }

    //Constructor
    public Order(String orderID, String shippingAddress, String customerID,
            ArrayList<Item> items) {
        this.orderID = orderID;
        this.shippingAddress = shippingAddress;
        this.customerID = customerID;
        this.items = items;

    }

    //Getters and setters
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public String getOrderID() {
        return orderID;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public String getCustomerID() {
        return customerID;

    }



}

