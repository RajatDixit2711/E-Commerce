package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterFace{
    GridPane loginPage;
    HBox headerBar;
    HBox footerBar;
    VBox body;
    Button signInButton;
    Button placeOrderButton = new Button("Place Order");

    Label welcomeLable;
     Customer loggedInCustomer;
     ProductList productList= new ProductList();
     VBox productPage;
     ObservableList<Product> itemInCard= FXCollections.observableArrayList();



     public BorderPane createContent()
    {
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600);
     //  root.setCenter(loginPage);

       root.setTop(headerBar);
      //  root.getChildren().add(loginPage);// method to add notes as children  to probo
        body= new VBox();
        body.setPadding( new Insets(10));
        body.setAlignment(Pos.CENTER);
       // body.setStyle("-fx-background-color:BLACK;");
        root.setCenter(body);
        productPage=productList.getAllProducts();
       body.getChildren().add(productPage);
       root.setBottom(footerBar);

        return root;

    }
    public  UserInterFace()
    {
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }
    private  void createLoginPage()
    {
        Text userNameText=new Text("User Name");
        Text passwordText= new Text(" Passward");

        TextField userName= new TextField();
        userName.setPromptText("Type your user Name here");
        PasswordField password= new PasswordField();
        password.setPromptText("Type your password here");
        Label messagelabel= new Label("Hi");
        Button loginButton = new Button("Login");

        loginPage = new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(userNameText,0,0);
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(messagelabel,0,2);
        loginPage.add(loginButton,1,2);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name, pass);    // check
                if (loggedInCustomer != null) {
                    messagelabel.setText("welcome " + loggedInCustomer.getName());
                    messagelabel.setStyle("-fx-background-color:white;");
                    welcomeLable.setText("Welcome " + loggedInCustomer.getName());
                     welcomeLable.setStyle("-fx-background-color:white;");
                    headerBar.getChildren().add(welcomeLable);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);

                } else {
                    messagelabel.setText("Login failed !! please give correct user name and password");
                }
            }

          });
      }


    private void createHeaderBar()
    {
        Button homeButton = new Button();
        Image image= new Image("C:\\Users\\Rajat Dixit\\IdeaProjects\\E-Commerce\\src\\ecommer.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(200);
        homeButton.setGraphic(imageView);


     TextField searchBar=new TextField();
     searchBar .setPromptText("Search here");
     searchBar.setPrefWidth(280);

     Button searchButton = new Button("search");
     signInButton = new Button("sign in");
          welcomeLable=new Label();
          Button cardButton= new Button(" Cart ");



        headerBar = new HBox();
        headerBar.setStyle("-fx-background-color:BLACK;");// add colour
        headerBar.setPadding(new Insets(10));
        headerBar.setSpacing(10);
        headerBar.setAlignment(Pos.CENTER);




        headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,cardButton);
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signInButton);
                //footerBar.getChildren().remove(cardButton);
//                footerBar.getChildren().remove(bu);
                   //footerBar.setVisible(false);


            }
        });
        cardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage= productList.getProductsInCard(itemInCard);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);

            }
        });
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // need list of product and a customer
            public void handle(ActionEvent actionEvent) {

                if(itemInCard==null)
                {
                    //
                    showDialog("Please add some product in the card to place an order!");
                    return ;
                }
                if(loggedInCustomer==null)
                {
                    showDialog("Please login first to place order");
                    return ;
                }
               int count = order.placeMultipleOrder(loggedInCustomer, itemInCard);
                if(count!=0)
                {
                    showDialog("Order Place for "+ count+ " products  SuccessFull !!");
                    ///////
                    body.getChildren().clear();

                    showDialog("Card Is Empty!! ADD some products to card ");


                }
                else {
                    showDialog("Order Failed");
                }


            }
        });
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);

                if(loggedInCustomer==null&&headerBar.getChildren().indexOf(signInButton)==-1)
               {
                      headerBar.getChildren().add(signInButton);


                }
            }
        });


    }


    private void createFooterBar()
    {

        Button buyNowButton  = new Button("Buy Now");
        Button addToCardButton = new Button("Add to Card");

       footerBar = new HBox();

        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.setStyle("-fx-background-color: Black;");
        footerBar.getChildren().addAll(buyNowButton,addToCardButton );

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                if(product==null)
                {
                    //
                    showDialog("Please select the product first to place the order!");
                    return ;
                }
                if(loggedInCustomer==null)
                {
                    showDialog("Please login first to place order");
                    return ;
                }
                boolean status = order.placeOrder(loggedInCustomer,product);
                if(status == true)
                {
                    showDialog("Order Place SuccessFull !!");
                }
                else {
                    showDialog("Order Failed");
                }


            }


        });
        addToCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                if(product==null)
                {
                    //
                    showDialog("Please select the product first to add it to card !");
                    return ;
                }
                itemInCard.add(product);
                showDialog(" Selected item has been added to the card successfully !");

            }
        });

    }
    private void showDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}
