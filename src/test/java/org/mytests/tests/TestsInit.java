package org.mytests.tests;

import com.applitools.eyes.selenium.Eyes;
import org.mytests.uiobjects.example.site.SiteJdi;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static java.lang.String.format;
import static org.mytests.uiobjects.example.site.SiteJdi.homePage;

public class TestsInit {
    public static Eyes eyes = defaultEyes();

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        initSite(SiteJdi.class);
        homePage.open();
        logger.info("Run Tests");
    }
    @BeforeMethod
    public void before(Method method) {
        String testName = format("%s.%s", method.getDeclaringClass().getSimpleName(), method.getName());
        eyes.open(getDriver(), "JDI integration with Applitools", testName);
    }
    @AfterSuite(alwaysRun = true)
    public static void teardown() {
        try {
            if (eyes.getIsOpen())
                eyes.close();
        } finally {
            if (eyes.getIsOpen())
                eyes.abortIfNotClosed();
        }
        killAllSeleniumDrivers();
    }

    private static Eyes defaultEyes() {
        Eyes eyes = new Eyes();
        String apiKey = System.getenv("APPLITOOLS_API_KEY");
        if (apiKey == null)
            throw new RuntimeException("Please specify APPLITOOLS_API_KEY in Environment variables");
        eyes.setApiKey(apiKey);
        return eyes;
    }
}
