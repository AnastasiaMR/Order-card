package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderCard {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
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
