package chap07.autoDebitRegisterTest;

public class RegisterResult {
    public static RegisterResult error(CardValidity validity) {
        return new RegisterResult();
    }

    public static RegisterResult success() {
        return null;
    }

    public CardValidity getValidity() {
        return CardValidity.VALID;
    }
}
