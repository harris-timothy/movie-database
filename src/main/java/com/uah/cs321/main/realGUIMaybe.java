/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.uah.cs321.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javax.swing.*;
import main.java.com.uah.cs321.movie_database.database.MovieDatabase;


/**
 *
 * @author 9abrewer
 */
public class realGUIMaybe {
  private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
   }
} 
   // public static final int HEIGHT = 800;
    //public static final int WIDTH = 800;
    public static void main(String[] args){
//---------------------------------------------------------------------------------
        //Declarations
        
        String path = System.getProperty("user.dir");
        System.out.println(path);
        MovieDatabase moviesObj = new MovieDatabase();
        //MovieDatabase.populate(pathToFile);
        JList movieList = new JList(moviesObj.arr.toArray(new String[0]));
        JFrame mainFrame = new JFrame("Main Frame");
        JFrame descriptionFrame = new JFrame("Movie Description");
        JFrame searchResultsFrame = new JFrame("Search Results");
        JPanel mainPanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        mainPanel.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
//---------------------------------------------------------------------------------
        //Main window components
        JLabel mainLabel = new JLabel();		
        mainLabel.setText("Enter Name :");
	mainLabel.setBounds(10, 10, 100, 100);
        movieList.setBounds(20, 10, 300, 300);
        
        JLabel textLabel = new JLabel();
	textLabel.setBounds(100, 110, 200, 100);
	
        JTextField mainTextField= new JTextField(50);
        mainTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = mainTextField.getText();
                    System.out.println(text);
                }
            }
        });
        
	mainTextField.setBounds(110, 50, 100, 40);      
        JButton searchButton = new JButton("SEARCH");
        searchButton.setBounds(50, 200, 100, 50);
        searchButton.addActionListener(e->{
           searchResultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           searchResultsFrame.setSize(width/2, height/2);
           searchResultsFrame.setLocationRelativeTo(null);
           searchResultsFrame.setVisible(true);
        });
     
        
        mainFrame.setContentPane(mainPanel);
        mainPanel.add(searchButton);
        mainPanel.add(mainLabel);
        mainPanel.add(textLabel);
	mainPanel.add(mainTextField);
        mainPanel.add(searchButton);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(width/2, height/2);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
