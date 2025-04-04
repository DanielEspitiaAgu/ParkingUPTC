package co.edu.uptc.presenter;

import co.edu.uptc.model.ModelSystem;
import co.edu.uptc.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class Presenter {
    private static Presenter presenter;
    private ModelSystem modelSystem;
    private View view;

    private Presenter() {

        this.modelSystem = new ModelSystem();
        this.view = new View();
    }

    public void login(String idNumber, String password) {
           view.showReceptionistMenu("Mandarina");
 //       if (modelSystem.login(idNumber, password)==1){
   //     }else if (modelSystem.login(idNumber, password)==2){
     //       view.showAdminMenu(modelSystem.getUserName());
      //  }else{
        //    view.showErrorMessage("Error","Usuario o contraseña incorrectos");
        //}
    }

    public void generateTicket(String plate) {
            view.showGenerateEntryTicketPanel("PArkingUPTc", "18/04/2025", plate, "3:40");
    }

    public static Presenter getInstance() {
        if (presenter == null) {
            presenter = new Presenter();
        }
        return presenter;
    }

    public String getParkingInfo(){
        return modelSystem.getParkingInfo();
    }

	public boolean registerParking(String name, String address, int capacity, ArrayList<String> scheduleList) {
		return modelSystem.createParking(name, address, capacity, scheduleList);
	}

    public boolean registerReceptionist(String idNumber, String email, String name, String lastName, String phone, String address) {
        return modelSystem.createReceptionist(idNumber, email, name+idNumber, name, lastName, phone, address);
    }

    public String getReceptionistInfo(String idNumber) {
        return modelSystem.getReceptionistInfo(idNumber);
    }

    public ArrayList<String> getReceptionistList(){
        return modelSystem.getReceptionistList();
    }

}
