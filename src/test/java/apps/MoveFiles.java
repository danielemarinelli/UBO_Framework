package apps;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import tests.TestBase;
import core.excelUserData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


}
