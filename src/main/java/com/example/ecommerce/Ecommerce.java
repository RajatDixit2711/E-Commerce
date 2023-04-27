package com.example.ecommerce;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {

    UserInterFace userInterFace= new UserInterFace();



        @Override
        public void start (Stage stage) throws IOException {
        Scene scene = new Scene(userInterFace.createContent());
        stage.setTitle("ECommerce");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}