package conversor.TiposConversiones;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conversor.Conversor;


public class Datos{
    public void showMenu() {
    	 	
        JPanel datosMenu = new JPanel();
        datosMenu.setLayout(new GridLayout(2, 2));
        
        JComboBox<String> datosAConvertir = new JComboBox<>( new String[] {
        	"Byte B",
        	"Kilobyte KB",
        	"Megabyte MB",
        	"Gigabyte GB",
        	"Terabyte TB",
        	"Petabyte PB"
        });
        
        JComboBox<String> datosConvertirA = new JComboBox<>( new String[] {
    		"Byte B",
        	"Kilobyte KB",
        	"Megabyte MB",
        	"Gigabyte GB",
        	"Terabyte TB",
        	"Petabyte PB"	
        });
        
        datosMenu.add(new JLabel("Convertir: "));
        datosMenu.add(datosAConvertir);
        datosMenu.add(new JLabel("A: "));
        datosMenu.add(datosConvertirA);
        
        int result = JOptionPane.showOptionDialog(
        		null,
        		datosMenu,
        		"Datos",
        		JOptionPane.OK_CANCEL_OPTION,
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		null,
        		null);
        if(result == JOptionPane.OK_OPTION) {
        	String selectedDato = (String) datosAConvertir.getSelectedItem();
        	String selectedDatoTo = (String) datosConvertirA.getSelectedItem();
        	
        	converterOptions(selectedDato, selectedDatoTo);	
        }else {
        	Conversor conversor = new Conversor();
        	conversor.showMenu();
        }
    }
    
    
    private void converterOptions(String datoAConvertir, String convertirDatoA) {
    	final double KilobyteToByte = 1024;
    	final double MegabyteToKilobyte = 1024;
    	final double GigabyteToMegabyte = 1024;
    	final double TerabyteToGigabyte = 1024;
    	final double PetabyteToTerabyte = 1024;
    	
    	Map<String, Double> factoresDeConversion = new HashMap<>();
    	factoresDeConversion.put("Byte B a Kilobyte KB", KilobyteToByte);
    	factoresDeConversion.put("Kilobyte KB a Byte B", 1.0 / KilobyteToByte);
    	factoresDeConversion.put("Kilobyte KB a Megabyte MB", MegabyteToKilobyte);
    	factoresDeConversion.put("Megabyte MB a Kilobyte KB", 1.0 / MegabyteToKilobyte);
    	factoresDeConversion.put("Megabyte MB a Gigabyte GB", GigabyteToMegabyte);
    	factoresDeConversion.put("Gigabyte GB a Megabyte MB", 1.0 / GigabyteToMegabyte);
    	factoresDeConversion.put("Gigabyte GB a Terabyte TB", TerabyteToGigabyte);
    	factoresDeConversion.put("Terabyte TB a Gigabyte GB", 1.0 / TerabyteToGigabyte);
    	factoresDeConversion.put("Terabyte TB a Petabyte PB", PetabyteToTerabyte);
    	factoresDeConversion.put("Petabyte PB a Terabyte TB", 1.0 / PetabyteToTerabyte);
    	
    	
    	String tipoConversion = datoAConvertir + " a " + convertirDatoA;
    	System.out.println(tipoConversion);
    	
    	if(factoresDeConversion.containsKey(tipoConversion)) {
    		double factor = factoresDeConversion.get(tipoConversion);
    		double resultado = convert(factor);
    		showResult(resultado, convertirDatoA);
    	} else {
    		JOptionPane.showMessageDialog(null, "No se encontró una conversión para las divisas especificadas");
    		showContinueMenu();
    		return;
    	}
    	
    }

    private double convert(double valorCambioDato) {
    	String cantidadAConvertirUsuario = JOptionPane.showInputDialog(
    			null,
    			"Ingrese el tamaño del dato a convertir: ",
    			"Ingreso de datos",
    			JOptionPane.INFORMATION_MESSAGE
    			);
    	
    	double cantidadPorConvertir = 0;
    	
    	try {
    		cantidadPorConvertir = Double.parseDouble(cantidadAConvertirUsuario);
    	}catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válidad, intente de nuevo");
    	}
    	
    	double resultadoConversion = cantidadPorConvertir / valorCambioDato;
// 		return Math.round(resultadoConversion);
    	return resultadoConversion;
    }
    
    private void showResult(double resultado, String tipoDato) {
    	JOptionPane.showMessageDialog(null, resultado + " " + tipoDato + ".");
    	showContinueMenu();
    }
    
    private void showContinueMenu() {
    	int response = JOptionPane.showConfirmDialog(null, "¿Quires continuar?");
		if(response == JOptionPane.YES_OPTION) {
			showMenu();
		} else {
			JOptionPane.showMessageDialog(null, "Programa Finalizado");
		}
    }
    
}
