package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderCard {

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver( options );
    }

    @Before
    public void setupTest() {
        ChromeDriver driver = new ChromeDriver( );
    }

    @After
    public void teardown() {
        WebDriver driver = null;
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldTest( ) {
        open("http://localhost:9999");
        $( "[data-test-id=name] input" ).setValue( "Василий Иванов" );
        $( "[data-test-id=phone] input" ).setValue( "+79270000000" );
        $( "[data-test-id=agreement]" ).click( );
        $( "[type=button]" ).click( );
        $( "[data-test-id=order-success]" ).shouldHave( exactText( "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время." ) );
    }

    @Test
    void shouldTestNoName( ) {
        open("http://localhost:9999");
        $( "[data-test-id=name] input" ).setValue( "" );
        $( "[data-test-id=phone] input" ).setValue( "+79270000000" );
        $( "[data-test-id=agreement]" ).click( );
        $( "[type=button]" ).click( );
        $( ".input__sub" ).shouldHave( exactText( "Поле обязательно для заполнения" ) );
    }
}
