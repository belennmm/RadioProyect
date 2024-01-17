import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

/**
 * Representa la Vista del programa.
 */
public class  Vista extends  Frame  implements ActionListener{

    private Radio radio;
 
    
    private Button  on_offBut;
    private Button  am_fmButt;
    private Button  nextStationBut;
    private Button[ ] estacionesButt ;
    private Button saveB ;
    private Label estadoRadio ;


    /**
     * Vista de la interfaz con la que el usuario interactúa.
     * @param radio
     */
    public Vista( Radio  radio){
        this.radio =  radio;

        this.listaPrueba  = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        setLayout (new  GridBagLayout() );
        GridBagConstraints espacio=new GridBagConstraints();

        espacio.insets = new Insets(5, 0, 5, 0);

        // para que los botones se encuentren juntos horizontalmente
        espacio.fill = GridBagConstraints.HORIZONTAL;


        // botón de On/Off
        on_offBut = new Button(" O ");
        customizeButton( on_offBut);
        espacio.gridx=  2;
        espacio.gridy =  0;
        espacio.gridwidth= 1 ;
        add(on_offBut , espacio) ;

        // botón am/fm
        am_fmButt =new Button("AM/FM");
        customizeButton(am_fmButt);
        espacio.gridx= 3;
        espacio.gridy =  0;
        espacio.gridwidth= 1;
        add(am_fmButt , espacio);

        // botones para 12 estaciones
        estacionesButt = new Button[12];

        for(int count=0 ; count<estacionesButt.length;  count++){
            // se establecen los botones y el espacio en donde se ubicarán
            estacionesButt[ count] =new Button("" +(count +1) ) ;

            customizeButton( estacionesButt[count]);
            espacio.gridx = count % 6; // para que en cada fila queden 6 estaciones
            espacio.gridy= 1 + count / 6;
            espacio.gridwidth = 1;
    
            add(estacionesButt[ count], espacio);
        }

        // botón para guardar estaciones
        saveB = new Button("⌧");
        customizeButton(saveB);
        espacio.gridx= 2;
        espacio.gridy =  3;
        espacio.gridwidth= 1;
        add(saveB , espacio) ;

        // botón siguiente
        nextStationBut = new Button(" >> ");
        customizeButton(nextStationBut);
        espacio.gridx= 3;
        espacio.gridy =  3;
        espacio.gridwidth= 1;
        add(nextStationBut , espacio) ;

        // para mostrar el estado del radio 

        estadoRadio= new Label("Status:                    ");

        estadoRadio= new Label("Status:                            ");

        espacio.gridx= 0;
        espacio.gridy =  4;
        espacio.gridwidth= 7;
        espacio.insets = new Insets(10, -52, 0, 0);
        add(estadoRadio , espacio) ;

        radioLabel(estadoRadio);

        //Caracgterísticas de la pantalla
        setTitle("Radio");
        setSize(700, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setBackground(Color.decode("#ffebc9"));
    }

    // carcterísticas de los botones 
    private void customizeButton( Button button){
        button.setFont(new  Font("Monospaced" , Font.PLAIN, 20));
        button.setBackground(Color.lightGray);
        button.addActionListener(this );

        // cambiar las caracterísitcas del botón on/off
        if(button==on_offBut){
            button.setBackground( Color.decode("#F36161") ); 
            button.setFont(new Font("Monospaced", Font.BOLD, 22));
            button.setForeground(Color.decode("#631818"));
        } 

        // cambiar las caracterísitcas del botón de AM/FM
        if(button==am_fmButt){
            button.setBackground( Color.decode("#7CA3BC") ); 
            button.setFont(new Font("Monospaced", Font.PLAIN, 20));
            button.setForeground(Color.decode("#122835"));
        } 

        // cambiar las caracterísitcas del botón de siguiente estación
        if(button==nextStationBut){
            button.setBackground( Color.decode("#9ab9b6") ); 
            button.setFont(new Font("Monospaced", Font.BOLD, 20));
            button.setForeground(Color.decode("#213533"));
        } 

        // cambiar las caracterísitcas del botón guardar estación
        if(button==saveB){
            button.setBackground( Color.decode("#9ab9b6") ); 
            button.setFont(new Font("Monospaced", Font.PLAIN, 20));
            button.setForeground(Color.decode("#213533"));
        } 

    }


     private List<Integer> listaPrueba;     //Creación de lista que contiene números del 1 al 12 para comparar y castear el "label" de los botones
     private LinkedList<String> lastClickedLabels = new LinkedList<>();

    
    @Override
    /**
     * Establece la acción que realiza cada botón.
     */
    public void actionPerformed( ActionEvent e){
        if (e.getSource() == on_offBut){
            radio.switchOnOff();
        } 
        else if (e.getSource() == am_fmButt){
            radio.switchAMFM();
        } 
        else if (e.getSource() == nextStationBut){
            radio.nextStation();
        } 
        else if (e.getSource() == saveB){
           
            if( !lastClickedLabels.isEmpty() ){     //Para validar que el último botón oprimido fuera un label casteable en int, no el botón de guardar.
                int buttonId =Integer.parseInt(lastClickedLabels.getLast());
                radio.saveStation(buttonId, radio.getCurrentStation());
                // mostrar en terminal
                System.out.println( "Station saved to button " +  buttonId);
            }
        } 

        else if(listaPrueba.contains(Integer.parseInt(e.getActionCommand()))){ //Para validar que el último botón oprimido fuera un label casteable en int, no el botón de guardar
            lastClickedLabels.add(e.getActionCommand());
            int buttonId = Integer.parseInt(e.getActionCommand());

            double selectedStation = radio.selectStation(buttonId);
            // mostrar en terminal
            System.out.println("Selected Station for button " +  buttonId + ":  " + selectedStation);
        }

        updateestadoRadio();
    }
    

    /**
     * Actualiza la etiqueta con el status actual del Radio
     */
    private void updateestadoRadio(){

        String estado = "Status: " + (radio.isOn() ? "On" : "Off") ;

        if(radio.isOn()){
            estado += " " + (radio.isAM() ? "AM": "FM");

            estado +=  " " +  String.format("%.2f", radio.getCurrentStation());

            // mostrar la estación guardada del button presionado
            if (!lastClickedLabels.isEmpty()){
                 int buttonId = Integer.parseInt(lastClickedLabels.getLast());
                double savedStation;
                  if (radio.isAM()) {
                    savedStation = radio.listaAM[buttonId-1];
                  }
                  else {
                    savedStation = radio.listaFM[buttonId-1];
                  }  
                 estado += " | Saved: " + String.format("%.2f",  savedStation) + "";
             }

        }

        estadoRadio.setText(estado);

    }

    
    /**
     * Caracterísitcas visuales de Label.
     * @param label
     */
    private void radioLabel(Label label){
        label.setFont(new Font("Monospaced", Font.BOLD, 17));
        estadoRadio.setBackground(Color.decode("#e5c297"));
        estadoRadio.setForeground(Color.decode("#543523"));

    }

}
