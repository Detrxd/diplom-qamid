package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentTime;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.CreateClaimSteps;
import ru.iteco.fmhandroid.ui.steps.EditClaimSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class ClaimPageTests extends GeneralHelper {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    ClaimsSteps ClaimsSteps = new ClaimsSteps();
    EditClaimSteps EditClaimSteps = new EditClaimSteps();
    CommonSteps CommonSteps = new CommonSteps();
    CreateClaimSteps CreateClaimSteps = new CreateClaimSteps();


    @Before
    public void loginCheck() {

        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin(userLogin);
        AuthorizationSteps.enterPassword(userPassword);
        AuthorizationSteps.signIn();
    }

    @Test
    @DisplayName("Открытие экрана претензий")
    public void openAllClaims() {
        MainSteps.waitForLoadingMain();
        MainSteps.openAllClaims();

        ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Открытие претензии и возврат из нее")
    public void openSingleClaim() {
        MainSteps.waitForLoadingMain();
        MainSteps.openSingleClaim();
        EditClaimSteps.isClaimsEditScreen();
        EditClaimSteps.backFromClaim();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Открытие экрана претензий из меню и переход к экрану создания претензии")
    public void claimScreen() {
        MainSteps.waitForLoadingMain();
        CommonSteps.goToScreen("Claims");
        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.createClaim();
        CreateClaimSteps.isCreateClaimsScreen();
    }

    @Test
    @DisplayName("Создание претензии")
    public void createClaim() {
        MainSteps.waitForLoadingMain();
        String claimTitleString = "Притензия тест " + getCurrentDate() + "T" + getCurrentTime();
        String newClaimTitleString = "Тествое описание " + getCurrentDate();
        String currentDate = getCurrentDate();
        String currentTime = getCurrentTime();
        MainSteps.createClaim();

        CreateClaimSteps.isCreateClaimsScreen();

        CreateClaimSteps.checkClaimTitleLength();

        CommonSteps.clickSave();

        CreateClaimSteps.checkToastEmptyFields();
        CommonSteps.clickOK();

        CreateClaimSteps.enterClaimTitle(claimTitleString);

        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(currentDate);
        CreateClaimSteps.enterClaimTime(currentTime);
        CreateClaimSteps.enterClaimDescription(newClaimTitleString);

        CommonSteps.clickSave();

        MainSteps.waitForLoadingMain();
        MainSteps.isMainScreen();
    }

}
