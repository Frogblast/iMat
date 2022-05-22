/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;


/**
 *
 * @author oloft
 */
public class iMatController implements Initializable, ShoppingCartListener {

    private List<Category> mainCategories;
    public static Cart currentCart;

    /** FrontPage */
    @FXML private AnchorPane homePage;
    @FXML private Label categoryText;
    @FXML private Label subText;
    @FXML private GridPane categoryGrid;
    @FXML private Button toFavorites;
    @FXML private Button toPreviousOrders;
    @FXML private Button toHomePage;
    @FXML private Button toGuide;
    @FXML private Button toDeliveryPane;

    /** Delivery Page */
    @FXML private AnchorPane deliveryPage;
    @FXML private Button backButtonD;
    @FXML private Button forwardButtonD;

    /** Payment Page */
    @FXML private AnchorPane paymentPage;
    @FXML private Button backButtonP;
    @FXML private Button forwardButtonP;

    /** Confirm Order Page */
    @FXML private AnchorPane confirmOrderPage;
    @FXML private Button backButtonC;
    @FXML private Button forwardButtonC;

    private Category bageri = new Category("Bageri");
    private Category bakning = new Category("Bakning");
    private Category chark = new Category("Chark");
    private Category mejeri = new Category("Mejeri");
    private Category skafferi = new Category("Skafferi");
    private Category fruktOchGrönt = new Category("Frukt och grönt");

    private List<Category> allCategories = new ArrayList<Category>(Arrays.asList(bageri,bakning, chark, mejeri, skafferi, fruktOchGrönt));

    // Shopping Pane
    @FXML private AnchorPane shopPane;
    @FXML private TextField searchField;
    @FXML private Label itemsLabel;
    @FXML private Label costLabel;
    @FXML private FlowPane productsFlowPane;
    
    // Account Pane
    @FXML private AnchorPane accountPane;
    @FXML ComboBox cardTypeCombo;
    @FXML private TextField numberTextField;
    @FXML private TextField nameTextField;
    @FXML private ComboBox monthCombo;
    @FXML private ComboBox yearCombo;
    @FXML private TextField cvcField;
    @FXML private Label purchasesLabel;
    
    // Other variables
    private final Model model = Model.getInstance();

    // Shop pane actions
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event) {
        
        List<Product> matches = model.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    
    @FXML
    private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }
    
    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }
    
    // Account pane actions
     @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model.getShoppingCart().addShoppingCartListener(this);

        updateProductList(model.getProducts());
        updateBottomPanel();
        
        setupAccountPane();
        
    }    
    
    // Navigation
    public void openAccountView() {
        updateAccountPanel();
        accountPane.toFront();
    }

    public void closeAccountView() {
        updateCreditCard();
        shopPane.toFront();
    }
    
    // Shope pane methods
    @Override
     public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
    }
   
    
    private void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new ProductPanel(product));
        }

    }
    
    private void updateBottomPanel() {
        
        ShoppingCart shoppingCart = model.getShoppingCart();
        
        itemsLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));
        
    }
    
    private void updateAccountPanel() {
        
        CreditCard card = model.getCreditCard();
        
        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());
        
        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());
        
        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");
        
    }
    
    private void updateCreditCard() {
        
        CreditCard card = model.getCreditCard();
        
        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());
        
        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);
        
        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));
        
        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));
        
        card.setVerificationCode(Integer.parseInt(cvcField.getText()));

    }
    
    private void setupAccountPane() {
        
        monthCombo.getItems().addAll(model.getMonths());
        
        yearCombo.getItems().addAll(model.getYears());
        
    }


    /** Categories */
    private void populateMainCategoryGrid(){
        for (int i = 0; i < categoryGrid.getColumnCount(); i++) {
            for (int j = 0; j < categoryGrid.getRowCount(); j++) {
                for (int k = 0; k < mainCategories.size(); k++) {
                    categoryGrid.add(mainCategories.get(k), i, j);
                }
            }
        }
    }

    private void populateSubCategoryGrid(Category mainCategory){
        for (int i = 0; i < categoryGrid.getColumnCount(); i++) {
            for (int j = 0; j < categoryGrid.getRowCount(); j++) {
                for (int k = 0; k < mainCategory.subCategories.size(); k++) {
                    categoryGrid.add(mainCategory.subCategories.get(k), i, j);
                }
            }
        }
    }

    private void toSubcategories(Category mainCategory){
        populateSubCategoryGrid(mainCategory);
        categoryText.setText("Hem");
        //categoryText.setColor(grå) CSS
        //categoryText.set(understruken)
        subText.setText(" > " + mainCategory.name);
        subText.toFront();
    }

    /** Confirm Order */
    public static void saveOrder(){
        PreviousOrders.previousOrders.add(currentCart);
    }

    /** Open page buttons */
    public void openFavorites(ActionEvent actionEvent){
//        favoritesPage.toFront();
    }
    public void openStart(ActionEvent actionEvent){
        homePage.toFront();
    }
    public void openGuide(ActionEvent actionEvent){
//        guidePage.toFront();
    }
    public void openOrders(ActionEvent actionEvent){
//      previousOrdersPage.toFront();
    }
    public void openDeliveryPane(ActionEvent actionEvent) {
        deliveryPage.toFront();
    }
    public void openPaymentPane(ActionEvent actionEvent) {
        paymentPage.toFront();
    }
    public void openConfirmPane(ActionEvent actionEvent) {
        confirmOrderPage.toFront();
    }
}
