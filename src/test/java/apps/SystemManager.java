package apps;

import io.appium.java_client.windows.WindowsDriver;
import tests.TestBase;

public class SystemManager extends TestBase {

    public void closeWatchDog(WindowsDriver driverWinSM){

        driverWinSM.findElementByName("Process: LCWatchDog").click();
        System.out.println("SystemManager.....!!!!");
    }

}
