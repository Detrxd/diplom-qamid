package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.utils.Utils.waitId;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AuthorizationScreen;

public class AuthorizationSteps {
    AuthorizationScreen AuthorizationScreen = new AuthorizationScreen();

    public void isAuthorizationScreen() {
        Allure.step("Проверка, что это экран авторизации");
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
    }

    public void waitLoginField(){
        onView(isRoot()).perform(waitId(R.id.enter_button,7000));
    }

    public void enterLogin(String userLogin) {
        Allure.step("Ввод логина '" + userLogin + "'");
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.login.perform(replaceText(userLogin));
    }

    public void enterPassword(String text) {
        Allure.step("Ввод пароля '" + text + "'");
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.password.perform(replaceText(text));
    }

    public void signIn() {
        Allure.step("Нажатие кнопки входа");
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));
        AuthorizationScreen.buttonSignIn.perform(click());
        onView(isRoot()).perform(waitId(R.id.container_custom_app_bar_include_on_fragment_main,7000));
    }
}
