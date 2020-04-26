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
import java.awt.Font;
import static java.awt.PageAttributes.MediaType.C;
import static java.awt.SystemColor.text;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import main.java.com.uah.cs321.movie_database.database.MovieDatabase;
import main.java.com.uah.cs321.movie_database.database.Movie;

/**
 * @author Tim Harris
 * @author Alisa Brewer
 * @author Riley Arnold
 * @author Quen Parson
 */
public class realGUIMaybe {
    static int getNum = 0;
    static boolean flipDate = true;
    static boolean flipTitle = true;
    static boolean dateSort = false;
    static boolean titleSort = false;
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
        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel favoritesModel = new DefaultListModel();
        DefaultListModel resultsModel = new DefaultListModel();
        moviesObj.sortTitle(true); // Default sort by ascending alphabetical order
        for (int i = 0; i < moviesObj.arr.size(); i++){
            listModel.addElement(moviesObj.arr.get(i));
        }
        Movie tempArr[] = new Movie[moviesObj.arr.size()];
        moviesObj.arr.toArray(tempArr);
        JList<Movie> movieList = new JList<Movie>(listModel);
        JList<Movie> resultsList = new JList<Movie>(resultsModel);
        JList<Movie> favoritesList = new JList<Movie>(favoritesModel);
        JFrame mainFrame = new JFrame("Main Frame");
        JFrame descriptionFrame = new JFrame("Movie Description");
        JFrame searchResultsFrame = new JFrame("Search Results");
        JPanel descriptionPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel mainLabel = new JLabel();
        JLabel favoritesLabel = new JLabel();
        JLabel falseLabel = new JLabel();
        JLabel trueLabel = new JLabel();
        JLabel searchLabel = new JLabel();
        JLabel searchPrompt = new JLabel();
        JTextField mainTextField= new JTextField(50);
        JButton addMovieButton = new JButton("Add Movie");
        JButton searchButton = new JButton("GO");
        JButton sortDateButton = new JButton("SORT - Year");
        JButton sortTitleButton = new JButton("SORT - Title");
        JButton quitButton = new JButton("QUIT");       
//---------------------------------------------------------------------------------       
        mainPanel.setLayout(null);
        searchPanel.setLayout(null);
        descriptionPanel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(movieList);
        JScrollPane scrollResults = new JScrollPane(resultsList);
        JScrollPane scrollFavorites = new JScrollPane(favoritesList);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        searchLabel.setText("RESULTS...");
        searchLabel.setForeground(Color.DARK_GRAY);        
//--------------------------------------------------------------------------------- 
        mainFrame.setResizable(false);
        descriptionFrame.setResizable(false);
        searchResultsFrame.setResizable(false);
        searchButton.setBackground(Color.white);
        sortDateButton.setBackground(Color.white);
        sortTitleButton.setBackground(Color.white);
        addMovieButton.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));      
//---------------------------------------------------------------------------------
        //New button sort stuff (TIM)
        sortDateButton.addActionListener(e->{
            moviesObj.sortTitle(true);
            if(flipDate == true){
                moviesObj.sortDate(true);
                listModel.removeAllElements();
                for (int i = 0; i < moviesObj.arr.size(); i++){
                    listModel.addElement(moviesObj.arr.get(i));
                }
                flipDate = false;
            }
            else{
               moviesObj.sortDate(false);
               listModel.removeAllElements();
                for (int i = 0; i < moviesObj.arr.size(); i++)
                {
                    listModel.addElement(moviesObj.arr.get(i));
                }
                flipDate = true;
            }
            titleSort = false;
        });        
        sortTitleButton.addActionListener(e->{
           if(flipTitle == true){
                moviesObj.sortTitle(false);
                listModel.removeAllElements();
                for (int i = 0; i < moviesObj.arr.size(); i++){
                    listModel.addElement(moviesObj.arr.get(i));
                }
                flipTitle = false;
            }
           else{
               moviesObj.sortTitle(true);
               listModel.removeAllElements();
                for (int i = 0; i < moviesObj.arr.size(); i++){
                    listModel.addElement(moviesObj.arr.get(i));
                }
                flipTitle = true;
            }
            dateSort = false;
        });     
//---------------------------------------------------------------------------------
        JLabel currentTitle = new JLabel();
        JLabel currentYear = new JLabel();
        JLabel currentRating = new JLabel();
        JLabel currentBoxOffice = new JLabel();
        movieList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    // Double-click detected
                    int index = list.getSelectedIndex();
                    //System.out.println("double-click");                    
                    currentTitle.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 24));                   
                    currentTitle.setBounds(10, 0, 500, 60);
                    currentTitle.setText(moviesObj.arr.get(index).getTitle());
                    descriptionPanel.add(currentTitle);
                    descriptionFrame.setSize(700,400);
                    descriptionFrame.setLocationRelativeTo(null);
                    descriptionFrame.setVisible(true);                  
                }
            }
        });        
