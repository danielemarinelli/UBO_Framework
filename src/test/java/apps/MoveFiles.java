package apps;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import tests.TestBase;
import core.excelUserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MoveFiles extends TestBase {

    public static List<Map<String,String>> app_folders;
    WindowsDriver driverWinMerge=null;
    public MoveFiles(WindowsDriver driverWinMerge){ this.driverWinMerge = driverWinMerge; }


    public void moveFile() throws IOException {
        System.out.println("inside method MOVE-FILES Files.move");
                Path temp = Files.move
                (Paths.get("C:\\TEST\\OldAppVersionMove\\0000000003.tpi"),
                        Paths.get("C:\\TEST\\FolderOldFiles\\0000000003.tpi"));
            if(temp != null)
            {System.out.println("File renamed and moved successfully");}
            else
            {System.out.println("Failed to move the file");}

    }

    public int moveFiles() throws IOException {
        System.out.println("inside method MOVE-FILES Files.move");
        app_folders = excelUserData.getFoldersNamesFromExcelSheet();
        ArrayList l_All_HH = new ArrayList();  //contains all HH
        List files = driverWinMerge.findElementsByAccessibilityId("ListViewSubItem-0");
        for (Object file : files) {l_All_HH.add(((RemoteWebElement) file).getText());}
        System.out.println("HH size: -> "+l_All_HH.size());
        int newFiles=0;
        int oldFiles=0;
        for (int j = 0; j < l_All_HH.size(); j++) {
            Path oldMove = Files.move
                    (Paths.get(app_folders.get(0).get("OldFolderApp")+"\\"+l_All_HH.get(j).toString()),
                            Paths.get(app_folders.get(0).get("OldFolderTest")+"\\"+l_All_HH.get(j).toString()));
            if(oldMove != null)
            {System.out.println("File "+l_All_HH.get(j).toString()+" renamed and moved successfully");
            oldFiles++;}
            else
            {System.out.println("Failed to move the file "+l_All_HH.get(j).toString());}
            Path newMove = Files.move
                    (Paths.get(app_folders.get(0).get("NewFolderApp")+"\\"+l_All_HH.get(j).toString()),
                            Paths.get(app_folders.get(0).get("NewFolderTest")+"\\"+l_All_HH.get(j).toString()));
            if(newMove != null)
            {System.out.println("File "+l_All_HH.get(j).toString()+" renamed and moved successfully");
            newFiles++;}
            else
            {System.out.println("Failed to move the file "+l_All_HH.get(j).toString());}

        }
        return newFiles+oldFiles;
    }


    public int eraseLineInFile() throws IOException {

        File inputFile = new File("C:\\Windows\\Publisher.ini");
        File tempFile = new File("C:\\Windows\\Publisher_copy.ini");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            if(null!=currentLine && !currentLine.contains("nToPanelFileVer")){   //nToPanelFileVer
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

        // Delete the original file
        if (!inputFile.delete()) {System.out.println("Could not delete original file");}
        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inputFile)) {System.out.println("Could not rename file");}


        boolean successful = tempFile.renameTo(inputFile);
        System.out.println(successful);
        return 1;
    }

    public int cancelPublisherOldExeFileFromUnitamSWFolder() throws IOException {
        Path temp = Files.move
                (Paths.get("C:\\UNITAM SW\\Publisher_OLD_VERS.exe"),
                        Paths.get("C:\\TEST\\old_EXE\\Publisher_OLD_VERS.exe"));
        if(temp != null)
        {System.out.println("File renamed and moved successfully");}
        else
        {System.out.println("Failed to move the file");}
        int e = Objects.requireNonNull(new File("C:\\TEST\\old_EXE").list()).length;
        return e;
    }
}
