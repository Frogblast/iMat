package imatmini;

import java.util.ArrayList;
import java.util.List;

public class PreviousOrders {

    static List<Cart> previousOrders = new ArrayList<>();

    public static void addOrderToCart(Cart order){
        Cart cart = new Cart();
        cart.fillCart();
    }
}
