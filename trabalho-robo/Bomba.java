public class Bomba extends Obstaculo{
  public Bomba(int ID, int x, int y){
    super(ID, x, y);
  }
  public void bater(Robo robo){
    robo.morrer();

    x = -1;
    y = -1;
	
	System.out.println("BOOOM, " + robo.getCor() + " explodiu");
  }
}
