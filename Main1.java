import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner teclado_string = new Scanner(System.in);
		Scanner teclado_int = new Scanner(System.in);
		
		Robo robo = new Robo("Cinza");
		
		int xAlimento = 0;
		int yAlimento = 0;
		
		System.out.println("digite a posicao do alimento (coordenadas 0-3, 0-3)");
		boolean loop = true;
		while (loop){
			xAlimento = teclado_int.nextInt();
			yAlimento = teclado_int.nextInt();
			if (xAlimento > 3 || xAlimento < 0 || yAlimento > 3 || yAlimento < 0){
				System.out.println("coordenadas invalidas");
			}
			else{
				loop = false;
			}
		}
		System.out.println("o robo esta em " + robo.getX() + ", " + robo.getY());
		
		loop = true;
		
		while (loop){
			System.out.println("digite o movimento (up-down-left-right)");
			String movimento = teclado_string.nextLine();
			
			
			try{
			  robo.mover(movimento); 
			}
			catch (MovimentoInvalidoException erro){
			  System.out.println(erro.getMessage());
			}
			
			if (robo.verificarAlimento(xAlimento, yAlimento)){
				loop = false;
				System.out.println("achou!");
			}
			
			System.out.println("o robo esta em " + robo.getX() + ", " + robo.getY());
		}
		
	}
}