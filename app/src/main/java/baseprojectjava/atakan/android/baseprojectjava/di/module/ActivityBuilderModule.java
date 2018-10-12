package baseprojectjava.atakan.android.baseprojectjava.di.module;



import baseprojectjava.atakan.android.baseprojectjava.view.activity.TestActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract TestActivity contributeTestActivity();



}