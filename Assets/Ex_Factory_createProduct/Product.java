import java.util.Date;
public abstract class Product {
    protected String product_id;
    protected String product_name;
    protected int product_type;
    protected int product_quantity;
    protected double product_price;
    protected String title;
    protected String author_id;
    protected String publisher;
    protected Date publication_date;
    protected String manufacturer_name;

    public void setProductID(String product_id) {
        this.product_id = product_id;
    }

    public String getProductID() {
        return this.product_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAuthorID(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthorID() {
        return this.author_id;
    }

    // Triển khai phương thức displayProductInfo trong lớp cha Product
    public abstract void displayProductInfo();
}
