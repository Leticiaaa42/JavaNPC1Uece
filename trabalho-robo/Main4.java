import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main4 {
	public static void main(String[] args) {
		Scanner teclado_string = new Scanner(System.in);
		Scanner teclado_int = new Scanner(System.in);
		
		Robo robo = new Robo("robo normal");
		Robo robo_inteligente = new Robo("robo inteligente");
		
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
		
		String tipo_obstaculo = null;
		int xObstaculo = 0;
		int yObstaculo = 0;
		ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
		int id = 0;
		System.out.println("digite um tipo de obstaculo (rocha - bomba - pronto)");
		loop = true;
		boolean loop2 = true;
		while (loop){
			tipo_obstaculo = teclado_string.nextLine();
			if (tipo_obstaculo.equalsIgnoreCase("bomba")){
				while (loop2){
					xObstaculo = teclado_int.nextInt();
					yObstaculo = teclado_int.nextInt();
					if (xObstaculo > 3 || xObstaculo < 0 || yObstaculo > 3 || yObstaculo < 0){
						System.out.println("coordenadas invalidas");
					}
					else{
						loop2 = false;
						
						obstaculos.add(new Bomba(id, xObstaculo, yObstaculo));
						id++;
					}
				}
				loop2 = true;
			}
			else if (tipo_obstaculo.equalsIgnoreCase("rocha")){
				while (loop2){
					xObstaculo = teclado_int.nextInt();
					yObstaculo = teclado_int.nextInt();
					if (xObstaculo > 3 || xObstaculo < 0 || yObstaculo > 3 || yObstaculo < 0){
						System.out.println("coordenadas invalidas");
					}
					else{
						loop2 = false;
						
						obstaculos.add(new Rocha(id, xObstaculo, yObstaculo));
						id++;
					}
				}
				loop2 = true;
			}
			else{
				break;
			}
		}
		
		
		
		System.out.println("o robo normal esta em " + robo.getX() + ", " + robo.getY());
		System.out.println("o robo inteligente esta em " + robo_inteligente.getX() + ", " + robo_inteligente.getY());
		
		loop = true;
		Random rand = new Random();
		
		while (loop){
			if (robo.estaVivo()){
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
				
				for (Obstaculo obstaculo : obstaculos){
					if (robo.getX() == obstaculo.getX() && robo.getY() == obstaculo.getY()){
					  obstaculo.bater(robo);
					}
				}
				
				
				System.out.println("o robo normal esta em " + robo.getX() + ", " + robo.getY());
			}
			//---------------
			if (robo_inteligente.estaVivo()){
				int movimento = rand.nextInt(3) + 1;
				
				
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
				
				for (Obstaculo obstaculo : obstaculos){
					if (robo_inteligente.getX() == obstaculo.getX() && robo_inteligente.getY() == obstaculo.getY()){
					  obstaculo.bater(robo_inteligente);
					}
				}
				
				
				System.out.println("o robo inteligente esta em " + robo_inteligente.getX() + ", " + robo_inteligente.getY());
			}
			if (!robo.estaVivo() && !robo_inteligente.estaVivo()){
				break;
			}
		}
		
	}
}
