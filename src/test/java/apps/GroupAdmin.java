package apps;

import core.excelUserData;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GroupAdmin extends TestBase {

    public GroupAdmin(WindowsDriver driverWinGA,WindowsDriver driverWinPub){
        this.driverWinGA = driverWinGA;
        this.driverWinPub = driverWinPub;
    }
    List<Map<String,String>> allDataFromFile;
    List<Map<String,String>> getHHFromFile;
    Publisher p = null;

    public void openGroupAdminApp() throws Exception {
        System.out.println("UserID & PASSWORD needed to connect to GroupAdmin");
        allDataFromFile = excelUserData.getDataFromExcelFile();
        driverWinGA.findElementByAccessibilityId("1005").clear();
        driverWinGA.findElementByAccessibilityId("1005").sendKeys(allDataFromFile.get(0).get("DB Name"));
        driverWinGA.findElementByAccessibilityId("1006").clear();
        driverWinGA.findElementByAccessibilityId("1006").sendKeys(allDataFromFile.get(0).get("User"));
        driverWinGA.findElementByAccessibilityId("1007").sendKeys(allDataFromFile.get(0).get("Password"));
        driverWinGA.findElementByName("Connect").click();
        System.out.println("GroupAdmin opens correctly...");
        Thread.sleep(12000);
    }

    public String publishAll_HH() throws Exception {
        driverWinGA.findElementByAccessibilityId("1007").click();
        System.out.println("clicked on READ....");
        Thread.sleep(3000);
        driverWinGA.findElementByAccessibilityId("1010").click();
        System.out.println("clicked on Select All....");
        System.out.println(driverWinGA.findElementByAccessibilityId("1013").getText());
        driverWinGA.findElementByAccessibilityId("1008").click();
        System.out.println("clicked on WRITE button....");
        checkIfWritingIsOver();
        String[] title = driverWinGA.getTitle().split(" ");
        Thread.sleep(5000);
        takeAppSnap(driverWinGA, title[0]);
        driverWinGA.findElementByAccessibilityId("1").click();
        return title[0];
    }

    public String publishOnlyActive_HH() throws Exception {
        System.out.println("clicked FILTER button....");
        driverWinGA.findElementByName("Advanced filter:").click();
        driverWinGA.findElementByName("...").click();
        Thread.sleep(1000);

        WebElement status = driverWinGA.findElementByName("Status:");
        boolean selectedStatus = status.isSelected();
        if(!selectedStatus){
            status.click();
        driverWinGA.findElementByName("Active").click();
        driverWinGA.findElementByName("To be published").click();
        driverWinGA.findElementByName("Installed").click();
        }else{
            System.out.println("Status already selected");
        }
        Thread.sleep(1000);
        driverWinGA.findElementByName("OK").click();
        Thread.sleep(1000);
        driverWinGA.findElementByName("Read").click();
        List checksHH = driverWinGA.findElementsByAccessibilityId("1009");
        //switchToWindowGA(getDriverGA());

        WebElement selectAll = driverWinGA.findElementByAccessibilityId("1010");
        Actions a = new Actions(driverWinGA);
        Thread.sleep(1000);
        a.moveToElement(selectAll).click().build().perform();
        getElementCoordinates(selectAll);
        System.out.println();
        driverWinGA.findElementByName("Write").click();
        System.out.println("clicked on WRITE button....");
        System.out.println(driverWinGA.findElementByAccessibilityId("1014").getText());
        checkIfWritingIsOver();
        driverWinGA.findElementByAccessibilityId("1").click();
        String[] title = driverWinGA.getTitle().split(" ");
        takeAppSnap(driverWinGA, title[0]);

        return title[0];

    }

    public void checkIfWritingIsOver() throws Exception {
        int subtraction;
        getHHFromFile = excelUserData.getFoldersNamesFromExcelSheet();
        int partial_subtraction = -1;
        do {
            //Thread.sleep(2000);
            String HH_toPublish_init=driverWinGA.findElementByAccessibilityId("1014").getText();
            String[] hh = HH_toPublish_init.split("/");
            int a = Integer.parseInt(hh[0]);
            int b = Integer.parseInt(hh[1]);
            subtraction= b - a;
            if(partial_subtraction==subtraction){
                System.out.println("There are "+subtraction+" households with PUBLISH FLAG NOT SET");
                break; }
            partial_subtraction=subtraction;
        } while (subtraction != 0);
        int newLength=0;
        int length = Objects.requireNonNull(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings").list()).length;
        System.out.println("The files in ToPanel folder as soon as GROUP ADMIN finished to write are: "+length);
        int hh =  Integer.parseInt(getHHFromFile.get(0).get("HH_To_Publish").replace(".0",""));
        while(length<hh) {
            Thread.sleep(1000);
            newLength=Objects.requireNonNull(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings").list()).length;
            System.out.println(")()()()()()()(");
            System.out.println("Waiting to reach "+hh+" files on ToPanel/Setting Folder....");
            if (newLength==9){ //.........................switch to PUBLISHER and take Publisher snapshot................
                switchToWindowPublisher(driverWinPub);
                Thread.sleep(1000);
                p = new Publisher(driverWinPub);
                p.getPublisherScreenShotWhilePublishing();
                Thread.sleep(1000);
                switchToWindowGA(driverWinGA);
            }
            length=newLength;
        }
        System.out.println("The final number of files in ToPanel folder is: "+length);

    }

    public String selectOnly_number_Active_HH() throws Exception {
        getHHFromFile = excelUserData.getFoldersNamesFromExcelSheet();
        int hh =  Integer.parseInt(getHHFromFile.get(0).get("HH_To_Publish").replace(".0",""));
        System.out.println("clicked FILTER button....");
        Thread.sleep(17500);
        //WebDriverWait wait = new WebDriverWait(driverWinGA, 40);
        //wait.until(ExpectedConditions.visibilityOf(driverWinGA.findElementByName("Advanced filter:")));  //elementToBeClickable(driverWinGA.findElementByName("Advanced filter:")));
        driverWinGA.findElementByName("Advanced filter:").click();
        driverWinGA.findElementByName("...").click();
        Thread.sleep(1000);
        WebElement status = driverWinGA.findElementByName("Status:");
        boolean selectedStatus = status.isSelected();
        if(!selectedStatus){
            status.click();
            driverWinGA.findElementByName("Active").click();
            driverWinGA.findElementByName("To be published").click();
            driverWinGA.findElementByName("Installed").click();
        }else{System.out.println("Status already selected");}
        Thread.sleep(1000);
        driverWinGA.findElementByName("OK").click();
        Thread.sleep(500);
        driverWinGA.findElementByName("Read").click();
        Thread.sleep(8000);
        WebElement selectAll = driverWinGA.findElementByAccessibilityId("1010");
        Actions a = new Actions(driverWinGA);
        Thread.sleep(500);
        getElementCoordinates(selectAll);
        int i = 0;
        for (int countHH = 0; countHH < hh; countHH++) {   //300
        a.moveToElement(selectAll, 10, -500).click().build().perform();
        driverWinGA.findElementByAccessibilityId("DownButton").click();
        i++;}
        System.out.println("Selected "+i+" HH to publish...###");
        driverWinGA.findElementByName("Write").click();
        System.out.println("clicked on WRITE button....");
        checkIfWritingIsOver();
        Thread.sleep(2500);
        driverWinGA.findElementByAccessibilityId("1").click();
        String[] title = driverWinGA.getTitle().split(" ");
        takeAppSnap(driverWinGA, title[0]);
        return title[0];
    }


}
