package apps;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;
import core.excelUserData;
import java.util.List;
import java.util.Map;

public class SignalAdmin extends TestBase {

    List<Map<String,String>> allDataFromFile;
    WindowsDriver driverWinSA;
    public SignalAdmin(WindowsDriver driverWinSA){ this.driverWinSA = driverWinSA; }


    public void openSignalsAdminApp() throws Exception {
        allDataFromFile = excelUserData.getDataFromExcelFile();
        System.out.println("UserID & PASSWORD needed to connect to SignalsAdmin app");
        driverWinSA.findElementByAccessibilityId("1005").clear();
        driverWinSA.findElementByAccessibilityId("1005").sendKeys(allDataFromFile.get(0).get("DB Name"));
        driverWinSA.findElementByAccessibilityId("1006").clear();
        driverWinSA.findElementByAccessibilityId("1006").sendKeys(allDataFromFile.get(0).get("User"));
        driverWinSA.findElementByAccessibilityId("1007").sendKeys(allDataFromFile.get(0).get("Password"));
        System.out.println("handler here inserting PASSWORD---> "+driverWinSA.getWindowHandle());
        driverWinSA.findElementByName("Connect").click();
        Thread.sleep(7000);
    }

    public String processPriorityList() throws Exception {
        System.out.println("SignalsAdmin opens correctly and pressing Lock button...");
        System.out.println("handler here IN THE SECOND PAGE---> "+driverWinSA.getWindowHandle());
        String title[] = driverWinSA.getTitle().split(" ");
        Thread.sleep(10000);
        //WindowsDriverWait wait = new WebDriverWait(driverWinSA, 40);
        //wait.until(ExpectedConditions.visibilityOf(driverWinSA.findElementByName("Lock")));
        driverWinSA.findElementByName("Lock").click();
        driverWinSA.findElementByAccessibilityId("1328").click();//Publish region & CI rules button
        System.out.println("Clicked on button 'Publish region & CI rules'...");
        driverWinSA.findElementByAccessibilityId("6").click(); // '7' is NO button , '6' is YES button
        System.out.println(title[0]);
        takeAppSnap(driverWinSA, title[0]);
        return title[0];
    }

}
