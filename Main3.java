import java.util.Scanner;
import java.util.Random;

public class Main3 {
	public static void main(String[] args) {
		Scanner teclado_string = new Scanner(System.in);
		Scanner teclado_int = new Scanner(System.in);
		
		Robo robo = new Robo("Cinza");
		Robo robo_inteligente = new Robo("Dourado");
		
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
		System.out.println("o robo normal esta em " + robo.getX() + ", " + robo.getY());
		System.out.println("o robo inteligente esta em " + robo_inteligente.getX() + ", " + robo_inteligente.getY());
		
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
				System.out.println("o robo normal achou!");
				break;
			}
			
			System.out.println("o robo normal esta em " + robo.getX() + ", " + robo.getY());
			
			//---------------
			movimento = rand.nextInt(3) + 1;
			
			
			try{
			  robo_inteligente.mover(movimento); 
			}
			catch (MovimentoInvalidoException erro){
			  System.out.println(erro.getMessage());
			}
			
			if (robo_inteligente.verificarAlimento(xAlimento, yAlimento)){
				System.out.println("o robo inteligente achou!");
				break;
			}
			
			System.out.println("o robo inteligente esta em " + robo.getX() + ", " + robo.getY());
		}
		
	}
}