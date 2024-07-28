import java.util.Scanner;

// Product class
class Meal {
    private String burger;
    private String drink;
    private String fries;

    public void setBurger(String burger) {
        this.burger = burger;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setFries(String fries) {
        this.fries = fries;
    }

    @Override
    public String toString() {
        return "Meal [burger=" + burger + ", drink=" + drink + ", fries=" + fries + "]";
    }
}

// Builder interface
interface MealBuilder {
    void buildBurger();
    void buildDrink();
    void buildFries();
    Meal getMeal();
}

// Concrete Builder classes
class VegMealBuilder implements MealBuilder {
    private Meal meal;

    public VegMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public void buildBurger() {
        meal.setBurger("Veg Burger");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Coke");
    }

    @Override
    public void buildFries() {
        meal.setFries("Large Fries");
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}

class NonVegMealBuilder implements MealBuilder {
    private Meal meal;

    public NonVegMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public void buildBurger() {
        meal.setBurger("Chicken Burger");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Pepsi");
    }

    @Override
    public void buildFries() {
        meal.setFries("Medium Fries");
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}

// Director class
class MealDirector {
    public Meal constructMeal(MealBuilder builder) {
        builder.buildBurger();
        builder.buildDrink();
        builder.buildFries();
        return builder.getMeal();
    }
}

// Client
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealDirector director = new MealDirector();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose meal type: 1 - Veg, 2 - Non-Veg");
        int mealType = scanner.nextInt();

        MealBuilder builder;

        if (mealType == 1) {
            builder = new VegMealBuilder();
        } else {
            builder = new NonVegMealBuilder();
        }

        Meal meal = director.constructMeal(builder);
        System.out.println("Your meal: " + meal);
    }
}
