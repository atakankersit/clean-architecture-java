package baseprojectjava.atakan.android.baseprojectjava.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.class)
public class SharedPrefModule {

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
