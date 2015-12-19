package btterm;

/**
 *  An interface for terminal.
 *
 *  @author Mustafa Elsheikh 
 *  with inspirations form Franz-Josef Elmer
 */
public interface Terminal {

    /** Return <code>true</code> if there is no character typed. */
    public boolean isEmpty();

    /**
     *  Blocks for a line
     */
    public String read();

    /**
     *  Write a character to the terminal.
     */
    public void write(String s);

}
