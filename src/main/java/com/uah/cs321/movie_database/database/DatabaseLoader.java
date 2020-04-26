package main.java.com.uah.cs321.movie_database.database;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class for importing and exporting databases to file.
 * @author Quen Parson
 * @version 1.0
 * @since 1.8
 */
public class DatabaseLoader {
    /**
     * The scanner used for reading the files, this is necessary for keeping the place in the file.
     */
    private Scanner input;
    private int linenum = 1;

    /**
     * Opens a file and begins reading. This will not return any objects. Rather, it is needed for nextEntry to work().
     * @param filePath The path to the file to read.
     * @throws FileNotFoundException
     */
    public void openFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        input = new Scanner(file);
    }

    /**
     * Returns the next movie entry from a file opened using openFile.
     * @return The next movie entry in the file. To go backwards, resetReader() needs to be called.
     */
    public Movie nextEntry() {
        if (input == null) {
            return null;
        }
        if (input.hasNextLine()) {
            System.out.print(linenum++ + ": ");
            String line = input.nextLine();
            Movie out = new Movie();
            Scanner data = new Scanner(line);
            data.useDelimiter(",");
            // Check to see if title is in quotation marks
            String temp = data.next();
            String title;
            if (temp.charAt(0) == '"') {
                // Title may contain a comma, so use quotation marks as the delimiter
                data = new Scanner(line);
                data.useDelimiter("\"");
                title = data.next();
                data.useDelimiter(",");
                data.next();
            } else {
                // Title isn't in quotation marks
                data = new Scanner(line);
                data.useDelimiter(",");
                title = data.next();
            }
            out.setTitle(title);
            System.out.print(title + ", ");
            Date dt = new Date();
            dt.setYear(data.nextInt());
            out.setDate(dt);
            System.out.print(dt.toString() + ", ");
            float rating = -1.0f;
            if (data.hasNextFloat()) {
                rating = data.nextFloat();
                rating *= 5;
                rating = Math.round(rating);
            }
            out.setRating(Math.round(rating));
            System.out.print(Math.round(rating) + ", ");
            float bo = -1.0f;
            if (data.hasNextFloat()) {
                bo = data.nextFloat();
            }
            out.setBoxOffice(bo);
            System.out.println(bo);
            return out;
        }
        return null;
    }

    /**
     * Writes a database to file.
     * @param filePath The file path to write to. If no file exists then a file will be created.
     * @param arr The movie database array to export.
     */
    public void export(String filePath, ArrayList<Movie> arr) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // Iterate through array and add everything to file
            for (int i=0; i < arr.size(); i++) {
                String line = "";
                line += '"' + arr.get(i).getTitle() + '"' + ",";
                line += arr.get(i).getDate().getYear() + ",";
                float temp = (float) arr.get(i).getRating();
                temp /= 5;
                line += temp + ",";
                line += arr.get(i).getBoxOffice() + "\n";
                bw.write(line);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the reader to the top of the opened file.
     */
    public void resetReader() {
        if (input != null) {
            input.reset();
        }
    }
}
