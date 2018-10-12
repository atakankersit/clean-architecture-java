package baseprojectjava.atakan.android.baseprojectjava.di.module;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import baseprojectjava.atakan.android.baseprojectjava.network.AbstractApiUtils;
import baseprojectjava.atakan.android.baseprojectjava.network.ApiUtils;
import baseprojectjava.atakan.android.baseprojectjava.network.AuthorizedAPIService;
import baseprojectjava.atakan.android.baseprojectjava.network.CustomCallbackSender;
import baseprojectjava.atakan.android.baseprojectjava.network.UnauthorizedAPIService;
import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module(includes = {TokenManagerModule.class, NetworkModule.class})
public class ApiUtilsModule {

    @ApplicationScope
    @Provides
    public AbstractApiUtils apiUtils(UnauthorizedAPIService unauthorizedAPIService, AuthorizedAPIService authorizedAPIService, CustomCallbackSender customCallbackSender, TokenManager tokenManager) {
        //return new MockApiUtils(unauthorizedAPIService, authorizedAPIService, customCallbackSender, tokenManager);
        return new ApiUtils(unauthorizedAPIService, authorizedAPIService, customCallbackSender, tokenManager);
    }
}
