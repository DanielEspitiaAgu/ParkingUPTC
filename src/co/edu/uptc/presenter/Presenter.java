package co.edu.uptc.presenter;

import co.edu.uptc.model.ModelSystem;
import co.edu.uptc.view.View;

import java.lang.reflect.Array;
import java.time.LocalDate;
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
        int result = modelSystem.login(idNumber, password);
        if (result == 1) {
            view.showReceptionistMenu("Recepcionista: "+modelSystem.getUserName());
        } else if (result == 2) {
            view.showAdminMenu("Administrador: "+modelSystem.getUserName());
        } else{
            view.showErrorMessage("Error", "Usuario o contraseña incorrectos.");
        }
    }

    public void generateTicket(String plate) {
        try{
            if(modelSystem.registerVehicle(plate)){
                view.showGenerateEntryTicketPanel(modelSystem.getEntryTicketInformation(plate));
            }else{
                view.showErrorMessage("Error", "No se pudo registrar el vehículo, por favor verifique las plazas disponibles o si el vehículo haya salido del parqueadero.");
            }
        }
        catch(NullPointerException ex){
            view.showErrorMessage("Error", "No existe un parqueadero.");
        }
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

    public void consultTicket(String plate){
        view.showGenerateExitTicketPanel(plate, modelSystem.searchCost(plate));
    }

    public void modifyReceptionist(String id, String newId, String password){
        if(modelSystem.editReceptionist(id, newId, password)){
            view.showErrorMessage("Error", "No se pudo modificar el recepcionista.");
        }else{
            view.showSimpleMessage("Modificación Exitosa", "El correo para facilitar el uso de las nuevas credenciales para el recepcionista se ha enviado.\n"+modelSystem.getReceptionistInfo(newId));
        }
    }

    public void generateReport(LocalDate date){
        view.createReportPanel(date);
    }
    public String[] generateParkingReport(LocalDate date){ 
        return modelSystem.generateParkingReport(date);
    }

    public String[][] generateReceptionistReport(LocalDate date){  
        return modelSystem.generateReceptionistReport(date);
    }

    public int getFreeSpaces(){
        return modelSystem.getFreeParkingSpaces();
    }

}
