public class Olives extends PizzaDecorator {
    public Olives(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    public double getCost() {
        return pizza.getCost() + 1.0;
    }
}