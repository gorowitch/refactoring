package examples.presentation.foodtruck;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

class FoodTruckTest {

    private FoodTruck foodTruck = new FoodTruck("Fred's Burger Truck", "1-WNX-400", "Bacon", "Pepper Cheese", "Sensation");

    @Test
    void name() {
        assertThat(foodTruck.name()).isEqualTo("Fred's Burger Truck");
    }

    @Test
    void licencePlate() {
        assertThat(foodTruck.licencePlate()).isEqualTo("1-WNX-400");
    }

    @Test
    void itemsOnMenuCanBeOrdered() {
        assertThat(foodTruck.order("Bacon")).extracting(Burger::name).isEqualTo("Bacon Burger");
        assertThat(foodTruck.order("Pepper Cheese")).extracting(Burger::name).isEqualTo("Pepper Cheese Burger");
        assertThat(foodTruck.order("Sensation")).extracting(Burger::name).isEqualTo("Sensation Burger");
    }

    @Test
    void itemsNotOnMenuCantBeOrdered() {
        assertThatThrownBy(() -> foodTruck.order("Kanibal"))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Kanibal is not on the menu");
    }

    @Test
    void itemsCanBeOrderedCaseInsensitive() {
        assertThat(foodTruck.order("BACON")).extracting(Burger::name).isEqualTo("Bacon Burger");
    }

    @Test
    void menu() {
        assertThat(foodTruck.menu()).containsExactly("Bacon", "Pepper Cheese", "Sensation");
    }

}
