/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.uah.cs321.main;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Boolean.FALSE;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.Spring.width;
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener; 
import javax.swing.JScrollPane;
import main.java.com.uah.cs321.movie_database.database.MovieDatabase;
import main.java.com.uah.cs321.movie_database.database.Movie;
/**
 * @author Alisa Brewer
 * @author Tim Harris
 * @author Riley Arnold
 * @author Quen Parson
 */
public class realGUIMaybe {
    public static void main(String[] args){
       String path = System.getProperty("user.dir");
        String pathSep = File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String fileName = "MovieDataTrimmed.csv";
        String finalFile = path  + pathSep + fileName;
        System.out.println(finalFile);
        MovieDatabase moviesObj = new MovieDatabase();
        try {
            moviesObj.populate(finalFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(realGUIMaybe.class.getName()).log(Level.SEVERE, null, ex);
        }
        Movie tempArr[] = new Movie[moviesObj.arr.size()];
        moviesObj.sortTitle(true);
        moviesObj.arr.toArray(tempArr);
        JList<Movie> movieList = new JList<Movie>(tempArr);
        //movieList.setBounds(20, 15, 300, 300);
        
        JFrame mainFrame = new JFrame("Main Frame");
        JFrame descriptionFrame = new JFrame("Movie Description");
        JFrame searchResultsFrame = new JFrame("Search Results");
        JPanel descriptionPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel mainLabel = new JLabel();
        JLabel textLabel = new JLabel();
        JLabel searchLabel = new JLabel();
        JTextField mainTextField= new JTextField(50);
        JButton searchButton = new JButton("GO");
        JButton quitButton = new JButton("QUIT");
        mainPanel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(movieList);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        mainFrame.setResizable(false);
        descriptionFrame.setResizable(false);
        searchResultsFrame.setResizable(false);
        searchButton.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //movieList.setBorder(BorderFactory.createLineBorder(Color.black));
//---------------------------------------------------------------------------------

//---------------------------------------------------------------------------------
        //Main window components
	mainLabel.setBounds(400, 0, 100, 60);
	textLabel.setBounds(100, 110, 200, 100);
        searchButton.setBounds(550, 20, 60, 20);
	mainTextField.setBounds(450, 20, 100, 20); 
        scrollPane.setBounds(20, 15, 415, 300);
        quitButton.setBounds(550, 20, 60, 20);
//--------------------------------------------------------------------------------- 
        searchButton.addActionListener(e->{
           searchResultsFrame.setDefaultCloseOperation(searchResultsFrame.DISPOSE_ON_CLOSE);
           searchResultsFrame.setSize(700, 700);
           searchResultsFrame.setLocationRelativeTo(null);
           searchResultsFrame.setVisible(true);
           searchLabel.setText("RESULTS...");
           searchLabel.setBounds(10, 0, 100, 60);
        });
//--------------------------------------------------------------------------------- 
        quitButton.addActionListener(e->{
                searchResultsFrame.dispose();
        });
//--------------------------------------------------------------------------------- 
        
	mainPanel.add(mainTextField);
        mainPanel.add(searchButton);
        mainFrame.setContentPane(mainPanel);
        searchResultsFrame.setContentPane(searchPanel);
        descriptionFrame.setContentPane(descriptionPanel);
        searchPanel.add(searchLabel);
        searchPanel.add(quitButton);
        mainFrame.add(scrollPane);
        mainFrame.getContentPane().setBackground(Color.lightGray);
        descriptionFrame.getContentPane().setBackground(Color.lightGray);
        searchResultsFrame.getContentPane().setBackground(Color.lightGray);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
} 
}
