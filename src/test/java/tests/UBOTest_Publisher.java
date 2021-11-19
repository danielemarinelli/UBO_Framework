package tests;

import apps.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UBOTest_Publisher extends TestBase{

    Publisher p = null;
    RFAS rfas = null;
    GroupAdmin ga = null;
    SignalAdmin sa = null;
    SystemView sv = null;
    LogCollector lc = null;
    WinMerge wm = null;
    CopyFiles copy = null;
    public String[] ArrayPublisherVersion = null;
    MoveFiles move = null;
    Delete delete = null;

    //@Test(priority=1, groups={"Publisher_old"})
    public void deleteFilesOfPreviousTest() throws Exception {
        delete = new Delete();
        int numberFiles = delete.deleteFiles();
        if(numberFiles==0){
            Assert.assertTrue(true);
        }else{
            Assert.fail();}
    }

    //@Test(priority=1, groups={"Publisher_new"})
    public void emptyFilesInToPanelSetting() throws IOException {
        delete = new Delete();
        int numberFiles = delete.emptyFolderSetting();
        if(numberFiles==0){
            Assert.assertTrue(true);
        }else{
            Assert.fail();}
    }

    //@Test(priority=2, groups={"Publisher_old","Publisher_new"})
    public void checkIfClientIsAuthorized() {
        rfas = new RFAS(getDriverRFAS());
         int client_RFAS = 0;
         try {
             client_RFAS = rfas.setAuthorization();
         } catch (Exception e) {
             e.printStackTrace();
         }
         Assert.assertEquals( client_RFAS,1);
    }

    @Test(priority=3, groups={"Publisher_old","Publisher_new"})
    public void checkIfPublisherOpensCorrectly()  {
        setUpPublisher();
        p = new Publisher(getDriverPub());
        String title_Publisher = null;
        try {
            title_Publisher = p.openPublisherApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayPublisherVersion = p.getPublisherVersion().replace("."," ").split(" ");
        System.out.println("Publisher Version is... "+ArrayPublisherVersion[3]);
        Assert.assertEquals(title_Publisher,"Publisher");
        //getDriverPub().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //@Test(priority=4, groups={"Publisher_old","Publisher_new"})
    public void performPriorityListAction() {
        String title_SignalsAdmin = null;
        try {
            setUpSignalsAdmin();
            sa = new SignalAdmin(getDriverSignalsAdmin());
            sa.openSignalsAdminApp();
            switchToWindowSA(getDriverSignalsAdmin());
            title_SignalsAdmin = sa.processPriorityList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(title_SignalsAdmin,"SignalsAdmin");
        getDriverSignalsAdmin().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     }

    //@Test(priority=5, groups={"Publisher_old","Publisher_new"})
    public void checkIfPublishHouseHoldsProcessIsCorrect()  {
        String title_GroupAdmin = null;
        try {
            setUpGroupAdmin();
            ga = new GroupAdmin(getDriverGA(),getDriverPub());
            ga.openGroupAdminApp();
            switchToWindowGA(getDriverGA());
            //title_GroupAdmin = ga.publishAll_HH();  1322
            //title_GroupAdmin = ga.publishOnlyActive_HH();  //  818
            title_GroupAdmin = ga.selectOnly_number_Active_HH();  //300
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(title_GroupAdmin,"GroupAdmin");
    }

    //@Test(priority=6, groups={"Publisher_old","Publisher_new"})
    public void readLogsFromSystemView() throws Exception {
        System.out.println("##########System View############");
        String title_sv = null;
        try {
            setUpSystemView();
            sv = new SystemView(getDriverSV());
            title_sv = sv.displaySystemViewLogs();
            lc = new LogCollector();
            lc.get_LC_Picture(getDriverLC());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(title_sv,"SystemView");
    }

    //@Test(priority=7, groups={"Publisher_old"})
    public void copyGeneratedFilesToOldTestVersionFolder() throws Exception {
        System.out.println("Inside copyGeneratedFiles() method");
         copy = new CopyFiles();
         boolean t = copy.copyFilesFromAppFolderToOldTestFolder(ArrayPublisherVersion[3]);
         //copy.renameFileMasterFolder();       to do!!!!
         System.out.println("files all copied to OLD folder....");
        Assert.assertTrue(t);
    }

    //@Test(priority=7, groups={"Publisher_new"})
    public void copyGeneratedFilesToNewTestVersionFolder() throws Exception {
        System.out.println("Inside copyGeneratedFiles() method");
        copy = new CopyFiles();
        boolean t = copy.copyFilesFromAppFolderToNewTestFolder();
        //copy.renameFileMasterFolder();       to do!!!!
        System.out.println("files all copied to NEW folder....");
        Assert.assertTrue(t);
    }

    //ALWAYS REMEMBER TO NAME:
    //C:\UNITAM SW\Publisher the old version and C:\TEST\PublisherXXX the new version where XXX is the new version, ex 117
    //@Test(priority=8, groups={"Publisher_old"})
    public void verifyDownloadNewApp() throws Exception {
        System.out.println("Inside verifyDownloadNewApp() method");
        copy = new CopyFiles();
        copy.installNewApp(getDriverPub());
    }

    //@Test(priority=9, groups={"Publisher_old",})
    public void closeAllApps() throws InterruptedException {
        //switchToWindowWinMerge(getDriverWinMerge());
        //tearDownWinMerge();
        System.out.println("WinMerge closed...");
        switchToWindowSA(getDriverSignalsAdmin());
        tearDownSA();
        System.out.println("SystemAdmin closed...");
        switchToWindowSV(getDriverSV());
        tearDownSV();
        System.out.println("System View closed...");
        switchToWindowRFAS(getDriverRFAS());
        tearDownRFAS();
        System.out.println("RFAS closed...");
        switchToWindowGA(getDriverGA());
        tearDownGA();
        switchToWindowFM(getDriverFM());
        tearDownFM();
        System.out.println("FM closed...");
        switchToWindowLC(getDriverLC());
        tearDownLC();
        System.out.println("App closed!!!!");
    }

    //THE LAST TEST AFTER BOTH VERS APPS PUBLISHED!!
    @Test(priority=8, groups={"Publisher_new"})
    public void verifyCompareFiles() throws Exception {
            wm = new WinMerge(getDriverWinMerge());
            //int files = wm.selectFilesToCompare();
            //int files = wm.generateReportToSend();
            int files = wm.generateReportToSend(ArrayPublisherVersion[3]);
            if(files>0){
                Assert.assertTrue(true);
            }else{
                Assert.fail();}
    }

    //@Test(priority=9, groups={"Publisher_new"})  //NOT NEEDED
    public void moveFiles() throws IOException {
        move = new MoveFiles(getDriverWinMerge());
        int files = move.moveFiles();
        if(files>0){
            Assert.assertTrue(true);
        }else{
            Assert.fail();}
    }

}
