package tests;

import com.relevantcodes.extentreports.LogStatus;
import core.TestReporter;
import core.email;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private WindowsDriver driverWinMerge=null;
    private WindowsDriver driverWinLC=null;
    private WindowsDriver driverWinFM=null;
    private WindowsDriver driverWinRFAS=null;
    protected WindowsDriver driverWinPub=null;
    public WindowsDriver driverWinGA=null;
    private WindowsDriver driverWinSA=null;
    private WindowsDriver driverWinSV=null;
    private TestReporter reporter;
    String date = null;


    @BeforeSuite(groups={"Publisher_new"})
    public void openWinMergeApp() {
        DesiredCapabilities WM = new DesiredCapabilities();
        WM.setCapability("app", "C:\\Program Files\\WinMerge\\WinMergeU.exe");
        WM.setCapability("platformName", "Windows_WM");
        WM.setCapability("deviceName", "WindowsPC_WM");
        try {
        driverWinMerge = new WindowsDriver(new URL("http://127.0.0.1:4723/"), WM);
        driverWinMerge.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertNotNull(driverWinMerge,"WinMerge App didn't open");
            Set<String> windowsWinMerge = driverWinMerge.getWindowHandles();
            System.out.println("WinMERGE -----> "+windowsWinMerge);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @BeforeSuite(groups={"Publisher_old","Publisher_new"})
    public void setUpLC_FM_RFAS() throws IOException {
        DesiredCapabilities LC = new DesiredCapabilities();
        LC.setCapability("app", "C:\\UNITAM SW\\LogCollector.exe");
        LC.setCapability("platformName", "Windows_LC");
        LC.setCapability("deviceName", "WindowsPC_LC");
        try {
            driverWinLC = new WindowsDriver(new URL("http://127.0.0.1:4723/"), LC);
            Set<String> windowsLC = driverWinLC.getWindowHandles();
            System.out.println(windowsLC);
        driverWinLC.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertNotNull(driverWinLC,"Log Collector didn't open");

        DesiredCapabilities FM = new DesiredCapabilities();
        FM.setCapability("app","C:\\UNITAM SW\\FileMaster.exe");
        FM.setCapability("platformName","Windows_FM");
        FM.setCapability("deviceName","WindowsPC_FM");
        //try {
        driverWinFM = new WindowsDriver(new URL("http://127.0.0.1:4723/"),FM);
        Set<String> windowsFM = driverWinFM.getWindowHandles();
        System.out.println(windowsFM);
        driverWinFM.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertNotNull(driverWinFM,"File Master didn't open");

        DesiredCapabilities RFAS = new DesiredCapabilities();
        RFAS.setCapability("app","C:\\UNITAM SW\\RFAS.exe");
        RFAS.setCapability("platformName","Windows_RFAS");
        RFAS.setCapability("deviceName","WindowsPC_RFAS");

            driverWinRFAS = new WindowsDriver(new URL("http://127.0.0.1:4723/"),RFAS);
            Assert.assertNotNull(driverWinRFAS,"RFAS didn't open");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Set<String> windowsRFAS = driverWinRFAS.getWindowHandles();
        System.out.println(windowsRFAS);
        driverWinRFAS.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        reporter = new TestReporter();
    }

    public void setUpPublisher() {
        DesiredCapabilities Publisher = new DesiredCapabilities();
        Publisher.setCapability("app", "C:\\UNITAM SW\\Publisher.exe");
        Publisher.setCapability("platformName", "Windows_Publisher");
        Publisher.setCapability("deviceName", "WindowsPC_Publisher");
        try {
            driverWinPub = new WindowsDriver(new URL("http://127.0.0.1:4723/"), Publisher);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(driverWinPub,"Publisher didn't open");
        driverWinPub.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setUpSignalsAdmin() {
        DesiredCapabilities SignalsAdmin = new DesiredCapabilities();
        SignalsAdmin.setCapability("app", "C:\\UNITAM SW\\TOOLS\\SignalsAdmin.exe");
        SignalsAdmin.setCapability("platformName", "Windows_SignalsAdmin");
        SignalsAdmin.setCapability("deviceName", "WindowsPC_SignalsAdmin");
        try {
            System.out.println("Set Up Signal Admin...");
            driverWinSA = new WindowsDriver(new URL("http://127.0.0.1:4723/"), SignalsAdmin);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //String currentWindowHandle = driverWinSA.getWindowHandle();
        Set<String> windowsSA = driverWinSA.getWindowHandles();
        System.out.println("SA handler login: "+windowsSA);
        Assert.assertNotNull(driverWinSA,"SignalsAdmin didn't open");
        driverWinSA.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setUpSystemView() {
        DesiredCapabilities SystemView = new DesiredCapabilities();
        SystemView.setCapability("app", "C:\\UNITAM SW\\TOOLS\\SystemView.exe");
        SystemView.setCapability("platformName", "Windows_SystemView");
        SystemView.setCapability("deviceName", "WindowsPC_SystemView");
        try {
            driverWinSV = new WindowsDriver(new URL("http://127.0.0.1:4723/"), SystemView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Set<String> windowsSV = driverWinSV.getWindowHandles();
        Assert.assertNotNull(driverWinSV,"SystemView didn't open");
        System.out.println("SV handler: "+windowsSV);
        driverWinSV.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setUpGroupAdmin() {
        DesiredCapabilities GroupAdmin = new DesiredCapabilities();
        GroupAdmin.setCapability("app", "C:\\UNITAM SW\\GroupAdmin.exe");
        GroupAdmin.setCapability("platformName", "Windows_GroupAdmin");
        GroupAdmin.setCapability("deviceName", "WindowsPC_GroupAdmin");
        try {
            driverWinGA = new WindowsDriver(new URL("http://127.0.0.1:4723/"), GroupAdmin);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Set<String> windowsGA = driverWinGA.getWindowHandles();
        Assert.assertNotNull(driverWinGA,"GroupAdmin didn't open");
        System.out.println("GA handler: "+windowsGA);
        //driverWinGA.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WindowsDriver getDriverLC() { return driverWinLC;}
    public WindowsDriver getDriverFM() { return driverWinFM;}
    public WindowsDriver getDriverRFAS() { return driverWinRFAS;}
    public WindowsDriver getDriverPub() { return driverWinPub;}
    public WindowsDriver getDriverGA() { return driverWinGA;}
    public WindowsDriver getDriverSignalsAdmin() { return driverWinSA;}
    public WindowsDriver getDriverWinMerge() { return driverWinMerge;}
    public WindowsDriver getDriverSV() { return driverWinSV;}


    public void tearDownSA() {
        driverWinSA.close();
        driverWinSA.findElementByName("Yes").click();
    }
        //@AfterSuite
        public void tearDownFM(){
        driverWinFM.close();
            for (int i = 0; i < 2; i++) { driverWinFM.findElementByName("Yes").click(); }
        }

    @AfterMethod(groups={"Publisher_old","Publisher_new"})
    public void testDone(){System.out.println("##########Test is over, proceed with next one...");}

    @BeforeMethod(groups={"Publisher_old","Publisher_new"})
    public void startTest(){System.out.println("#########Test is starting...");}

    public void tearDownSV() { driverWinSV.close(); }

    public void tearDownWinMerge() { driverWinMerge.close(); }

    public void tearDownGA() throws InterruptedException {
        Thread.sleep(1000);
        driverWinGA.close();
        driverWinGA.findElementByName("Yes").click();
    }

        //@AfterSuite
        public void tearDownRFAS() {
            driverWinRFAS.close();
            for (int i = 0; i < 2; i++) { driverWinRFAS.findElementByName("Yes").click(); }
        }

        //@AfterSuite   //  LC close after all apps closed
        public void tearDownLC(){
        System.out.println("---> closing LC ");
        driverWinLC.close();
            for (int i = 0; i < 3; i++) { driverWinLC.findElementByName("Yes").click(); }
        }

        public WebElement waitTillButtonIsDisplayed(WindowsDriver wd, WebElement ele) {
            WebDriverWait wait = new WebDriverWait(wd, 40);
            return wait.until(ExpectedConditions.elementToBeClickable(ele));
          }


    public void rightMouseClick(WindowsDriver ele,WindowsDriver driver){
        Actions actions = new Actions(driver);
        actions.contextClick((WebElement) ele).perform();
    }

    public void switchToWindowGA(WindowsDriver driverWinGA) {
        Set<String> windows = driverWinGA.getWindowHandles();
        for(String w : windows) {
            driverWinGA.switchTo().window(w);
            System.out.println("---> GA: "+w);
        }
    }

    public void switchToWindowWinMerge(WindowsDriver driverWinMerge) {
        Set<String> windows = driverWinMerge.getWindowHandles();
        for(String w : windows) {
            driverWinMerge.switchTo().window(w);
            System.out.println("---> DriverWinMerge: "+w);
        }
    }

    public void switchToWindowSA(WindowsDriver driverWinSA) {
        Set<String> windows = driverWinSA.getWindowHandles();
        for(String w : windows) {
            driverWinSA.switchTo().window(w);
            System.out.println("---> SA window: "+w);
        }
    }

    public void switchToWindowPublisher(WindowsDriver driverWinPub) {
        System.out.println();
        Set<String> windows = driverWinPub.getWindowHandles();
        for(String w : windows) {
            driverWinPub.switchTo().window(w);
            System.out.println("---> Publisher window: "+w);
        }
    }

    public void switchToWindowSV(WindowsDriver driverWinSV) {
        System.out.println();
        Set<String> windows = driverWinSV.getWindowHandles();
        for(String w : windows) {
            driverWinSV.switchTo().window(w);
            System.out.println("---> SystemView window: "+w);
        }
    }

    public void switchToWindowRFAS(WindowsDriver driverWinRFAS) {
        System.out.println();
        Set<String> windows = driverWinRFAS.getWindowHandles();
        for(String w : windows) {
            driverWinRFAS.switchTo().window(w);
            System.out.println("---> RFAS window: "+w);
        }
    }

    public void switchToWindowFM(WindowsDriver driverWinFM) {
        System.out.println();
        Set<String> windows = driverWinFM.getWindowHandles();
        for(String w : windows) {
            driverWinFM.switchTo().window(w);
            System.out.println("---> FM window: "+w);
        }
    }

    public void switchToWindowLC(WindowsDriver driverWinLC) {
        System.out.println();
        Set<String> windows = driverWinLC.getWindowHandles();
        for(String w : windows) {
            driverWinLC.switchTo().window(w);
            System.out.println("---> LC window: "+w);
        }
    }

    @BeforeMethod(groups={"Publisher_old","Publisher_new"})
    public void initTestReport(Method method){ reporter.startReporting(method.getName()); }

    public TestReporter reporter(){
        if(reporter!=null){
            return reporter;
        }
        return null;
    }

    @AfterMethod(groups={"Publisher_old","Publisher_new"})
    public void closeReport(){ reporter.endReporting(); }

    @AfterSuite(groups={"Publisher_old","Publisher_new"})
    public void clearReport(){
        reporter.flushReport();
    }


    @AfterMethod(groups={"Publisher_old","Publisher_new"})
    public void testStatusInExtentReport(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()){
            reporter().report(LogStatus.FAIL,"Failed test is: "+result.getName());
            reporter().report(LogStatus.FAIL,result.getThrowable());
        }else if(ITestResult.SUCCESS == result.getStatus()){
            reporter().report(LogStatus.PASS,"Test passed: "+result.getName());
        }else if(ITestResult.SKIP == result.getStatus()){
            reporter().report(LogStatus.SKIP,"Test skipped: "+result.getName());
        }
    }

    @AfterMethod(groups={"Publisher_old","Publisher_new"})
    public void takeScreenShotIfFailurePublisherTests(ITestResult result) throws Exception {
        if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("checkIfClientIsAuthorized")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("checkIfClientIsAuthorized");
        } else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("checkIfPublishHouseHoldsProcessIsCorrect")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinGA);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("checkIfPublishHouseHoldsProcessIsCorrect");
        } else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("performPriorityListAction")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinSA);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("performPriorityListAction");
        } else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("readLogsFromSystemView")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinSV);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("readLogsFromSystemView");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("checkIfPublisherOpensCorrectly")) {
            TakesScreenshot camera =((TakesScreenshot)driverWinPub);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__"+result.getName()+"__"+formatDate()+".png");
            FileHandler.copy(screenShot,DestFile);
            email.sendEmailForPublisherFailure("checkIfPublisherOpensCorrectly");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("verifyCompareFiles")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinPub);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("verifyCompareFiles");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("copyGeneratedFiles")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("copyGeneratedFiles");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("verifyDownloadNewApp")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinLC);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("verifyDownloadNewApp");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("closeAllApps")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("closeAllApps");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("verifyCompareFiles")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("verifyCompareFiles");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("moveFiles")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("moveFiles");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("deleteFiles")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("deleteFiles");
        }else if (ITestResult.FAILURE == result.getStatus() && result.getName().equals("emptyFilesInToPanelSetting")) {
            TakesScreenshot camera = ((TakesScreenshot) driverWinRFAS);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./src/main/screenShots/FAIL__" + result.getName() + "__" + formatDate() + ".png");
            FileHandler.copy(screenShot, DestFile);
            email.sendEmailForPublisherFailure("emptyFilesInToPanelSetting");
        }
    }

    public String formatDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        date = date.replaceAll("/","").replaceAll(":","").replaceAll(" ","");
        return date;
    }

       public void takeAppSnap(WindowsDriver driver, String app) throws IOException {
           TakesScreenshot ts = (TakesScreenshot) driver;
           File file = ts.getScreenshotAs(OutputType.FILE);
           FileUtils.copyFile(file,new File("./src/main/screenShots/"+app+"__"+formatDate()+".png"));
           if(!file.exists()){ file.mkdir(); }
       }

    public void takeFileDiffSnap(WindowsDriver driver, String fileName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("./src/main/screenShots/"+fileName+".png"));
        if(!file.exists()){ file.mkdir(); }
    }

       public String takeScreenForReport(WindowsDriver driver, String app) throws IOException {
           TakesScreenshot ts = (TakesScreenshot) driver;
           File file = ts.getScreenshotAs(OutputType.FILE);
           Path srcPath = file.toPath();
           File DestFile=new File("./src/main/screenShots/"+app+"__"+formatDate()+".png");
           Path destPath = DestFile.toPath();
           Files.copy(srcPath,destPath);
           return destPath.toString();
       }

       public void scrollDownTillElementIsVisible(WebElement ele){ ele.sendKeys(Keys.PAGE_DOWN); }

       public void getElementCoordinates(WebElement ele) {
        Point point = ele.getLocation();
        int xcord = point.getX();
        int ycord = point.getY();
        System.out.println("Coordinates are: X-> "+xcord+" and Y -> "+ycord);
    }


    public void copyPathFiles(String s) throws Exception {
        // copying File path to Clipboard
        StringSelection str = new StringSelection(s);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Robot rb = new Robot();
        // press Control+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Control+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
    }


}