package type;

public enum CoffeeType {
    TEA("T"),
    COFFEE("C"),
    CHOCOLATE("H");

    String coffee;

    CoffeeType(String coffeeType) {
        this.coffee = coffeeType;
    }

    public String getCoffeeType() {
        return coffee;
    }
}