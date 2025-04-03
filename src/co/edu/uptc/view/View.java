package co.edu.uptc.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import co.edu.uptc.presenter.Presenter;

import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener{

    private JPanel loginPanel;
    private JPanel receptionistMenuPanel;
    private JPanel adminMenuPanel;
    private JPanel sectionPanel;
    private JOptionPane jOptionPane;

    public View(){
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());
        jOptionPane = new JOptionPane();
        createLoginPanel();
        createSectionPanel();
        createAdminMenuPanel();
        createReceptionistMenuPanel();
        //JTextField textField = new JTextField();
        //textField.setText(regex);
        //JButton button = new JButton("ingresar[action]");
        //ClaseEsa.add(LoginPanel);

        setVisible(true);
    }

    private void createSectionPanel(){
        sectionPanel = new JPanel();
        sectionPanel.setLayout(new CardLayout());
        sectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
    }

    private void createLoginPanel(){
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        loginPanel.add(new JLabel("Señor ingrese su NIT y contraseña para Ingresar al sistema"), config);

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("NIT"), config);

        config.gridx = 1;
        config.gridy = 1;
        config.anchor = GridBagConstraints.LINE_START;
        JTextField idField = new JTextField(20);
        loginPanel.add(idField, config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Contraseña"), config);

        config.gridx = 1;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, config);

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton("Ingresar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Presenter.getInstance().login(idField.getText(), passwordField.getText());
               idField.setText("");
               passwordField.setText("");
            }
        });
        loginPanel.add(button, config);

        getContentPane().add(loginPanel, "Login Panel");
    }

    private void createReceptionistMenuPanel(){
        loginPanel.setVisible(false);
        receptionistMenuPanel = new JPanel();
        receptionistMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton vehicleEntryButton = new JButton("Ingreso de vehiculo");
        vehicleEntryButton.addActionListener(this);
        JButton vehicleExitButton = new JButton("Salida de vehículo");
        vehicleEntryButton.addActionListener(this);
        JButton disponibleSpaces = new JButton("Espacios disponibles");
        disponibleSpaces.addActionListener(this);
        JButton logOutButton = new JButton("Cerrar sesión");
        logOutButton.addActionListener(this);
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
        buttonBar.add(vehicleEntryButton);
        buttonBar.add(vehicleExitButton);
        buttonBar.add(disponibleSpaces);
        buttonBar.add(logOutButton);

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        receptionistMenuPanel.add(buttonBar, config);
        
        config.gridy = 1;
        config.weightx = 1;
        config.weighty = 1;
        config.fill = GridBagConstraints.BOTH;
        config.insets = new Insets(5, 10, 10, 10);

        createInitReceptionistPanel("Daniel");
        createVehicleEntryPanel(213123);
        receptionistMenuPanel.add(sectionPanel, config);
        getContentPane().add(receptionistMenuPanel, "Receptionist Panel");
    }

    private void createInitReceptionistPanel(String name){
        JPanel initPanel = new JPanel();
        initPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Recepcionista: "+name+", Bienvenido al sistema de ParkingUPTC!"), config);

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Señor/a usuario, selecciona alguna de las opciones del menú"), config);

        sectionPanel.add(initPanel, "Init Panel");
    }

    private void createVehicleEntryPanel(int disponibleSpaces){
        JPanel vehicleEntryPanel = new JPanel();
        vehicleEntryPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new JLabel("Plazas disponibles: "+ disponibleSpaces));

        config.gridy = 1;
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new JLabel("Digite la placa del vehículo que ingresa: "), config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new TextField(20), config);

        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        JButton generateTicketButton = new JButton("Generar ticket");
        generateTicketButton.addActionListener(this);
        vehicleEntryPanel.add(generateTicketButton, config);

        sectionPanel.add(vehicleEntryPanel, "Vehicle Entry Panel");
    }

    private void createGenerateEntryTicketPanel(String parkingName, String date, String vehicle, String entryHour){
        JPanel generateEntryTicketPanel = new JPanel();
        generateEntryTicketPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        generateEntryTicketPanel.add(new JLabel("Se ha registrado el vehículo con éxito"), config);

        JPanel entryTicketPanel = new JPanel();
        entryTicketPanel.setLayout(new GridBagLayout());
        entryTicketPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel(""+parkingName), config);

        entryTicketPanel.setLayout(new GridBagLayout());
        config.gridy = 1;
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel("Fecha: "+ date), config);

        config.gridy = 2;
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel("Vehículo: "+ vehicle), config);

        config.gridy = 3;
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel("Hora: "+ entryHour), config);
        
        config.gridy = 1;
        config.gridwidth = 2;
        generateEntryTicketPanel.add(entryTicketPanel, config);

        config.gridy = 2;
        config.anchor = GridBagConstraints.CENTER;
        config.gridwidth = 1;
        JButton printTicketButton = new JButton("Imprimir ticket");
        printTicketButton.addActionListener(this);
        generateEntryTicketPanel.add(printTicketButton, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.CENTER;
        JButton anotherVehicleButton= new JButton("Ingresar otro vehículo");
        anotherVehicleButton.addActionListener(this);
        generateEntryTicketPanel.add(anotherVehicleButton, config);

        sectionPanel.add(generateEntryTicketPanel, "Generate Entry Ticket Panel");
    }

    private void createVehicleExitPanel(){

    }

    private void createDisponibleSpacesPanel(){

    }

    private void createAdminMenuPanel(){
        adminMenuPanel = new JPanel();
        adminMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton registParkingButton = new JButton("Registrar parqueadero, Generar reporte de ventas, Cerrar sesión");
        JButton createReceptionistButton = new JButton("Crear recepcionista");
        JButton modifyReceptionistButton = new JButton("Modificar recepcionista");
        JButton generateSalesReportButton = new JButton("Generar reporte de ventas");
        JButton logOutButton = new JButton("Cerrar sesión");
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
        buttonBar.add(registParkingButton);
        buttonBar.add(createReceptionistButton);
        buttonBar.add(modifyReceptionistButton);
        buttonBar.add(generateSalesReportButton);
        buttonBar.add(logOutButton);

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        adminMenuPanel.add(buttonBar, config);

        config.gridy = 1;
        config.weightx = 1;
        config.weighty = 1;
        config.fill = GridBagConstraints.BOTH;
        config.insets = new Insets(5, 10, 10, 10);

        adminMenuPanel.add(sectionPanel, config);

        getContentPane().add(adminMenuPanel, "Admin Panel");
    }

    public ArrayList<String> getInputs() {
        return null;
    }

    public void showLoginPanel(){
        
    }

    public void showReceptionistMenu(){
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Receptionist Panel");
    }

    public void showAdminMenu(){
        
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Receptionist Panel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component component: loginPanel.getComponents()) {
            if (component instanceof JButton) {
                if (((JButton)(e.getSource())).getText()=="Ingresar") {
                    createReceptionistMenuPanel();
                    createAdminMenuPanel();
                    showReceptionistMenu();
                }else{
                    continue;
                }
            }
        }
        for (Component component : receptionistMenuPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component component2 : ((JPanel)(component)).getComponents()) {
                    if (component2 instanceof JButton) {
                        if (((JButton)(e.getSource())).getText()=="Ingreso de vehiculo") {
                            createVehicleEntryPanel(213123);
                            ((CardLayout)(sectionPanel).getLayout()).show(sectionPanel, "Vehicle Entry Panel");
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                    }
                }
            }
        }
        for (Component component : adminMenuPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component component2 : ((JPanel)(component)).getComponents()) {
                    if (component2 instanceof JButton) {
                        if (((JButton)(e.getSource())).getText()=="") {
                            
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                    }
                }
            }
        }
        for (Component component : sectionPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component component2 : ((JPanel)(component)).getComponents()) {
                    if (component2 instanceof JButton) {
                        if (((JButton)(e.getSource())).getText()=="Generar ticket") {
                            createGenerateEntryTicketPanel("Parking", "18/04/2025", "ADC123", "3:40");
                            ((CardLayout)(sectionPanel).getLayout()).show(sectionPanel, "Generate Entry Ticket Panel");
                        }
                        if (((JButton)(e.getSource())).getText()=="Imprimir ticket") {
                            JOptionPane.showMessageDialog(sectionPanel, "El ticket se ha impreso con exito");
                        }
                        if (((JButton)(e.getSource())).getText()=="Ingresar otro vehículo") {
                            ((CardLayout)(sectionPanel).getLayout()).show(sectionPanel, "Vehicle Entry Panel");
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }else{
                            continue;
                        }
                    }
                }
        }
    }
}
}
