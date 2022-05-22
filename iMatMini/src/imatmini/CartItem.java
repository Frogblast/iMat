package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CartItem extends Node {

    private int quantity;
    @FXML private ImageView cartItemImage;
    @FXML private ImageView plusImage;
    @FXML private ImageView minusImage;
    @FXML private Label cartItemName;
    @FXML private Label cartItemPrice;
    @FXML private TextField cartItemAmount;
    @FXML private Button cartAddButton;
    @FXML private Button cartReduceButton;

    CartItem(String name, Image img, int price, int quantity){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        assert false;
        cartItemName.setText(name);
        cartItemImage.setImage(img);
        cartItemPrice.setText(Integer.toString(price));
        cartItemAmount.setText(Integer.toString(quantity));
        this.quantity = quantity;
    }

    public void incrementQuantity(){
        quantity += 1;
        cartItemAmount.setText(Integer.toString(quantity));
    }

    public void decrementQuantity(){
        quantity -= 1;
        cartItemAmount.setText(Integer.toString(quantity));
    }

    public void openDetailPane(){
        // itemDetailPane.toFront();
    }

    /** Getters & setters */
    public ImageView getImage() {
        return cartItemImage;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return Integer.parseInt(cartItemPrice.toString());
    }
    public String getName() {
        return cartItemName.toString();
    }
}