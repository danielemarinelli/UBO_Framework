package core;

import org.apache.commons.mail.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class email {

    public static List<Map<String,String>> email_info;
    public static List<Map<String,String>> folder_info;

    public static void sendEmailForPublisherFailure(String testName) throws Exception {
        email_info = excelUserData.getInfoFromEmailSheet();
        Email email = new SimpleEmail();
        email.setHostName(email_info.get(0).get("smtp"));
        email.setSmtpPort(Integer.parseInt(email_info.get(0).get("smtp_port")));
        email.setAuthenticator(new DefaultAuthenticator(email_info.get(0).get("user_name"), email_info.get(0).get("password")));
        email.setSSLOnConnect(true);
        email.setFrom(email_info.get(0).get("email_set_from"));
        email.setSubject(email_info.get(0).get("subject"));
        email.setMsg("REGRESSION PUBLISHER TEST: "+testName+" FAILED!");
        for (int i = 0; i < (Integer.parseInt(email_info.get(0).get("MailList components"))); i++) {email.addTo(email_info.get(0).get("email"+(i+1))); }
        email.send();
        System.out.println("EMAILS SENT!!!");
    }

    public static void attachFilesInEmail(ArrayList<String> filesWithDifferences) throws Exception {
        email_info = excelUserData.getInfoFromEmailSheet();
        folder_info = excelUserData.getFoldersNamesFromExcelSheet();
        EmailAttachment attachmentNew = new EmailAttachment();
        EmailAttachment attachmentScreen = new EmailAttachment();
        EmailAttachment attachmentOld = new EmailAttachment();
        MultiPartEmail email = new MultiPartEmail();
        for (int j = 0; j < filesWithDifferences.size(); j++) {
            attachmentScreen.setPath("./src/main/screenShots/"+filesWithDifferences.get(j).replace(".","")+".png");
            attachmentScreen.setDisposition(EmailAttachment.ATTACHMENT);
            attachmentScreen.setDescription("Different with screen");
            attachmentScreen.setName("SCREEN_"+filesWithDifferences.get(j));
            attachmentNew.setPath(folder_info.get(0).get("NewFolderApp")+"\\"+filesWithDifferences.get(j));
            attachmentNew.setDisposition(EmailAttachment.ATTACHMENT);
            attachmentNew.setDescription("Different new File");
            attachmentNew.setName("new "+filesWithDifferences.get(j));
            attachmentOld.setPath(folder_info.get(0).get("OldFolderApp")+"\\"+filesWithDifferences.get(j));
            attachmentOld.setDisposition(EmailAttachment.ATTACHMENT);
            attachmentOld.setDescription("Different old File");
            attachmentOld.setName("old "+filesWithDifferences.get(j));
            // Create the email message
            email.setHostName(email_info.get(0).get("smtp"));
            email.setSmtpPort(Integer.parseInt(email_info.get(0).get("smtp_port")));
            email.setAuthenticator(new DefaultAuthenticator(email_info.get(0).get("user_name"), email_info.get(0).get("password")));
            email.setSSLOnConnect(true);
            for (int i = 0; i < (Integer.parseInt(email_info.get(0).get("MailList components"))); i++) {email.addTo(email_info.get(0).get("email"+(i+1))); }
            email.setFrom(email_info.get(0).get("email_set_from"));
            email.setSubject("## Publisher Regression ## This email contains the FILES with differences");
            email.setMsg("Check the attached FILES, they are different");
            // add the attachment
            email.attach(attachmentNew);
            email.attach(attachmentOld);
            email.attach(attachmentScreen);
        }
        // send the email
        email.send();
        System.out.println("EMAILS SENT WITH ATTACH!!!");
    }

    public static void sendReportAfterCompare(String version, String versionTPI) throws Exception {
        email_info = excelUserData.getInfoFromEmailSheet();
        folder_info = excelUserData.getFoldersNamesFromExcelSheet();
        EmailAttachment attachment = new EmailAttachment();
        MultiPartEmail email = new MultiPartEmail();
        attachment.setPath(folder_info.get(0).get("Report")+"\\Publisher_Report");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Reporting File");
        attachment.setName("Publisher_Regression_Report");
        email.setHostName(email_info.get(0).get("smtp"));
        email.setSmtpPort(Integer.parseInt(email_info.get(0).get("smtp_port")));
        email.setAuthenticator(new DefaultAuthenticator(email_info.get(0).get("user_name"), email_info.get(0).get("password")));
        email.setSSLOnConnect(true);
        for (int i = 0; i < (Integer.parseInt(email_info.get(0).get("MailList components"))); i++) {email.addTo(email_info.get(0).get("email"+(i+1))); }
        email.setFrom(email_info.get(0).get("email_set_from"));
        email.setSubject("## REPORT Publisher Regression with new version "+version+"  ##");
        email.setMsg("TPI VERSION: "+versionTPI+". REPORT generated with WinMerge TimeStamp and SourceAppVersion Filters ACTIVE. " +
                "Report contains PRIORITY LISTS comparation too. Download it locally and open it with any editor available");
        email.attach(attachment);
        email.send();
        System.out.println("REPORT SENT VIA EMAIL....");
    }

    public static void sendPriorityListsReportAfterCompare() throws Exception {
        email_info = excelUserData.getInfoFromEmailSheet();
        folder_info = excelUserData.getFoldersNamesFromExcelSheet();
        EmailAttachment attachment = new EmailAttachment();
        MultiPartEmail email = new MultiPartEmail();
        attachment.setPath(folder_info.get(0).get("Report")+"\\PriorityLists_Report");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Reporting File");
        attachment.setName("Publisher_Regression_Report");
        email.setHostName(email_info.get(0).get("smtp"));
        email.setSmtpPort(Integer.parseInt(email_info.get(0).get("smtp_port")));
        email.setAuthenticator(new DefaultAuthenticator(email_info.get(0).get("user_name"), email_info.get(0).get("password")));
        email.setSSLOnConnect(true);
        for (int i = 0; i < (Integer.parseInt(email_info.get(0).get("MailList components"))); i++) {email.addTo(email_info.get(0).get("email"+(i+1))); }
        email.setFrom(email_info.get(0).get("email_set_from"));
        email.setSubject("## REPORT Priority Lists - Publisher Regression ##");
        email.setMsg("Priority Lists REPORT generated");
        email.attach(attachment);
        email.send();
        System.out.println("Priority Lists REPORT SENT VIA EMAIL....");
    }

    public static void sendEmailForPolluxFailure(String testName) throws Exception {
        email_info = excelUserData.getInfoFromEmailSheet();
        Email email = new SimpleEmail();
        email.setHostName(email_info.get(0).get("smtp"));
        email.setSmtpPort(Integer.parseInt(email_info.get(0).get("smtp_port")));
        email.setAuthenticator(new DefaultAuthenticator(email_info.get(0).get("user_name"), email_info.get(0).get("password")));
        email.setSSLOnConnect(true);
        email.setFrom(email_info.get(0).get("email_set_from"));
        email.setSubject(email_info.get(0).get("subject"));
        email.setMsg("REGRESSION POLLUXGW TEST: "+testName+" FAILED!");
        for (int i = 0; i < (Integer.parseInt(email_info.get(0).get("MailList components"))); i++) {email.addTo(email_info.get(0).get("email"+(i+1))); }
        email.send();
        System.out.println("EMAILS SENT for PolluxGW results!!!");
    }
}
