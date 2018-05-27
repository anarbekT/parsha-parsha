package com.example.ui.base;

public class AbstractBasePresenter {

    public AbstractBasePresenter() {
        MvpInjector.Inject(this);
    }
}
