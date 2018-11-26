
package reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateReport {
    public static ExtentReports extent;
    public static ExtentTest logger;

    public void startReport(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-a");
        Date date = new Date();
        extent = new ExtentReports (System.getProperty("user.dir") +"/Results/VerifyReceipt_"+ formatter.format(date) +".html", true);
        extent
                .addSystemInfo("Host Name", "OneMarket")
                .addSystemInfo("Environment", "POC");
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
    }

    public void passTest(String testName){
        logger = extent.startTest(testName);
        Assert.assertTrue(true);
        logger.log(LogStatus.PASS, "Testcase Passed. Name of test case is : " + testName);
        endTest();
    }

    public void failTest(String testName){
        logger = extent.startTest(testName);
        Assert.assertTrue(false);
        logger.log(LogStatus.PASS, "Testcase Failed. Name of test case is : " + testName);
        endTest();
    }

    public void endTest(){
        extent.endTest(logger);
    }

    public void endReport(){
        try {
            extent.flush();
            extent.close();
        }catch (Exception e){
            System.out.println("Report not generated. Exception occured");
            e.printStackTrace();
        }
    }
}

