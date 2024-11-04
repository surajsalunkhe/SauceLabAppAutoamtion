package report_manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        String osDetails=System.getProperty("os.name")+"\t"+System.getProperty("os.version");;
        System.out.println("OS Details:"+osDetails);
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-reports/extent-report.html");
        reporter.config().setReportName("Sauce Labs Application Test Automation Report");
        reporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System OS", osDetails);
        extentReports.setSystemInfo("Author", "Suraj Salunkhe");
        return extentReports;
    }
}