package apps;

import io.appium.java_client.windows.WindowsDriver;
import tests.TestBase;

import java.io.IOException;

public class LogCollector extends TestBase {

    //WindowsDriver driverWinLC;
    //public LogCollector(WindowsDriver driverWinLC){ this.driverWinLC = driverWinLC; }

    public void get_LC_Picture(WindowsDriver driverWinLC) throws IOException {
        String title[] = driverWinLC.getTitle().split(" ");
        System.out.println("LogCollector screen");
        takeAppSnap(driverWinLC, title[0]);
    }

}
