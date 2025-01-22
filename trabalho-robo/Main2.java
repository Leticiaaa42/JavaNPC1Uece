import java.util.Scanner;
import java.util.Random;

public class Main2 {
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
		Random rand = new Random();
		
		while (loop){
			int movimento = rand.nextInt(3) + 1;
			
			
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
