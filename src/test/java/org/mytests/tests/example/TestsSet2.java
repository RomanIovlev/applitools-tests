package org.mytests.tests.example;

import com.epam.jdi.tools.Timer;
import org.mytests.tests.TestsInit;
import org.testng.annotations.Test;

import static com.applitools.eyes.selenium.fluent.Target.region;
import static org.mytests.uiobjects.example.entities.Defaults.DEFAULT_USER;
import static org.mytests.uiobjects.example.site.SiteJdi.*;
import static org.mytests.uiobjects.example.site.pages.Html5Page.blueButton;

public class TestsSet2 extends TestsInit {
    @Test
    public void screenshotPopupTest() {
        userIcon.click();
        loginForm.loginAs(DEFAULT_USER);
        html5Page.open();
        blueButton.click();
        Timer.sleep(1000);
        eyes.checkWindow("Try to catch alert");
    }
    @Test
    public void screenshotHiddenElementTest() {
        eyes.check("Element is not displayed creates page screenshot instead of error",
            region(userName.core().getWebElement()));
        userIcon.click();
        loginForm.loginAs(DEFAULT_USER);
        eyes.check("Element is displayed > good screenshot",
                region(userName.core().getWebElement()));
    }
}
