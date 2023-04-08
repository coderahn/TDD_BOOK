package chap07.autoDebitRegisterTest;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 자동이체등록
 */
public class AutoDebitRegister {
    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(
            CardNumberValidator validator
            , AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) throws IOException {
        CardValidity validity = validator.validate(req.getCardNumber());

        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }

        AutoDebitInfo info = repository.findOne(req.getUserId());

        //기존에 있는 유저면 카드번호 업데이트
        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        //첫유저면 신규자동이체 저장
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
            repository.save(newInfo);
        }

        return RegisterResult.success();
    }


}
