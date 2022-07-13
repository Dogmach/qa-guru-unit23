package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvitoAuth extends TestBase {

    String url = "https://www.etxt.ru/";

    @Test
    @AllureId("11161")
    @DisplayName("Авторизация на Авито через электронную почту и паролю")
    @Story("Авторизация на главной странице")
    @Owner("allure8")
    @Feature("Auth")

    public void testAuth() {
        step("Открываем главную страницу");
        open(url);

        step("Нажимаем на Вход и регистрация");
        $x("//a[@class='mod-header__auth-login js-target']").click();

        step("Авторизуемся как пользователь qaguru1", () -> {
            step("Вводим логин qaguru1");
            $x("//input[@placeholder='Логин']").setValue("qaguru1");
            step("Вводим пароль qaguru123");
            $x("//input[@placeholder='Пароль']").setValue("qaguru123");
            step("Убираем галочку напротив Запомнить пароль");
            $x("//label[@for='remember']").click();
            step("Нажимаем кнопку Войти");
            $x("//input[@name='submit']").click();
        });

        step("Проверяем что авторизовались правильно", () -> {
            step("Мы находимся на главной странице сайта");
//            $("title").shouldHave(Condition.attribute("text", "eTXT."));
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            assertEquals(url, currentUrl);

            step("Пользователь авторизован");
            $x("//span[@class='js-openUserMenu']").shouldHave(Condition.text("qaguru1"));
        });
        step("Разлогиниваемся");
        $x("//img[@class='js-openUserMenu']").click();
        $("div[class='mod-header__user-info'] li:nth-child(5) a:nth-child(1)").click();
    }
}
