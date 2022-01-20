package apps;

import core.excelUserData;
import tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


public class CopyFiles extends TestBase {

    public static List<Map<String,String>> app_new_vers;

    public boolean copyFilesFromAppFolderToOldTestFolder(String v) throws Exception {
            app_new_vers = excelUserData.getFoldersNamesFromExcelSheet();
            File theDir = new File(app_new_vers.get(0).get("OldFolderApp"));
            if (!theDir.exists()){theDir.mkdirs();}
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

    public boolean installNewApp() throws Exception {
        System.out.println("rename of apps in unitamsw");
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
        return (renamed && renamed1);
    }

    public boolean copyFilesFromAppFolderToNewTestFolder() throws Exception {
        app_new_vers = excelUserData.getFoldersNamesFromExcelSheet();
        File theDir = new File(app_new_vers.get(0).get("NewFolderApp"));
        if (!theDir.exists()){theDir.mkdirs();}
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

    public boolean copyFilesFromChannelCodeFolderToTestFolder() throws Exception {
        app_new_vers = excelUserData.getPolluxGWDataFromFile();
        File ChannelCode = new File("C:\\UNITAM\\PolluxGateway\\Panel_0\\Files\\ChannelCode");
        if (!ChannelCode.exists()){
            ChannelCode.mkdir();
        }
        ProcessBuilder ps = new ProcessBuilder("xcopy",app_new_vers.get(0).get("ChannelCode"), "C:\\UNITAM\\PolluxGateway\\Panel_0\\Files\\ChannelCode");
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine())  != null) {
            System.out.println(line);}
        pr.waitFor();
        System.out.println("ok! Files copied under Panel_0\\Files\\ChannelCode Folder");
        in.close();
        return true;
    }

    public boolean copyFilesFromSkyExceptionsFolderToTestFolder() throws Exception {
        app_new_vers = excelUserData.getPolluxGWDataFromFile();
        File SkyExceptions = new File("C:\\UNITAM\\PolluxGateway\\Panel_0\\Files\\SkyExceptions");
        if (!SkyExceptions.exists()){SkyExceptions.mkdir();}
        ProcessBuilder ps = new ProcessBuilder("xcopy",app_new_vers.get(0).get("SkyExceptions"), "C:\\UNITAM\\PolluxGateway\\Panel_0\\Files\\SkyExceptions");
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine())  != null) {
            System.out.println(line);}
        pr.waitFor();
        System.out.println("ok! Files copied under Panel_0\\Files\\SkyExceptions Folder");
        in.close();
        return true;
    }

    public boolean copyFilesFromNTACodeFolderToTestFolder() throws Exception {
        app_new_vers = excelUserData.getPolluxGWDataFromFile();
        File NTACode = new File("C:\\UNITAM\\PolluxGateway\\Panel_0\\Files\\NTACode");
        if (!NTACode.exists()){NTACode.mkdir();}
        ProcessBuilder ps = new ProcessBuilder("xcopy",app_new_vers.get(0).get("NTACode"), "C:\\UNITAM\\PolluxGateway\\Panel_0\\Files\\NTACode");
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine())  != null) {
            System.out.println(line);}
        pr.waitFor();
        System.out.println("ok! Files copied under Panel_0\\Files\\NTACode Folder");
        in.close();
        return true;
    }

}
