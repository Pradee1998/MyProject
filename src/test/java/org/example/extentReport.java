package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReport {

    static ExtentReports extent;

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter repo = new ExtentSparkReporter(path);
        repo.config().setReportName("Mobile Automations");
        repo.config().setDocumentTitle(" Automations results");

        extent = new ExtentReports();
        extent.attachReporter(repo);
        extent.setSystemInfo("tester", "rahul shetty");
        return extent;

    }
}
