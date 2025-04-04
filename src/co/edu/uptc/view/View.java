package co.edu.uptc.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import co.edu.uptc.presenter.Presenter;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{

    private JPanel loginPanel;
    private JPanel receptionistMenuPanel;
    private JPanel adminMenuPanel;
    private JPanel receptionistSectionPanel;
    private JPanel adminSectionPanel;
    private JOptionPane jOptionPane;

    public View(){
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());
        jOptionPane = new JOptionPane();
        createLoginPanel();
        createReceptionistSectionPanel();
        createAdminMenuPanel();
        createReceptionistMenuPanel();
        //JTextField textField = new JTextField();
        //textField.setText(regex);
        //JButton button = new JButton("ingresar[action]");
        //ClaseEsa.add(LoginPanel);

        setVisible(true);
    }

    private void createReceptionistSectionPanel(){
        receptionistSectionPanel = new JPanel();
        receptionistSectionPanel.setLayout(new CardLayout());
        receptionistSectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
    }

    private void createAdminSectionPanel(){
        adminSectionPanel = new JPanel();
        adminSectionPanel.setLayout(new CardLayout());
        adminSectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
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
        idField.setText("[0-9]+");
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
        ButtonSummitControl loginSummitControl = new ButtonSummitControl(button, loginPanel);
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
        vehicleEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Entry Panel");
            }
        });
        JButton vehicleExitButton = new JButton("Salida de vehículo");
        vehicleExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Exit Panel");
            }
        });
        JButton disponibleSpaces = new JButton("Espacios disponibles");
        disponibleSpaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Disponible Spaces Panel");
            }
        });
        JButton logOutButton = new JButton("Cerrar sesión");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(getContentPane(), "¿Estás seguro de que deseas cerrar sesión?")==JOptionPane.YES_OPTION){
                    ((CardLayout)(getContentPane()).getLayout()).show(getContentPane(), "Login Panel");  
                }
            }
        });
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
        createExitVehiclePanel();
        createDisponibleSpacesPanel();
        receptionistMenuPanel.add(receptionistSectionPanel, config);
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

        receptionistSectionPanel.add(initPanel, "Init Panel");
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
        vehicleEntryPanel.add(new JTextField(20), config);

        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        JButton generateTicketButton = new JButton("Generar ticket");
        ButtonSummitControl loginSummitControl = new ButtonSummitControl(generateTicketButton, vehicleEntryPanel);
        generateTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Presenter.getInstance().generateTicket("ABC123");
            }
        });
        vehicleEntryPanel.add(generateTicketButton, config);

        receptionistSectionPanel.add(vehicleEntryPanel, "Vehicle Entry Panel");
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
        printTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Generate Entry Ticket Panel");
            }
        });
        generateEntryTicketPanel.add(printTicketButton, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.CENTER;
        JButton anotherVehicleButton= new JButton("Ingresar otro vehículo");
        anotherVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Disponible Spaces Panel");
            }
        });
        generateEntryTicketPanel.add(anotherVehicleButton, config);
        receptionistSectionPanel.add(generateEntryTicketPanel, "Generate Entry Ticket Panel");
    }

    private void createExitVehiclePanel() {
        JPanel exitVehiclePanel = new JPanel();
        exitVehiclePanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        exitVehiclePanel.add(new JLabel("Digite la placa del vehículo que desea salir: "), config);
    
        config.gridy = 1;
        JTextField plateField = new JTextField(20);
        exitVehiclePanel.add(plateField, config);
    
        config.gridy = 2;
        JButton consultButton = new JButton("Consultar");
        consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Presenter.getInstance().consultTicket(plateField.getText());
            }
        });
        exitVehiclePanel.add(consultButton, config);
    
        receptionistSectionPanel.add(exitVehiclePanel, "Vehicle Exit Panel");
    }

    private void createConsultResultTicketPanel(String plate, double amount) {
        JPanel consultResultTicketPanel = new JPanel();
        consultResultTicketPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        consultResultTicketPanel.add(new JLabel("El valor a pagar por el vehículo " + plate + " es: $" + amount), config);
    
        config.gridy = 1;
        config.gridwidth = 1;
        JTextField receivedAmountField = new JTextField(10);
        consultResultTicketPanel.add(new JLabel("Valor recibido"), config);
        
        config.gridx = 1;
        consultResultTicketPanel.add(receivedAmountField, config);
    
        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 2;
        consultResultTicketPanel.add(new JLabel("El método de pago solo debe ser en efectivo"), config);
    
        config.gridy = 3;
        config.gridwidth = 1;
        JButton correctPlateButton = new JButton("Corregir Placa");
        consultResultTicketPanel.add(correctPlateButton, config);

        config.gridx = 1;
        JButton calculateAmountButton = new JButton("Calcular monto");
        consultResultTicketPanel.add(calculateAmountButton, config);
    
        receptionistSectionPanel.add(consultResultTicketPanel, "Generate Exit Ticket Panel");
    }

    private void createGenerateExitTicketPanel(String plate, double amount) {
        JPanel generateExitTicketPanel = new JPanel();
        generateExitTicketPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        generateExitTicketPanel.add(new JLabel("El valor a pagar por el vehículo " + plate + " es: $" + amount), config);
    
        config.gridy = 1;
        config.gridwidth = 1;
        generateExitTicketPanel.add(new JLabel("Valor recibido: "), config);
    
        config.gridx = 1;
        JTextField receivedAmountField = new JTextField(10);
        generateExitTicketPanel.add(receivedAmountField, config);

        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 2;
        generateExitTicketPanel.add(new JLabel("Cambio: $0.00"), config); 

        config.gridy = 3;
        config.gridwidth = 1;
        JButton anotherVehicleButton = new JButton("Salida de otro Vehículo");
        generateExitTicketPanel.add(anotherVehicleButton, config);
    
        config.gridx = 1;
        JButton generateReceiptButton = new JButton("Generar Recibo");
        generateExitTicketPanel.add(generateReceiptButton, config);
    
        receptionistSectionPanel.add(generateExitTicketPanel, "Generate Exit Ticket Panel");
    }

    private void createExitTicketPanel(String plate, String date, String entryTime, String exitTime, double amount, double receivedAmount, double change) {
        JPanel exitTicketReceiptPanel = new JPanel();
        exitTicketReceiptPanel.setLayout(new GridBagLayout());
        exitTicketReceiptPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        exitTicketReceiptPanel.add(new JLabel("Nombre Parqueadero"), config);

        config.gridy = 1;
        exitTicketReceiptPanel.add(new JLabel("Vehículo: " + plate + "      Fecha: " + date), config);

        config.gridy = 2;
        exitTicketReceiptPanel.add(new JLabel("Hora de entrada: " + entryTime + "      Hora de salida: " + exitTime), config);

        config.gridy = 3;
        exitTicketReceiptPanel.add(new JLabel("Costo: $" + amount + "      Cambio: $" + change), config);

        config.gridy = 4;
        exitTicketReceiptPanel.add(new JLabel("Valor recibido: $" + receivedAmount), config);

        config.gridy = 5;
        exitTicketReceiptPanel.add(new JLabel("¡Tenga un buen viaje!"), config);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        config.gridy = 0;
        config.gridwidth = 1;
    
        JButton printReceiptButton = new JButton("Imprimir Recibo");
        buttonsPanel.add(printReceiptButton, config);
    
        config.gridx = 1;
        JButton anotherVehicleButton = new JButton("Salida de otro Vehículo");
        buttonsPanel.add(anotherVehicleButton, config);
        receptionistSectionPanel.add(buttonsPanel, "Exit Ticket Buttons Panel");

        receptionistSectionPanel.add(exitTicketReceiptPanel, "Exit Ticket Receipt Panel");
    }

    private void createDisponibleSpacesPanel(){

    }

    //UwU
    private void createAdminMenuPanel(){
        adminMenuPanel = new JPanel();
        adminMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton registParkingButton = new JButton("Registrar parqueadero");
        registParkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Register Parking Panel");
            }
        });
        JButton createReceptionistButton = new JButton("Crear recepcionista");
        createReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Register Receptionist Panel");
            }
        });
        JButton modifyReceptionistButton = new JButton("Modificar recepcionista");
        modifyReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Edit Receptionist Panel");
                
            }
        });
        JButton generateSalesReportButton = new JButton("Generar reporte de ventas");
        generateSalesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Generate Sales Report Panel");
            }
        });
        JButton logOutButton = new JButton("Cerrar sesión");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(getContentPane(), "¿Estás seguro de que deseas cerrar sesión?")==JOptionPane.YES_OPTION){
                    ((CardLayout)(getContentPane()).getLayout()).show(getContentPane(), "Login Panel");  
                }
            }
        });
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

        createAdminSectionPanel();
        createRegisterParkingPanel();
        createRegisterRecepcionistPanel();
        createEditReceptionistPanel();
        adminMenuPanel.add(adminSectionPanel, config);

        getContentPane().add(adminMenuPanel, "Admin Panel");
    }

    private void createInitAdminPanel(String name){
        JPanel initPanel = new JPanel();
        initPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Administrador: "+name+", Bienvenido al sistema de ParkingUPTC!"), config);

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Señor/a usuario, selecciona alguna de las opciones del menú"), config);

        adminSectionPanel.add(initPanel, "Init Panel");
    }

    public void createRegisterParkingPanel() {
        JPanel registerParkingPanel = new JPanel();
        registerParkingPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel parkingNameLabel = new JLabel("Nombre de parqueadero:");
        JTextField parkingNameField = new JTextField(20);
        config.gridx = 0;
        config.gridy = 0;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(parkingNameLabel, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(parkingNameField, config);

        JLabel addressLabel = new JLabel("Dirección:");
        JTextField addressField = new JTextField(20);
        config.gridx = 0;
        config.gridy = 1;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(addressLabel, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(addressField, config);

        JLabel spacesLabel = new JLabel("Número de espacios:");
        JTextField spacesField = new JTextField(20);
        spacesField.setText("[0-9]+");
        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(spacesLabel, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(spacesField, config);

        config.gridy = 3;
        config.gridx = 0;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(new JLabel("Día"), config);
        
        String[] weekDays = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Festivo"};
        JComboBox<String> dayComboBox = new JComboBox<>(weekDays);
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(dayComboBox, config);

        ArrayList<Component> restrictComponents = new ArrayList<Component>();

        config.gridy++;
        config.gridx = 0;
        registerParkingPanel.add(new JLabel("Hora de apertura"), config);
        JTextField openingHour = new JTextField(10);
        openingHour.setText("([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]");
        config.gridx = 1;
        registerParkingPanel.add(openingHour, config);
        restrictComponents.add(openingHour);
        
        config.gridy++;
        config.gridx = 0;
        registerParkingPanel.add(new JLabel("Hora de cierre"), config);
        JTextField closingHour = new JTextField(10);
        closingHour.setText("([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]");
        config.gridx = 1;
        registerParkingPanel.add(closingHour, config);
        restrictComponents.add(closingHour);

        config.fill = GridBagConstraints.HORIZONTAL;
        config.gridy = 7;
        config.gridx = 0;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;

        String[] columnNames = {"Día", "Fecha de apertura", "Fecha de cierre"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        registerParkingPanel.add(scrollPane, config);

        config.gridy = 6;
        config.fill = GridBagConstraints.CENTER;
        JButton saveConfigButton = new JButton("Guardar configuración");
        ButtonSummitControl saveConfigSummitControl = new ButtonSummitControl(saveConfigButton, restrictComponents);
        saveConfigButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                for(int i = 0; i < tableModel.getRowCount(); i++){
                    if (tableModel.getValueAt(i, 0).equals((String)dayComboBox.getSelectedItem())) 
                        isValid = false;
                }
                if(isValid){
                    tableModel.addRow(new String[]{(String)dayComboBox.getSelectedItem(), openingHour.getText(), closingHour.getText()});  
                }
            }
        });
        registerParkingPanel.add(saveConfigButton, config);
        
        config.fill = GridBagConstraints.CENTER;
        config.gridy = 8;
        JButton inputButton = new JButton("Ingresar");
        ButtonSummitControl inputSummitControl = new ButtonSummitControl(inputButton, registerParkingPanel);
        inputButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> scheduleList = new ArrayList<String>();
                for(int i = 0; i < tableModel.getRowCount(); i++){
                    scheduleList.add(tableModel.getValueAt(i, 0).toString()+"-"+tableModel.getValueAt(i, 1).toString()+"-"+tableModel.getValueAt(i, 2).toString());
                }
                if (tableModel.getRowCount()>=1) {
                    if(Presenter.getInstance().registerParking(parkingNameField.getText(), addressField.getText(), Integer.parseInt(spacesField.getText()), scheduleList)){
                        showSimpleMessage("Parqueadero registrado", "El parqueadero ha sido registrado con éxito.\n "+Presenter.getInstance().getParkingInfo());
                        tableModel.setRowCount(0);
                        parkingNameField.setText("");
                        addressField.setText("");    
                        spacesField.setText("");
                        openingHour.setText("");
                        closingHour.setText("");
                    }else{
                        showErrorMessage("Error", "No se pudo registrar el parqueadero.");
                    }
                }else{
                    showErrorMessage("Error", "Debe ingresar al menos 1 días de apertura y cierre.");
                }
            }
        });
        registerParkingPanel.add(inputButton, config);
    
        adminSectionPanel.add(registerParkingPanel, "Register Parking Panel");
    }

    public void createRegisterRecepcionistPanel() {
        JPanel registerReceptionistPanel = new JPanel();
        registerReceptionistPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.HORIZONTAL;

        config.gridx = 0;
        config.gridy = 0;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel documentLabel = new JLabel("Documento de identidad:");
        registerReceptionistPanel.add(documentLabel, config);
        
        JTextField documentField = new JTextField(20);
        documentField.setText("[0-9]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(documentField, config);

        config.gridx = 0;
        config.gridy = 1;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel firstNameLabel = new JLabel("Nombres:");
        registerReceptionistPanel.add(firstNameLabel, config);
        
        JTextField firstNameField = new JTextField(20);
        firstNameField.setText("[a-zA-Z]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(firstNameField, config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel lastNameLabel = new JLabel("Apellidos:");
        registerReceptionistPanel.add(lastNameLabel, config);
        
        JTextField lastNameField = new JTextField(20);
        lastNameField.setText("[a-zA-Z]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(lastNameField, config);

        config.gridx = 0;
        config.gridy = 3;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel phoneLabel = new JLabel("Número de teléfono:");
        registerReceptionistPanel.add(phoneLabel, config);
        
        JTextField phoneField = new JTextField(20);
        phoneField.setText("[0-9]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(phoneField, config);

        config.gridx = 0;
        config.gridy = 4;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel addressLabel = new JLabel("Dirección:");
        registerReceptionistPanel.add(addressLabel, config);
        
        JTextField addressField = new JTextField(20);
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(addressField, config);

        config.gridx = 0;
        config.gridy = 5;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel emailLabel = new JLabel("E-Mail:");
        registerReceptionistPanel.add(emailLabel, config);
        
        JTextField emailField = new JTextField(20);
        emailField.setText("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(emailField, config);
    
        config.gridx = 0;
        config.gridy = 6;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        config.fill = GridBagConstraints.CENTER;

        JButton inputButton = new JButton("Ingresar");
        ButtonSummitControl inputSummitControl = new ButtonSummitControl(inputButton, registerReceptionistPanel);
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Presenter.getInstance().registerReceptionist(documentField.getText(), emailField.getText(), firstNameField.getText(), lastNameField.getText(), phoneField.getText(), addressField.getText())){
                    showSimpleMessage("Registro Exitoso", "El recepcionista ha sido registrado con éxito.\n"+Presenter.getInstance().getReceptionistInfo(documentField.getText()));
                    documentField.setText("");
                    emailField.setText("");
                    firstNameField.setText("");
                    lastNameField.setText("");
                    phoneField.setText("");
                    addressField.setText("");
                }else{
                    showErrorMessage("Error", "No se pudo registrar el recepcionista.");
                }
            }
        });
        registerReceptionistPanel.add(inputButton, config);
    
        adminSectionPanel.add(registerReceptionistPanel, "Register Receptionist Panel");
    }

    public void createEditReceptionistPanel() {
        JPanel editReceptionistPanel = new JPanel();
        editReceptionistPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.HORIZONTAL;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        JLabel selectLabel = new JLabel("Seleccione el recepcionista:");
        editReceptionistPanel.add(selectLabel, config);
    
        config.gridx = 0;
        config.gridy = 3;
        editReceptionistPanel.add(new JSeparator(), config);
        
        config.gridy = 4;
        JLabel modifyLabel = new JLabel("Digite los datos a modificar del recepcionista: ");
        editReceptionistPanel.add(modifyLabel, config);
        
        config.gridy = 1;
        JList<String> receptionistDropdown = new JList<>(new String[]{"ingrese un nombre"});
        receptionistDropdown.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modifyLabel.setText("Digite los datos a modificar del recepcionista ("+receptionistDropdown.getSelectedValue()+"): ");
            }
            
        });
        receptionistDropdown.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(receptionistDropdown);
        scrollPane.setPreferredSize(new Dimension(200, 70));
        editReceptionistPanel.add(scrollPane, config);

        config.gridy = 2;
        config.gridwidth = 1;
        config.gridx = 0;
        config.anchor = GridBagConstraints.LINE_END;
        editReceptionistPanel.add(new JLabel("Nombre del recepcionista:"), config);
    
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        JTextField nameField = new JTextField(20);
        nameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void onChange(DocumentEvent e) {
                uptdateList(nameField.getText(), receptionistDropdown);
            }
        });
        editReceptionistPanel.add(nameField, config);

        config.gridy = 5;
        config.gridx = 0;
        config.gridwidth = 1;
        config.anchor = GridBagConstraints.LINE_END;
        editReceptionistPanel.add(new JLabel("Documento de identidad:"), config);
    
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        JTextField documentField = new JTextField(20);
        documentField.setText("[0-9]+");
        editReceptionistPanel.add(documentField, config);

        config.gridy = 6;
        config.gridx = 0;
        editReceptionistPanel.add(new JLabel("Contraseña:"), config);
    
        config.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setText(".{8,}");
        editReceptionistPanel.add(passwordField, config);
        
        config.gridy = 7;
        config.gridx = 0;
        editReceptionistPanel.add(new JLabel("Confirmar contraseña:"), config);
        
        config.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setText(".{8,}");
        editReceptionistPanel.add(confirmPasswordField, config);
    
        config.gridy = 8;
        config.gridx = 0;
        config.gridwidth = 2;
        JLabel passwordNote = new JLabel("<html><font color='red'>Tenga en cuenta que la nueva contraseña no debe ser repetida ni tener caracteres especiales y debe ser de 8 dígitos.</font></html>");
        editReceptionistPanel.add(passwordNote, config);

        config.gridy = 9;
        config.anchor = GridBagConstraints.CENTER;
        JButton acceptButton = new JButton("Aceptar");
        ButtonSummitControl acceptSummitControl = new ButtonSummitControl(acceptButton, editReceptionistPanel);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().equals(confirmPasswordField.getText())) {
                    try{
                        String[] trim = receptionistDropdown.getSelectedValue().split(":");
                        Presenter.getInstance().modifyReceptionist(trim[1], documentField.getText(), passwordField.getText());
                    }catch (PatternSyntaxException ex){
                        showErrorMessage("Error", "Debe escoger un recepcionista.");
                    }catch (NullPointerException ex){
                        showErrorMessage("Error", "Debe escoger un recepcionista.");
                    }
                }else{
                    showErrorMessage("Error", "Las contraseñas no coinciden.");
                }
            }
            
        });
        editReceptionistPanel.add(acceptButton, config);
    
        adminSectionPanel.add(editReceptionistPanel, "Edit Receptionist Panel");
    }
    
    public void showLoginPanel(){
        
    }

    public void showReceptionistMenu(String name){
        ((CardLayout)(adminSectionPanel.getLayout())).show(adminSectionPanel, "Init Panel");
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Receptionist Panel");
    }



    public void showVehicleEntryPanel(){
        ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Vehicle Entry Panel");
    }

    public void showGenerateEntryTicketPanel(String parkingName, String date, String vehicle, String entryHour){
        createGenerateEntryTicketPanel(parkingName, date, vehicle, entryHour);
        ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Generate Entry Ticket Panel");
    }

    public void showGenerateExitTicketPanel(String date, double cost){
        createGenerateExitTicketPanel(date, cost);
        ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Generate Exit Ticket Panel");
    }

    public void showErrorMessage(String title, String message){
        JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showSimpleMessage(String title, String message){
        JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAdminMenu(String name){
        createInitAdminPanel(name);
        ((CardLayout)(adminSectionPanel.getLayout())).show(adminSectionPanel, "Init Panel");
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Admin Panel");
    }
    
    private void uptdateList(String name, JList<String> jList) {
        ArrayList<String> results = new ArrayList<String>();
        for (String n : Presenter.getInstance().getReceptionistList()) {
            boolean match = true;
            if (name.length() <= n.length()) {
                for(int i = 0; i < name.length() && i<n.length() && match; i++) {
                    if (n.toLowerCase().charAt(i) != name.toLowerCase().charAt(i)) 
                        match = false;
                }    
                if (match) 
                    results.add(n);
            }
        }
        if (results.size() == 0){
            jList.setListData(new String[]{"No se encontraron recepcionistas con ese nombre."});
            jList.setEnabled(false);
        }else {
            String[] resultsArray = new String[results.size()];
            for (int i = 0; i < results.size(); i++)
                resultsArray[i] = results.get(i);
            jList.setEnabled(true);
            jList.setListData(resultsArray);
        }
    }


}
