package apps;

import core.excelUserData;
import org.apache.commons.io.FileUtils;
import tests.TestBase;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Delete extends TestBase {

    public static List<Map<String,String>> app_folders;

    public int deleteFiles() throws Exception {
        System.out.println("@@@ DELETING FOLDERS BEFORE STARTING THE TESTS @@@");
        app_folders = excelUserData.getFoldersNamesFromExcelSheet();
        File oldDir = new File(app_folders.get(0).get("OldFolderApp"));
        if (!oldDir.exists()){oldDir.mkdirs();}
        File newDir = new File(app_folders.get(0).get("NewFolderApp"));
        if (!newDir.exists()){newDir.mkdirs();}
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("NewFolderApp")));
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("OldFolderApp")));
        /*File oldPrioDir = new File(app_folders.get(0).get("OldFolderPrioLists"));
        if (!oldPrioDir.exists()){oldPrioDir.mkdirs();}
        File newPrioDir = new File(app_folders.get(0).get("NewFolderPrioLists"));
        if (!newPrioDir.exists()){newPrioDir.mkdirs();}
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("NewFolderPrioLists")));
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("OldFolderPrioLists")));   */
        File oldExe = new File(app_folders.get(0).get("oldExe"));
        if (!oldExe.exists()){oldExe.mkdirs();}
        FileUtils.cleanDirectory(oldExe);
        File screen = new File(app_folders.get(0).get("screenShots"));
        if (!screen.exists()){screen.mkdirs();}
        FileUtils.cleanDirectory(screen);
        File report = new File(app_folders.get(0).get("Report"));
        if (!report.exists()){report.mkdirs();}
        FileUtils.cleanDirectory(report);
        FileUtils.cleanDirectory(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings"));
        System.out.println("All folders of previous regression are empty");

        int n = Objects.requireNonNull(new File(app_folders.get(0).get("NewFolderApp")).list()).length;
        int o = Objects.requireNonNull(new File(app_folders.get(0).get("OldFolderApp")).list()).length;
        int r = Objects.requireNonNull(new File(app_folders.get(0).get("Report")).list()).length;
        int e = Objects.requireNonNull(new File(app_folders.get(0).get("oldExe")).list()).length;
        int p = Objects.requireNonNull(new File(app_folders.get(0).get("screenShots")).list()).length;
        int s = Objects.requireNonNull(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings").list()).length;
        return n+o+s+r+e+p;
    }


    public int emptyFolderSetting() throws IOException {
        System.out.println("@@@ DELETING SETTING FOLDER BEFORE STARTING THE TESTS WITH NEW VERSION @@@");
        FileUtils.cleanDirectory(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings"));
        int s = Objects.requireNonNull(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings").list()).length;
        return s;
    }
}
