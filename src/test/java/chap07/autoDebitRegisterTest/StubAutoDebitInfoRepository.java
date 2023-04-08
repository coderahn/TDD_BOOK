package chap07.autoDebitRegisterTest;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository{
    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }

    @Override
    public void save(AutoDebitInfo newInfo) {

    }
}
