package conversor;

public class Main {
	
		
	public static void main(String[] args) {
		
		try {
			Conversor conversor = new Conversor();
			conversor.showMenu();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
