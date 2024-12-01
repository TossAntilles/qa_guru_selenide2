import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;


public class EnterprizePageTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void enterprizePageOpenWideBrowserTest() {

        //open wide browser
        Configuration.browserSize = "1920x1080";
        open("");

        //navigate through menu
        $(".HeaderMenu").$(byText("Solutions")).hover();
        $$(".HeaderMenu-dropdown a").findBy(text("Enterprises")).click();

        //check URL and header
        webdriver().shouldHave(url("https://github.com/enterprise"));
        $("[data-testId=SubNav-root]").shouldHave(text("Enterprise"));

        //check page content
        $(".enterprise-hero").shouldHave(text("The AI-powered\n" +
                "developer platform"));
    }

    @Test
    void enterprizePageOpenNarrowBrowserTest() {

        //open narrow browser
        Configuration.browserSize = "800x600";
        open("");

        //navigate through menu
        $(".Button-content").click();
        $(byText("Enterprise")).click();
        $(byText("Enterprise platform")).click();

        //check URL and header
        webdriver().shouldHave(url("https://github.com/enterprise"));
        $("[data-testId=SubNav-root]").shouldHave(text("Enterprise"));

        //check page content
        $(".enterprise-hero").shouldHave(text("The AI-powered\n" +
                "developer platform"));
    }
}
