import java.util.Scanner;

public class JogadorAzarado extends Jogador{
	
	public JogadorAzarado(String cor, int casa_atual, int numero_de_jogadas){
		super(cor, casa_atual, numero_de_jogadas);
	}
	
	public int lancarDados(boolean debug){
		int soma = 1000;
		boolean repetido = false;
		
		if (debug){
			System.out.println("Insira valor da soma de dados: ");
			Scanner teclado = new Scanner(System.in);
			return teclado.nextInt();
		}
		
		while (soma > 6){
			soma = gerarNumeros();
			if (soma > 100){
				repetido = true;
				soma -= 100;
			}
		}
		
		if (repetido){
			return soma + 100;
		}
		return soma;
	}
}