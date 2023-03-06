package conversor;
import javax.swing.*;

import conversor.TiposConversiones.Datos;
import conversor.TiposConversiones.Divisa;

public class Conversor {

	public void showMenu() {
		enum Option { Divisas, Datos }
		
		Option optionSelected = null;
		
		try {
			optionSelected = (Option) JOptionPane.showInputDialog(
				null,
				"Seleccione el tipo de conversión:",
				"Conversor",
				JOptionPane.QUESTION_MESSAGE,
				null,
				Option.values(),
				Option.Divisas
			);
			
			switch (optionSelected) {
			case Divisas:
				showDivisasMenu();
				break;
				
			case Datos:
				showDatosMenu();
				break;
			default:
				break;
			}		
			
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Programa Finalizado");
			return;
		}
		
	}
	
	private void showDivisasMenu() {
		Divisa divisa = new Divisa();
		divisa.showMenu();
	}
	
	private void showDatosMenu() {
		Datos datos = new Datos(); 
		datos.showMenu();
	}
	
}
