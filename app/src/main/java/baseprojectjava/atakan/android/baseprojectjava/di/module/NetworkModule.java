package baseprojectjava.atakan.android.baseprojectjava.di.module;

import android.content.Context;
import android.os.ConditionVariable;
import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.ApplicationContextQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.AuthorizedQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.BaseUrlQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.UnauthorizedQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import baseprojectjava.atakan.android.baseprojectjava.network.AuthenticationManager;
import baseprojectjava.atakan.android.baseprojectjava.network.AuthorizationInterceptor;
import baseprojectjava.atakan.android.baseprojectjava.network.AuthorizedAPIService;
import baseprojectjava.atakan.android.baseprojectjava.network.CustomCallbackSender;
import baseprojectjava.atakan.android.baseprojectjava.network.LoginInterceptor;
import baseprojectjava.atakan.android.baseprojectjava.network.MyAuthenticator;
import baseprojectjava.atakan.android.baseprojectjava.network.NetworkConstants;
import baseprojectjava.atakan.android.baseprojectjava.network.UnauthorizedAPIService;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.CertificatePinner;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@ApplicationScope
@Module(includes = {TokenManagerModule.class,AppModule.class})
public class NetworkModule {

    @ApplicationScope
    @Provides
    public UnauthorizedAPIService provideUnauthorizedApiService(@UnauthorizedQualifier OkHttpClient okHttpClient, Gson gson, @BaseUrlQualifier String baseUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())) // TODO: 10.01.2018 neden Create bi bakılmalı
                .addConverterFactory(GsonConverterFactory.create(gson));
        return retrofitBuilder.build().create(UnauthorizedAPIService.class);
    }


    @ApplicationScope
    @Provides
    public AuthorizedAPIService provideAuthorizedApiService(@AuthorizedQualifier OkHttpClient okHttpClient, Gson gson, @BaseUrlQualifier String baseUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())) // TODO: 10.01.2018 neden Create bi bakılmalı
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson));
        return retrofitBuilder.build().create(AuthorizedAPIService.class);
    }


    @ApplicationScope
    @Provides
    @AuthorizedQualifier
    public OkHttpClient provideAuthorizedOkHttpClient(@AuthorizedQualifier OkHttpClient.Builder builder) {
        return  builder.build();
    }

    @ApplicationScope
    @Provides
    @UnauthorizedQualifier
    public OkHttpClient provideUnauthorizedOkHttpClient(@UnauthorizedQualifier OkHttpClient.Builder builder) {
        return  builder.build();
    }


    @ApplicationScope
    @Provides
    @AuthorizedQualifier
    public CertificatePinner proviceCertificatePinner(){
        // TODO: 8/15/18 public key girilmeli
        CertificatePinner certPinner = new CertificatePinner.Builder()
                .add("turkcell.com.tr",
                        "sha256/4hw5tz+scE+TW+mlai5YipDfFWn1dqvfLG+nU7tq1V8=")
                .build();
        return certPinner;
    }


    @ApplicationScope
    @Provides
    @AuthorizedQualifier
    public OkHttpClient.Builder provideAuthorizedOkHttpBuilder(HttpLoggingInterceptor loggingInterceptor, LoginInterceptor loginIntercepter, AuthorizationInterceptor authorizationInterceptor, MyAuthenticator myAuthenticator, CookieJar cookieJar) {


        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //okHttpBuilder.cookieJar(cookieJar);
        okHttpBuilder.cookieJar(cookieJar)
                .addInterceptor(loggingInterceptor)
                //.certificatePinner(certPinner)
                //Authorized oldugu icin loginIntercepter ekleniyor.
                .addInterceptor(loginIntercepter)
                .authenticator(myAuthenticator)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(authorizationInterceptor);
        return okHttpBuilder;

    }

    @ApplicationScope
    @Provides
    @UnauthorizedQualifier
    public OkHttpClient.Builder provideUnauthorizedOkHttpBuilder(HttpLoggingInterceptor loggingInterceptor,LoginInterceptor loginInterceptor) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //okHttpBuilder.cookieJar(cookieJar);
        okHttpBuilder.addInterceptor(loggingInterceptor);
        okHttpBuilder.addInterceptor(loginInterceptor);
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        return okHttpBuilder;

    }

    //region Intercepters

    @ApplicationScope
    @Provides
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }

    @ApplicationScope
    @Provides
    public AuthorizationInterceptor getNetworkIntercepter(final Gson gson, final ConditionVariable LOCK, final AtomicBoolean mIsRefreshing) {
        return new AuthorizationInterceptor(mIsRefreshing, LOCK, gson);
    }

    @ApplicationScope
    @Provides
    public LoginInterceptor getLoginIntercepter(Gson gson, TokenManager tokenManager, ConditionVariable LOCK, AtomicBoolean mIsRefreshing) {
        return new LoginInterceptor(tokenManager, gson, LOCK, mIsRefreshing);
    }
    //endregion

    @ApplicationScope
    @Provides
    public MyAuthenticator getAuthenticator(TokenManager tokenManager, ConditionVariable LOCK, AtomicBoolean mIsRefreshing, AuthenticationManager authenticationManager) {
        return new MyAuthenticator(mIsRefreshing, LOCK, tokenManager, authenticationManager);
    }

    @ApplicationScope
    @Provides
    public ConditionVariable conditionVariable() {
        return new ConditionVariable(true);
    }

    @ApplicationScope
    @Provides
    public AtomicBoolean atomicBoolean() {
        return new AtomicBoolean(false);
    }

    @ApplicationScope
    @Provides
    public AuthenticationManager providesAuthenticationManager(TokenManager tokenManager,
                                                               AtomicBoolean atomicBoolean,
                                                               ConditionVariable conditionVariable,
                                                               CustomCallbackSender customCallbackSender,
                                                               UnauthorizedAPIService unauthorizedAPIService,
                                                               @ApplicationContextQualifier Context context
    ) {

        return new AuthenticationManager(context,tokenManager, conditionVariable,  atomicBoolean,customCallbackSender,unauthorizedAPIService);
    }

    @ApplicationScope
    @Provides
    public Gson provideGson(){
        return  new Gson();
    }

    @ApplicationScope
    @Provides
    public CookieJar provideCookieJar() {
        return new CookieJar() {
            public static final String TAG = "Cookie Jar";
            public List<Cookie> cookies = Collections.EMPTY_LIST;

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                for (Cookie cookie : cookies) {
                    Log.i(TAG, "saveFromResponse: ahan da cookie " + cookie.toString());
                }
                this.cookies = cookies;
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                Log.d(TAG, "loadForRequest() called with: url = [" + url + "]");
                return cookies;
            }
        };
    }

    @ApplicationScope
    @Provides
    public NetworkConstants provideNetworkConstants() {
        return new NetworkConstants();
    }

    @ApplicationScope
    @Provides
    public CustomCallbackSender provideCustomCallbackSender(){
        return new CustomCallbackSender();
    }
}