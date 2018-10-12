package baseprojectjava.atakan.android.baseprojectjava.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import baseprojectjava.atakan.android.baseprojectjava.BR;
import baseprojectjava.atakan.android.baseprojectjava.view.dialog.BaseProgressDialog;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<VM extends ViewModel, DB extends ViewDataBinding> extends Fragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    protected VM viewModel;

    protected DB binding;

    protected abstract Class<VM> getViewModel();

    @LayoutRes
    public abstract int getLayoutRes();

    protected BaseProgressDialog baseProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        initViewModel();
        baseProgressDialog = BaseProgressDialog.getDialog();
    }

    protected void initViewModel(){
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(getViewModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        binding.setVariable(BR.viewModel,viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public void loadFragment(int contanerId, Fragment fragment, String name , boolean addToBackStack) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (addToBackStack) {
            ft.addToBackStack(name);
        }
        ft.replace(contanerId, fragment);
        ft.commit();
    }


    public void loadFragment(int contanerId, Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.replace(contanerId, fragment);
        ft.commit();
    }

    protected void showMessageDialog(String title, String message, FragmentManager fragmentManager) {
/*  MessageDialog dialog= MessageDialog.newInstance(title, message);
        dialog.setListener(dialog::dismiss);
        dialog.show(fragmentManager,"");*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        baseProgressDialog.dismiss();
    }

}
