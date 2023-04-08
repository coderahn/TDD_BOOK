package chap03;

import sun.util.resources.LocaleData;

import java.time.LocalDate;

public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    private PayData(){

    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public void setFirstBillingDate(LocalDate firstBillingDate) {
        this.firstBillingDate = firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    //빌더 스태틱 내부 클래스 만들기
    //스태틱 builder()에 접근하면 new Builder()를 만들어줌. 둘다 스태틱이어야 함
    //Builder 클래스 내부에는 PayData객체 필요.
    //builder로 셋팅할떄 payData.setXXX로 넣은후 return Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData data = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            data.setFirstBillingDate(firstBillingDate);
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            data.setBillingDate(billingDate);
            return this;
        }

        public Builder payAmount(int payAmount) {
            data.setPayAmount(payAmount);
            return this;
        }

        public PayData build() {
            return data;
        }
    }
}
