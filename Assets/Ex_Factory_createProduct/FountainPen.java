public class FountainPen extends Product {
    protected String nib_size;
    // các phương thức getter và setter cho thuộc tính nib_size

    // Triển khai lại phương thức setProductID của lớp cha Product
    public void setProductID(String product_id) {
        super.setProductID(product_id);
    }

    // Triển khai phương thức setNibSize của lớp FountainPen
    public void setNibSize(String nib_size) {
        this.nib_size = nib_size;
    }

    // Triển khai phương thức displayProductInfo của lớp cha Product
    public void displayProductInfo() {
        System.out.println("This is a fountain pen with ID " + this.product_id + " and nib size " + this.nib_size);
    }
}