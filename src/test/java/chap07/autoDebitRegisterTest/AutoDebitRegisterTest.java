package chap07.autoDebitRegisterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() throws IOException {
        //업체에서 받은 테스트용 유효한 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req); //외부 api에 의존
        Assertions.assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    void theftCard() throws IOException {
        //업체에서 받은 테스트용 유효한 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412345678");
        RegisterResult result = this.register.register(req); //외부 api에 의존

        Assertions.assertEquals(CardValidity.THEFT, result.getValidity());
    }
}
