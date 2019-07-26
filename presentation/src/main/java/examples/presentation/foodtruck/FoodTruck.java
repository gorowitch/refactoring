package examples.presentation.foodtruck;

public class FoodTruck {
    private String name;
    private String licencePlate;
    private String[] menu;

    public FoodTruck(String name, String licencePlate, String... menu) {
        this.name = name;
        this.licencePlate = licencePlate;
        this.menu = menu;
    }

    public String name() {
        return name;
    }

    public String licencePlate() {

        return licencePlate;
    }

    public Burger order(String item) {
        for ( String menuItem: menu) {
            if (menuItem.equalsIgnoreCase(item)) {
                String[] parts = item.split("\\s+");
                for (int index = 0; index < parts.length; index++) {
                    parts[index] = parts[index].toUpperCase().substring(0,1) + parts[index].toLowerCase().substring(1,parts[index].length());
                }

                return new Burger(String.join(" ",parts) + " Burger");
            }
        }

        throw new IllegalArgumentException((String.format("%s is not on the menu",item)));

    }

    public String[] menu() {
        return menu;
    }
}
