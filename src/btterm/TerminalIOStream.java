package btterm;

import javax.microedition.midlet.MIDlet;
/*
 * TerminalOutputStream.java
 *
 * Created on June 14, 2006, 4:37 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author Mustafa Elsheikh <elsheikhmh@hotmail.com>
 */
public class TerminalIOStream {
    StringBuffer line;
    private boolean full;
    
    /** Creates a new instance of TerminalOutputStream */
    public TerminalIOStream() {
        reset();
    }
    
    public void append(char c) {
        line.append(c);
    }
    
    public void append(char [] l) {
        line.append(l);
    }
    
    /** 
     * Reads a line in the buffer.
     * <b>Note</b> for SimpleTerminal, all lines
     * are filled to the end with blanks. So, proper usage should be
     * <code>read().trim()</code>
     * @return <code>String</code> for that line.
     */
    public synchronized String read() {
        try {
            while (full == false) { wait(); }
        } catch (Exception e) { e.printStackTrace(); }
        String s = line.toString();
        reset();
        return s;

    }
   
    public void write(String s) {
        line.append(s);
        flush();
    }
    
    public void reset() {
        line = new StringBuffer();
        full = false;
    }
    
    public synchronized void flush() {
        full = true;
        notifyAll();
    }
    
    public synchronized boolean isFull() {
        return full;
    }
}
