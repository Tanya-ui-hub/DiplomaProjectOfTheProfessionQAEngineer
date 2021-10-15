package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getInvalidCardNumber1() {
        return "4444 4444 4444 4443";
    }

    public static String getInvalidCardNumber2() {
        return "4444 4444 4444 444";
    }

    public static String getEmptyCardNumberValue() {
        return " ";
    }

    public static String getValidMonth() {
        String[] monthNumbers = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int itemIndex = (int) (Math.random() * monthNumbers.length);
        return monthNumbers[itemIndex];
    }

    public static String getInvalidMonth() {
        return "15";
    }

    public static String getInvalidMonthZero() {
        return "00";
    }

    public static String getInvalidMonthOneCharacter() {
        return "1";
    }

    public static String getEmptyMonthValue() {
        return " ";
    }

    public static String getValidYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.plusYears(4);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }

    public static String getExpiredYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.minusYears(1);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }

    public static String getInvalidYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.plusYears(7);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }

    public static String getEmptyYearValue() {
        return " ";
    }

    public static String getEmptyYearValue1() {
        return "00";
    }

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getInvalidOwner1() {
        return "Sevilleja Chris";
    }

    public static String getInvalidOwner2() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().lastName();
    }

    public static String getInvalidOwner3() {
        return "W";
    }

    public static String getInvalidOwner4() {
        return "TGFJVNCMDKELWOQIAJZNDTMDLMREW IWJDNRYFBSYRHFYTVCPQZMSHRBD";
    }

    public static String getInvalidOwner5() {
        return "!@#$%^&**()?/";
    }

    public static String getInvalidOwner6() {
        return "ivan ivanov";
    }

    public static String getInvalidOwner7() {
        return "1234567890";
    }

    public static String getEmptyOwnerValue() {
        return " ";
    }

    public static String getValidCVV() {
        return "457";
    }

    public static String getInvalidCVV() {
        return " ";
    }

    public static String getInvalidCVV1() {
        return "1";
    }

    public static String getInvalidCVV2() {
        return "12";
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidCardNumberInfo() {
        return new CardInfo(getInvalidCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidCardNumberInfo1() {
        return new CardInfo(getInvalidCardNumber1(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidCardNumberInfo2() {
        return new CardInfo(getInvalidCardNumber2(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidCardInfo() {
        return new CardInfo(getEmptyCardNumberValue(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidMonthInfo() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonth(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidMonthInfo1() {
        return new CardInfo(getApprovedCardNumber(), getEmptyMonthValue(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidMonthZeroInfo() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonthZero(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidMonthInfo2() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonthOneCharacter(), getValidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getExpiredYearInfo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getExpiredYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidYearInfo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getInvalidYear(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidYearInfo1() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getEmptyYearValue(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidYearInfo2() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getEmptyYearValue1(), getValidOwner(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getEmptyOwnerValue(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo1() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner1(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo2() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner2(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo3() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner3(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo4() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner4(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo5() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner5(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo6() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner6(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo7() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner7(), getValidCVV());
    }

    public static CardInfo getInvalidOwnerInfo8() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwner(), getValidCVV());
    }

    public static CardInfo getEmptyFields() {
        return new CardInfo(getEmptyCardNumberValue(), getEmptyMonthValue(), getEmptyYearValue(),
                getEmptyOwnerValue(), getInvalidCVV());
    }

    public static CardInfo getInvalidCVVInfo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getInvalidCVV());
    }

    public static CardInfo getInvalidCVVInfo1() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getInvalidCVV1());
    }

    public static CardInfo getInvalidCVVInfo2() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getInvalidCVV2());
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvv;
    }
}
