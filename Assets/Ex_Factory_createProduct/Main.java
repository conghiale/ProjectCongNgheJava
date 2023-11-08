public class Main {

  public static void main(String[] args) {
    ProductFactory bookFactory = new BookFactory();
    ProductFactory penFactory = new PenFactory();

    Product myTextbook = bookFactory.createProduct("textbook");
    myTextbook.setProductID("TB001");
    myTextbook.setTitle("Java Programming");
    myTextbook.displayProductInfo();

    Product myFountainPen = penFactory.createProduct("fountain pen");
    myFountainPen.setProductID("FP001");
    ((FountainPen) myFountainPen).setNibSize("Medium");
    myFountainPen.displayProductInfo();
  }
}
