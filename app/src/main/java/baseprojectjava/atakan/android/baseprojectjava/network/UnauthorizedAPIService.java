package baseprojectjava.atakan.android.baseprojectjava.network;


import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface UnauthorizedAPIService {


	@GET("http://echo.jsontest.com/key/value/one/two")
	Observable<CustomTempModel> getTempModel();

/*
	@POST("")
	Observable<BaseResponse<TokenResponse>> generateAccessToken(@Header("refreshToken") String refreshToken);

	@GET("uaa/captcha/getCaptchaImage?ldapName=username")
	Observable<BaseResponse> firstLogin();


	@GET("uaa/otp/refreshOtpCode")
	Observable<ResultModel> refreshOtp(@Query("ldapName") String username);

	@POST("uaa/oauth/token")
	@FormUrlEncoded
	Observable<LoginModel> refreshToken(
            @Field("grant_type") String grant_type,
            @Field("client_id") String client_id,
            @Field("refresh_token") String refresh_token
    );*/

}