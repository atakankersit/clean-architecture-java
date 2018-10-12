package baseprojectjava.atakan.android.baseprojectjava.base;

import android.arch.lifecycle.ViewModelProvider;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import javax.inject.Inject;

import baseprojectjava.atakan.android.baseprojectjava.R;
import baseprojectjava.atakan.android.baseprojectjava.view.dialog.BaseProgressDialog;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {


    public DB binding;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @LayoutRes
    public abstract int getLayoutRes();

    private BroadcastReceiver actionReceiver;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

/*    @Inject
    public SharedPreferenceManager sharedPreferenceManager;*/

    protected BaseProgressDialog baseProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutRes());
        binding.setLifecycleOwner(this);
        baseProgressDialog = BaseProgressDialog.getDialog();
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public void loadFragment(int contanerId, Fragment fragment, String name , boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (addToBackStack) {
            ft.addToBackStack(name);
        }
        ft.replace(contanerId, fragment);
        ft.commit();
    }

    public void loadFragment(int contanerId, Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.replace(contanerId, fragment);
        ft.commit();
    }


/*
    private void showNavigationMessage(Intent intent) {
        String message = intent.getStringExtra(Navigate.NAVIGATION_MESSAGE);
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
*/

    protected void showMessageDialog(String title, String message, FragmentManager fragmentManager) {
       /* MessageDialog dialog= MessageDialog.newInstance(title, message);
        dialog.setListener(dialog::dismiss);
        dialog.show(fragmentManager,"");*/
    }

    public void showLoading(boolean show) {
        if (show) {
            baseProgressDialog.show(BaseActivity.this);
        } else {
            baseProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(actionReceiver);
        super.onDestroy();
    }
}
