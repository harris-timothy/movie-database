/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.uah.cs321.main;

//********************
//JAVA IMPORTS
//********************
import java.io.File;

//********************
//JAVAFX IMPORTS
//********************
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;

/**
 *
 * @author Tim Harris
 */
public class GUI extends Application {
    
    public static void main(String[] args) { 

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        
        Scene scene = new Scene(createBorderPane(borderPane.getChildren()), 1000, 1000);

        primaryStage.setTitle("Movie Database");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public BorderPane createBorderPane(ObservableList<Node> children) throws Exception {

        BorderPane borderPane = new BorderPane();
        borderPane.setId("borderpane");

        //creating the menubar
        MenuBar menuBar = new MenuBar();

        //creating the File menu
        Menu fileMenu = new Menu("Options");

        //telling the Exit menu item to exit application
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> Platform.exit());

        //creating the Add Movie option 
        MenuItem addMovie = new MenuItem("Add Movie");

        fileMenu.getItems().addAll(addMovie,
                new SeparatorMenuItem(), exitItem);

        //creating the Edit menu
        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(new MenuItem("Undo"), new MenuItem("Redo"));

        //creating About window 
        MenuItem aboutItem = new MenuItem("About...");
        aboutItem.setOnAction((ActionEvent event) -> {
            Stage popupAbout = new Stage();
            //popupAbout.initModality(Modality.APPLICATION_MODAL);
            popupAbout.initStyle(StageStyle.UTILITY);
            popupAbout.setTitle("About the Project");
            
            //creating a text area
            Text aboutText = new Text();
            
            //setting the text font
            aboutText.setFont(Font.font("times new roman", FontPosture.REGULAR, 15));
            
            //setting the position of the text
            //aboutText.setTextAlignment(TextAlignment.CENTER);
            //setting the About text
            String text = "N/A";
            
            aboutText.setText(text);
            
            //creating a layout for the text area
            VBox vbox = new VBox(aboutText);
            
            //creating the popup scene
            Scene popScene = new Scene(vbox, 1200, 300);
            popupAbout.setScene(popScene);
            popupAbout.showAndWait();
        });

        MenuItem helpItem = new MenuItem("Help Contents");
        helpItem.setOnAction((ActionEvent event) -> {
            Stage popupHelp = new Stage();
            //popupAbout.initStyle(StageStyle.UTILITY);
            popupHelp.initModality(Modality.APPLICATION_MODAL);
            popupHelp.setTitle("Help Contents");
            
            //creating a help menu
            Menu infoMenu = new Menu("Info");
            //infoMenu.getItems().addAll(new MenuItem("Creating a Gauge", new MenuItem());
            
            //creating a menu bar
            MenuBar infoBar = new MenuBar();
            infoBar.getMenus().addAll(infoMenu);
            
            //adding the menu bar to the popup window
            //creating a layout for the text area
            VBox vbox = new VBox(infoBar);
            
            //creating the popup scene
            Scene helpScene = new Scene(vbox, 500, 500);
            popupHelp.setScene(helpScene);
            popupHelp.showAndWait();
        });

        //creating the Help menu
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().addAll(aboutItem, new SeparatorMenuItem(), helpItem);

        //add all the menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        ToolBar toolBar = new ToolBar(new Button("Search"));
        VBox vbox = new VBox();

        //add toolbar here if wanted
        vbox.getChildren().addAll(menuBar, toolBar);

        ListView <String> list1 = new ListView<>();
        ListView <String> list2 = new ListView<>();
       
        //create new tabpane on the left
        TabPane tbLeft = new TabPane();
        TabPane tbRight = new TabPane();

        //set the tabs 
        Tab tab1 = new Tab("View Favorite Movies");
        Tab tab2 = new Tab("View All Movies");

        //fill the tabs with content from the checkbox tree view item lists
        tab1.setContent(list1);
        tab2.setContent(list2);

        //add the tab pane content to the left tab pane
        tbLeft.getTabs().addAll(tab1);
        
        tbRight.getTabs().addAll(tab2);
        
        Label exLabel = new Label(); 

        //************************
        //BORDER PANE SETUP 
        //************************
        
        //setting up the border pane
        borderPane.setTop(vbox);
        borderPane.setLeft(tbLeft);
        borderPane.setCenter(new TextArea("This could be where individual movies are displayed?"));
        borderPane.setRight(tbRight);
        borderPane.setBottom(exLabel);

        return borderPane;
    }
}