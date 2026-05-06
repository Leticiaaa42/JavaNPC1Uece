import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogo{
	Scanner teclado_string = new Scanner(System.in); //recomendo criar um teclado pra cada tipo de input pra evitar problema com buffer
	
	boolean debug;
	ArrayList<Jogador> lista_jogadores = new ArrayList<Jogador>();
	
	public Jogo(boolean debug){
		this.debug = debug;
	}
	
	//------------------------------------
	
	void criarJogador(String nome, String cor, String tipo){
		if (tipo.equalsIgnoreCase("normal")){
			lista_jogadores.add(new JogadorNormal(nome, cor, 0, 0));
		}
		else if (tipo.equalsIgnoreCase("azarado")){
			lista_jogadores.add(new JogadorAzarado(nome, cor, 0, 0));
		}
		else{
			lista_jogadores.add(new JogadorSortudo(nome, cor, 0, 0));
		}
	}
	
	public boolean computarRodada(Jogador jogador){ //quando isso der true, esse jogador venceu
		//sugestão: toda vez que algo for chamar essa função, peça que o jogador aperte "enter". dá uma sensação tátil como se vc jogasse os dados
		boolean dado_repetido = false;
		int lancamento;
		jogador.incrementarNumeroDeJogadas();
		
		if (jogador.getPreso()){
			jogador.setPreso(false);
			System.out.println("");
			System.out.println(jogador.getNome() + " se desprende. O seu turno foi gasto apenas com isso.");
		}
		else{
			System.out.println("Agora é a vez de " + jogador.getNome() + ". Jogue os dados! (aperte ENTER)");
			String tatil; tatil = teclado_string.nextLine();
			lancamento = jogador.lancarDados(debug);
			if (lancamento >= 100){ //testa se os dados foram repetidos
				lancamento -= 100;
				dado_repetido = true;
				System.out.println(jogador.getNome() + " tirou o mesmo número nos dois dados e tem direito a jogar de novo!");
			}
			
			jogador.setCasaAtual(jogador.getCasaAtual() + lancamento);
			System.out.println(jogador.getNome() + " move " + lancamento + " casas e cai na posição " + jogador.getCasaAtual());
			
			if (jogador.getCasaAtual() >= 40){ //vitoria
				System.out.println("=|=|= " + jogador.getNome() + " ganhou o jogo! =|=|=");
				return true;
			}
			else if (jogador.getCasaAtual() == 10 || jogador.getCasaAtual() == 25 || jogador.getCasaAtual() == 38){ //jogador preso
				jogador.setPreso(true);
				System.out.println(jogador.getNome() + " se prendeu! Será necessário esperar o proximo turno para se soltar.");
			}
			else if (jogador.getCasaAtual() == 13){ //jogador muda para um tipo aleatorio
				Random rand = new Random();
				int aleatorio = rand.nextInt(3);
				if (aleatorio == 0){
					jogador = new JogadorNormal(jogador.getNome(), jogador.getCor(), jogador.getCasaAtual(), jogador.getNumeroDeJogadas());
					System.out.println(jogador.getNome() + " puxa uma carta para mudar de tipo e agora é: normal!");
				}
				else if (aleatorio == 1){
					jogador = new JogadorAzarado(jogador.getNome(), jogador.getCor(), jogador.getCasaAtual(), jogador.getNumeroDeJogadas());
					System.out.println(jogador.getNome() + " puxa uma carta para mudar de tipo e agora é: azarado!");
				}
				else{
					jogador = new JogadorSortudo(jogador.getNome(), jogador.getCor(), jogador.getCasaAtual(), jogador.getNumeroDeJogadas());
					System.out.println(jogador.getNome() + " puxa uma carta para mudar de tipo e agora é: sortudo!");
				}
			}
			else if (jogador.getCasaAtual() == 5 || jogador.getCasaAtual() == 15 || jogador.getCasaAtual() == 30){ //jogador move 3 casas a mais
				jogador.setCasaAtual(jogador.getCasaAtual() + 3);
				System.out.println("Sorte! " + jogador.getNome() + " avança 3 casas adicionais e agora esta na casa " + jogador.getCasaAtual());
			}
			else if (jogador.getCasaAtual() == 17 || jogador.getCasaAtual() == 27){ //jogador escolhe alguém para recomeçar o jogo
				System.out.println(jogador.getNome() + " tem o direito de fazer um jogador voltar para o começo!");
				
				String nome_selecionado = null;
				Jogador jogador_selecionado = null;
				boolean nome_valido = false;
				
				while (!nome_valido){
					System.out.println("Digite um nome válido: ");
					nome_selecionado = teclado_string.nextLine();
					
					for (Jogador jogador_analisado : lista_jogadores){
						if (nome_selecionado.equalsIgnoreCase(jogador_analisado.getNome())){
							jogador_selecionado = jogador_analisado;
							nome_valido = true;
						}
					}
				}
				
				jogador_selecionado.setCasaAtual(0);
				System.out.println(nome_selecionado + " voltou para a casa 0!");
				
			}
			else if (jogador.getCasaAtual() == 20 || jogador.getCasaAtual() == 35){ //jogador troca de lugar com quem está mais atrás
				Jogador atrasado = null;
				int menor_posicao = 40;
				
				for (Jogador jogador_analisado : lista_jogadores){
					if (jogador_analisado.getCasaAtual() < menor_posicao){
						atrasado = jogador_analisado;
						menor_posicao = jogador_analisado.getCasaAtual();
					}
				}
				
				atrasado.setCasaAtual(jogador.getCasaAtual());
				jogador.setCasaAtual(menor_posicao);
				
				System.out.println("Azar! " + jogador.getNome() + " trocou de posição com " + atrasado.getNome() + " e agora esta na posição " + menor_posicao + ".");
			}
		}
		if (dado_repetido){
			System.out.println(jogador.getNome() + " joga novamente:");
			return computarRodada(jogador); //computar outra rodada caso os dados tenham saído repetidos
		}
		
		return false;
	}
	
	public void computarJogo(){
		ArrayList<String> nomes = new ArrayList<String>();
		while (0 == 0){
			System.out.println("RODADA COMEçA:");
			System.out.println("Digite o nome dos jogadores que NÃO jogarão nessa rodada, um em cada linha, e termine com uma linha com um '.':");
			String nome = "teste"; nomes.add(nome);
			nomes.clear();
			nome = teclado_string.nextLine();
			
			while (!nome.equalsIgnoreCase(".")){
				nomes.add(nome);
				nome = teclado_string.nextLine();
			}
			
			for (Jogador jogador : lista_jogadores){
				boolean jogador_invalido = false;
				for (String nome_verificado : nomes){
					if (jogador.getNome().equalsIgnoreCase(nome_verificado)){
						jogador_invalido = true;
					}
				}
				
				if (!jogador_invalido){
					if (computarRodada(jogador)){//jogador venceu
						for (Jogador jogador_final : lista_jogadores){
							System.out.println(jogador_final.getNome() + " terminou com " + jogador_final.getNumeroDeJogadas() + " jogadas e na posição " + jogador_final.getCasaAtual());
						}
						return;
					}
				}
			}
		}
	}
	
}
