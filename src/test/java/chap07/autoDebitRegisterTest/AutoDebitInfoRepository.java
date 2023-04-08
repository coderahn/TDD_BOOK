package chap07.autoDebitRegisterTest;

public interface AutoDebitInfoRepository {
    AutoDebitInfo findOne(String userId);

    void save(AutoDebitInfo newInfo);
}
