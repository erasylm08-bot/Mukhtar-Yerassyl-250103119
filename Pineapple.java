public class Pineapple extends PizzaDecorator {
    public Pineapple(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", Pineapple";
    }

    public double getCost() {
        return pizza.getCost() + 1.5;
    }
}