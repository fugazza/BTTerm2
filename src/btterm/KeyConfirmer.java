package btterm;

import btterm.MIDPTerminal;
import java.util.*;

public class KeyConfirmer extends TimerTask {
    
    MIDPTerminal sterm;
    
    public KeyConfirmer(MIDPTerminal sterm) {
        this.sterm = sterm;
    }
    
    public void run() {
        sterm.keyConfirmed();
    }
}
