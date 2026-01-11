import java.util.Objects;
import java.util.UUID;

public class Product {
    private String name;
    private Category category;
    private double price;
    private UUID id;

    // specific constructor for full initialization
    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;

        this.id = generateID();
        setPrice(price);
    }

    public Product() {
        this.id = generateID();
    }

    public Product(String name) {
        this.name = name;
        this.id = generateID();
    }

    // Getters and Setters ...

    private UUID generateID() {
        return UUID.randomUUID();
    }

    private void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Category getCategory() { return category; }


    // CRITICAL FOR SEARCHING:
    // Two products are "equal" if their IDs are the same.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        // %.2f formats the price to 2 decimal places
        return String.format("Product: id=%-30s | %-20s |  %10.2f czk",
                id, name, price);
    }
}