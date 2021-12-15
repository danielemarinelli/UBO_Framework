package apps;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import tests.TestBase;
import core.excelUserData;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesActions extends TestBase {

    public static List<Map<String, String>> app_folders;
    WindowsDriver driverWinMerge = null;
    //public FilesActions(WindowsDriver driverWinMerge){ this.driverWinMerge = driverWinMerge; }
    public static List<Map<String,String>> allCommonFoldersFromFile;


    public void moveFile() throws Exception {
        System.out.println("inside method MOVE-FILES Files.move");
        Thread.sleep(20000);
        Path temp = Files.move
                (Paths.get("C:\\Windows\\Publisher.ini"), Paths.get("C:\\TEST\\old_Exe\\Publisher.ini"));
        if (temp != null) {
            System.out.println("File Publisher.ini moved successfully");
        } else {
            System.out.println("Failed to move the file");
        }

    }

    public int moveFiles() throws IOException {
        System.out.println("inside method MOVE-FILES Files.move");
        app_folders = excelUserData.getFoldersNamesFromExcelSheet();
        ArrayList l_All_HH = new ArrayList();  //contains all HH
        List files = driverWinMerge.findElementsByAccessibilityId("ListViewSubItem-0");
        for (Object file : files) {
            l_All_HH.add(((RemoteWebElement) file).getText());
        }
        System.out.println("HH size: -> " + l_All_HH.size());
        int newFiles = 0;
        int oldFiles = 0;
        for (int j = 0; j < l_All_HH.size(); j++) {
            Path oldMove = Files.move
                    (Paths.get(app_folders.get(0).get("OldFolderApp") + "\\" + l_All_HH.get(j).toString()),
                            Paths.get(app_folders.get(0).get("OldFolderTest") + "\\" + l_All_HH.get(j).toString()));
            if (oldMove != null) {
                System.out.println("File " + l_All_HH.get(j).toString() + " renamed and moved successfully");
                oldFiles++;
            } else {
                System.out.println("Failed to move the file " + l_All_HH.get(j).toString());
            }
            Path newMove = Files.move
                    (Paths.get(app_folders.get(0).get("NewFolderApp") + "\\" + l_All_HH.get(j).toString()),
                            Paths.get(app_folders.get(0).get("NewFolderTest") + "\\" + l_All_HH.get(j).toString()));
            if (newMove != null) {
                System.out.println("File " + l_All_HH.get(j).toString() + " renamed and moved successfully");
                newFiles++;
            } else {
                System.out.println("Failed to move the file " + l_All_HH.get(j).toString());
            }

        }
        return newFiles + oldFiles;
    }

    public String getTpiVersionFromPublisherINI() throws Exception {
        File inputFile = new File("C:\\Windows\\Publisher.ini");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String currentLine;
        String version = null;
        int count = 0;
        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (currentLine != null && currentLine.contains("nToPanelFileVer")) {   //READ VERSION
                String[] words = currentLine.split("=");  //read the string and split it at = symbol
                System.out.println("Version TPI BEFORE NEW VERSION --> " + words[1]);
                System.out.println("Criteria met at Line number " + count);
                version = words[1];
                break;
            }
        }
        return version;
    }

    public int eraseLineInFilePublisherINI() throws Exception {

        //File inputFile = new File("C:\\ProgramData\\UNITAM\\Config\\Publisher.ini");

        File inputFile = new File("C:\\Windows\\Publisher.ini");
        /*HOW CREATE EMPTY FILE
        File tempFile = new File("Publisher_copy.ini");
        Path tempFile = Paths.get("C:\\Windows\\Publisher_copy.ini");
        byte[] buf = "".getBytes();  //CREATE EMPTY .TXT FILE
        Files.write(tempFile, buf); */
        Thread.sleep(1000);
        File tempFile = new File("C:\\Windows\\Publisher_copy.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (null != currentLine && !currentLine.contains("nToPanelFileVer")) {   //nToPanelFileVer
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        System.out.println("Erased row!!");
        writer.close();
        reader.close();

        //HOW CREATE EMPTY FILE
        //File file = new File(fileName);
        //Path file = Paths.get("C:\\telepanel_1.txt");
        //byte[] buf = "".getBytes();  //CREATE EMPTY .TXT FILE
        //Files.write(file, buf);
        System.out.println();

        //moveFile();
        //System.out.println();
        //String extension = ".INI";
        //File inputFileRenamed = new File("C:\\Windows\\Publisher_renamed"+extension);
        // Delete the original file
        //inputFile.renameTo(inputFileRenamed);
        //if (!inputFile.delete()) {System.out.println("Could not delete original file");}
        System.out.println();
        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename file");
        } else {
            System.out.println("######Rename file CORRECTLY##########");
        }

        boolean successful = tempFile.renameTo(inputFile);
        System.out.println(successful);
        return 1;
    }

    public int cancelPublisherOldExeFileFromUnitamSWFolder() throws IOException {
        Path temp = Files.move
                (Paths.get("C:\\UNITAM SW\\Publisher_OLD_VERS.exe"),
                        Paths.get("C:\\TEST\\old_EXE\\Publisher_OLD_VERS.exe"));
        if (temp != null) {
            System.out.println("File renamed and moved successfully");
        } else {
            System.out.println("Failed to move the file");
        }
        int e = Objects.requireNonNull(new File("C:\\TEST\\old_EXE").list()).length;
        return e;
    }

    public int unzipCommonDataFiles() throws IOException {
        allCommonFoldersFromFile = excelUserData.getFoldersNamesFromExcelSheet();
        String zipFilePath = allCommonFoldersFromFile.get(0).get("Common data");
        String destinationDir = "C:\\UNITAM\\FileMaster\\Files";
        File destDir = new File(destinationDir);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destinationDir + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        int common = Objects.requireNonNull(new File(destinationDir).list()).length;
        return common;
    }

    public int unzipDailyDataFiles() throws IOException {
        allCommonFoldersFromFile = excelUserData.getFoldersNamesFromExcelSheet();
        String zipFilePath = allCommonFoldersFromFile.get(0).get("Daily data");
        String destinationDir = allCommonFoldersFromFile.get(0).get("Daily data destination");
        File destDir = new File(destinationDir);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destinationDir + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        int daily = Objects.requireNonNull(new File(destinationDir).list()).length;
        return daily;
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        final int BUFFER_SIZE = 4096;  //Size of the buffer to read/write data
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public void renameFileMasterFolder() throws InterruptedException {
        Thread.sleep(10000);
        File sourceFile = new File("C:\\UNITAM\\FileMaster");
        File destFile = new File("C:\\UNITAM\\FileMaster_OLD");
        if (sourceFile.renameTo(destFile)) {
            System.out.println("Directory FileMaster renamed successfully in FileMaster_OLD");
        } else {
            System.out.println("Failed to rename directory in FileMaster_OLD");
        }

    }

    public void removeDirectory() {
        String dirPath = "C:\\UNITAM\\FileMaster";
        File dir = new File(dirPath);
        try {
            boolean deleted = dir.delete();
            if (deleted) {
                System.out.println("Directory removed");
            } else {
                System.out.println("Directory could not be removed");
            }
        } catch (SecurityException ex) {
            System.out.println("Delete is denied.");
        }
    }

    public int removeDirectory(File dir) {
        //System.out.print("delFM_old.");
        if (!dir.exists()) { dir.mkdir();}
        if(dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {removeDirectory(aFile);}
            }
            dir.delete();
        } else {
            dir.delete();
        }
        return 1;
    }

    public int changeTagInTestRunnerFileToNew(String oldLine, String newLine) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\TestRunner.xml";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();
        //System.out.println("Contents of the file: "+fileContents);
        //closing the Scanner object
        sc.close();
        //String oldLine = "<include name=\"Publisher_old\" />";
        //String newLine = "<include name=\"Publisher_new\" />";
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        //System.out.println("");
        //System.out.println("new data: "+fileContents);
        writer.append(fileContents);
        writer.flush();
        return 1;
    }

    /*public int changeTagInTestRunnerFileToOld(String origLine, String lineBackToOld) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\TestRunner.xml";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();
        //System.out.println("Contents of the file: "+fileContents);
        //closing the Scanner object
        sc.close();
        //String oldLine = "<include name=\"Publisher_old\" />";
        //String newLine = "<include name=\"Publisher_new\" />";
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(origLine, lineBackToOld);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        //System.out.println("");
        //System.out.println("new data: "+fileContents);
        writer.append(fileContents);
        writer.flush();
        return 1;

    }  */

    public int launchTestFromCommandLine(String mvn_test) throws IOException {
        String[] command = {"dir"};
        Runtime.getRuntime().exec("dir");
        ProcessBuilder builder = new ProcessBuilder(command);
        builder = builder.directory(new File("C:\\TEST\\dataExcel\\"));
        Process p = builder.start();
        return 1;
    }

    public void createFoldersToUnzipCommonData() throws IOException {
        //To create single directory/folder
        allCommonFoldersFromFile = excelUserData.getFoldersNamesFromExcelSheet();
        //allCommonFoldersFromFile.get(0).get("CommonFolder1");
        //System.out.println(allCommonFoldersFromFile.get(0).get("CommonFolder1"));
        //System.out.println("size is: "+allCommonFoldersFromFile.get(0).size());
        for (int i = 1; i <= allCommonFoldersFromFile.get(0).size()-9; i++) {
            File file = new File("C:\\UNITAM\\FileMaster\\Files\\"+allCommonFoldersFromFile.get(0).get("CommonFolder"+i));
            if (!file.exists()) {
                if (file.mkdir()) {System.out.println("Directory "+allCommonFoldersFromFile.get(0).get("CommonFolder"+i)+" is created!");
                } else {System.out.println("Failed to create directory "+allCommonFoldersFromFile.get(0).get("CommonFolder"+i)+"!");}
            }

        }
        /*
        File CIRuleLists = new File("C:\\UNITAM\\FileMaster\\Files\\CIRuleLists");
        if (!CIRuleLists.exists()) {
            if (CIRuleLists.mkdir()) {System.out.println("Directory CIRuleLists is created!");
            } else {System.out.println("Failed to create directory CIRuleLists!");}
        }
        File Common = new File("C:\\UNITAM\\FileMaster\\Files\\Common");
        if (!Common.exists()) {
            if (Common.mkdir()) {System.out.println("Directory Common is created!");
            } else {System.out.println("Failed to create directory Common!");}
        }
        File CPLists = new File("C:\\UNITAM\\FileMaster\\Files\\CPLists");
        if (!CPLists.exists()) {
            if (CPLists.mkdir()) {System.out.println("Directory CPLists is created!");
            } else {System.out.println("Failed to create directory CPLists!");}
        }
        File CSPKeysFile = new File("C:\\UNITAM\\FileMaster\\Files\\CSPKeysFile");
        if (!CSPKeysFile.exists()) {
            if (CSPKeysFile.mkdir()) {System.out.println("Directory CSPKeysFile is created!");
            } else {System.out.println("Failed to create directory CSPKeysFile!");}
        }
        File CSPReferenceFile = new File("C:\\UNITAM\\FileMaster\\Files\\CSPReferenceFile");
        if (!CSPReferenceFile.exists()) {
            if (CSPReferenceFile.mkdir()) {System.out.println("Directory CSPReferenceFile is created!");
            } else {System.out.println("Failed to create directory CSPReferenceFile!");}
        }
        File DeviceLists = new File("C:\\UNITAM\\FileMaster\\Files\\DeviceLists");
        if (!DeviceLists.exists()) {
            if (DeviceLists.mkdir()) {System.out.println("Directory DeviceLists is created!");
            } else {System.out.println("Failed to create directory DeviceLists!");}
        }
        File DMXStatements = new File("C:\\UNITAM\\FileMaster\\Files\\DMXStatements");
        if (!DMXStatements.exists()) {
            if (DMXStatements.mkdir()) {System.out.println("Directory DMXStatements is created!");
            } else {System.out.println("Failed to create directory DMXStatements!");}
        }
        File GTAMHHCPLists = new File("C:\\UNITAM\\FileMaster\\Files\\GTAMHHCPLists");
        if (!GTAMHHCPLists.exists()) {
            if (GTAMHHCPLists.mkdir()) {System.out.println("Directory GTAMHHCPLists is created!");
            } else {System.out.println("Failed to create directory GTAMHHCPLists!");}
        }
        File HHLists = new File("C:\\UNITAM\\FileMaster\\Files\\HHLists");
        if (!HHLists.exists()) {
            if (HHLists.mkdir()) {System.out.println("Directory HHLists is created!");
            } else {System.out.println("Failed to create directory HHLists!");}
        }
        File HHSettings = new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings");
        if (!HHSettings.exists()) {
            if (HHSettings.mkdir()) {System.out.println("Directory HHSettings is created!");
            } else {System.out.println("Failed to create directory HHSettings!");}
        }
        File MVD = new File("C:\\UNITAM\\FileMaster\\Files\\MVD");
        if (!MVD.exists()) {
            if (MVD.mkdir()) {System.out.println("Directory MVD is created!");
            } else {System.out.println("Failed to create directory MVD!");}
        }
        File NSMeterLists = new File("C:\\UNITAM\\FileMaster\\Files\\NSMeterLists");
        if (!NSMeterLists.exists()) {
            if (NSMeterLists.mkdir()) {System.out.println("Directory NSMeterLists is created!");
            } else {System.out.println("Failed to create directory NSMeterLists!");}
        }
        File PolluxConfigs = new File("C:\\UNITAM\\FileMaster\\Files\\PolluxConfigs");
        if (!PolluxConfigs.exists()) {
            if (PolluxConfigs.mkdir()) {System.out.println("Directory PolluxConfigs is created!");
            } else {System.out.println("Failed to create directory PolluxConfigs!");}
        }
        File ReelLists = new File("C:\\UNITAM\\FileMaster\\Files\\ReelLists");
        if (!ReelLists.exists()) {
            if (ReelLists.mkdir()) {System.out.println("Directory ReelLists is created!");
            } else {System.out.println("Failed to create directory ReelLists!");}
        }
        File RefSetLists = new File("C:\\UNITAM\\FileMaster\\Files\\RefSetLists");
        if (!RefSetLists.exists()) {
            if (RefSetLists.mkdir()) {System.out.println("Directory RefSetLists is created!");
            } else {System.out.println("Failed to create directory RefSetLists!");}
        }
        File RegionLists = new File("C:\\UNITAM\\FileMaster\\Files\\RegionLists");
        if (!RegionLists.exists()) {
            if (RegionLists.mkdir()) {System.out.println("Directory RegionLists is created!");
            } else {System.out.println("Failed to create directory RegionLists!");}
        }
        File SignalMaps = new File("C:\\UNITAM\\FileMaster\\Files\\SignalMaps");
        if (!SignalMaps.exists()) {
            if (SignalMaps.mkdir()) {System.out.println("Directory SignalMaps is created!");
            } else {System.out.println("Failed to create directory SignalMaps!");}
        }
        File Strategies = new File("C:\\UNITAM\\FileMaster\\Files\\Strategies");
        if (!Strategies.exists()) {
            if (Strategies.mkdir()) {System.out.println("Directory Strategies is created!");
            } else {System.out.println("Failed to create directory Strategies!");}
        }
        File StreamingSourceLists = new File("C:\\UNITAM\\FileMaster\\Files\\StreamingSourceLists");
        if (!StreamingSourceLists.exists()) {
            if (StreamingSourceLists.mkdir()) {System.out.println("Directory StreamingSourceLists is created!");
            } else {System.out.println("Failed to create directory StreamingSourceLists!");}
        }
        File VODFiles = new File("C:\\UNITAM\\FileMaster\\Files\\VODFiles");
        if (!VODFiles.exists()) {
            if (VODFiles.mkdir()) {System.out.println("Directory VODFiles is created!");
            } else {System.out.println("Failed to create directory VODFiles!");}
        }  */

    }


}
