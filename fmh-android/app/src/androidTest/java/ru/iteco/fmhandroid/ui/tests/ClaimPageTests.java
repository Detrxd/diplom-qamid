package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentTime;

import android.os.SystemClock;

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

public class ClaimPageTests extends BasicTest {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    ClaimsSteps ClaimsSteps = new ClaimsSteps();
    EditClaimSteps EditClaimSteps = new EditClaimSteps();
    CommonSteps CommonSteps = new CommonSteps();
    CreateClaimSteps CreateClaimSteps = new CreateClaimSteps();


    @Before
    public void loginCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
        SystemClock.sleep(2000);
    }

    @Test
    @DisplayName("Открытие экрана претензий")
    public void openAllClaims() {
        MainSteps.openAllClaims();
        ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Открытие претензии и возврат из нее")
    public void openSingleClaim() {
        MainSteps.openSingleClaim();
        EditClaimSteps.isClaimsEditScreen();
        EditClaimSteps.backFromClaim();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Открытие экрана претензий из меню и переход к экрану создания претензии")
    public void claimScreen() {
        CommonSteps.goToScreen("Claims");
        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.createClaim();
        CreateClaimSteps.isCreateClaimsScreen();
    }

    @Test
    @DisplayName("Создание претензии")
    public void createClaim() {
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
        SystemClock.sleep(2000);

        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(currentDate);
        CreateClaimSteps.enterClaimTime(currentTime);
        CreateClaimSteps.enterClaimDescription(newClaimTitleString);

        CommonSteps.clickSave();
        MainSteps.isMainScreen();
    }

}
