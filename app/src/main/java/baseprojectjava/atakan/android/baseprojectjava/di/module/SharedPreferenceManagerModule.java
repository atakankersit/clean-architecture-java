package baseprojectjava.atakan.android.baseprojectjava.di.module;

import android.content.Context;
import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.ApplicationContextQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import baseprojectjava.atakan.android.baseprojectjava.manager.SharedPreferenceManager;
import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module(includes = AppModule.class)
public class SharedPreferenceManagerModule {

    @ApplicationScope
    @Provides
    public SharedPreferenceManager sharedPreferenceManager(@ApplicationContextQualifier Context context){
     return new SharedPreferenceManager(context);
    }
}
