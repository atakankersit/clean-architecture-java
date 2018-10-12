package baseprojectjava.atakan.android.baseprojectjava.network;

import android.os.ConditionVariable;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by atakankersit on 15.12.2017.
 */
public class AuthorizationInterceptor implements Interceptor {
    private final AtomicBoolean mIsRefreshing;
    private final ConditionVariable LOCK;
    private final Gson gson;

    public AuthorizationInterceptor(AtomicBoolean mIsRefreshing, ConditionVariable LOCK, Gson gson) {
        this.mIsRefreshing = mIsRefreshing;
        this.LOCK = LOCK;
        this.gson = gson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (mIsRefreshing.get()) {
            LOCK.block();
        }
         return chain.proceed(request);
    }
}
