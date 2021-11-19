package apps;

import io.appium.java_client.windows.WindowsDriver;
import tests.TestBase;

import java.io.IOException;


public class Publisher extends TestBase {

    WindowsDriver driverWinPub;
    public Publisher(WindowsDriver driverWinPub){ this.driverWinPub = driverWinPub; }

    public String openPublisherApp() throws Exception {
        System.out.println("opening Publisher");
        String title[] = driverWinPub.getTitle().split(" ");
        Thread.sleep(2000);
        takeAppSnap(driverWinPub, title[0]+"DB");   //early screenshot
        return title[0];
    }

    public void closePublisher(){
        driverWinPub.close();
        for (int i = 0; i < 2; i++) { driverWinPub.findElementByName("Yes").click(); }
        System.out.println("Publisher closed...");
    }

    public String getPublisherVersion()  {
        String title[] = driverWinPub.getTitle().split(" ");
        System.out.println();
        return title[3];
    }

    public void getPublisherLogs(){
        int numb_logs_lines = driverWinPub.findElementsByName("Custom1").size();
        System.out.println("numb_logs_lines");
    }

    public void getPublisherScreenShotWhilePublishing() throws IOException {
        takeAppSnap(driverWinPub, "PublisherPublishingHH");
        System.out.println("### Took Publisher screenshot ###");
    }
}
