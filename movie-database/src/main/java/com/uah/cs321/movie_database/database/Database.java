package main.java.com.uah.cs321.movie_database.database;

/**
 * Interface for a Database object, contains critical method declarations that need implementation for a valid database.
 * @author Quen Parson
 * @version 1.0
 * @since 1.8
 */
public interface Database {
    /**
     * Gets database entry at a specific index.
     * @param index Integer index in the range [0,database size - 1]
     * @return The database entry at that index.
     */
    DatabaseEntry getEntry(int index);

    /**
     * Removes the entry at a specified index.
     * @param index Integer index in the range [0,database size - 1] of the entry to remove.
     */
    void removeEntry(int index);
}
