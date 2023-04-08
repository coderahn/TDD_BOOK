package chap07.autoDebitRegisterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() throws IOException {
        stubValidator.setInvalidNo("1111222223333");

        AutoDebitReq req = new AutoDebitReq("user1", "1111222233333");
        RegisterResult result = register.register(req);

        Assertions.assertEquals(CardValidity.INVALID, result.getValidity());
    }
}
