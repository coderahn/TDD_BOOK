package chap07.autoDebitRegisterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class AutoDebitRegister_Fake_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void alreadyRegistered_InfoUpdated() throws IOException {
        //미리 DB에 user1 정보 저장
        repository.save(
                new AutoDebitInfo("user1", "111222333444", LocalDateTime.now())
        );

        //변경된 정보로 등록
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123");
        RegisterResult result = register.register(req);

        //변경된 카드번호 확인
        AutoDebitInfo saved = repository.findOne("user1");
        Assertions.assertEquals("1234567890123", saved.getCardNumber());
    }

    @Test
    void notYetRegisterd_newInfoRegisterd() throws IOException {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        Assertions.assertEquals("1234123412341234", saved.getCardNumber());
    }
}
