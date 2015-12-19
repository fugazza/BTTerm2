package btterm;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;


/**
 *
 * @author Administrator
 */
public class BTTerm extends MIDlet implements CommandListener {
    public String output;
    private boolean midletPaused = false;
    private final BTSerialPort bluetoothSerialPort = new BTSerialPort();

    MIDPTerminal sterm;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Command exitCommand;
    private Command connectCommand;
    private Command backCommand;
    private Command backCommand1;
    private Command exitCommand1;
    private Command exitCommand2;
    private Command helpCommand;
    private Command helpCommand1;
    private Command backCommand2;
    private Command backCommand3;
    private Command exitCommand3;
    private Command helpCommand2;
    private Command composeTextCommand;
    private Command sendCommand;
    private Command backCommand4;
    private Command helpCommand3;
    private Command exitCommand4;
    private Command disconnectCommand;
    private Command helpCommand4;
    private Command exitCommand5;
    private Command helpCommand5;
    private Command exitCommand6;
    private Command screenCommand;
    private Command backCommand5;
    private Command okCommand;
    private Form form1;
    private StringItem stringItem1;
    private WaitScreen deviceDiscoveryWaitScreen;
    private List devicesList;
    private WaitScreen connectWaitScreen;
    private Alert discoveryFailedAlert;
    private Alert connectionFailedAlert;
    private Form terminalForm;
    private TextBox textBox;
    private Alert alert;
    private SimpleCancellableTask task;
    private SimpleCancellableTask task1;
//</editor-fold>//GEN-END:|fields|0|

