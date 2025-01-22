public class Rocha extends Obstaculo{
  public Rocha(int ID, int x, int y){
    super(ID, x, y);
  }
  public void bater(Robo robo){
    robo.setX(robo.getLastX());
    robo.setY(robo.getLastY());
	System.out.println("*" + robo.getCor() + " bateu numa pedra*");
  }
}
