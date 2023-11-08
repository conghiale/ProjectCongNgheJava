public class PenFactory implements ProductFactory {
    public Product createProduct(String productType) {
        if (productType.equals("ballpoint pen")) {
            return new BallpointPen();
        } else if (productType.equals("fountain pen")) {
            return new FountainPen();
        } else {
            return null;
        }
    }
}