    /**
     * Creates a new instance of BTTerm
     */
    public BTTerm() {
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application. It is called only once when the MIDlet is
     * started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {
//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here

        /* create the terminal and This example shows the use of midpterminal
         * First create an instance of the MIDPTerminal passing both the
         * display and the parent screen (in case of pressing back).
         */
       sterm = new MIDPTerminal(getDisplay(), getForm1());

    }//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {
//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
        switchDisplayable(null, sterm);
    }//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {
//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code>
     * instance is taken from <code>getDisplay</code> method. This method is
     * used by all actions in the design for switching displayable.
     *
     * @param alert the Alert which is temporarily set to the display; if
     * <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {
//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null && nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
}//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
if (displayable == alert) {//GEN-BEGIN:|7-commandAction|1|278-preAction
            if (command == backCommand5) {//GEN-END:|7-commandAction|1|278-preAction
                // write pre-action user code here
sendMethod();//GEN-LINE:|7-commandAction|2|278-postAction
                // write post-action user code here
} else if (command == okCommand) {//GEN-LINE:|7-commandAction|3|280-preAction
                // write pre-action user code here
switchDisplayable(null, getTerminalForm());//GEN-LINE:|7-commandAction|4|280-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|5|192-preAction
} else if (displayable == connectWaitScreen) {
    if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|5|192-preAction
 // write pre-action user code here
switchDisplayable(getConnectionFailedAlert(), getConnectWaitScreen());//GEN-LINE:|7-commandAction|6|192-postAction
 // write post-action user code here
} else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|7|191-preAction
 // write pre-action user code here
startBluetoothSerialMethod();//GEN-LINE:|7-commandAction|8|191-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|9|215-preAction
} else if (displayable == connectionFailedAlert) {
    if (command == backCommand3) {//GEN-END:|7-commandAction|9|215-preAction
        // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|10|215-postAction
        // write post-action user code here
} else if (command == exitCommand3) {//GEN-LINE:|7-commandAction|11|218-preAction
        // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|12|218-postAction
        // write post-action user code here
} else if (command == helpCommand2) {//GEN-LINE:|7-commandAction|13|220-preAction
        // write pre-action user code here
switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|14|220-postAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|15|177-preAction
} else if (displayable == deviceDiscoveryWaitScreen) {
    if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|15|177-preAction
 // write pre-action user code here
switchDisplayable(getDiscoveryFailedAlert(), getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|16|177-postAction
 // write post-action user code here
} else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|17|176-preAction
 // write pre-action user code here
switchDisplayable(null, getDevicesList());//GEN-LINE:|7-commandAction|18|176-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|19|182-preAction
} else if (displayable == devicesList) {
    if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|19|182-preAction
                // write pre-action user code here
devicesListAction();//GEN-LINE:|7-commandAction|20|182-postAction
                // write post-action user code here
} else if (command == backCommand) {//GEN-LINE:|7-commandAction|21|188-preAction
                // write pre-action user code here
switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|22|188-postAction
                // write post-action user code here
} else if (command == connectCommand) {//GEN-LINE:|7-commandAction|23|185-preAction
                // write pre-action user code here
switchDisplayable(null, getConnectWaitScreen());//GEN-LINE:|7-commandAction|24|185-postAction
                // write post-action user code here
} else if (command == exitCommand2) {//GEN-LINE:|7-commandAction|25|203-preAction
 // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|26|203-postAction
 // write post-action user code here
} else if (command == helpCommand) {//GEN-LINE:|7-commandAction|27|205-preAction
 // write pre-action user code here
switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|28|205-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|29|198-preAction
} else if (displayable == discoveryFailedAlert) {
    if (command == backCommand1) {//GEN-END:|7-commandAction|29|198-preAction
 // write pre-action user code here
switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|30|198-postAction
 // write post-action user code here
} else if (command == exitCommand1) {//GEN-LINE:|7-commandAction|31|201-preAction
 // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|32|201-postAction
 // write post-action user code here
} else if (command == helpCommand1) {//GEN-LINE:|7-commandAction|33|207-preAction
 // write pre-action user code here
switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|34|207-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|35|211-preAction
} else if (displayable == form1) {
    if (command == backCommand2) {//GEN-END:|7-commandAction|35|211-preAction
 // write pre-action user code here
switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|36|211-postAction
 // write post-action user code here
} else if (command == exitCommand) {//GEN-LINE:|7-commandAction|37|19-preAction
                // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|38|19-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|39|228-preAction
} else if (displayable == terminalForm) {
    if (command == composeTextCommand) {//GEN-END:|7-commandAction|39|228-preAction
 // write pre-action user code here
switchDisplayable(null, getTextBox());//GEN-LINE:|7-commandAction|40|228-postAction
 // write post-action user code here
} else if (command == disconnectCommand) {//GEN-LINE:|7-commandAction|41|242-preAction
 // write pre-action user code here
disconnectMethod();//GEN-LINE:|7-commandAction|42|242-postAction
 // write post-action user code here
} else if (command == exitCommand5) {//GEN-LINE:|7-commandAction|43|246-preAction
 // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|44|246-postAction
 // write post-action user code here
} else if (command == helpCommand4) {//GEN-LINE:|7-commandAction|45|244-preAction
 // write pre-action user code here
switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|46|244-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|47|235-preAction
} else if (displayable == textBox) {
    if (command == backCommand4) {//GEN-END:|7-commandAction|47|235-preAction
 // write pre-action user code here
switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|48|235-postAction
 // write post-action user code here
} else if (command == exitCommand4) {//GEN-LINE:|7-commandAction|49|240-preAction
 // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|50|240-postAction
 // write post-action user code here
} else if (command == helpCommand3) {//GEN-LINE:|7-commandAction|51|238-preAction
 // write pre-action user code here
switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|52|238-postAction
 // write post-action user code here
} else if (command == sendCommand) {//GEN-LINE:|7-commandAction|53|233-preAction
 // write pre-action user code here
sendMethod();//GEN-LINE:|7-commandAction|54|233-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|55|7-postCommandAction
        }//GEN-END:|7-commandAction|55|7-postCommandAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|56|
//</editor-fold>//GEN-END:|7-commandAction|56|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {
//GEN-END:|18-getter|0|18-preInit
 // write pre-init user code here
exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
 // write post-init user code here
}//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|18-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form1 ">//GEN-BEGIN:|166-getter|0|166-preInit
    /**
     * Returns an initialized instance of form1 component.
     *
     * @return the initialized component instance
     */
    public Form getForm1() {
        if (form1 == null) {
//GEN-END:|166-getter|0|166-preInit
            // write pre-init user code here
form1 = new Form("form1", new Item[]{getStringItem1()});//GEN-BEGIN:|166-getter|1|166-postInit
            form1.addCommand(getExitCommand());
            form1.addCommand(getBackCommand2());
            form1.setCommandListener(this);//GEN-END:|166-getter|1|166-postInit
            // write post-init user code here
}//GEN-BEGIN:|166-getter|2|
        return form1;
    }
//</editor-fold>//GEN-END:|166-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|171-getter|0|171-preInit
    /**
     * Returns an initialized instance of stringItem1 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {
//GEN-END:|171-getter|0|171-preInit
            // write pre-init user code here
stringItem1 = new StringItem("Bluetooth Terminal v.1.0", "This terminal program is for connecting to bluetooth modules like BlueSmirf, BTM-222, BTM-112 and other Bluetooth SPP modules.For support contact at http://www.microcontroller-bg.com.");//GEN-LINE:|171-getter|1|171-postInit
 // write post-init user code here
}//GEN-BEGIN:|171-getter|2|
        return stringItem1;
    }
//</editor-fold>//GEN-END:|171-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the
     * <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
//</editor-fold>//GEN-END:|methods|0|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: deviceDiscoveryWaitScreen ">//GEN-BEGIN:|173-getter|0|173-preInit
    /**
     * Returns an initialized instance of deviceDiscoveryWaitScreen component.
     *
     * @return the initialized component instance
     */
    public WaitScreen getDeviceDiscoveryWaitScreen() {
        if (deviceDiscoveryWaitScreen == null) {
//GEN-END:|173-getter|0|173-preInit
 // write pre-init user code here
deviceDiscoveryWaitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|173-getter|1|173-postInit
            deviceDiscoveryWaitScreen.setTitle("Bluetooth search");
            deviceDiscoveryWaitScreen.setCommandListener(this);
            deviceDiscoveryWaitScreen.setText("Searching for bluetooth devices");
            deviceDiscoveryWaitScreen.setTask(getTask());//GEN-END:|173-getter|1|173-postInit
 // write post-init user code here
}//GEN-BEGIN:|173-getter|2|
        return deviceDiscoveryWaitScreen;
    }
//</editor-fold>//GEN-END:|173-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|178-getter|0|178-preInit
    /**
     * Returns an initialized instance of task component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {
//GEN-END:|178-getter|0|178-preInit
 // write pre-init user code here
task = new SimpleCancellableTask();//GEN-BEGIN:|178-getter|1|178-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|178-getter|1|178-execute
// write task-execution user code here
    Vector devices = BTSerialPort.discoverDevices();
    int deviceCount = devices.size();
    for (int i = 0; i < deviceCount; i++) {
        RemoteDevice remoteDevice = (RemoteDevice) devices.elementAt(i);
        devicesList.append(remoteDevice.getBluetoothAddress(), null);
    }            
                }//GEN-BEGIN:|178-getter|2|178-postInit
            });//GEN-END:|178-getter|2|178-postInit
 // write post-init user code here
}//GEN-BEGIN:|178-getter|3|
        return task;
    }
//</editor-fold>//GEN-END:|178-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectCommand ">//GEN-BEGIN:|184-getter|0|184-preInit
    /**
     * Returns an initialized instance of connectCommand component.
     *
     * @return the initialized component instance
     */
    public Command getConnectCommand() {
        if (connectCommand == null) {
//GEN-END:|184-getter|0|184-preInit
 // write pre-init user code here
connectCommand = new Command("Connect", Command.SCREEN, 0);//GEN-LINE:|184-getter|1|184-postInit
 // write post-init user code here
}//GEN-BEGIN:|184-getter|2|
        return connectCommand;
    }
//</editor-fold>//GEN-END:|184-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: devicesList ">//GEN-BEGIN:|180-getter|0|180-preInit
    /**
     * Returns an initialized instance of devicesList component.
     *
     * @return the initialized component instance
     */
    public List getDevicesList() {
        if (devicesList == null) {
//GEN-END:|180-getter|0|180-preInit
 // write pre-init user code here
devicesList = new List("Device list", Choice.IMPLICIT);//GEN-BEGIN:|180-getter|1|180-postInit
            devicesList.addCommand(getConnectCommand());
            devicesList.addCommand(getBackCommand());
            devicesList.addCommand(getHelpCommand());
            devicesList.addCommand(getExitCommand2());
            devicesList.setCommandListener(this);//GEN-END:|180-getter|1|180-postInit
 // write post-init user code here
}//GEN-BEGIN:|180-getter|2|
        return devicesList;
    }
//</editor-fold>//GEN-END:|180-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: devicesListAction ">//GEN-BEGIN:|180-action|0|180-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * devicesList component.
     */
    public void devicesListAction() {
//GEN-END:|180-action|0|180-preAction
 // enter pre-action user code here
String __selectedString = getDevicesList().getString(getDevicesList().getSelectedIndex());//GEN-LINE:|180-action|1|180-postAction
 // enter post-action user code here
}//GEN-BEGIN:|180-action|2|
//</editor-fold>//GEN-END:|180-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|187-getter|0|187-preInit
    /**
     * Returns an initialized instance of backCommand component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {
//GEN-END:|187-getter|0|187-preInit
 // write pre-init user code here
backCommand = new Command("Search again", Command.BACK, 0);//GEN-LINE:|187-getter|1|187-postInit
 // write post-init user code here
}//GEN-BEGIN:|187-getter|2|
        return backCommand;
    }
//</editor-fold>//GEN-END:|187-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectWaitScreen ">//GEN-BEGIN:|190-getter|0|190-preInit
    /**
     * Returns an initialized instance of connectWaitScreen component.
     *
     * @return the initialized component instance
     */
    public WaitScreen getConnectWaitScreen() {
        if (connectWaitScreen == null) {
//GEN-END:|190-getter|0|190-preInit
 // write pre-init user code here
connectWaitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|190-getter|1|190-postInit
            connectWaitScreen.setTitle("Connecting...");
            connectWaitScreen.setCommandListener(this);
            connectWaitScreen.setText("Connecting to selected device");
            connectWaitScreen.setTask(getTask1());//GEN-END:|190-getter|1|190-postInit
 // write post-init user code here
}//GEN-BEGIN:|190-getter|2|
        return connectWaitScreen;
    }
//</editor-fold>//GEN-END:|190-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task1 ">//GEN-BEGIN:|193-getter|0|193-preInit
    /**
     * Returns an initialized instance of task1 component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask1() {
        if (task1 == null) {
//GEN-END:|193-getter|0|193-preInit
 // write pre-init user code here
task1 = new SimpleCancellableTask();//GEN-BEGIN:|193-getter|1|193-execute
            task1.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|193-getter|1|193-execute
// write task-execution user code here
           String MAC = devicesList.getString(devicesList.getSelectedIndex());
           bluetoothSerialPort.Connect(MAC);
                }//GEN-BEGIN:|193-getter|2|193-postInit
            });//GEN-END:|193-getter|2|193-postInit
 // write post-init user code here
}//GEN-BEGIN:|193-getter|3|
        return task1;
    }
//</editor-fold>//GEN-END:|193-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|197-getter|0|197-preInit
    /**
     * Returns an initialized instance of backCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {
//GEN-END:|197-getter|0|197-preInit
 // write pre-init user code here
backCommand1 = new Command("Search again", Command.BACK, 0);//GEN-LINE:|197-getter|1|197-postInit
 // write post-init user code here
}//GEN-BEGIN:|197-getter|2|
        return backCommand1;
    }
//</editor-fold>//GEN-END:|197-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|200-getter|0|200-preInit
    /**
     * Returns an initialized instance of exitCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {
//GEN-END:|200-getter|0|200-preInit
 // write pre-init user code here
exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|200-getter|1|200-postInit
 // write post-init user code here
}//GEN-BEGIN:|200-getter|2|
        return exitCommand1;
    }
//</editor-fold>//GEN-END:|200-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand2 ">//GEN-BEGIN:|202-getter|0|202-preInit
    /**
     * Returns an initialized instance of exitCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand2() {
        if (exitCommand2 == null) {
//GEN-END:|202-getter|0|202-preInit
 // write pre-init user code here
exitCommand2 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|202-getter|1|202-postInit
 // write post-init user code here
}//GEN-BEGIN:|202-getter|2|
        return exitCommand2;
    }
//</editor-fold>//GEN-END:|202-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand ">//GEN-BEGIN:|204-getter|0|204-preInit
    /**
     * Returns an initialized instance of helpCommand component.
     *
     * @return the initialized component instance
     */
    public Command getHelpCommand() {
        if (helpCommand == null) {
//GEN-END:|204-getter|0|204-preInit
 // write pre-init user code here
helpCommand = new Command("Help", Command.HELP, 0);//GEN-LINE:|204-getter|1|204-postInit
 // write post-init user code here
}//GEN-BEGIN:|204-getter|2|
        return helpCommand;
    }
//</editor-fold>//GEN-END:|204-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand1 ">//GEN-BEGIN:|206-getter|0|206-preInit
    /**
     * Returns an initialized instance of helpCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getHelpCommand1() {
        if (helpCommand1 == null) {
//GEN-END:|206-getter|0|206-preInit
 // write pre-init user code here
helpCommand1 = new Command("Help", Command.HELP, 0);//GEN-LINE:|206-getter|1|206-postInit
 // write post-init user code here
}//GEN-BEGIN:|206-getter|2|
        return helpCommand1;
    }
//</editor-fold>//GEN-END:|206-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|210-getter|0|210-preInit
    /**
     * Returns an initialized instance of backCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {
//GEN-END:|210-getter|0|210-preInit
 // write pre-init user code here
backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|210-getter|1|210-postInit
 // write post-init user code here
}//GEN-BEGIN:|210-getter|2|
        return backCommand2;
    }
//</editor-fold>//GEN-END:|210-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|214-getter|0|214-preInit
    /**
     * Returns an initialized instance of backCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand3() {
        if (backCommand3 == null) {
//GEN-END:|214-getter|0|214-preInit
 // write pre-init user code here
backCommand3 = new Command("Search again", Command.BACK, 0);//GEN-LINE:|214-getter|1|214-postInit
 // write post-init user code here
}//GEN-BEGIN:|214-getter|2|
        return backCommand3;
    }
//</editor-fold>//GEN-END:|214-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand3 ">//GEN-BEGIN:|217-getter|0|217-preInit
    /**
     * Returns an initialized instance of exitCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand3() {
        if (exitCommand3 == null) {
//GEN-END:|217-getter|0|217-preInit
 // write pre-init user code here
exitCommand3 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|217-getter|1|217-postInit
 // write post-init user code here
}//GEN-BEGIN:|217-getter|2|
        return exitCommand3;
    }
//</editor-fold>//GEN-END:|217-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand2 ">//GEN-BEGIN:|219-getter|0|219-preInit
    /**
     * Returns an initialized instance of helpCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getHelpCommand2() {
        if (helpCommand2 == null) {
//GEN-END:|219-getter|0|219-preInit
 // write pre-init user code here
helpCommand2 = new Command("Help", Command.HELP, 0);//GEN-LINE:|219-getter|1|219-postInit
 // write post-init user code here
}//GEN-BEGIN:|219-getter|2|
        return helpCommand2;
    }
//</editor-fold>//GEN-END:|219-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: composeTextCommand ">//GEN-BEGIN:|227-getter|0|227-preInit
    /**
     * Returns an initialized instance of composeTextCommand component.
     *
     * @return the initialized component instance
     */
    public Command getComposeTextCommand() {
        if (composeTextCommand == null) {
//GEN-END:|227-getter|0|227-preInit
 // write pre-init user code here
composeTextCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|227-getter|1|227-postInit
 // write post-init user code here
}//GEN-BEGIN:|227-getter|2|
        return composeTextCommand;
    }
//</editor-fold>//GEN-END:|227-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendCommand ">//GEN-BEGIN:|232-getter|0|232-preInit
    /**
     * Returns an initialized instance of sendCommand component.
     *
     * @return the initialized component instance
     */
    public Command getSendCommand() {
        if (sendCommand == null) {
//GEN-END:|232-getter|0|232-preInit
 // write pre-init user code here
sendCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|232-getter|1|232-postInit
 // write post-init user code here
}//GEN-BEGIN:|232-getter|2|
        return sendCommand;
    }
//</editor-fold>//GEN-END:|232-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|234-getter|0|234-preInit
    /**
     * Returns an initialized instance of backCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand4() {
        if (backCommand4 == null) {
//GEN-END:|234-getter|0|234-preInit
 // write pre-init user code here
backCommand4 = new Command("Back", Command.BACK, 0);//GEN-LINE:|234-getter|1|234-postInit
 // write post-init user code here
}//GEN-BEGIN:|234-getter|2|
        return backCommand4;
    }
//</editor-fold>//GEN-END:|234-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand3 ">//GEN-BEGIN:|237-getter|0|237-preInit
    /**
     * Returns an initialized instance of helpCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getHelpCommand3() {
        if (helpCommand3 == null) {
//GEN-END:|237-getter|0|237-preInit
 // write pre-init user code here
helpCommand3 = new Command("Help", Command.HELP, 0);//GEN-LINE:|237-getter|1|237-postInit
 // write post-init user code here
}//GEN-BEGIN:|237-getter|2|
        return helpCommand3;
    }
//</editor-fold>//GEN-END:|237-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand4 ">//GEN-BEGIN:|239-getter|0|239-preInit
    /**
     * Returns an initialized instance of exitCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand4() {
        if (exitCommand4 == null) {
//GEN-END:|239-getter|0|239-preInit
 // write pre-init user code here
exitCommand4 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|239-getter|1|239-postInit
 // write post-init user code here
}//GEN-BEGIN:|239-getter|2|
        return exitCommand4;
    }
//</editor-fold>//GEN-END:|239-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: disconnectCommand ">//GEN-BEGIN:|241-getter|0|241-preInit
    /**
     * Returns an initialized instance of disconnectCommand component.
     *
     * @return the initialized component instance
     */
    public Command getDisconnectCommand() {
        if (disconnectCommand == null) {
//GEN-END:|241-getter|0|241-preInit
 // write pre-init user code here
disconnectCommand = new Command("Disconnect", Command.SCREEN, 0);//GEN-LINE:|241-getter|1|241-postInit
 // write post-init user code here
}//GEN-BEGIN:|241-getter|2|
        return disconnectCommand;
    }
//</editor-fold>//GEN-END:|241-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand4 ">//GEN-BEGIN:|243-getter|0|243-preInit
    /**
     * Returns an initialized instance of helpCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getHelpCommand4() {
        if (helpCommand4 == null) {
//GEN-END:|243-getter|0|243-preInit
 // write pre-init user code here
helpCommand4 = new Command("Help", Command.HELP, 0);//GEN-LINE:|243-getter|1|243-postInit
 // write post-init user code here
}//GEN-BEGIN:|243-getter|2|
        return helpCommand4;
    }
//</editor-fold>//GEN-END:|243-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand5 ">//GEN-BEGIN:|245-getter|0|245-preInit
    /**
     * Returns an initialized instance of exitCommand5 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand5() {
        if (exitCommand5 == null) {
//GEN-END:|245-getter|0|245-preInit
 // write pre-init user code here
exitCommand5 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|245-getter|1|245-postInit
 // write post-init user code here
}//GEN-BEGIN:|245-getter|2|
        return exitCommand5;
    }
//</editor-fold>//GEN-END:|245-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand5 ">//GEN-BEGIN:|253-getter|0|253-preInit
    /**
     * Returns an initialized instance of helpCommand5 component.
     *
     * @return the initialized component instance
     */
    public Command getHelpCommand5() {
        if (helpCommand5 == null) {
//GEN-END:|253-getter|0|253-preInit
 // write pre-init user code here
helpCommand5 = new Command("Help", Command.HELP, 0);//GEN-LINE:|253-getter|1|253-postInit
 // write post-init user code here
}//GEN-BEGIN:|253-getter|2|
        return helpCommand5;
    }
//</editor-fold>//GEN-END:|253-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand6 ">//GEN-BEGIN:|255-getter|0|255-preInit
    /**
     * Returns an initialized instance of exitCommand6 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand6() {
        if (exitCommand6 == null) {
//GEN-END:|255-getter|0|255-preInit
 // write pre-init user code here
exitCommand6 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|255-getter|1|255-postInit
 // write post-init user code here
}//GEN-BEGIN:|255-getter|2|
        return exitCommand6;
    }
//</editor-fold>//GEN-END:|255-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|257-getter|0|257-preInit
    /**
     * Returns an initialized instance of screenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {
//GEN-END:|257-getter|0|257-preInit
 // write pre-init user code here
screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|257-getter|1|257-postInit
 // write post-init user code here
}//GEN-BEGIN:|257-getter|2|
        return screenCommand;
    }
//</editor-fold>//GEN-END:|257-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: discoveryFailedAlert ">//GEN-BEGIN:|195-getter|0|195-preInit
    /**
     * Returns an initialized instance of discoveryFailedAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getDiscoveryFailedAlert() {
        if (discoveryFailedAlert == null) {
//GEN-END:|195-getter|0|195-preInit
 // write pre-init user code here
discoveryFailedAlert = new Alert("Discovery failed", "No bluetooth device was discovered", null, null);//GEN-BEGIN:|195-getter|1|195-postInit
            discoveryFailedAlert.addCommand(getBackCommand1());
            discoveryFailedAlert.addCommand(getHelpCommand1());
            discoveryFailedAlert.addCommand(getExitCommand1());
            discoveryFailedAlert.setCommandListener(this);
            discoveryFailedAlert.setTimeout(Alert.FOREVER);//GEN-END:|195-getter|1|195-postInit
 // write post-init user code here
}//GEN-BEGIN:|195-getter|2|
        return discoveryFailedAlert;
    }
//</editor-fold>//GEN-END:|195-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectionFailedAlert ">//GEN-BEGIN:|213-getter|0|213-preInit
    /**
     * Returns an initialized instance of connectionFailedAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getConnectionFailedAlert() {
        if (connectionFailedAlert == null) {
//GEN-END:|213-getter|0|213-preInit
 // write pre-init user code here
connectionFailedAlert = new Alert("Conneciton failed", "Connection to selected device failed", null, null);//GEN-BEGIN:|213-getter|1|213-postInit
            connectionFailedAlert.addCommand(getBackCommand3());
            connectionFailedAlert.addCommand(getHelpCommand2());
            connectionFailedAlert.addCommand(getExitCommand3());
            connectionFailedAlert.setCommandListener(this);
            connectionFailedAlert.setTimeout(Alert.FOREVER);//GEN-END:|213-getter|1|213-postInit
 // write post-init user code here
}//GEN-BEGIN:|213-getter|2|
        return connectionFailedAlert;
    }
//</editor-fold>//GEN-END:|213-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: terminalForm ">//GEN-BEGIN:|226-getter|0|226-preInit
    /**
     * Returns an initialized instance of terminalForm component.
     *
     * @return the initialized component instance
     */
    public Form getTerminalForm() {
        if (terminalForm == null) {
//GEN-END:|226-getter|0|226-preInit
 // write pre-init user code here
terminalForm = new Form("form");//GEN-BEGIN:|226-getter|1|226-postInit
            terminalForm.addCommand(getComposeTextCommand());
            terminalForm.addCommand(getDisconnectCommand());
            terminalForm.addCommand(getHelpCommand4());
            terminalForm.addCommand(getExitCommand5());
            terminalForm.setCommandListener(this);//GEN-END:|226-getter|1|226-postInit
 // write post-init user code here
}//GEN-BEGIN:|226-getter|2|
        return terminalForm;
    }
//</editor-fold>//GEN-END:|226-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|230-getter|0|230-preInit
    /**
     * Returns an initialized instance of textBox component.
     *
     * @return the initialized component instance
     */
    public TextBox getTextBox() {
        if (textBox == null) {
//GEN-END:|230-getter|0|230-preInit
 // write pre-init user code here
textBox = new TextBox("textBox", null, 100, TextField.ANY);//GEN-BEGIN:|230-getter|1|230-postInit
            textBox.addCommand(getSendCommand());
            textBox.addCommand(getBackCommand4());
            textBox.addCommand(getHelpCommand3());
            textBox.addCommand(getExitCommand4());
            textBox.setCommandListener(this);//GEN-END:|230-getter|1|230-postInit
 // write post-init user code here
}//GEN-BEGIN:|230-getter|2|
        return textBox;
    }
//</editor-fold>//GEN-END:|230-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startBluetoothSerialMethod ">//GEN-BEGIN:|260-entry|0|261-preAction
    /**
     * Performs an action assigned to the startBluetoothSerialMethod
     * entry-point.
     */
    public void startBluetoothSerialMethod() {
//GEN-END:|260-entry|0|261-preAction
 // write pre-action user code here

 // start to listen for incoming conneciton
 new Thread(bluetoothSerialPort).start();

        switchDisplayable(null, getTerminalForm());//GEN-LINE:|260-entry|1|261-postAction
 // write post-action user code here
}//GEN-BEGIN:|260-entry|2|
//</editor-fold>//GEN-END:|260-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: sendMethod2 ">//GEN-BEGIN:|264-entry|0|265-preAction
    /**
     * Performs an action assigned to the sendMethod2 entry-point.
     */
    public void sendMethod2() {
//GEN-END:|264-entry|0|265-preAction
 // write pre-action user code here 
switchDisplayable(null, getTerminalForm());//GEN-LINE:|264-entry|1|265-postAction
 // write post-action user code here
}//GEN-BEGIN:|264-entry|2|
//</editor-fold>//GEN-END:|264-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: disconnectMethod ">//GEN-BEGIN:|268-entry|0|269-preAction
    /**
     * Performs an action assigned to the disconnectMethod entry-point.
     */
    public void disconnectMethod() {
//GEN-END:|268-entry|0|269-preAction
 // write pre-action user code here
 bluetoothSerialPort.Closeconnection();
        switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|268-entry|1|269-postAction
 // write post-action user code here
}//GEN-BEGIN:|268-entry|2|
//</editor-fold>//GEN-END:|268-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: sendMethod ">//GEN-BEGIN:|272-if|0|272-preIf
    /**
     * Performs an action assigned to the sendMethod if-point.
     */
    public void sendMethod() {
//GEN-END:|272-if|0|272-preIf
 // enter pre-if user code here
    boolean sendSuccessfull = true;
    String textToSend = textBox.getString();
    try {
    bluetoothSerialPort.write(textToSend);
    sterm.write(textToSend);
    } catch (IOException ex) {
        sendSuccessfull = false;
    }

        if (sendSuccessfull) {//GEN-LINE:|272-if|1|273-preAction
 // write pre-action user code here
switchDisplayable(null, getTerminalForm());//GEN-LINE:|272-if|2|273-postAction
 // write post-action user code here
} else {//GEN-LINE:|272-if|3|274-preAction
 // write pre-action user code here
switchDisplayable(null, getAlert());//GEN-LINE:|272-if|4|274-postAction
 // write post-action user code here
}//GEN-LINE:|272-if|5|272-postIf
 // enter post-if user code here
}//GEN-BEGIN:|272-if|6|
//</editor-fold>//GEN-END:|272-if|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand5 ">//GEN-BEGIN:|277-getter|0|277-preInit
    /**
     * Returns an initialized instance of backCommand5 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand5() {
        if (backCommand5 == null) {
//GEN-END:|277-getter|0|277-preInit
 // write pre-init user code here
backCommand5 = new Command("Repeat", Command.BACK, 0);//GEN-LINE:|277-getter|1|277-postInit
 // write post-init user code here
}//GEN-BEGIN:|277-getter|2|
        return backCommand5;
    }
//</editor-fold>//GEN-END:|277-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|279-getter|0|279-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
//GEN-END:|279-getter|0|279-preInit
 // write pre-init user code here
okCommand = new Command("Continue", Command.OK, 0);//GEN-LINE:|279-getter|1|279-postInit
 // write post-init user code here
}//GEN-BEGIN:|279-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|279-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">//GEN-BEGIN:|275-getter|0|275-preInit
    /**
     * Returns an initialized instance of alert component.
     *
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {
//GEN-END:|275-getter|0|275-preInit
 // write pre-init user code here
alert = new Alert("alert");//GEN-BEGIN:|275-getter|1|275-postInit
            alert.addCommand(getBackCommand5());
            alert.addCommand(getOkCommand());
            alert.setCommandListener(this);
            alert.setTimeout(Alert.FOREVER);//GEN-END:|275-getter|1|275-postInit
 // write post-init user code here
}//GEN-BEGIN:|275-getter|2|
        return alert;
    }
//</editor-fold>//GEN-END:|275-getter|2|

    /**
     * Returns a display instance.
     *
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        bluetoothSerialPort.Closeconnection();
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
}

    /**
     * Called when MIDlet is started. Checks whether the MIDlet have been
     * already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     *
     * @param unconditional if true, then the MIDlet has to be unconditionally
     * terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
