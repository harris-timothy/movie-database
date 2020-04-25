/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.uah.cs321.main;

import javax.swing.JList;
import main.java.com.uah.cs321.movie_database.database.MovieDatabase;

/**
 *
 * @author Tim Harris
 */
public class Controller extends MovieDatabase {
    MovieDatabase moviesObj = new MovieDatabase();
    JList movieList = new JList(moviesObj.arr.toArray(new String[0]));
}
