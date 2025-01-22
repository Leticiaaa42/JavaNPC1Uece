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
	
	void criarJogador(String cor, String tipo){
		if (tipo.equalsIgnoreCase("normal")){
			lista_jogadores.add(new JogadorNormal(cor, 0, 0));
		}
		else if (tipo.equalsIgnoreCase("azarado")){
			lista_jogadores.add(new JogadorAzarado(cor, 0, 0));
		}
		else{
			lista_jogadores.add(new JogadorSortudo(cor, 0, 0));
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
			System.out.println("O jogador " + jogador.getCor() + " se desprende. O seu turno foi gasto apenas com isso.");
		}
		else{
			System.out.println("O proximo jogador e o " + jogador.getCor() + ". Jogue os dados! (aperte ENTER)");
			String tatil; tatil = teclado_string.nextLine();
			lancamento = jogador.lancarDados(debug);
			if (lancamento >= 100){ //testa se os dados foram repetidos
				lancamento -= 100;
				dado_repetido = true;
				System.out.println("O jogador " + jogador.getCor() + " tirou o mesmo numero nos dois dados e tem direito a jogar de novo!");
			}
			
			jogador.setCasaAtual(jogador.getCasaAtual() + lancamento);
			System.out.println("Jogador " + jogador.getCor() + " move " + lancamento + " casas e cai na posicao " + jogador.getCasaAtual());
			
			if (jogador.getCasaAtual() >= 40){ //vitoria
				System.out.println("=|=|= O jogador " + jogador.getCor() + " ganhou o jogo! =|=|=");
				return true;
			}
			else if (jogador.getCasaAtual() == 10 || jogador.getCasaAtual() == 25 || jogador.getCasaAtual() == 38){ //jogador preso
				jogador.setPreso(true);
				System.out.println("O jogador " + jogador.getCor() + " ficou preso! O jogador gastara o proximo turno para se soltar.");
			}
			else if (jogador.getCasaAtual() == 13){ //jogador muda para um tipo aleatorio
				Random rand = new Random();
				int aleatorio = rand.nextInt(3);
				if (aleatorio == 0){
					jogador = new JogadorNormal(jogador.getCor(), jogador.getCasaAtual(), jogador.getNumeroDeJogadas());
					System.out.println("O jogador " + jogador.getCor() + " puxa uma carta para mudar de tipo e agora e: normal!");
				}
				else if (aleatorio == 1){
					jogador = new JogadorAzarado(jogador.getCor(), jogador.getCasaAtual(), jogador.getNumeroDeJogadas());
					System.out.println("O jogador " + jogador.getCor() + " puxa uma carta para mudar de tipo e agora e: azarado!");
				}
				else{
					jogador = new JogadorSortudo(jogador.getCor(), jogador.getCasaAtual(), jogador.getNumeroDeJogadas());
					System.out.println("O jogador " + jogador.getCor() + " puxa uma carta para mudar de tipo e agora e: sortudo!");
				}
			}
			else if (jogador.getCasaAtual() == 5 || jogador.getCasaAtual() == 15 || jogador.getCasaAtual() == 30){ //jogador move 3 casas a mais
				jogador.setCasaAtual(jogador.getCasaAtual() + 3);
				System.out.println("Sorte! O jogador " + jogador.getCor() + " avanca 3 casas adicionais e agora esta na casa " + jogador.getCasaAtual());
			}
			else if (jogador.getCasaAtual() == 17 || jogador.getCasaAtual() == 27){ //jogador escolhe alguém para recomeçar o jogo
				System.out.println("O jogador " + jogador.getCor() + " tem o direito de fazer um jogador voltar para o comeco!");
				
				String cor_selecionado = null;
				Jogador jogador_selecionado = null;
				boolean nome_valido = false;
				
				while (!nome_valido){
					System.out.println("Digite um nome valido: ");
					cor_selecionado = teclado_string.nextLine();
					
					for (Jogador jogador_analisado : lista_jogadores){
						if (cor_selecionado.equalsIgnoreCase(jogador_analisado.getCor())){
							jogador_selecionado = jogador_analisado;
							nome_valido = true;
						}
					}
				}
				
				jogador_selecionado.setCasaAtual(0);
				System.out.println(cor_selecionado + " voltou para a casa 0!");
				
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
				
				System.out.println("Azar! O jogador " + jogador.getCor() + " trocou de posicao com o jogador " + atrasado.getCor() + " e agora esta na posicao " + menor_posicao + ".");
			}
		}
		if (dado_repetido){
			System.out.println("O jogador joga novamente:");
			return computarRodada(jogador); //computar outra rodada caso os dados tenham saído repetidos
		}
		
		return false;
	}
	
	public void computarJogo(){
		ArrayList<String> cores = new ArrayList<String>();
		while (0 == 0){
			System.out.println("RODADA COMECA:");
			System.out.println("Digite o nome dos jogadores que NAO jogarao nessa rodada, um em cada linha, e termine com uma linha com um '.':");
			String cor = "teste"; cores.add(cor);
			cores.clear();
			cor = teclado_string.nextLine();
			
			while (!cor.equalsIgnoreCase(".")){
				cores.add(cor);
				cor = teclado_string.nextLine();
			}
			
			for (Jogador jogador : lista_jogadores){
				boolean jogador_invalido = false;
				for (String cor_verificada : cores){
					if (jogador.getCor().equalsIgnoreCase(cor_verificada)){
						jogador_invalido = true;
					}
				}
				
				if (!jogador_invalido){
					if (computarRodada(jogador)){//jogador venceu
						for (Jogador jogador_final : lista_jogadores){
							System.out.println("O jogador " + jogador_final.getCor() + " terminou com " + jogador_final.getNumeroDeJogadas() + " jogadas e na posicao " + jogador_final.getCasaAtual());
						}
						return;
					}
				}
			}
		}
	}
	
}
