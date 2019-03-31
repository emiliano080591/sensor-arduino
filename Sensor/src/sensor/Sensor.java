/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
/**
 *
 * @author drago
 */
public class Sensor {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        Ventana v= new Ventana();
        v.setVisible(true);
        v.setTitle("Proyecto instrumentacion");
        v.setSize(750,600);

    }
}
