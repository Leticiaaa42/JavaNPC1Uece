import java.util.Scanner;

class Main {
    public static void main(String[] args)
    {	
		boolean jogando = true;
		while (jogando){
			Scanner teclado_string = new Scanner(System.in);
			Scanner teclado_int = new Scanner(System.in);
			String[] cores_completo = {"Vermelho", "Laranja", "Amarelo", "Verde", "Azul", "Roxo"};
			System.out.println("Esse é um jogo de tabuleiro simples criado originalmente para um projeto de POO de faculdade.");
			System.out.println("Você deve escolher o número de jogadores de cada classe, seus nomes, e então vocês irão jogar um jogo de sorte.");
			System.out.println("Joga-se dois dados simultâneos para mover suas peças pelo tabuleiro. Existem algumas casas especiais que:");
			System.out.println("avançam casas extras, trocam de posição com outros jogadores, fazem jogadores ficarem preso por 1 turno, etc");
			System.out.println("Iniciar no modo debug? [s/n]");
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
				System.out.println("Selecione o número de jogadores de cada categoria, depois escolha seus nomes");
				System.out.println("(Sortudo nunca tira abaixo de 7 e azarado nunca tira acima de 6)");
				System.out.println("Número total de jogadores deve ser de 2 a 6");
				System.out.println("Digite o número de jogadores normais (max: 5): ");
				int num1 = teclado_int.nextInt();
				System.out.println("Digite o número de jogadores azarados (max: 5): ");
				int num2 = teclado_int.nextInt();
				System.out.println("Digite o número de jogadores sortudos (max: 5): ");
				int num3 = teclado_int.nextInt();
				
				if (num1 > 5 || num2 > 5 || num3 > 5 || num1 + num2 + num3 < 2 || num1 + num2 + num3 > 6){
					System.out.println("Configuração invalida - refaça");
				} 
				else{
					select = false;
					
					int cor_count = 0;
					for (int i = 0; i < num1; i++){
						System.out.println("Digite o nome de um jogador normal: ");
						String nome = teclado_string.nextLine();
						jogo.criarJogador(nome, cores_completo[cor_count], "normal");
						System.out.println("Jogador normal " + nome + " ("+ cores_completo[cor_count] +") entrou no jogo!");
						cor_count++;
					}
					for (int i = 0; i < num2; i++){
						System.out.println("Digite o nome de um jogador azarado: ");
						String nome = teclado_string.nextLine();
						jogo.criarJogador(nome, cores_completo[cor_count], "azarado");
						System.out.println("Jogador azarado " + nome + " ("+ cores_completo[cor_count] +") entrou no jogo!");
						cor_count++;
					}
					for (int i = 0; i < num3; i++){
						System.out.println("Digite o nome de um jogador sortudo: ");
						String nome = teclado_string.nextLine();
						jogo.criarJogador(nome, cores_completo[cor_count], "sortudo");
						System.out.println("Jogador sortudo " + nome + " ("+ cores_completo[cor_count] +") entrou no jogo!");
						cor_count++;
					}
				}
			}
			
			System.out.println("=== O JOGO COMEÇOU ===");
			jogo.computarJogo();
			
			System.out.println("Jogar de novo? [s/n]");
			debug = teclado_string.nextLine();
			if (debug.equalsIgnoreCase("n")){
				jogando = false;
			}
		}
    }
}
