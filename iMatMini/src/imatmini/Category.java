package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public class Category extends Node {

    @FXML private ImageView categoryImage;
    @FXML private Label categoryName;
    String name;
    List<Category> subCategories;

    Category (String name){
        this.name = name;
        //categoryName.setText(name);
        //categoryImage.setImage(getCategoryImage(this.name));
    }

    public Image getCategoryImage(String category) {
        String iconPath = "";
        switch (category) {
            case "Bageri":
                iconPath = "C:/Users/Bookah/Desktop/iMat3/src/images/test.png";
                break;
            case "Chark":
                iconPath = "RecipeSearch/resources/icon_flag_greece.png";
                break;
            case "Skafferi":
                iconPath = "RecipeSearch/resources/icon_flag_france.png";
                break;
            case "Frukt och grönt":
                iconPath = "RecipeSearch/resources/icon_flag_india.png";
                break;
            case "Mejeri":
                iconPath = "RecipeSearch/resources/icon_flag_africa.png";
                break;
            case "Bakning":
                iconPath = "RecipeSearch/resources/icon_flag_asia.png";
                break;
        }
        return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
    }

    public String getName(){
        return name;
    }
}

