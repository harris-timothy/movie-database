package com.uah.cs321.movie_database.database;

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
    // TODO LOADER?

    /**
     * Default constructor.
     */
    public MovieDatabase() {
        arr = new ArrayList<Movie>();
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
