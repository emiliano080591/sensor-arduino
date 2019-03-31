package sensor;

 
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Ventana extends JFrame {
  private JPanel contentPane;
    private JTextField textField;
    Enumeration puertos_libres =null;

    OutputStream out = null;
    InputStream in = null;
    int temperatura=10;
    Thread timer;
    static JLabel lblNewLabel;
    JButton btnNewButton,btnNewButton_1;
    static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    public Ventana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 636, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
         timer = new Thread(new ImplementoRunnable());
         timer.start();
         timer.interrupt();
         
        //Se insertan los textos en el JFrame 
        JLabel lblnombres = new JLabel("Equipo 10");
        lblnombres.setBounds(350, 30, 200, 35);
        JLabel lblnombres1 = new JLabel("Carmona Medina Victor Angel");
        lblnombres1.setBounds(290, 50, 200, 35);
        JLabel lblnombres2 = new JLabel("Martinez Guzman Emiliano Ruben");
        lblnombres2.setBounds(280, 70, 200, 35);
        JLabel lblnombres3 = new JLabel("Mercado De la Serna Daniel Michael");
        lblnombres3.setBounds(275, 90, 230, 35);
        
        contentPane.add(lblnombres);
        contentPane.add(lblnombres1);
        contentPane.add(lblnombres2);
        contentPane.add(lblnombres3);
        
        JLabel lblnombres4 = new JLabel("3CV1");
        lblnombres4.setBounds(360, 120, 200, 24);
        contentPane.add(lblnombres4);
        lblNewLabel = new JLabel("Luz");
        lblNewLabel.setBounds(300, 160, 300, 35);
        lblNewLabel .setFont(new java.awt.Font("Arial", 0, 30));
        lblNewLabel .setForeground(Color.blue);
        contentPane.add(lblNewLabel);
        
        //Se inserta en el JFrame la imagen de ipn
        ImageIcon icono = new javax.swing.ImageIcon(getClass().getResource("ipn.jpg"));
        Image imagen = icono.getImage();
        ImageIcon iconoEscalado = new ImageIcon (imagen.getScaledInstance(115,115,Image.SCALE_SMOOTH));
        JLabel img=new JLabel();
        img.setIcon(iconoEscalado);
        img.setBounds(20, 30, 115, 115);
        contentPane.add(img);
        
        //Se inserta en el JFrame la imagen de escom
        ImageIcon icono2 = new javax.swing.ImageIcon(getClass().getResource("escom.jpg"));
        Image imagen2 = icono2.getImage();
        ImageIcon iconoEscalado2 = new ImageIcon (imagen2.getScaledInstance(100,100,Image.SCALE_SMOOTH));
        JLabel img2=new JLabel();
        img2.setIcon(iconoEscalado2);
        img2.setBounds(600, 30, 100, 100);
        contentPane.add(img2);
        
        //Se grafican los lumenes obtenidos por el arduino
        dataset.setValue(0, "Obetenidos del sensor", "Lumenes");
        
        JFreeChart chart = ChartFactory.createBarChart3D("Sensor", "","Lumenes", dataset, PlotOrientation.VERTICAL, false,true, false);
        ChartPanel myChart = new ChartPanel(chart);
        myChart.setMouseWheelEnabled(true);
        myChart.setBounds(200, 200, 350, 350);
        contentPane.add(myChart,BorderLayout.CENTER);
        contentPane.validate();
        
    }
   static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
   static String aux;
   static int decimal;
    
    private static final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
 
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                   
                    aux=ino.printMessage();
                    //convierte de binario a decimal
                    decimal=Integer.parseInt(aux,2);
                    lblNewLabel.setText(""+decimal+" lumenes");
                    dataset.setValue(decimal, "Obetenidos del sensor", "Lumenes");
                    System.out.println(aux);
                    
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };
    
    private class ImplementoRunnable implements Runnable{
        int aux;
        public void run() {
            try {
                ino.arduinoRX("COM3", 9600, listener);
            } catch (ArduinoException | SerialPortException ex) {
                Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }
            }}}
