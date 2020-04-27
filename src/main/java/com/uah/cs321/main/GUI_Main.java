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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
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
public class GUI_Main {
    static int getNum = 0;
    static boolean flipDate = true;
    static boolean flipTitle = true;
    static boolean dateSort = false;
    static boolean titleSort = false;
    static boolean duplicate = false;
    static int index = 0;
    static boolean removeButton = false;
    public static void main(String[] args){
//--------------------------------------------------------------------------------- 
//      DECLARATIONS
//--------------------------------------------------------------------------------- 
        String path = System.getProperty("user.dir");
        String pathSep = File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String fileName = "MovieDataTrimmed.csv";
        String finalFile = path  + pathSep + fileName;
        String favesName = "Favorites.csv";
        String favesFile = path + pathSep + favesName;
        MovieDatabase moviesObj = new MovieDatabase();
        MovieDatabase favesObj = new MovieDatabase();
        
        try {
            moviesObj.populate(finalFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try {
            favesObj.populate(favesFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel favoritesModel = new DefaultListModel();
        DefaultListModel resultsModel = new DefaultListModel();
        moviesObj.sortTitle(true); // Default sort by ascending alphabetical order
        favesObj.sortTitle(true); // Default sort by ascending alphabetical order
        
        for (int i = 0; i < moviesObj.arr.size(); i++){
            listModel.addElement(moviesObj.arr.get(i));
        }
        
        for (int i = 0; i < favesObj.arr.size(); i++)
        {
            favoritesModel.addElement(favesObj.arr.get(i));
        }
        
        ArrayList<Movie> result = new ArrayList<Movie>();
        Movie tempArr[] = new Movie[moviesObj.arr.size()];
        moviesObj.arr.toArray(tempArr);
        JList<Movie> movieList = new JList<Movie>(listModel);
        JList<Movie> resultsList = new JList<Movie>(resultsModel);
        JList<Movie> favoritesList = new JList<Movie>(favoritesModel);
        JFrame mainFrame = new JFrame("Main Frame");
        JFrame descriptionFrame = new JFrame("Movie Description");
        JFrame searchResultsFrame = new JFrame("Search Results");
        JFrame errorFrame = new JFrame("ERROR");
        JPanel errorPanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel mainLabel = new JLabel();
        JLabel favoritesLabel = new JLabel();
        JLabel falseLabel = new JLabel();
        JLabel trueLabel = new JLabel();
        JLabel searchLabel = new JLabel();
        JLabel searchPrompt = new JLabel();
        JLabel currentTitle = new JLabel();
        JLabel currentYear = new JLabel();
        JLabel currentRating = new JLabel();
        JLabel currentBoxOffice = new JLabel();
        JTextField mainTextField= new JTextField(50);
        //JButton addMovieButton = new JButton("Add Movie");
        JButton addFavoritesButton = new JButton("Add to Favorites");
        JButton removeFavoritesButton = new JButton("Remove from Favorites");
        JButton resultsFavoritesButton = new JButton("Add to Favorites");
        JButton errorClose = new JButton("CLOSE");
        JButton searchButton = new JButton("GO");
        JButton sortDateButton = new JButton("SORT - Year");
        JButton sortTitleButton = new JButton("SORT - Title");
        JButton quitButton = new JButton("QUIT");       
//---------------------------------------------------------------------------------  
//      LAYOUT
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
        mainFrame.setResizable(false);
        descriptionFrame.setResizable(false);
        searchResultsFrame.setResizable(false);
        errorClose.setBackground(Color.white);
        searchButton.setBackground(Color.white);
        sortDateButton.setBackground(Color.white);
        sortTitleButton.setBackground(Color.white);
        //addMovieButton.setBackground(Color.white);
        removeFavoritesButton.setBackground(Color.white);
        addFavoritesButton.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));      
//---------------------------------------------------------------------------------
//      Sort Main List by Date
//---------------------------------------------------------------------------------
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
//---------------------------------------------------------------------------------      
//      Sort Main List by Title
//---------------------------------------------------------------------------------
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
//      Double-Click Main List Functionality
//---------------------------------------------------------------------------------
        movieList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    // Double-click detected
                    index = list.getSelectedIndex();
                    System.out.println("movieList");
                    descriptionPanel.remove(removeFavoritesButton);
                    float trueRating = (float)moviesObj.arr.get(index).getRating()/10;
                    BigDecimal accBoxOffice = BigDecimal.valueOf(moviesObj.arr.get(index).getBoxOffice());
                    int trueBoxOffice = accBoxOffice.multiply(new BigDecimal(100)).intValue() * 10000;
                    String starString = "";
                    String ratingString = Float.toString(trueRating);
                    currentTitle.setText(moviesObj.arr.get(index).getTitle());
                    currentYear.setText(moviesObj.arr.get(index).getDate().toString());
                    for(int i = 0; i < Math.round(trueRating); i++)
                    {
                        starString = starString + "*";
                    }
                    currentRating.setText("Rating: " + starString + "(" + ratingString + ")");
                    currentBoxOffice.setText("Box Office Returns: $" + Integer.toString(trueBoxOffice));
                    addFavoritesButton.setBounds(250, 200, 200, 40);
                    descriptionPanel.add(currentTitle);
                    descriptionPanel.add(currentYear);
                    descriptionPanel.add(currentRating);
                    descriptionPanel.add(currentBoxOffice);
                    descriptionPanel.add(addFavoritesButton);
                    descriptionFrame.setDefaultCloseOperation(descriptionFrame.DISPOSE_ON_CLOSE);
                    descriptionFrame.setSize(500,300);
                    descriptionFrame.setLocationRelativeTo(null);
                    descriptionFrame.setVisible(true);                  
                }
            }
        });   
