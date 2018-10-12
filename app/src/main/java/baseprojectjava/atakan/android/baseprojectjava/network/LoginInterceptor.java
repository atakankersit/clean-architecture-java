package baseprojectjava.atakan.android.baseprojectjava.network;

import android.os.ConditionVariable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by atakankersit on 15.12.2017.
 */
public class LoginInterceptor implements Interceptor {
    private final TokenManager tokenManager;
    private final Gson gson;
    private final ConditionVariable LOCK;
    private final AtomicBoolean mIsRefreshing;
    private final String basicAuthorizationHeader;

    public LoginInterceptor(TokenManager tokenManager, Gson gson, ConditionVariable LOCK, AtomicBoolean mIsRefreshing) {
        this.tokenManager = tokenManager;
        this.gson = gson;
        this.LOCK = LOCK;
        this.mIsRefreshing = mIsRefreshing;
        // FIXME: 15.12.2017 NetworkConstant a tasinacak
        this.basicAuthorizationHeader =Credentials.basic(NetworkConstants.CLIENT_ID_MOBILE_CLIENT, NetworkConstants.CLIENT_ID_MOBILE_CLNT_PSWRD);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request;
        boolean isLoginRequest = false;

        String encodedPath = request.url().encodedPath();

        if ("/api/uaa/oauth/token".contains(encodedPath)) {
            isLoginRequest = true;
            newRequest = request.newBuilder().header("Authorization", basicAuthorizationHeader).build();
        }
        String accessToken = tokenManager.getAccesToken();
        if (!TextUtils.isEmpty(accessToken) && !isLoginRequest) {
            newRequest = request.newBuilder().header("Authorization", "Bearer " + accessToken).build();
        }
        Response response = chain.proceed(newRequest);
        if (isLoginRequest && response.isSuccessful()) {
            ResponseBody body = response.body();
            String bodyString = body.string();
        /*    LoginModel loginModel = gson.fromJson(bodyString, LoginModel.class);
            if (loginModel != null) {
                String token1=loginModel.getAccessToken();
                String token2=loginModel.getRefreshToken();
                Log.wtf("access_token",token1);
                Log.wtf("refresh_token",token2);
                tokenManager.setAccessToken(token1);
                tokenManager.setRefreshToken(token2);
            }*/
            response =  response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString)).build();
            LOCK.open();
            mIsRefreshing.set(false);
        }


        return response;
    }
}
