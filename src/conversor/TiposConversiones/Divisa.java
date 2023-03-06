package conversor.TiposConversiones;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import consumoAPI.ApiDivisaCliente;
import conversor.Conversor;

public class Divisa {
	ApiDivisaCliente apiDivisa = new ApiDivisaCliente();

	public void showMenu() {
				
		JPanel divisaMenu = new JPanel();
		divisaMenu.setLayout(new GridLayout(2, 2));
		
		JComboBox<String> monedaAConvertir = new JComboBox<>(new String[] {
				"Quetzales",
				"Dolares",
				"Euros",
				"Libras Esterlinas",
				"Yen Japones",
				"Won Sur-Coreano"
		});
		JComboBox<String> convertirA = new JComboBox<>(new String[] {
				"Quetzales",
				"Dolares",
				"Euros",
				"Libras Esterlinas",
				"Yen Japones",
				"Won Sur-Coreano"
		});
		
		divisaMenu.add(new JLabel("Convertir: "));
		divisaMenu.add(monedaAConvertir);
		divisaMenu.add(new JLabel("a: "));
		divisaMenu.add(convertirA);
		
		int result = JOptionPane.showOptionDialog(
				null, 
				divisaMenu,
				"Divisas",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		if(result == JOptionPane.OK_OPTION) {
			String selectedCurrencyToConvert = (String) monedaAConvertir.getSelectedItem();
			String selectedConvertTo = (String) convertirA.getSelectedItem();
			
			convertOptions(selectedCurrencyToConvert, selectedConvertTo);
		} else {
			Conversor conversor = new Conversor();
			conversor.showMenu();			
		}
		
	}
	
	public void convertOptions(String divisaAConvertir, String convertirDivisaA) {		
		
//		final double cambioDolarAQuetzales = 7.81;
		final double cambioDolarAQuetzales = apiDivisa.apiGetData("GTQ");
	final double cambioEuroAQuetzales = 8.33;
		final double cambioLibrasEsterlinasAQuetzales = 9.39;
		final double cambioYenJaponesAQuetzales = 0.057;
		final double cambioWonSurCoreanoAQuetzales= 0.0060; 
		
		Map<String, Double> factoresDeConversion = new HashMap<>();
		factoresDeConversion.put("Quetzales a Dolares", cambioDolarAQuetzales);
		factoresDeConversion.put("Dolares a Quetzales", 1.0 / cambioDolarAQuetzales);
		factoresDeConversion.put("Quetzales a Euros", cambioEuroAQuetzales); 	
		factoresDeConversion.put("Euros a Quetzales", 1.0 / cambioEuroAQuetzales);
		factoresDeConversion.put("Quetzales a Libras Esterlinas", cambioLibrasEsterlinasAQuetzales);
		factoresDeConversion.put("Libras Esterlinas a Quetzales", 1.0 / cambioLibrasEsterlinasAQuetzales);
		factoresDeConversion.put("Quetzales a Yen Japones", cambioYenJaponesAQuetzales);
		factoresDeConversion.put("Yen Japones a Quetzales", 1.0 / cambioYenJaponesAQuetzales);
		factoresDeConversion.put("Quetzales a Won Sur-Coreano", cambioWonSurCoreanoAQuetzales);
		factoresDeConversion.put("Won Sur-Coreano a Quetzales", 1.0 / cambioWonSurCoreanoAQuetzales);
		
		String tipoConversion = divisaAConvertir + " a " + convertirDivisaA;
		
		if(factoresDeConversion.containsKey(tipoConversion)) {
			double factor = factoresDeConversion.get(tipoConversion);
			double resultado = convert(factor);
			if(resultado > 0)
				showResult(resultado, convertirDivisaA);
			else showContinueMenu();
		} else {
			JOptionPane.showMessageDialog(null, "No se econtró una conversión para las divisas especificadas.");
			showContinueMenu();
		}		
	}
	
	private double convert(double tipoCambioDivisa) {
		final String mensaje = "Ingrese la cantidad de dinero que desea convertir:";
		final String titulo = "Ingreso de datos";
		final String mensaje_error = "La cantidad ingresado no es válida, intente nuevamente.";
		
		
		String cantidadAConvertirUsuario = JOptionPane.showInputDialog(
				null,
				mensaje,
				titulo,
				JOptionPane.INFORMATION_MESSAGE
		);
		
		double cantidadPorConvertir = 0;
		
		try {
			cantidadPorConvertir = Double.parseDouble(cantidadAConvertirUsuario);			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, mensaje_error);
		}
		
		
		double resultadoConversion = cantidadPorConvertir / tipoCambioDivisa;										
		return Math.round(resultadoConversion * 100.0) / 100.0;	
	}
	
	private void showResult(double resultado, String divisa) {
		JOptionPane.showMessageDialog(null, "Tines $. " + resultado + " " + divisa + ".");
		showContinueMenu();
	}
	
	public void showContinueMenu() {
		int response = JOptionPane.showConfirmDialog(null, "¿Quires continuar?");
		if(response == JOptionPane.YES_OPTION) {
			showMenu();
		} else {
			JOptionPane.showMessageDialog(null, "Programa Finalizado");
		}
	}
	
}
