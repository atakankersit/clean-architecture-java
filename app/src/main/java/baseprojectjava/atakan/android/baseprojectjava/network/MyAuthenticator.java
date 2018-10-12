package baseprojectjava.atakan.android.baseprojectjava.network;

import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import static android.content.ContentValues.TAG;


public class MyAuthenticator implements Authenticator {
    private final AtomicBoolean mIsRefreshing;
    private final ConditionVariable LOCK;
    private final TokenManager tokenManager;
    private AuthenticationManager authenticationManager;

    public MyAuthenticator(AtomicBoolean mIsRefreshing, ConditionVariable LOCK, TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.mIsRefreshing = mIsRefreshing;
        this.LOCK = LOCK;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, final Response response) throws IOException {
        /*
        *  Because we send out multiple HTTP requests in parallel, they might all list a 401 at the same time.
        *  Only one of them should refresh the token, because otherwise we'd refresh the same token multiple times
        *  and that is bad. Therefore we have these two static objects, a ConditionVariable and a boolean. The
        *  first thread that gets here closes the ConditionVariable and changes the boolean flag.
        */
        if (mIsRefreshing.compareAndSet(false, true)) {
            LOCK.close();
            Log.d(TAG, "locking:" + response.request().toString());
            if (TextUtils.isEmpty(tokenManager.getRefreshToken())) {
                authenticationManager.onLoginRequired();
            } else {
                authenticationManager.onRefreshAccessTokenRequired();
                LOCK.block();
            }
        } else {// Another thread is refreshing the token for us, let's wait for it.
            Log.d(TAG, "waiting:" + response.request().toString());
            LOCK.block();
        }
        String token = tokenManager.getAccesToken();
        return TextUtils.isEmpty(token) ? null : response.request().newBuilder().header("Authorization", "Bearer " + token).build();
    }
}
