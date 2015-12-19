package btterm;

import java.util.Vector;
import javax.bluetooth.RemoteDevice;

import java.io.IOException;
import java.util.Timer;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.io.StreamConnection;
import java.io.InputStreamReader;
import javax.microedition.lcdui.*;
import java.io.*;
import javax.microedition.io.Connector;

/**
 * MIDPTerminal: J2ME MIDP Canvas Terminal Component (Simple Console for MIDP)
 * Provides low level UI input/output along with graphics.
 * Allows interactive command line interface (CLI) for MIDP applications.
 * First create an instance of the MIDPTerminal passing both the display and the parent
 * screen (in case of pressing back). 
 * Communicate with terminal by (blocking) read() and write(). Threads are mandatory here. 
 *
 *
 * @example
 *   // create the terminal
 *   MIDPTerminal term = new MIDPTerminal(Display.getDisplay(this), get_form1());
 *   // switch to the terminal
 *   Display.getDisplay(this).setCurrent(term);
 *   // send to terminal
 *   sterm.write(toTerminalString);
 *   // receive from terminal
 *   String fromTerminalString = sterm.read();
 *   <use getGraphics(), cls() for performing graphics on the termnial>
 *
 * Published under GPL at cellbasic.sourceforge.net/midpterminal
 * @author Mustafa Elsheikh
 * with inspirations form Franz-Josef Elmer
 */
public class MIDPTerminal extends Canvas implements Terminal {
    public String URL = "";
    StringBuffer strbuf = new StringBuffer();
    List list = null;
    Vector two = new Vector();

    public String output;
    StringBuffer sb = new StringBuffer(0);
    BluetoothDeviceDiscovery disc;
    public String col[]= null;
    public String BUG = "";
    BTTerm midi;
    public  boolean what;
    private char[][] content;
    private short numberOfRows, numberOfColumns;
    private int cursorRow, cursorCol;
    private int width, height;
    private short charHeight, charWidth;
    private Font font;
    private Image offscreenT;
    private Image offscreenG;
    private char[] typeAheadBuffer = new char[20];
    private int freePos = 0, nextPos = 0;
    private Timer keyTimer = null;
    boolean timerActive = false, upperCase = false, userInput = false, starInput = false;
    private String keys[] = {
        " 0=", "1.,'", "abc2", "def3", "ghi4", "jkl5",
                "mno6", "pqrs7", "tuv8", "wxyz9",
                "?!\"-()@/:_;+&%*=<>$[]{}\\~^#|",  "#"
    };
    Display display = null; // handle to display MIDlet
    Displayable parent = null;

    public MIDPTerminal(Display display, Displayable parent) {
        super();

        this.display = display;
        this.parent = parent;
        
        initialize();
        this.write("> BlueTerm v1.0" + "\n");        
    }
    

    public void initialize()  {

        
        width = getWidth();
        height = getHeight();
        offscreenT = Image.createImage(width, height);
        offscreenG = Image.createImage(width, height);
        // get font and metrics
        font = Font.getFont( Font.FACE_MONOSPACE,
                Font.STYLE_PLAIN, Font.SIZE_SMALL);
        charHeight = (short) font.getHeight();
        charWidth = (short) font.stringWidth("w");
        // calculate how many rows and columns we display
        numberOfColumns = (short) ( width / charWidth );
        numberOfRows = (short) ( height / charHeight );
        content = new char[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            content[i] = new char[numberOfColumns];
        }
        cls();
        
    }
    
    public Graphics getGraphics() {
        return offscreenG.getGraphics();
    }
    
    public void cls() {
        clearGraphixScreen();
        clearTextScreen();
    }
    
