package baseprojectjava.atakan.android.baseprojectjava.di.component;

import android.app.Application;

import baseprojectjava.atakan.android.baseprojectjava.base.BaseApplication;
import baseprojectjava.atakan.android.baseprojectjava.di.module.ActivityBuilderModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.AppModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.FragmentBuilderModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.NetworkModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.RepositoryModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.SharedPrefModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.SharedPreferenceManagerModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.TokenManagerModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.UseCaseModule;
import baseprojectjava.atakan.android.baseprojectjava.di.module.ViewModelModule;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                FragmentBuilderModule.class,
                NetworkModule.class,
                RepositoryModule.class,
                SharedPreferenceManagerModule.class,
                SharedPrefModule.class,
                TokenManagerModule.class,
                UseCaseModule.class,
                ViewModelModule.class
        }
)
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
    void inject(BaseApplication app);
}