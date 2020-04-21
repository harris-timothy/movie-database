package main.java.com.uah.cs321.movie_database.database;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Database for movie storage. Implements the Database interface and handles movie objects.
 * @author Quen Parson
 * @version 1.0
 * @since 1.8
 */
public class MovieDatabase implements Database {
    /**
     * Storage for database.
     */
    private ArrayList<Movie> arr;
    private DatabaseLoader loader;


    /**
     * Default constructor.
     */
    public MovieDatabase() {
        arr = new ArrayList<Movie>();
        loader = new DatabaseLoader();
    }

    /**
     * Advanced constructor for populating automatically upon creation.
     * @param filePath The path to the file for input. This is fed to the loader so see documentation there for specifics.
     */
    public MovieDatabase(String filePath) throws FileNotFoundException {
        arr = new ArrayList<Movie>();
        loader = new DatabaseLoader();
        populate(filePath);
    }

    /**
     * Uses the database loader to read a file and populate the database with data from said file.
     * @param filePath The path of the file to read from.
     */
    public void populate(String filePath) throws FileNotFoundException {
        loader.openFile(filePath);
        Movie data = loader.nextEntry();
        while (data != null) {
            arr.add(data);
            data = loader.nextEntry();
        }
    }

    /**
     * Uses the database loader to save this database to file.
     * @param filepath The path of the output file to write to. If no file exists, will create one.
     */
    public void export(String filepath) {
        loader.export(filepath,arr);
    }

    /**
     * Tries to find the index of the movie with a specified title.
     * @param name Title of the movie to look for.
     * @return Index of the movie with the title, or -1 if no such movie exists.
     */
    public int searchTitle(String name) {
        for (int i=0; i < arr.size(); i++) {
            if (arr.get(i).getTitle().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts the database alphabetically by movie title.
     * @param ascending If true, sorts ascending (A-Z). If false, sorts descending (Z-A).
     */
    public void sortTitle(boolean ascending) {
        ArrayList<Movie> newArr = new ArrayList<Movie>();
        // Keep going until main array is empty.
        while (arr.size() > 0) {
            // Index of current max or min, depending on sort.
            int extrema = 0;
            for (int i=0; i < arr.size(); i++) {
                // Check sorting method
                if (ascending) {
                    if (arr.get(extrema).getTitle().compareTo(arr.get(i).getTitle()) > 0) {
                        extrema = i;
                    }
                } else {
                    if (arr.get(extrema).getTitle().compareTo(arr.get(i).getTitle()) < 0) {
                        extrema = i;
                    }
                }
            }
            newArr.add(arr.get(extrema));
            arr.remove(extrema);
        }
        // Swap the arrays.
        arr = newArr;
    }

    /**
     * Sorts the database by rating.
     * @param ascending If true, sorts ascending (lowest to highest). If false, sorts descending (highest to lowest).
     */
    public void sortRating(boolean ascending) {
        ArrayList<Movie> newArr = new ArrayList<Movie>();
        // Keep going until main array is empty.
        while (arr.size() > 0) {
            // Index of current max or min, depending on sort.
            int extrema = 0;
            for (int i=0; i < arr.size(); i++) {
                // Check sorting method
                if (ascending) {
                    if (arr.get(extrema).getRating() > arr.get(i).getRating()) {
                        extrema = i;
                    }
                } else {
                    if (arr.get(extrema).getRating() < arr.get(i).getRating()) {
                        extrema = i;
                    }
                }
            }
            newArr.add(arr.get(extrema));
            arr.remove(extrema);
        }
        // Swap the arrays.
        arr = newArr;
    }

    /**
     * Gets a movie object from the database.
     * @param index Integer index in the range [0,database size - 1]
     * @return Movie data at specified index.
     */
    public Movie getEntry(int index) {
        // Make sure the index is actually within the array boundaries
        if ((index < 0) || (index > (arr.size()-1))) {
            return null;
        }
        return arr.get(index);
    }

    /**
     * Overwrites a movie at the specified index with a new movie.
     * @param index The index to overwrite.
     * @param newMovie The movie to replace with.
     */
    public void replaceEntry(int index, Movie newMovie) {
        // Make sure the index is actually within the array boundaries
        if ((index < 0) || (index > (arr.size()-1))) {
            return;
        }
        arr.set(index,newMovie);
    }

    /**
     * Appends a movie to the end of the database.
     * @param newMovie Movie to insert.
     */
    public void appendEntry(Movie newMovie) {
        arr.add(newMovie);
    }

    /**
     * Removes the movie at the specified index.
     * @param index Integer index in the range [0,database size - 1] of the entry to remove.
     */
    public void removeEntry(int index) {
        // Make sure the index is actually within the array boundaries
        if ((index < 0) || (index > (arr.size()-1))) {
            return;
        }
        arr.remove(index);
    }
}
