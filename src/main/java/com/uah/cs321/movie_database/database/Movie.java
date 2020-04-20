package com.uah.cs321.movie_database.database;

/**
 * DatabaseEntry class for the movie database.
 * @author Quen Parson
 * @version 1.0
 * @since 1.8
 */
public class Movie extends DatabaseEntry {
    /**
     * Title of the movie.
     */
    private String title;
    /**
     * Release date of the film.
     */
    private Date releaseDate;
    /**
     * Rating of the cinema in the range [0,50].
     * Divide by 10 to get the actual rating [0.0,5.0].
     */
    private int rating;
    /**
     * Approx box office returns of the feature in millions of USD.
     */
    private float boxOffice;

    /**
     * Default constructor.
     * Sets blank screen for title, default (N/A for every field) date, and 0 for every other field.
     */
    public Movie() {
        super();
        title = "";
        releaseDate = new Date();
        rating = 0;
        boxOffice = 0.0f;
    }

    /**
     * Full constructor.
     * @param newTitle Title of the film.
     * @param newDate Release date.
     * @param newRating Rating in the range [0,50], represents a 0-5 star rating with one decimal point (so a rating of 35 corresponds to 3.5 stars).
     * @param newBoxOffice Float for approx box office.
     */
    public Movie(String newTitle, Date newDate, int newRating, float newBoxOffice) {
        super();
        title = newTitle;
        releaseDate = newDate;
        rating = newRating;
        boxOffice = newBoxOffice;
    }

    /**
     * Gets the title of the film.
     * @return String title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the film.
     * @param newTitle The new title of the movie.
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    /**
     * Gets the date object of the film.
     * @return The release date of the film.
     */
    public Date getDate() {
        return releaseDate;
    }

    /**
     * Sets a new release date for the film.
     * @param newDate The new release date for the film.
     */
    public void setDate(Date newDate) {
        releaseDate = newDate;
    }

    /**
     * Gets the rating of the film in integer form.
     * @return The rating of the film in the range [0,50] for a 5 star rating system. Ex. 35 = 3.5 stars.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets a new rating for the film.
     * @param newRating The new rating for the movie, see the docs for getRating() for the format.
     */
    public void setRating(int newRating) {
        rating = newRating;
    }


    /**
     * Gets the approx box office returns for this film.
     * @return The approximate box office as a float.
     */
    public float getBoxOffice() {
        return boxOffice;
    }

    /**
     * Sets the box office value to a new value.
     * @param newBoxOffice The new box office value.
     */
    public void setBoxOffice(float newBoxOffice) {
        boxOffice = newBoxOffice;
    }

    /**
     * Debug/testing string of this film.
     * @return A string for debug/testing purposes.
     */
    @Override
    public String toString() {
        String out = "";
        // Add title to string
        if (title.isEmpty()) {
            out += "No Title, ";
        } else {
            out += title + ", ";
        }
        // Add date to string
        out += releaseDate.toString();
        return out;
    }
}
