package apps;

import core.excelUserData;
import tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


public class CopyFiles extends TestBase {

    public static List<Map<String,String>> folders;

    public boolean copyFilesFromAppFolderToOldTestFolder(String v) throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
            File theDir = new File(folders.get(0).get("OldFolderApp"));
            if (!theDir.exists()){theDir.mkdirs();}
            ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings", folders.get(0).get("OldFolderApp"));
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
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        File oldPub = new File("C:\\UNITAM SW\\Publisher.exe");
        File oldPub1 = new File(folders.get(0).get("RenamePublisherVersion"));
        // Renames the file
        boolean renamed = oldPub.renameTo(oldPub1);
        if (renamed) { System.out.println("File renamed to " + oldPub1.getPath()); }
        else { System.out.println("Error renaming file " + oldPub.getPath()); }
        File newPub1 = new File(folders.get(0).get("NewPublisherVersion"));
        File newPub = new File("C:\\UNITAM SW\\Publisher.exe");
        boolean renamed1 = newPub1.renameTo(newPub);
        if (renamed1) { System.out.println("File renamed to " + newPub.getPath()); }
        else { System.out.println("Error renaming file " + newPub1.getPath()); }
        return (renamed && renamed1);
    }

    public boolean copyFilesFromAppFolderToNewTestFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        File theDir = new File(folders.get(0).get("NewFolderApp"));
        if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings", folders.get(0).get("NewFolderApp"));
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

    //PriorityList with OLD VERSION
    public boolean ActiveCPListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\CPLists\\ActiveCPList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveCPListFile copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveDeviceListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\DeviceLists\\ActiveDeviceList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveDeviceListFile copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveRefSetXDeviceListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\DeviceLists\\ActiveRefSetXDeviceList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveRefSetXDeviceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveGTAMHHCPListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\GTAMHHCPLists\\ActiveGTAMHHCPList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveGTAMHHCPList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveHHListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        //File theDir = new File("C:\\TEST\\OLD_PriorityLists");
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\HHLists\\ActiveHHList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveHHList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveNSMeterListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\NSMeterLists\\ActiveNSMeterList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveNSMeterList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean MainReelListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\ReelLists\\MainReelList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! MainReelList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean MainReferenceListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\ReelLists\\MainReferenceList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! MainReferenceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveRefSetListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RefSetLists\\ActiveRefSetList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveRefSetList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveCensusListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RefSetLists\\ActiveCensusList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveCensusList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveRegionListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RegionLists\\ActiveRegionList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveRegionList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveHHByRegionListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RegionLists\\ActiveHHByRegionList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveHHByRegionList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveStreamingSourceListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\StreamingSourceLists\\ActiveStreamingSourceList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveStreamingSourceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveVODListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\VODFiles\\ActiveVODList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveVODList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveVODByDeviceListFileFromFileMasterToTestPriorityListOldFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("OldFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\VODFiles\\ActiveVODByDeviceList", folders.get(0).get("OldFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveVODByDeviceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    //PriorityList with NEW VERSION
    public boolean ActiveCPListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\CPLists\\ActiveCPList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveCPListFile copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveDeviceListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\DeviceLists\\ActiveDeviceList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveDeviceListFile copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveRefSetXDeviceListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\DeviceLists\\ActiveRefSetXDeviceList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveRefSetXDeviceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveGTAMHHCPListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\GTAMHHCPLists\\ActiveGTAMHHCPList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveGTAMHHCPList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveHHListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\HHLists\\ActiveHHList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveHHList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveNSMeterListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\NSMeterLists\\ActiveNSMeterList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveNSMeterList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean MainReelListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\ReelLists\\MainReelList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! MainReelList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean MainReferenceListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\ReelLists\\MainReferenceList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! MainReferenceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveRefSetListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RefSetLists\\ActiveRefSetList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveRefSetList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveCensusListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RefSetLists\\ActiveCensusList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveCensusList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveRegionListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RegionLists\\ActiveRegionList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveRegionList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveHHByRegionListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\RegionLists\\ActiveHHByRegionList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveHHByRegionList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveStreamingSourceListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\StreamingSourceLists\\ActiveStreamingSourceList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveStreamingSourceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveVODListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\VODFiles\\ActiveVODList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveVODList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }

    public boolean ActiveVODByDeviceListFileFromFileMasterToTestPriorityListNewFolder() throws Exception {
        //File theDir = new File("C:\\TEST\\NEW_PriorityLists");
        folders = excelUserData.getFoldersNamesFromExcelSheet();
        //File theDir = new File(folders.get(0).get("NewFolderPrioLists"));
        //if (!theDir.exists()){theDir.mkdirs();}
        ProcessBuilder ps = new ProcessBuilder("xcopy", "C:\\UNITAM\\FileMaster\\Files\\VODFiles\\ActiveVODByDeviceList", folders.get(0).get("NewFolderApp"));
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) { System.out.println(line); }
        pr.waitFor();
        //System.out.println("ok! ActiveVODByDeviceList copied under C:\\TEST PriorityList Old Folder");
        in.close();
        return true;
    }


}
