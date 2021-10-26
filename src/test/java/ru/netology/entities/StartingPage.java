package ru.netology.entities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.pages.PaymentPage;
import ru.netology.pages.PaymentPageOnCredit;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StartingPage {
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private SelenideElement paymentByCard = $(byText("Оплата по карте"));
    private SelenideElement paymentByCreditCard = $(byText("Кредит по данным карты"));

    public PaymentPage payByDebitCard() {
        buyButton.click();
        paymentByCard.shouldHave(Condition.visible);
        return new PaymentPage();
    }

    public PaymentPageOnCredit payCreditByCard() {
        creditButton.click();
        paymentByCreditCard.shouldHave(Condition.visible);
        return new PaymentPageOnCredit();
    }
}
