package com.uah.cs321.movie_database.database;

/**
 * Abstract class for a database object for use with a Database implementing the relevant interface.
 * @author Quen Parson
 * @version 1.0
 * @since 1.8
 */
public abstract class DatabaseEntry {
    /**
     * String representation of this entry, mainly used for testing/debugging.
     * @return Debug string of this entry.
     */
    public abstract String toString();
}
