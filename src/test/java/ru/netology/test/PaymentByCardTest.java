package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.OfferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCardTest {
    OfferPage offerPage = new OfferPage();

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
        open("http://localhost:8080");
    }

    @Test
    void downloadFormPaymentByCard() {
        offerPage.payByDebitCard();
    }

    @Test
    void shouldPaymentByDebitCardWithTheStatusAPPROVED() {
        var payForm = offerPage.payByDebitCard();
        var approvedInfo = DataHelper.getApprovedCardInfo();
        payForm.fillingForm(approvedInfo);
        payForm.checkOperationIsApproved();
        String dataSQLPayment = SQLHelper.getPaymentStatus();
        assertEquals("APPROVED", dataSQLPayment);
    }

    @Test
    void shouldPaymentByDebitCardWithTheStatusDECLINED() {
        var payForm = offerPage.payByDebitCard();
        var declinedInfo = DataHelper.getDeclinedCardInfo();
        payForm.fillingForm(declinedInfo);
        payForm.checkErrorNotification();
        String dataSQLPayment = SQLHelper.getPaymentStatus();
        assertEquals("DECLINED", dataSQLPayment);
    }

    //Поле "Номер карты"
    @Test
    void shouldSendAnEmptyForm() {
        var payForm = offerPage.payByDebitCard();
        var emptyFields = DataHelper.getEmptyFields();
        payForm.fillFormNoSendRequest(emptyFields);
        payForm.checkWrongFormat();
        payForm.checkRequiredField();
    }

    @Test
    void shouldCheckTheInvalidCard() {
        var payForm = offerPage.payByDebitCard();
        var invalidCardNumber = DataHelper.getInvalidCardNumberInfo();
        payForm.fillingForm(invalidCardNumber);
        payForm.checkErrorNotification();
    }

    @Test
    void shouldCheckTheInvalidCard1() {
        var payForm = offerPage.payByDebitCard();
        var invalidCardNumber = DataHelper.getInvalidCardNumberInfo1();
        payForm.fillingForm(invalidCardNumber);
        payForm.checkErrorNotification();
    }

    @Test
    void shouldEnterTheCardNumberLessThan16Characters() {
        var payForm = offerPage.payByDebitCard();
        var invalidFormatCard = DataHelper.getInvalidCardNumberInfo2();
        payForm.fillFormNoSendRequest(invalidFormatCard);
        payForm.checkInvalidCardNumberT(invalidFormatCard);
    }

    @Test
    void shouldSendTheFormEmptyAndThenWithTheOwnersData() {
        var payForm = offerPage.payByDebitCard();
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
        var payForm = offerPage.payByDebitCard();
        var invalidFormatCard = DataHelper.getInvalidCardInfo();
        payForm.fillFormNoSendRequest(invalidFormatCard);
        payForm.checkInvalidCardNumberT(invalidFormatCard);
    }

    // Поле "Месяц"
    @Test
    void shouldCheckTheEmptyMonthField() {
        var payForm = offerPage.payByDebitCard();
        var invalidMonth = DataHelper.getInvalidMonthInfo1();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidMonth(invalidMonth);
    }

    @Test
    void shouldCheckTheInvalidMonthZero() {
        var payForm = offerPage.payByDebitCard();
        var invalidMonth = DataHelper.getInvalidMonthZeroInfo();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidMonth(invalidMonth);
    }

    @Test
    void shouldOneCharacterInTheMonthField() {
        var payForm = offerPage.payByDebitCard();
        var invalidMonth = DataHelper.getInvalidMonthInfo2();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidMonth(invalidMonth);
    }

    @Test
    void shouldEnterANonExistentMonthInTheField() {
        var payForm = offerPage.payByDebitCard();
        var invalidMonth = DataHelper.getInvalidMonthInfo();
        payForm.fillFormNoSendRequest(invalidMonth);
        payForm.checkInvalidExpirationDate();
    }

    // Поле "Год"
    @Test
    void shouldLeaveTheYearFieldEmpty() {
        var payForm = offerPage.payByDebitCard();
        var invalidYear = DataHelper.getInvalidYearInfo1();
        payForm.fillFormNoSendRequest(invalidYear);
        payForm.checkInvalidYearT();
    }

    @Test
    void shouldEnterTwoZerosInTheYearField() {
        var payForm = offerPage.payByDebitCard();
        var expiredYear = DataHelper.getInvalidYearInfo2();
        payForm.fillFormNoSendRequest(expiredYear);
        payForm.checkCardExpired();
    }

    @Test
    void shouldEnterAYearInTheFieldWithMoreThanTheAllowedPeriod() {
        var payForm = offerPage.payByDebitCard();
        var invalidYear = DataHelper.getInvalidYearInfo();
        payForm.fillFormNoSendRequest(invalidYear);
        payForm.checkInvalidExpirationDate();
    }

    @Test
    void shouldEnterTheExpiringYearInTheField() {
        var payForm = offerPage.payByDebitCard();
        var expiredYear = DataHelper.getExpiredYearInfo();
        payForm.fillFormNoSendRequest(expiredYear);
        payForm.checkCardExpired();
    }

    // Поле "Владелец"
    @Test
    void shouldLeaveTheOwnerFieldEmpty() {
        var payForm = offerPage.payByDebitCard();
        var requiredField = DataHelper.getInvalidOwnerInfo();
        payForm.fillFormNoSendRequest(requiredField);
        payForm.checkRequiredField();
    }

    @Test
    void shouldEnterTheLastNameFirstAndThenTheFirstNameInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo1();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterOnlyTheLastNameInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo2();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterOnlyOneLetterInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo3();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterOnlyALargeNumberOfLettersInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo4();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterSpecialCharactersInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo5();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterLowercaseLettersInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo6();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldEnterTheNumbersInTheOwnerField() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo7();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    @Test
    void shouldCheckTheOwnersDataInCyrillic() {
        var payForm = offerPage.payByDebitCard();
        var invalidOwner = DataHelper.getInvalidOwnerInfo8();
        payForm.fillFormNoSendRequest(invalidOwner);
        payForm.checkWrongFormat();
    }

    // Поле CVC/CVV
    @Test
    void shouldLeaveTheCVVFieldEmpty() {
        var payForm = offerPage.payByDebitCard();
        var invalidCVV = DataHelper.getInvalidCVVInfo();
        payForm.fillFormNoSendRequest(invalidCVV);
        payForm.checkInvalidCVVT();
    }

    @Test
    void shouldEnterTwoNumbersInTheCVVField() {
        var payForm = offerPage.payByDebitCard();
        var invalidCVV = DataHelper.getInvalidCVVInfo1();
        payForm.fillFormNoSendRequest(invalidCVV);
        payForm.checkInvalidCVVT();
    }

    @Test
    void shouldEnterOneNumbersInTheCVVField() {
        var payForm = offerPage.payByDebitCard();
        var invalidCVV = DataHelper.getInvalidCVVInfo2();
        payForm.fillFormNoSendRequest(invalidCVV);
        payForm.checkInvalidCVVT();
    }
}

