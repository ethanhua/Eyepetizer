package com.ethanhua.eyepetizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ethanhua.eyepetizer.binding.FragmentBindingComponent;
import com.ethanhua.eyepetizer.di.components.ApplicationComponent;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.di.modules.FragmentModule;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/17.
 */

public class BaseFragment extends Fragment {

    @Inject
    public FragmentBindingComponent bindComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .applicationComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
                .inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.ethanhua.eyepetizer.di.components.ApplicationComponent}
     */
    protected ApplicationComponent getAppComponent() {
        if (getActivity() != null) {
            return ((EyeApplication) getActivity().getApplication()).getApplicationComponent();
        }
        return null;
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.ethanhua.eyepetizer.di.modules.ActivityModule}
     */
    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

}
