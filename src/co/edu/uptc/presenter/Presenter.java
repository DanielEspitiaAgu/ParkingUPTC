package co.edu.uptc.presenter;

import co.edu.uptc.model.ModelSystem;

public final class Presenter {
    private static Presenter presenter;
    private ModelSystem modelSystem;

    private Presenter() {
        this.modelSystem = new ModelSystem();
    }

    public void login(){
        modelSystem.login();
    }

    public static Presenter getInstance() {
        if (presenter == null) {
            presenter = new Presenter();
        }
        return presenter;
    }
}
