package chap_C;

import chap07.userRegisterTest.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class GameGenMockTest {
    private UserRegister userRegister;
    //값만 제공하면 되는 테스트 대역(스텁)
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    @Mock
    private EmailNotifier mockEmailNotifier;

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, mockEmailNotifier);
    }
    @DisplayName("모의객체 메소드 인자에 따른 결과 생성")
    @Test
    void mockTest() {
        //모의객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        //모의객체 메소드가 특정값 리턴하도록 설정
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @DisplayName("모의객체 메소드 인자가 null일 때 예외 발생시키기")
    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(
                IllegalArgumentException.class,
                () -> genMock.generate(null)
        );
    }

    @DisplayName("모의객체 메소드의 리턴타입이 void 메서드일 때 Exception발생시키기")
    @Test
    void mockVoidThrowTest() {
        List<String> mockList = mock(List.class);
        //모의객체의 clear 메소드 실행시 Unsupport..발생
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThrows(
          UnsupportedOperationException.class,
                () -> mockList.clear()
        );
    }

    @DisplayName("인자 매칭")
    @Test
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num2);
    }

    @DisplayName("임의 값 일치와 정확한 값 일치가 필요한경우 eq사용")
    @Test
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);

        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        assertEquals("456", old);
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void UserRegisterMockTest() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        assertEquals("email@email.com", captor.getValue());
    }
}
