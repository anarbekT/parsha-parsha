package web.app.ui.base;

public class AbstractBasePresenter {

    public AbstractBasePresenter() {
        MvpInjector.Inject(this);
    }
}
