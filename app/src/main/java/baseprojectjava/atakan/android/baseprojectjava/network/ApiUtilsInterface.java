package baseprojectjava.atakan.android.baseprojectjava.network;



import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;
import retrofit2.http.Body;

interface ApiUtilsInterface {
    Observable<DataHolder<CustomTempModel>> getCustomTempData();

   /* Observable<DataHolder<LoginModel>> login(String userName, String password, String otpCode, String captchaCode);

    Observable<DataHolder<LoginModel>> refreshTohen();

    Observable<DataHolder<ResultModel>> refreshOtp(String username);

    Observable generateAccessToken(String refreshToken);

    Observable<DataHolder<BaseResponse>> getUnauthorizedService();*/



}
