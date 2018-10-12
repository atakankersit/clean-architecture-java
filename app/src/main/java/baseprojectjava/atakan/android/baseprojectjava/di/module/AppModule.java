package baseprojectjava.atakan.android.baseprojectjava.di.module;

import android.app.Application;
import android.content.Context;
import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.ApplicationContextQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.qualifiers.BaseUrlQualifier;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @ApplicationScope
    @ApplicationContextQualifier
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    @BaseUrlQualifier
    public String provideBaseUrl(){
        return "https://mayaint-ist.turkcell.com.tr/api/";
    }

/*    @Provides
    @ApplicationScope
    public FileUrlConvertor provideFileUrlConvertor(@BaseUrlQualifier String baseUrl){
        return new FileUrlConvertor(baseUrl);
    }

    @Provides
    @ApplicationScope
    public FirebaseAnalytics provideFirebaseAnalytics(@ApplicationContextQualifier Context context){
        return FirebaseAnalytics.getInstance(context);
    }*/

}