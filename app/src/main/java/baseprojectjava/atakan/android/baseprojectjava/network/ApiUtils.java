package baseprojectjava.atakan.android.baseprojectjava.network;



import java.util.concurrent.TimeUnit;

import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;


public class ApiUtils extends AbstractApiUtils{


    public ApiUtils(UnauthorizedAPIService unauthorizedAPIService, AuthorizedAPIService authorizedAPIService, CustomCallbackSender customCallbackSender, TokenManager tokenManager) {
        super(unauthorizedAPIService, authorizedAPIService, customCallbackSender, tokenManager);
    }

    @Override
    public Observable<DataHolder<CustomTempModel>> getCustomTempData() {
        return customCallbackSender.sendRequest(unauthorizedAPIService.getTempModel()).delay(3,TimeUnit.MILLISECONDS);
    }

  /*  @Override
    public Observable<DataHolder<LoginModel>> login(String userName, String password, String otpCode, String captchaCode) {
        Observable<DataHolder<LoginModel>> loginObservable = customCallbackSender.sendRequest(authorizedAPIService.firstLogin("password", userName.toUpperCase(), password, otpCode, captchaCode));
        Observable<DataHolder<LoginModel>> starttedObservable = Observable.just(DataHolder.createStartedDataHolderForRemote());
        Observable concated = starttedObservable.concatWith(loginObservable);
        return concated;
    }

    @Override
    public Observable<DataHolder<LoginModel>> refreshTohen() {
        return customCallbackSender.sendRequest(unauthorizedAPIService.refreshToken("refresh_token", NetworkConstants.CLIENT_ID_MOBILE_CLIENT,tokenManager.getRefreshToken()));
    }

    @Override
    public Observable<DataHolder<ResultModel>> refreshOtp(String username) {
        return customCallbackSender.sendRequest(unauthorizedAPIService.refreshOtp(username));
    }

    @Override
    public Observable generateAccessToken(String refreshToken) {
        return customCallbackSender.sendRequest(unauthorizedAPIService.generateAccessToken(refreshToken));
    }

    @Override
    public Observable<DataHolder<BaseResponse>> getUnauthorizedService() {
        return customCallbackSender.sendRequest(authorizedAPIService.getUnauthorizedRequest("SY_CXO"));
    }

    */

}