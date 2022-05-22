package imatmini;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    public List<CartItem> cartItemList;
    private double totalCost;
    @FXML private Label totalCartCost;
    @FXML private FlowPane cartItemPane;

    Cart(){
        totalCost = 0;
        cartItemList = new ArrayList<>();
    }

    public void fillCart() {
        cartItemPane.getChildren().clear();
        for (CartItem item : cartItemList) {
            totalCost += (item.getPrice());
            cartItemPane.getChildren().add(item);
        }
    }

    private void decreaseCartQuantity(CartItem item) {
        if (cartItemList.contains(item)) {
            item.decrementQuantity();
            if (item.getQuantity() < 1) {
                cartItemList.remove(item);
            }
        }
        totalCost -= (item.getPrice());
    }

    private void increaseCartQuantity(CartItem item) {
        if (cartItemList.contains(item)) {
            item.incrementQuantity();
        } else {
            cartItemList.add(new CartItem(item.getName(), item.getImage().getImage(), item.getPrice(), 1));
        }
        totalCost += (item.getPrice());
    }

    public void removeFromCart(CartItem item) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).equals(item)) {
                cartItemList.remove(i);
                break;
            }
        }
    }
}

