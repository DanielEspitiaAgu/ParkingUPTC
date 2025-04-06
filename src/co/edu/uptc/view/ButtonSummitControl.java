

package co.edu.uptc.view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import java.awt.Color;

public class ButtonSummitControl  {
    JButton button;
    ArrayList<JComponent> components;
    ArrayList<JComponent> readyComponents;
    ArrayList<String> componentsRegex;

    public ButtonSummitControl(JButton button, JPanel panel) {
        this.button = button;
        button.setEnabled(false);
        components = new ArrayList<JComponent>();
        componentsRegex = new ArrayList<String>();
        readyComponents = new ArrayList<JComponent>();
        
        getRestrictions(panel.getComponents());
    }                     

    public ButtonSummitControl(JButton button, ArrayList<Component> restrictComponents) {
        this.button = button;
        button.setEnabled(false);
        components = new ArrayList<JComponent>();
        componentsRegex = new ArrayList<String>();
        readyComponents = new ArrayList<JComponent>();
        
        Component[] componentsArray = restrictComponents.toArray(new Component[restrictComponents.size()]);
        getRestrictions(componentsArray);
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public ArrayList<JComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<JComponent> components) {
        this.components = components;
    }

    public ArrayList<JComponent> getRedyComponents() {
        return readyComponents;
    }

    public void setRedyComponents(ArrayList<JComponent> readyComponents) {
        this.readyComponents = readyComponents;
    }

    public ArrayList<String> getComponentsRegex() {
        return componentsRegex;
    }

    public void setComponentsRegex(ArrayList<String> componentsRestrictions) {
        this.componentsRegex = componentsRestrictions;
    }

    private void getRestrictions(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                asignListeners((JTextField) component);
            }
            if (component instanceof JPasswordField) {
                asignListeners((JPasswordField) component);
            }
        }
        checkRestrictions();
    }

    private void asignListeners(JTextField field) {
        if (field.getText().isEmpty())
            return;
        componentsRegex.add(field.getText());
        components.add(field);
        field.setText("");
        field.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void onChange(DocumentEvent e) {
                int index = components.indexOf(field);
                if (index >= 0 && field.getText().matches(componentsRegex.get(index))) {
                    if (!readyComponents.contains(field)) {
                        readyComponents.add(field);
                        field.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.WHITE));
                    }
                } else {
                    readyComponents.remove(field);
                    field.setBorder(BorderFactory.createEtchedBorder(Color.RED,Color.RED));
                }
                checkRestrictions();
            }
        });
    }

    private void checkRestrictions() {
        if(readyComponents.size() == components.size()){
            button.setEnabled(true);
        }
        else{
            button.setEnabled(false); 
        }
    }
}