//---------------------------------------------------------------------------------
        //Main window components
        searchLabel.setBounds(10, 0, 100, 60);
        mainLabel.setText("Movie Database");
        mainLabel.setForeground(Color.DARK_GRAY);
        mainLabel.setBounds(20, -5, 100, 60);
        favoritesLabel.setText("Favorites");
        favoritesLabel.setForeground(Color.DARK_GRAY);
	favoritesLabel.setBounds(450, 45, 100, 60);
        searchPrompt.setText("Search:");
        searchPrompt.setForeground(Color.DARK_GRAY);
        searchPrompt.setBounds(450, -5, 100, 60);
        trueLabel.setBounds(300, 50, 300, 100);
        searchButton.setBounds(605, 35, 60, 20);
        addMovieButton.setBounds(335, 600, 100, 50);
        sortDateButton.setBounds(20, 35, 195, 20);
        sortTitleButton.setBounds(219, 35, 200, 20);
	mainTextField.setBounds(450, 35, 150, 20); 
        scrollPane.setBounds(20, 60, 415, 535);
        scrollFavorites.setBounds(450, 85, 215, 510);
        quitButton.setBounds(550, 20, 60, 20);
        trueLabel.setText(text + " was found!");
        trueLabel.setForeground(Color.DARK_GRAY);
        trueLabel.setBounds(300, 575, 300, 100);
        falseLabel.setText("0 RESULTS");
        falseLabel.setForeground(Color.DARK_GRAY);
        falseLabel.setBounds(300, 60, 300, 100);          
//--------------------------------------------------------------------------------- 
        ArrayList<Movie> result = new ArrayList<Movie>();
        searchButton.addActionListener(e->{
           result.clear();
           boolean isFound = false;
           String text = mainTextField.getText();           
           System.out.println(text);
           for(int i = 0; i < tempArr.length; i++ ){ 
               if(tempArr[i].getTitle().toLowerCase().contains(text.toLowerCase())){
                   result.add(tempArr[i]);
                   isFound = true;                   
               }
           }           
            getNum = result.size();
            trueLabel.setText(getNum + " results found.");           
            if(isFound == true)
            {
                for (int i = 0; i < result.size(); i++){
                    resultsModel.addElement(result.get(i));
                }
                Movie tempResults[] = new Movie[result.size()];
                result.toArray(tempResults);
                scrollResults.setBounds(20, 45, 415, 550);
                searchResultsFrame.add(scrollResults);
                searchPanel.remove(falseLabel);
                searchPanel.add(trueLabel);
            }
            if(isFound == false){
                searchPanel.remove(trueLabel);
                searchPanel.add(falseLabel);
                searchResultsFrame.remove(scrollResults);
            }
            searchResultsFrame.setDefaultCloseOperation(searchResultsFrame.DISPOSE_ON_CLOSE);
            searchResultsFrame.setSize(700, 700);
            searchResultsFrame.setLocationRelativeTo(null);
            searchResultsFrame.setVisible(true);
        });       
//--------------------------------------------------------------------------------- 
        quitButton.addActionListener(e->{
                searchResultsFrame.dispose();
                resultsModel.removeAllElements();
        });     
//--------------------------------------------------------------------------------- 
        Border blackline = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(blackline);         
	mainPanel.add(mainTextField);
        //searchFrame.add(trueLabel);
        mainPanel.add(searchButton);
        mainPanel.add(mainLabel);
        mainPanel.add(favoritesLabel);
        mainPanel.add(sortDateButton);
        mainPanel.add(sortTitleButton);
        mainPanel.add(searchPrompt);
        mainPanel.add(addMovieButton);
        mainFrame.setContentPane(mainPanel);
        searchResultsFrame.setContentPane(searchPanel);
        descriptionFrame.setContentPane(descriptionPanel);
        searchPanel.add(searchLabel);
        searchPanel.add(quitButton);
        mainFrame.add(scrollPane);
        mainFrame.add(scrollFavorites);
        mainFrame.getContentPane().setBackground(Color.lightGray);
        descriptionFrame.getContentPane().setBackground(Color.lightGray);
        searchResultsFrame.getContentPane().setBackground(Color.lightGray);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    } 
}
