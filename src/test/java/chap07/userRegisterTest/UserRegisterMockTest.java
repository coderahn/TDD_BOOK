package chap07.userRegisterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입실패")
    @Test
    void weakPassword() {
        //"pw"인자를 사용해서 모의객체의 checkPasswordWeak 메소드를 호출하면 결과로 true 리턴하도록 설정
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        Assertions.assertThrows(
                WeakPasswordException.class,
                () -> userRegister.register("id", "pw", "email@email.com")
        );
    }
}
