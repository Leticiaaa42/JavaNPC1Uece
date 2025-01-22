public abstract class Obstaculo{
  protected int ID;
  protected int x, y;

  public Obstaculo(int ID, int x, int y){
    this.ID = ID;
    this.x = x;
    this.y = y;
  }

  public int getID(){
    return this.ID;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }

  public abstract void bater(Robo robo);
}