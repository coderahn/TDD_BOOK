package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculatorExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() /10_000;

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        //앞과 뒷가 다르다는것은 -> 첫납부일과 납부일이 무조건 다르단 의미 -> 후보만료일의 일자를 첫달납부일의 일자로 맞춰서 반환하면 됨
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        if (!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            //후보만료일 포함 달의 마지막날 < 첫납부일의 일자
            if (dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }
}
