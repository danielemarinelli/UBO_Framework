package apps;

import core.excelUserData;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tests.TestBase;
import java.util.List;
import java.util.Map;

public class RFAS extends TestBase {

    WindowsDriver driverWinRFAS=null;
    List<Map<String,String>> allDataFromFile;

    public RFAS(WindowsDriver driverWinRFAS){
        this.driverWinRFAS = driverWinRFAS;
    }

    public int setAuthorization() throws Exception {
        System.out.println("Click on Logs Client TAB of the RFAS app");
        Thread.sleep(3000);
        driverWinRFAS.findElementByName("Log Clients").click();
        Thread.sleep(2000);
        List num_auth_cli = driverWinRFAS.findElementsByAccessibilityId("LCAuthorizedClientSortableListView");
        allDataFromFile = excelUserData.getDataFromExcelFile();
        String clientID = allDataFromFile.get(0).get("ID");
        WebElement rowClient = driverWinRFAS.findElementByName(clientID);
        if(rowClient.getLocation().getY()>400){
        System.out.println("Client Authorization process started");
        rowClient.click();
        Actions actions = new Actions(driverWinRFAS);
        actions.moveToElement(rowClient).contextClick().build().perform();
        driverWinRFAS.findElementByName("Authorize Client(s)").click();
        driverWinRFAS.findElementByName("Yes").click();
        }else{System.out.println("Client is already authorized");}
        Thread.sleep(2000);
        //System.out.println("Authorized Client RFAS...");
        String[] title = driverWinRFAS.getTitle().split(" ");
        takeAppSnap(driverWinRFAS, title[0]);
        return num_auth_cli.size();
    }

    public void tearDownRFAS() {
        driverWinRFAS.close();
        for (int i = 0; i < 2; i++) { driverWinRFAS.findElementByName("Yes").click(); }
    }

}
