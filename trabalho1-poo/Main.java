import java.util.Scanner;

class Main {
    public static void main(String[] args)
    {	
		boolean jogando = true;
		while (jogando){
			Scanner teclado_string = new Scanner(System.in);
			Scanner teclado_int = new Scanner(System.in);
			String[] cores_completo = {"Vermelho", "Laranja", "Amarelo", "Verde", "Azul", "Roxo"};
			
			System.out.println("iniciar no modo debug? [s/n]");
			String debug = teclado_string.nextLine();
			Jogo jogo;
			if (debug.equalsIgnoreCase("s")){
				jogo = new Jogo(true);
			}
			else{
				jogo = new Jogo(false);
			}
			
			int total = 6;
			boolean select = true;
			while (select){
				System.out.println("Numero total de jogadores deve ser de 2 a 6");
				System.out.println("Digite o numero de jogadores normais (max: 5): ");
				int num1 = teclado_int.nextInt();
				System.out.println("Digite o numero de jogadores azarados (max: 5): ");
				int num2 = teclado_int.nextInt();
				System.out.println("Digite o numero de jogadores sortudos (max: 5): ");
				int num3 = teclado_int.nextInt();
				
				if (num1 > 5 || num2 > 5 || num3 > 5 || num1 + num2 + num3 < 2 || num1 + num2 + num3 > 6){
					System.out.println("Configuracao invalida - refaca");
				} 
				else{
					select = false;
					
					int cor_count = 0;
					for (int i = 0; i < num1; i++){
						jogo.criarJogador(cores_completo[cor_count], "normal");
						System.out.println("Jogador " + cores_completo[cor_count] + " (normal) entrou no jogo!");
						cor_count++;
					}
					for (int i = 0; i < num2; i++){
						jogo.criarJogador(cores_completo[cor_count], "azarado");
						System.out.println("Jogador " + cores_completo[cor_count] + " (azarado) entrou no jogo!");
						cor_count++;
					}
					for (int i = 0; i < num3; i++){
						jogo.criarJogador(cores_completo[cor_count], "sortudo");
						System.out.println("Jogador " + cores_completo[cor_count] + " (sortudo) entrou no jogo!");
						cor_count++;
					}
				}
			}
			
			System.out.println("=== O JOGO COMECOU ===");
			jogo.computarJogo();
			
			System.out.println("Jogar de novo? [s/n]");
			debug = teclado_string.nextLine();
			if (debug.equalsIgnoreCase("n")){
				jogando = false;
			}
		}
    }
}