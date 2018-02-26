package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData;
import com.epam.web.matcher.testng.Assert;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.Condition.*;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.*;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.runParallel;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.waitTimeOut;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.isTrue;

public class LinkTests extends InitTests {
    private ILink link() { return footer.about; }

    @BeforeMethod
    public void before(final Method method) {
        isInState(HOME_PAGE, method);
    }

    @Test
    public void clickTest() {
        link().click();
        supportPage.checkOpened();
    }

    @Test
    @Step("#### Debugging step!")
    public void getReferenceTest() {
        logger.step("Hello from logger.step!");
        areEquals(link().getReference(), supportPage.url);
    }

    public static String isLinkBroken(URL url) throws Exception
    {
        String response = "";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try
        {
            connection.connect();
            response = connection.getResponseMessage();
            connection.disconnect();
            return response;

        }
        catch(Exception exp)
        {
            return exp.getMessage();
        }
    }

    @Test
    public void getURLTest() throws Exception {
        logger.info("Response code from server: " + isLinkBroken(link().getURL()));
    }

    @Test
    public void waitReferenceTest() {
        isInState(SUPPORT_PAGE);
        runParallel(homePage::open);
        areEquals(() -> link().waitReferenceContains("support.htm"), supportPage.url);
        isTrue(CommonActionsData.timer.timePassedInMSec() > waitTimeOut);
    }

    @Test
    public void waitMatchReferenceTest() throws Exception {
        isInState(SUPPORT_PAGE);
        runParallel(homePage::open);
        areEquals(() -> link().waitMatchReference(".*html"), supportPage.url);
        isTrue(CommonActionsData.timer.timePassedInMSec() > waitTimeOut);
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextTests("Link", HOME_PAGE, this::link, "About", "Abou", "Abou.*")
        };
    }

    @Test
    public void shouldTest(){
        link().shouldHave(exactText("About"), attribute("title", "Tip title"), attribute("href"))
                .shouldNotBe(hidden);
    }

    @Test
    public void imageIsDisplayedTest(){
        Assert.assertTrue(link().isDisplayed());
    }
}