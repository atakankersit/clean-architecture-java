package baseprojectjava.atakan.android.baseprojectjava.network;

import android.content.Context;
import android.os.ConditionVariable;



import java.util.concurrent.atomic.AtomicBoolean;

import baseprojectjava.atakan.android.baseprojectjava.R;
import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;

/**
 * Created by atakankersit on 15.12.2017.
 */

public class AuthenticationManager {

    private Context context;
    private TokenManager tokenManager;
    private ConditionVariable LOCK;
    private AtomicBoolean mIsRefreshing;
    private CustomCallbackSender customCallbackSender;
    private UnauthorizedAPIService unauthorizedAPIService;


    public AuthenticationManager(Context context, TokenManager tokenManager, ConditionVariable LOCK, AtomicBoolean mIsRefreshing, CustomCallbackSender customCallbackSender, UnauthorizedAPIService unauthorizedAPIService) {
        this.context = context;
        this.tokenManager = tokenManager;
        this.LOCK = LOCK;
        this.mIsRefreshing = mIsRefreshing;
        this.customCallbackSender = customCallbackSender;
        this.unauthorizedAPIService = unauthorizedAPIService;
    }

    public void onLoginRequired() {
       /* String warningText=context.getString(R.string.warning_login_required);
        Navigate.to(context, Navigate.action.RETURN_LOGIN,warningText);*/
    }

    public void onRefreshAccessTokenRequired() {
        refreshAccessToken();
    }

    private void refreshAccessToken() {
       /* customCallbackSender.sendRequest(
                unauthorizedAPIService.refreshToken(
                        "refresh_token",
                        NetworkConstants.CLIENT_ID_MOBILE_CLIENT,
                        tokenManager.getRefreshToken()))
                .subscribe(loginModelDataHolder -> {
                    //// FIXME: 11.01.2018 Burada data olursa ne yapilacak
                    String token=loginModelDataHolder.getData().getAccessToken();
                    tokenManager.setAccessToken(token);
            LOCK.open();
            mIsRefreshing.set(false);
                }, (Throwable throwable) -> {
                    onLoginRequired();
                });*/
    }
}
