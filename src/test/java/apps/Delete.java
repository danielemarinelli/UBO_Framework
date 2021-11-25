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
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("NewFolderApp")));
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("OldFolderApp")));
        Thread.sleep(2000);
        FileUtils.cleanDirectory(new File(app_folders.get(0).get("Report")));
        FileUtils.cleanDirectory(new File("C:\\TEST\\old_EXE"));
        FileUtils.cleanDirectory(new File("C:\\TEST\\screenShots"));
        FileUtils.cleanDirectory(new File("C:\\UNITAM\\FileMaster\\Files\\HHSettings\\ToPanel\\Settings"));
        System.out.println("All folders are empty");
        int n = Objects.requireNonNull(new File(app_folders.get(0).get("NewFolderApp")).list()).length;
        int o = Objects.requireNonNull(new File(app_folders.get(0).get("OldFolderApp")).list()).length;
        int r = Objects.requireNonNull(new File(app_folders.get(0).get("Report")).list()).length;
        int e = Objects.requireNonNull(new File("C:\\TEST\\old_EXE").list()).length;
        int p = Objects.requireNonNull(new File("C:\\TEST\\screenShots").list()).length;
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
