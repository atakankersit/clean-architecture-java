package baseprojectjava.atakan.android.baseprojectjava.di.module;


import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import baseprojectjava.atakan.android.baseprojectjava.manager.SharedPreferenceManager;
import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;
import dagger.Module;
import dagger.Provides;


@ApplicationScope
@Module(includes = SharedPreferenceManagerModule.class)
public class TokenManagerModule {

    @ApplicationScope
    @Provides
    public TokenManager tokenManager(SharedPreferenceManager sharedPreferenceManager){
     return new TokenManager(sharedPreferenceManager);
    }
}
