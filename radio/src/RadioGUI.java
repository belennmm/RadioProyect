import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioGUI {
    private JFrame frame;
    private CarRadio carRadio;
    private JLabel labelStatus, labelCurrentStation;
    private JButton btnOnOff, btnAMFM, btnNextStation, btnSaveStation, btnSelectStation;

    public JFrame getFrame() {
        return frame;
    }


    public RadioGUI() {
        carRadio = new CarRadio();
        initialize();
        
    }

    private void initialize() {

        frame = new JFrame("Car Radio Simulator");
        frame.setBounds(100, 100, 800, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        labelStatus = new JLabel("Radio Apagado");
        frame.add(labelStatus);

        // Botón Encender/Apagar
        btnOnOff = new JButton("Encender/Apagar");
        btnOnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRadio.switchOnOff();
                updateRadioStatus();
            }
        });
        frame.add(btnOnOff);

        // Botón Cambiar AM/FM
        btnAMFM = new JButton("Cambiar AM/FM");
        btnAMFM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRadio.switchAMFM();
                updateRadioStatus();
            }
        });
        frame.add(btnAMFM);

        // Botón Siguiente Estación
        btnNextStation = new JButton("Siguiente Estación");
        btnNextStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRadio.nextStation();
                updateRadioStatus();
            }
        });
        frame.add(btnNextStation);

        // Botón para guardar una estación
        btnSaveStation = new JButton("Guardar Estación");
        btnSaveStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonIdStr = JOptionPane.showInputDialog("Ingrese el número de botón (1-12) para guardar:");
                if (buttonIdStr != null && !buttonIdStr.trim().isEmpty()) {
                    try {
                        int buttonId = Integer.parseInt(buttonIdStr);
                        String stationStr = JOptionPane.showInputDialog("Ingrese la frecuencia de la estación:");
                        if (stationStr != null && !stationStr.trim().isEmpty()) {
                            double station = Double.parseDouble(stationStr);
                            carRadio.saveStation(buttonId, station);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Entrada inválida.");
                    }
                }
            }
        });
        frame.add(btnSaveStation);

        // Botón para seleccionar una estación guardada
        btnSelectStation = new JButton("Seleccionar Estación Guardada");
        btnSelectStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonIdStr = JOptionPane.showInputDialog("Ingrese el número de botón (1-12) para seleccionar:");
                if (buttonIdStr != null && !buttonIdStr.trim().isEmpty()) {
                    try {
                        int buttonId = Integer.parseInt(buttonIdStr);
                        double station = carRadio.selectStation(buttonId);
                        if (station != 0.0) {
                            String formattedStation = String.format("%.1f", station);
                            labelCurrentStation.setText("Estación actual: " + formattedStation);
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay estación guardada en este botón.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Entrada inválida.");
                    }
                }
            }
        });
        frame.add(btnSelectStation);

        // Etiqueta para mostrar la estación actual
        labelCurrentStation = new JLabel("Estación actual: ");
        frame.add(labelCurrentStation);

        frame.setVisible(true);
    }

    private void updateRadioStatus() {
        if (carRadio.isOn()) {
            labelStatus.setText("Radio Encendido - " + (carRadio.isAM() ? "AM" : "FM"));
            String formattedStation = String.format("%.1f", carRadio.getCurrentStation());
            labelCurrentStation.setText("Estación actual: " + formattedStation);
        } else {
            labelStatus.setText("Radio Apagado");
            labelCurrentStation.setText("Estación actual: ");
        }
    }

    

    // Método main para ejecutar la GUI
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RadioGUI window = new RadioGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}