//--------------------------------------------------------------------------------- 
//      Add 'Add-to-Favorites' Button to Description Window
//---------------------------------------------------------------------------------
        addFavoritesButton.addActionListener(e->{
            duplicate = false;
            for(int i = 0; i < favesObj.arr.size(); i++)
            {
                if(favesObj.arr.get(i).getTitle().equals(moviesObj.arr.get(index).getTitle()))
                {
                    duplicate = true;
                    break;
                }
            }
            if(duplicate == false)
            {
                favesObj.appendEntry(moviesObj.arr.get(index));
                favesObj.export(favesFile);
                favoritesModel.removeAllElements();
                for (int i = 0; i < favesObj.arr.size(); i++){
                    favoritesModel.addElement(favesObj.arr.get(i));
                }
            }
            else
            {
                errorFrame.setVisible(true);
            }
                 
        });  
//---------------------------------------------------------------------------------
//      Add 'Remove-from-Favorites' Button to Description Window
//--------------------------------------------------------------------------------- 
        removeFavoritesButton.addActionListener(e->{           
            favesObj.removeEntry(index);
            favesObj.export(favesFile);
            favoritesModel.removeAllElements();
            for (int i = 0; i < favesObj.arr.size(); i++){
                favoritesModel.addElement(favesObj.arr.get(i));
            }
            descriptionFrame.dispose();
        }); 
//--------------------------------------------------------------------------------- 
//      Double-Click Favorites List Functionality
//---------------------------------------------------------------------------------
        favoritesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    // Double-click detected
                    index = list.getSelectedIndex();
                    System.out.println("favoritesList");
                    descriptionPanel.remove(addFavoritesButton); 
                    float trueRating = (float)favesObj.arr.get(index).getRating()/10;
                    BigDecimal accBoxOffice = BigDecimal.valueOf(favesObj.arr.get(index).getBoxOffice());
                    int trueBoxOffice = accBoxOffice.multiply(new BigDecimal(100)).intValue() * 10000;
                    String starString = "";
                    String ratingString = Float.toString(trueRating);
                    currentTitle.setText(favesObj.arr.get(index).getTitle());
                    currentYear.setText(favesObj.arr.get(index).getDate().toString());
                    for(int i = 0; i < Math.round(trueRating); i++)
                    {
                        starString = starString + "*";
                    }
                    currentRating.setText("Rating: " + starString + "(" + ratingString + ")");
                    currentBoxOffice.setText("Box Office Returns: $" + Integer.toString(trueBoxOffice));
                    removeFavoritesButton.setBounds(250, 200, 200, 40);
                    descriptionPanel.add(currentTitle);
                    descriptionPanel.add(currentYear);
                    descriptionPanel.add(currentRating);
                    descriptionPanel.add(currentBoxOffice);
                    descriptionPanel.add(removeFavoritesButton);
                    descriptionFrame.setDefaultCloseOperation(descriptionFrame.DISPOSE_ON_CLOSE);
                    descriptionFrame.setSize(500,300);
                    descriptionFrame.setLocationRelativeTo(null);
                    descriptionFrame.setVisible(true);                  
                }
            }
        });    
//---------------------------------------------------------------------------------
//      Main window components
//---------------------------------------------------------------------------------
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
        //addMovieButton.setBounds(335, 600, 100, 50);
        sortDateButton.setBounds(20, 35, 195, 20);
        sortTitleButton.setBounds(219, 35, 200, 20);
	mainTextField.setBounds(450, 35, 150, 20); 
        scrollPane.setBounds(20, 60, 415, 535);
        scrollFavorites.setBounds(450, 85, 215, 510);
        quitButton.setBounds(535, 20, 75, 40);
        trueLabel.setText(text + " was found!");
        trueLabel.setForeground(Color.DARK_GRAY);
        trueLabel.setBounds(300, 575, 300, 100);
        falseLabel.setText("0 RESULTS");
        falseLabel.setForeground(Color.DARK_GRAY);
        falseLabel.setBounds(300, 60, 300, 100);          
