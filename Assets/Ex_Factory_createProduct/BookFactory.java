public class BookFactory implements ProductFactory {
    public Product createProduct(String productType) {
        if (productType.equals("textbook")) {
            return new Textbook();
        } else if (productType.equals("reference book")) {
            return new ReferenceBook();
        } else {
            return null;
        }
    }
}