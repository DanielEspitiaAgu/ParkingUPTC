package co.edu.uptc.presenter;

import co.edu.uptc.model.ModelSystem;
import co.edu.uptc.view.View;

public final class Presenter {
    private static Presenter presenter;
    private ModelSystem modelSystem;
    private View view;

    private Presenter() {

        this.modelSystem = new ModelSystem();
        this.view = new View();
    }

    public void login(String idNumber, String password) {
        if (true) {
            view.showReceptionistMenu();
        }
    }

    public static Presenter getInstance() {
        if (presenter == null) {
            presenter = new Presenter();
        }
        return presenter;
    }
}
