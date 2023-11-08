public class Textbook extends Product {
     public void setProductID(String product_id) {
        super.setProductID(product_id);
    }

    // Triển khai lại phương thức setTitle của lớp cha Product
    public void setTitle(String title) {
        super.setTitle(title);
    }

    // Triển khai phương thức displayProductInfo của lớp cha Product
    public void displayProductInfo() {
        System.out.println("This is a textbook with ID " + this.product_id + " and title " + this.title);
    }
}