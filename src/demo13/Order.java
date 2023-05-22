package demo13;

public class Order {
    private String id;
    private double amount;
    private String description;
    private String seller;
    private String buyer;

    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getSeller() {
        return seller;
    }
    
    public void setSeller(String seller) {
        this.seller = seller;
    }
    
    public String getBuyer() {
        return buyer;
    }
    
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}    
