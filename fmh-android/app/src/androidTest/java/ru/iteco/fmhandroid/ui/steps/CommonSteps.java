package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitId;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.CommonElements;

public class CommonSteps {

    CommonElements CommonElements = new CommonElements();

    @Step("Кликнуть выйти из приложения")
    public void logout() {
        CommonElements.manImage.perform(click());
        CommonElements.exitButton.perform(click());
    }

    public void waitLogoutField(){
        onView(isRoot()).perform(waitId(R.id.authorization_image_button,7000));
    }

    @Step("Кликнуть сохранить")
    public void clickSave() {
        CommonElements.buttonSave.perform(click());
    }

    @Step("Кликнуть ОК")
    public void clickOK() {
        CommonElements.buttonOkText.perform(click());
    }

    @Step("Кликнуть отмена")
    public void clickCancel() {
        CommonElements.buttonCancel.perform(click());
    }

    public void waitForAuthButton() {
        onView(isRoot()).perform(waitId(R.id.authorization_image_button,7000));
    }
    @Step("Кликнуть отмена для подтверждения")
    public void clickCancelText() {
        CommonElements.buttonCancelText.perform(click());
    }

    @Step("Перейти в тематические цитаты")
    public void goToThematicQuotes() {
        CommonElements.thematicQuotes.perform(click());
    }

    @Step("Перейти к нужному экрану через меню")
    public void goToScreen(String screen) {
        CommonElements.mainMenu.perform(click());
        switch (screen) {
            case ("Main"):
                CommonElements.menuMain.perform(click());
                break;
            case ("News"):
                CommonElements.menuNews.perform(click());
                break;
            case ("About"):
                CommonElements.menuAbout.perform(click());
                break;
            case ("Claims"):
                CommonElements.menuClaims.perform(click());
                break;
        }
    }
}
