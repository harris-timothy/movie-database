package com.uah.cs321.movie_database.database;

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
     * Replaces the database entry at specific index with newEntry.
     * @param index Integer index in the range [0,database size - 1]
     * @param newEntry The DatabaseEntry to overwrite
     */
    void replaceEntry(int index, DatabaseEntry newEntry);

    /**
     * Adds newEntry to the end of the database.
     * @param newEntry Entry to append to the database.
     */
    void appendEntry(DatabaseEntry newEntry);

    /**
     * Removes the entry at a specified index.
     * @param index Integer index in the range [0,database size - 1] of the entry to remove.
     */
    void removeEntry(int index);
}
