import java.util.Random;

public abstract class Jogador{
	private String cor;
	private int casa_atual;
	private int numero_de_jogadas;
	private boolean preso;
	
	public Jogador(String cor, int casa_atual, int numero_de_jogadas){
		this.cor = cor;
		this.casa_atual = casa_atual;
		this.numero_de_jogadas = numero_de_jogadas;
	}
	
	public String getCor(){
		return cor;
	}
	
	public int getCasaAtual(){
		return casa_atual;
	}
	
	public void setCasaAtual(int casa_atual){
		this.casa_atual = casa_atual;
	}
	
	public int getNumeroDeJogadas(){
		return numero_de_jogadas;
	}
	
	public void incrementarNumeroDeJogadas(){
		numero_de_jogadas++;
	}
	
	public boolean getPreso(){
		return preso;
	}
	
	public void setPreso(boolean preso){
		this.preso = preso;
	}
	
	//-------------------------------
	
	protected int gerarNumeros(){ //soma 100 ao numero se os dados forem iguais
		Random rand = new Random();
		int dado1, dado2;
		dado1 = rand.nextInt(6) + 1;
		dado2 = rand.nextInt(6) + 1;
		
		if (dado1 != dado2){
			return dado1 + dado2;
		}
		return dado1 + dado2 + 100;
	}
	
	public abstract int lancarDados(boolean debug); //serve de "filtro" 
	//pedir input se debug==true, se não, somar dois lançamentos repetidamente até critérios da subclasse serem atingidos
}