package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {
    private TableView <Product> productTable;
     private TableView <Product> cartTable;
    public VBox createTableForCart( ObservableList<Product > data)
    {
        TableColumn id= new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name =new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn price= new TableColumn("Price");
        price .setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTable = new TableView<>();
        cartTable.getColumns().addAll(id,name,price);
       cartTable .setItems(data);

        cartTable .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        VBox vBox= new VBox();
        vBox.setPadding(new Insets(10));

        vBox.getChildren().addAll(cartTable );
        return vBox;
    }
    public VBox createTable( ObservableList<Product > data)
    {
        TableColumn id= new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name =new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn price= new TableColumn("Price");
        price .setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable = new TableView<>();
        productTable.getColumns().addAll(id,name,price);
        productTable.setItems(data);

        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        VBox vBox= new VBox();
        vBox.setPadding(new Insets(10));

        vBox.getChildren().addAll(productTable);
        return vBox;
    }
    public  VBox getDummyTable(){
        ObservableList<Product > data= FXCollections.observableArrayList() ;
        data.add(new Product(2,"Iphone",3456));
        data.add(new Product(3, "samsung",23456));
        return createTable(data);
    }
    public VBox getAllProducts()
    {
        ObservableList<Product>data= Product.getAllProducts();
        return createTable(data);
    }
    public  Product getSelectedProduct()
    {
        return productTable.getSelectionModel().getSelectedItem();
    }
    public  VBox getProductsInCard(ObservableList<Product> data)
    {
        return createTableForCart(data);
    }

}