    /** Clear screen and set the cursor into the upper left corner. */
    public void clearTextScreen() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                content[i][j] = ' ';
            }
        }
        cursorRow = 0;
        cursorCol = 0;
        Graphics g = offscreenT.getGraphics();
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, width, height);
        repaint();
    }
    
    /** clear graphics */
    public void clearGraphixScreen() {
        clearTextScreen();
        Graphics g = offscreenG.getGraphics();
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, width, height);
        repaint();
    }
    
    private void processLineFeed() {
        if (cursorRow == numberOfRows - 1) { // scroll
            scrollDown();
        } else {
            cursorRow++;
        }
    }
    
    private void scrollDown() {
        char[] row = content[0];
        System.arraycopy(content, 1, content, 0, numberOfRows - 1);
        content[cursorRow] = row;
        for (int i = 0; i < numberOfColumns; i++) {
            row[i] = ' ';
        }
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    public void paint(Graphics g) {
        Graphics g1 = offscreenT.getGraphics();
        g1.setFont(font);
        //g.setStrokeStyle(g.DOTTED);
        g1.setColor(0);
        g1.fillRect(0, 0, width, height);
        g1.setColor(255, 255, 255);
        g1.drawImage(offscreenG, 0, 0, 0);
        int y = 0;
        content[cursorRow][cursorCol] = '_';
        for (int i = 0; i < numberOfRows; i++) {
            //String ss = String.valueOf(content[i]).trim();
            //g1.drawString(ss, 0, y, 0);
            g1.drawChars(content[i], 0, numberOfColumns, 0, y, 0);
            y += charHeight;
        }
        //System.out.println(content[i]);
        g.drawImage(offscreenT, 0, 0, 0);
    }
    

//  Implementation of the Terminal interface
//
    
    public boolean isEmpty() {
        return nextPos == freePos;
    }
    
    public char readChar() {
        if (isEmpty()) {
            return '\0';
        } else {
            char c = typeAheadBuffer[nextPos++];
            nextPos %= typeAheadBuffer.length;
            return c;
        }
    }
    
    public synchronized char getNextChar() {
        if (isEmpty()) {
            try {
                wait();
            } catch (Exception e) {}
        }
        char c = typeAheadBuffer[nextPos++];
        nextPos %= typeAheadBuffer.length;
        return c;
    }
    
    /**
     *  Write a character onto the screen. The following control
     *  characters will be treated specially:
     *  <table border=1>
     *      <tr><td><code>\r</code></td><td></td></tr>
     *      <tr><td><code>\n</code></td><td></td></tr>
     *      <tr><td><code>\t</code></td><td></td></tr>
     *      <tr><td><code>\f</code></td><td></td></tr>
     *      <tr><td><code>\b</code></td><td></td></tr>
     *  </table>
     * @param c
     */
    public void writeChar(char c) {
        /**
         * NEXT IINE IS BUGGY:
         * the idea behine such statement is that CR+LF is implemented as two
         * calls for writeChar(). now after each call paint() will instert the
         * cursor '_' as content[cursorRow][cursorCol]. then before new writeChar()
         * call, the cursor is cleared (so the no '_' trace is left). in '\r' call
         * cursorCol is reset to 0, hence in the following '\n' call, the first c/c
         * is cleared (bug!). overcome by checking the c/c first, if it's '_' remove
         * it. HOWEVER, a BUG resides here if the original text a line beginning is
         * '_' by chance, it will be removed.
         * Bug Creator :) Mustafa Elsheikh <elsheikhm@hotmail.com>
         */
        if (content[cursorRow][cursorCol] == '_') { content[cursorRow][cursorCol] = ' '; }
        switch (c) {
            case '\r': cursorCol = 0; break;
            case '\n':  /* CR+LF */
                cursorCol = 0;
                processLineFeed();
                break;
            case '\f': clearTextScreen(); break;
            case '\b':
                if (--cursorCol < 0) {
                    cursorCol = numberOfColumns - 1;
                    cursorRow = (cursorRow - 1 + numberOfRows) % numberOfRows;
                    // skip blank trailing of previous line
                    while (content[cursorRow][cursorCol] == ' ' && cursorCol > 0) { cursorCol--; }
                    // place the cursor after last 'non-blank' c/c of previous line
                    if (content[cursorRow][cursorCol] != ' ') {
                        try {
                            // test for the exception
                            System.out.print(content[cursorRow][cursorCol+1]);
                            // if success place the cursor after last..
                            cursorCol++;
                        } catch (ArrayIndexOutOfBoundsException e) { /* skip the INC if end of line */}
                    }
                }
                break;
            case '\t':
                do {
                    writeChar(' ');
                } while ((cursorCol % 8) != 7);
                break;
            default:
                if (c >= ' ') {
                    content[cursorRow][cursorCol++] = (upperCase == true) ? Character.toUpperCase(c) :
                        c;
                }
        }
        if (cursorCol >= numberOfColumns) { // new line
            cursorCol = 0;
            processLineFeed();
            if (userInput == true) {
                //ostream.append(content[cursorRow - 1]); 
            }
        }
        repaint();
    }
    
    /** draw two orthogonal lines  */
    public void axis() {
        Graphics g = offscreenG.getGraphics();
        g.setColor(255, 255, 255);
        g.drawLine(width/2, 0, width/2, height);
        g.drawLine(0, height/2, width, height/2);
        repaint();
        //serviceRepaints();
    }
    
    protected  void keyReleased(int keyCode) {}
    protected  void pointerDragged(int x, int y) {}
    protected  void pointerPressed(int x, int y) {}
    protected  void pointerReleased(int x, int y) {}
    public void destroyApp(boolean unconditional) {
    }

    public void cancelTimer(int kc) {
        if (keyTimer != null) keyTimer.cancel();
        timerActive = false;
    }
    
    /**
     * Called when a key is pressed.
     */
  /**  public synchronized void keyPressed(int keyCode) {
        userInput = true;
        char k;
        switch (getGameAction(keyCode)) {
            case FIRE:
                // NEWLINE:
                if (keyCode == KEY_NUM5) break;
               // if (keyCode == KEY_NUM5 + 1) break;
                cancelTimer(keyCode);
                display.setCurrent(get_StarTextBox()); // switch to advanced screen
                break;

              
        }
        switch (keyCode) {
            
            case KEY_POUND:
                k = '\0';
                cancelTimer(keyCode);
                upperCase = !upperCase;
                break;
            case -8: // CLEAR
                cancelTimer(keyCode);
                writeChar('\b'); writeChar(' '); writeChar('\b');
                break;
            case KEY_STAR:
                keyCode = KEY_NUM9;
                cancelTimer(keyCode);
                cancelTimer(keyCode);
                display.setCurrent(getlist()); // switch to advanced screen
                break;
               // writeChar('\n');
               // ostream.append(content[cursorRow - 1]);
               // ostream.flush();

              //  return;
            default:
                //if (keyTimer != null) cancelTimer(keyCode);
                k = keys[keyCode-48].charAt(0);
                if (keyCode == lastKey && timerActive == true) {
                    keyCount++;
                    keyCount %= keys[keyCode-48].length();
                    k = keys[keyCode-48].charAt(keyCount);
                    if (keyCount > 0) writeChar('\b');
                } else {
                    lastKey = keyCode;
                    keyCount = 0;
                }
                keyTimer = new Timer();
                timerActive = true;
                keyTimer.schedule(new KeyConfirmer(this), 4000);
                writeChar(k);
                break;
        }
    }

   * 
    /**
     * Called when a key is repeated (held down).
     * @param keyCode
     */
    public void keyRepeated(int keyCode) {
    }
    
    /**
     * implement the functionality performed when the letter is finally confirmed.
     * You just compare the letter. If the
     */
    public synchronized void keyConfirmed() {
        timerActive = false;
    }
    
    public void write(String s) {
        char ca[] = s.toCharArray();
        for (int i = 1; i< ca.length; i++) {
            writeChar(ca[i]);
        } 
    }


    public void starWrite(String s) {
        starInput = true; userInput = false;
        for (int i = 0; i < s.length(); i++) {
            writeChar(s.charAt(i));
        }
        starInput = false;
    }

    public String read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}