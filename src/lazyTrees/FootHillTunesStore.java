package lazyTrees;

import java.io.*;
import java.util.*;

/**
 * Manages a LazySearchTree of SongEntry objects
 */
public class FootHillTunesStore {
    public static final boolean SHOW_DETAILS = true;

    PrintObject<SongEntry> printObject = new PrintObject<SongEntry>();

    // The data structure, which we use to add and remove items.
    private LazySearchTree<SongEntry> tunes;

    /**
     * Instantiates inventory to be a LazySearchTree of SongEntry objects.
     */
    public FootHillTunesStore()
    {
        tunes = new LazySearchTree<SongEntry>();
    }

    /**
     * Add a SongEntry object
     * @param song
     */
    public void addSong(SongEntry song)
    {
        tunes.insert(song);
    }

    /**
     * Removes a song
     * @param song
     */
    public void removeSong(SongEntry song)
    {

        boolean isFound = tunes.contains(song);

        // check if the item exists in the inventory disregarding lazy deletion
        if (!isFound)
        {
            throw new NoSuchElementException();
        }

        tunes.remove(song);
    }


    /**
     * Display the first item and last song of the soft tree in lexical order.
     */
    public void showFirstAndLastSong()
    {
        try
        {
            SongEntry min = tunes.findMin();
            System.out.println ( "First song: " + min.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: minimum element not found!");
        }

        try
        {
            SongEntry max = tunes.findMax();
            System.out.println ( "Last song: " + max.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: maximum element not found!");
        }

    }


    /**
     * Displays both the hard and soft state of the tree
     */
    protected void displayTunesState()
    {

        System.out.println("\"hard\" number of songs (i.e. mSizeHard) = " + tunes.sizeHard());
        System.out.println("\"soft\" number of songs (i.e. mSize) = " + tunes.size());

        System.out.println( "\nTesting traversing \"hard\" tunes list:");

        // TODO: First, rename the public/private pair traverse() method of FHsearch_tree to traverseHard() method.
        //       Then, reuse this public/private pair of methods to traverses the tree
        //       and displays all the nodes.
        // NOTE: Here, we call the public version.
        tunes.traverseHard(printObject);


        System.out.println( "\n\nTesting traversing \"soft\" tunes list:");

        // TODO: Define a public/private pair of methods that traverses the tree
        //       and displays only nodes that have not been lazily deleted.
        // NOTE: Here, we call the public version.
        tunes.traverseSoft(printObject);
        System.out.println("\n");
    }

    public static void main(String[] args)
    {
        FootHillTunesStore fh = new FootHillTunesStore();

        int flag = 1;
        while(flag == 1)
        {
            System.out.println("Welcome to Foothill Tunes Store! What would you like to do?");
            System.out.println("1) Add a song");
            System.out.println("2) Remove a song");
            System.out.println("3) Display tunes statistics");
            System.out.println("4) Exit");

            Scanner keyboard = new Scanner(System.in);
            int selection;
            try {
                System.out.println("\nEnter the number of your choice: ");
                selection = Integer.parseInt(keyboard.nextLine());
            }
            catch (NumberFormatException ex)
            {
                selection = 5;
            }
            switch(selection)
            {
                case 1:
                    try {
                        System.out.print("Enter the Song title: ");
                        String title = keyboard.nextLine();
                        System.out.print("Enter the Song duration:");
                        int duration = Integer.parseInt(keyboard.nextLine());
                        System.out.print("Enter the Song artist: ");
                        String artist = keyboard.nextLine();
                        System.out.print("Enter the Song genre: ");
                        String genre = keyboard.nextLine();
                        fh.addSong(new SongEntry(title, duration, artist, genre));
                    }
                    catch(NumberFormatException ex)
                    {
                        System.out.println("Invalid Input\n");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter the Song title: ");
                    String title = keyboard.nextLine();
                    try {
                        fh.removeSong(new SongEntry(title, 0, null, null));
                    }
                    catch (java.util.NoSuchElementException ex)
                    {
                        System.out.printf("\nWarning: Unable to remove song since it wasn't found  \n");
                    }
                    break;
                case 3:
                    fh.showFirstAndLastSong();
                    fh.displayTunesState();
                    break;
                case 4:
                    flag = 0;
                    break;
                default:
                    System.out.println("Invalid input\n");
            }


        }

        // flush the error stream
        System.err.flush();

        System.out.println("\nDone with FootHillTunesStore.");
    }
}
