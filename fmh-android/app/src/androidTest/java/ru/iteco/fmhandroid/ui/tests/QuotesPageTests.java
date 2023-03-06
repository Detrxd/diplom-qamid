package ru.iteco.fmhandroid.ui.tests;

import android.os.SystemClock;

import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.ThematicQuotesSteps;

public class QuotesPageTests extends BasicTest {
    CommonSteps CommonSteps = new CommonSteps();
    ThematicQuotesSteps ThematicQuotesSteps = new ThematicQuotesSteps();
    @Test
    @DisplayName("Открытие экрана тематических цитат и взаимодействие с цитатами")
    public void thematicQuotes() {
        CommonSteps.goToThematicQuotes();
        SystemClock.sleep(3500);
        ThematicQuotesSteps.checkAll();
        SystemClock.sleep(3500);
        ThematicQuotesSteps.expandQuote();
        SystemClock.sleep(3500);
        ThematicQuotesSteps.collapseQuote();
    }
}
