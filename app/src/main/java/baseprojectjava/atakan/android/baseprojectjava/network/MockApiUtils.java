package baseprojectjava.atakan.android.baseprojectjava.network;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MockApiUtils extends AbstractApiUtils{


    public static final int DELAY = 1;
    Gson gson = new Gson();

    public MockApiUtils(UnauthorizedAPIService unauthorizedAPIService, AuthorizedAPIService authorizedAPIService, CustomCallbackSender customCallbackSender, TokenManager tokenManager) {
        super(unauthorizedAPIService, authorizedAPIService, customCallbackSender, tokenManager);
    }

    @Override
    public Observable<DataHolder<CustomTempModel>> getCustomTempData() {
        return null;
    }

   /* private <T> Observable<T> scheduleObsevable(Observable<T> observable){
        return observable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DataHolder<LoginModel>> login(String userName, String password, String otpCode, String captchaCode) {
        return scheduleObsevable(Observable.just(LoginMockResponse.LOGIN_SUCCESS))
                .delay(DELAY, TimeUnit.SECONDS)
                .map(s -> DataHolder.createSuccessDataHolderForRemote(gson.fromJson(s, LoginModel.class)));
    }

    @Override
    public Observable<DataHolder<LoginModel>> refreshTohen() {
        return scheduleObsevable(Observable.just(LoginMockResponse.LOGIN_SUCCESS))
                .delay(DELAY, TimeUnit.SECONDS)
                .map(s -> DataHolder.createSuccessDataHolderForRemote(gson.fromJson(s, LoginModel.class)));
    }

    @Override
    public Observable<DataHolder<ResultModel>> refreshOtp(String username) {
        // TODO: 16.01.2018 Mock tan ne donecegiz
        return null;
    }

  */
}
