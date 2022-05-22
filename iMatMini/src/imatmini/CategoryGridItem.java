package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CategoryGridItem extends Node {


    @FXML
    Label categoryLabel;
    @FXML
    ImageView categoryImage;
    private Category category;

    CategoryGridItem(Category category) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryGridItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.category = category;

        categoryLabel.setText(category.getName());

        categoryImage.setImage(category.getCategoryImage(category.getName()));

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
