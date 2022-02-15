package apps;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tests.TestBase;

import java.io.IOException;

public class SystemView extends TestBase {

    WindowsDriver driverWinSV;
    public SystemView(WindowsDriver driverWinSV){ this.driverWinSV = driverWinSV; }

    public String displaySystemViewLogs() throws IOException {
        driverWinSV.findElementByName("Log type").click();
        driverWinSV.findElementByAccessibilityId("DropDown").click();
        scrollDownTillElementIsVisible(driverWinSV.findElementByName("PUBLISHER"));
        Actions a = new Actions(driverWinSV);
        WebElement publisher= driverWinSV.findElementByName("PUBLISHER");
        a.moveToElement(publisher).click().build().perform();
        //driverWinSV.findElementByAccessibilityId("1002").click();  //untick the ‘show notification'
        driverWinSV.findElementByName("Read").click();
        //getElementCoordinates(publisher);
        System.out.println("DISPLAY LOGS PUBLISHER....");
        String[] title = driverWinSV.getTitle().split(" ");
        takeAppSnap(driverWinSV, title[0]);
        return title[0];
    }



}
