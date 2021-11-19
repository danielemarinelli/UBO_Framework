package apps;

import core.excelUserData;
import io.appium.java_client.windows.WindowsDriver;
import tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


public class CopyFiles extends TestBase {

    public static List<Map<String,String>> app_new_vers;

    //C:\TEST\OldAppVersion ; C:\TEST\NewAppVersion
    public boolean copyFilesFromAppFolderToOldTestFolder(String v) throws Exception {
            app_new_vers = excelUserData.getFoldersNamesFromExcelSheet();
            ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings", app_new_vers.get(0).get("OldFolderApp"));
            ps.redirectErrorStream(true);
            Process pr = ps.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine())  != null) {
                System.out.println(line);}
            pr.waitFor();
            System.out.println("ok! Files copied under C:\\TEST Old Folder");
            in.close();
        return true;
    }

    public void installNewApp(WindowsDriver driverPub) throws Exception {
        driverPub.close();
        driverPub.findElementByAccessibilityId("6").click();
        Thread.sleep(1000);
        driverPub.findElementByAccessibilityId("6").click();

        Thread.sleep(3000);
        app_new_vers = excelUserData.getFoldersNamesFromExcelSheet();
        File oldPub = new File("C:\\UNITAM SW\\Publisher.exe");
        File oldPub1 = new File(app_new_vers.get(0).get("RenamePublisherVersion"));
        // Renames the file
        boolean renamed = oldPub.renameTo(oldPub1);
        if (renamed) { System.out.println("File renamed to " + oldPub1.getPath()); }
        else { System.out.println("Error renaming file " + oldPub.getPath()); }
        File newPub1 = new File(app_new_vers.get(0).get("NewPublisherVersion"));
        File newPub = new File("C:\\UNITAM SW\\Publisher.exe");
        boolean renamed1 = newPub1.renameTo(newPub);
        if (renamed1) { System.out.println("File renamed to " + newPub.getPath()); }
        else { System.out.println("Error renaming file " + newPub1.getPath()); }

    }

    public boolean copyFilesFromAppFolderToNewTestFolder() throws Exception {
        app_new_vers = excelUserData.getFoldersNamesFromExcelSheet();
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings", app_new_vers.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        System.out.println("ok! Files copied under C:\\TEST New Folder");
        in.close();
        return true;
    }


}
