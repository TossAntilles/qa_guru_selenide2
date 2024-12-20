import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com/drag_and_drop";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void beforeEach() {
        open("");
        $("#column-a header").shouldHave(text("A"));
        $("#column-b header").shouldHave(text("B"));
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void moveElementByOffsetTest() {

        actions().moveToElement($("#column-a")).clickAndHold().
                moveByOffset(225,0).release().perform();

        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));
    }

    @Test
    void moveElementToAnotherElementTest() {

        actions().moveToElement($("#column-a")).clickAndHold().
                moveToElement($("#column-b")).release().perform();

        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));
    }

    @Test
    void moveWithDragAndDropWithoutActionsTest() {

        $("#column-a").dragAndDrop(to("#column-b"));

        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));
    }
}
