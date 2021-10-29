package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.entities.Pages;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardDataTest {
    Pages pages;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openForTests() {
        pages = open("http://localhost:8080", Pages.class);
    }

    @Test
    void shouldPaymentByDebitCardWithTheStatusAPPROVED() {
        var payForm = pages.payCreditByCard();
        var approvedInfo = DataHelper.getApprovedCardInfo();
        payForm.fillingForm(approvedInfo);
        payForm.checkOperationIsApproved();
        String dataSQLPayment = SQLHelper.getCreditStatus();
        assertEquals("APPROVED", dataSQLPayment);
    }

    @Test
    void shouldPaymentByDebitCardWithTheStatusDECLINED() {
        var payForm = pages.payCreditByCard();
        var declinedInfo = DataHelper.getDeclinedCardInfo();
        payForm.fillingForm(declinedInfo);
        payForm.checkErrorNotification();
        String dataSQLPayment = SQLHelper.getCreditStatus();
        assertEquals("DECLINED", dataSQLPayment);
    }

    //Поле "Номер карты"
    @Test
    void shouldSendAnEmptyForm() {
        var payForm = pages.payCreditByCard();
        var emptyFields = DataHelper.getEmptyFields();
        payForm.fillFormNoSendRequest(emptyFields);
        payForm.checkWrongFormat();
        payForm.checkRequiredField();
    }

    @Test
    void shouldCheckTheInvalidCard() {
        var payForm = pages.payCreditByCard();
        var invalidCardNumber = DataHelper.getInvalidCardNumberInfo();
        payForm.fillingForm(invalidCardNumber);
        payForm.checkErrorNotification();
    }

    @Test
    void shouldCheckTheInvalidCard1() {
        var payForm = pages.payCreditByCard();
        var invalidCardNumber = DataHelper.getInvalidCardNumberInfo1();
        payForm.fillingForm(invalidCardNumber);
        payForm.checkErrorNotification();
    }

    @Test
    void shouldEnterTheCardNumberLessThan16Characters() {
        var payForm = pages.payCreditByCard();
        var invalidFormatCard = DataHelper.getInvalidCardNumberInfo2();
        payForm.fillFormNoSendRequest(invalidFormatCard);
        payForm.checkInvalidCardNumberT(invalidFormatCard);
    }

    @Test
    void shouldSendTheFormEmptyAndThenWithTheOwnersData() {
        var payForm = pages.payCreditByCard();
        var emptyFields = DataHelper.getEmptyFields();
        var approvedInfo = DataHelper.getApprovedCardInfo();
        payForm.fillFormNoSendRequest(emptyFields);
        payForm.checkWrongFormat();
        payForm.checkRequiredField();
        payForm.fillingForm(approvedInfo);
        payForm.checkOperationIsApproved();
    }

    @Test
    void shouldLeaveTheCardNumberFieldEmpty() {
        var payForm = pages.payCreditByCard();
        var invalidFormatCard = DataHelper.getInvalidCardInfo();
        payForm.fillFormNoSendRequest(invalidFormatCard);
        payForm.checkInvalidCardNumberT(invalidFormatCard);
    }

    // Поле "Месяц"
    @Test
    void shouldCheckTheEmptyMonthField() {
        var payForm = pages.payCreditByCard();
        var invalidMonth = DataHelper.getInvalidMonthInfo1();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidMonth(invalidMonth);
    }

    @Test
    void shouldCheckTheInvalidMonthZero() {
        var payForm = pages.payCreditByCard();
        var invalidMonth = DataHelper.getInvalidMonthZeroInfo();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidMonth(invalidMonth);
    }

    @Test
    void shouldOneCharacterInTheMonthField() {
        var payForm = pages.payCreditByCard();
        var invalidMonth = DataHelper.getInvalidMonthInfo2();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidMonth(invalidMonth);
    }

    @Test
    void shouldEnterANonExistentMonthInTheField() {
        var payForm = pages.payCreditByCard();
        var invalidMonth = DataHelper.getInvalidMonthInfo();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidExpirationDate();
    }

    // Поле "Год"
    @Test
    void shouldLeaveTheYearFieldEmpty() {
        var payForm = pages.payCreditByCard();
        var invalidYear = DataHelper.getInvalidYearInfo1();
        payForm.fillFormNoSendRequest(invalidYear);
        payForm.checkInvalidYearT();
    }

    @Test
    void shouldEnterTwoZerosInTheYearField() {
        var payForm = pages.payCreditByCard();
        var expiredYear = DataHelper.getInvalidYearInfo2();
        payForm.fillFormNoSendRequest(expiredYear);
        payForm.checkCardExpired();
    }

    @Test
    void shouldEnterAYearInTheFieldWithMoreThanTheAllowedPeriod() {
        var payForm = pages.payCreditByCard();
        var invalidYear = DataHelper.getInvalidYearInfo();
        payForm.fillFormNoSendRequest(invalidYear);
        payForm.checkInvalidExpirationDate();
    }

    @Test
    void shouldEnterTheExpiringYearInTheField() {
        var payForm = pages.payCreditByCard();
        var expiredYear = DataHelper.getExpiredYearInfo();
        payForm.fillFormNoSendRequest(expiredYear);
        payForm.checkCardExpired();
    }

    // Поле "Владелец"
    @Test
    void shouldLeaveTheOwnerFieldEmpty() {
        var payForm = pages.payCreditByCard();
        var requiredField = DataHelper.getInvalidOwnerInfo();
        payForm.fillFormNoSendRequest(requiredField);
        payForm.checkRequiredField();
    }

    @Test
    void shouldEnterTheLastNameFirstAndThenTheFirstNameInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo1();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterOnlyTheLastNameInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo2();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterOnlyOneLetterInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo3();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterOnlyALargeNumberOfLettersInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo4();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterSpecialCharactersInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo5();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterLowercaseLettersInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo6();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterTheNumbersInTheOwnerField() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo7();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldCheckTheOwnersDataInCyrillic() {
        var payForm = pages.payCreditByCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo8();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    // Поле CVC/CVV
    @Test
    void shouldLeaveTheCVVFieldEmpty() {
        var payForm = pages.payCreditByCard();
        var invalidCVV = DataHelper.getInvalidCVVInfo();
        payForm.fillFormNoSendRequest(invalidCVV);
        payForm.checkInvalidCVVT();
    }

    @Test
    void shouldEnterTwoNumbersInTheCVVField() {
        var payForm = pages.payCreditByCard();
        var invalidCVV = DataHelper.getInvalidCVVInfo1();
        payForm.fillFormNoSendRequest(invalidCVV);
        payForm.checkInvalidCVVT();
    }

    @Test
    void shouldEnterOneNumbersInTheCVVField() {
        var payForm = pages.payCreditByCard();
        var invalidCVV = DataHelper.getInvalidCVVInfo2();
        payForm.fillFormNoSendRequest(invalidCVV);
        payForm.checkInvalidCVVT();
    }
}