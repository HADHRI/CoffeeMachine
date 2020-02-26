package type;

public enum CoffeeType {
    TEA("T"),
    HOT_TEA("Th"),
    HOT_COFFEE("Ch"),
    COFFEE("C"),
    CHOCOLATE("H"),
    HOT_CHOCOLATE("Hh"),
    ORANGE("O");

    String coffee;

    CoffeeType(String coffeeType) {
        this.coffee = coffeeType;
    }

    public String getCoffeeType() {
        return coffee;
    }

}