//--------------------------------------------------------------------------------- 
//      Search Functionality
//---------------------------------------------------------------------------------
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
//      Double-Click Search Results List Functionality
//---------------------------------------------------------------------------------
        resultsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    // Double-click detected
                    index = list.getSelectedIndex();
                    System.out.println(result.get(index));
                    float trueRating = (float)result.get(index).getRating()/10;
                    BigDecimal accBoxOffice = BigDecimal.valueOf(result.get(index).getBoxOffice());
                    int trueBoxOffice = accBoxOffice.multiply(new BigDecimal(100)).intValue() * 10000;
                    String starString = "";
                    String ratingString = Float.toString(trueRating);
                    currentTitle.setText(result.get(index).getTitle());
                    currentYear.setText(result.get(index).getDate().toString());
                    for(int i = 0; i < Math.round(trueRating); i++)
                    {
                        starString = starString + "*";
                    }
                    currentRating.setText("Rating: " + starString + "(" + ratingString + ")");
                    currentBoxOffice.setText("Box Office Returns: $" + Integer.toString(trueBoxOffice));
                    resultsFavoritesButton.setBounds(250, 200, 200, 40);
                    descriptionPanel.add(currentTitle);
                    descriptionPanel.add(currentYear);
                    descriptionPanel.add(currentRating);
                    descriptionPanel.add(currentBoxOffice);
                    descriptionPanel.add(resultsFavoritesButton);
                    descriptionFrame.setDefaultCloseOperation(descriptionFrame.DISPOSE_ON_CLOSE);
                    descriptionFrame.setSize(500,300);
                    descriptionFrame.setLocationRelativeTo(null);
                    descriptionFrame.setVisible(true);                  
                }
            }
        });   
//--------------------------------------------------------------------------------- 
//      Add 'Add-to-Favorites' Button to Search-Result Description Window
//---------------------------------------------------------------------------------
        resultsFavoritesButton.addActionListener(e->{       
            duplicate = false;
            for(int i = 0; i < favesObj.arr.size(); i++)
            {
                if(favesObj.arr.get(i).getTitle().equals(result.get(index).getTitle()))
                {
                    duplicate = true;
                    break;
                }
            }
            if(duplicate == false)
            {
                favesObj.appendEntry(result.get(index));
                favesObj.export(favesFile);
                favoritesModel.removeAllElements();
                for (int i = 0; i < favesObj.arr.size(); i++){
                    favoritesModel.addElement(favesObj.arr.get(i));
                }
            }
            else
            {
                errorFrame.setVisible(true);
            }    
        });     
//--------------------------------------------------------------------------------- 
//      Add Quit Button Functionality
//---------------------------------------------------------------------------------
        quitButton.addActionListener(e->{
                searchResultsFrame.dispose();
                resultsModel.removeAllElements();
        });     
//---------------------------------------------------------------------------------
//      Clears search results from list if the user doesn't use the quit button
//---------------------------------------------------------------------------------
        searchResultsFrame.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                searchResultsFrame.dispose();
                resultsModel.removeAllElements();
                
            }
        });
//---------------------------------------------------------------------------------
//      Add Close Button Functionality
//---------------------------------------------------------------------------------
        errorClose.addActionListener(e->{
                errorFrame.dispose();
        });     
         
//---------------------------------------------------------------------------------
//      Formatting
//---------------------------------------------------------------------------------
        Border blackline = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(blackline);         
	mainPanel.add(mainTextField);
        errorPanel.setLayout(null);
        errorFrame.setResizable(false);
        JLabel errorHeader = new JLabel();
        JLabel errorDesc = new JLabel(); 
        currentTitle.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 24));                   
        currentTitle.setBounds(10, 0, 450, 60);
        currentYear.setFont(new Font("Arial", Font.PLAIN, 18));
        currentYear.setBounds(10,25,500,60);
        currentRating.setFont(new Font("Arial", Font.PLAIN, 18));
        currentRating.setBounds(10,45,500,60);
        currentBoxOffice.setFont(new Font("Arial", Font.PLAIN, 18));
        currentBoxOffice.setBounds(10,65,500,60);
        errorHeader.setText("ERROR:");
        errorDesc.setText("Movie already exists in Favorites List.");
        errorHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 24));
        errorDesc.setFont(new Font("Arial", Font.PLAIN, 18));
        errorHeader.setForeground(Color.RED);
        errorHeader.setBounds(10, 10, 300,60);
        errorDesc.setBounds(10,35,300,60);
        errorClose.setBounds(250,100,100,50);
        errorPanel.add(errorClose);
        errorPanel.add(errorHeader);
        errorPanel.add(errorDesc);
        errorFrame.setContentPane(errorPanel);
        errorFrame.getContentPane().setBackground(Color.lightGray);
        errorFrame.setDefaultCloseOperation(errorFrame.DISPOSE_ON_CLOSE);
        errorFrame.setSize(400, 200);
        errorFrame.setLocationRelativeTo(null);
        mainPanel.add(searchButton);
        mainPanel.add(mainLabel);
        mainPanel.add(favoritesLabel);
        mainPanel.add(sortDateButton);
        mainPanel.add(sortTitleButton);
        mainPanel.add(searchPrompt);
        //mainPanel.add(addMovieButton);
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
