public class Robo{
  protected int x, y, lastX, lastY;
  protected String cor;
  protected boolean vivo;
  protected int num_movimentos;

  public Robo(String cor){
    this.cor = cor;
    this.x = 0;
    this.y = 0;
    this.lastX = 0;
    this.lastY = 0;
    this.vivo = true;
	this.num_movimentos = 0;
  }

  //todos os gets e variações
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public int getLastX(){
    return this.lastX;
  }
  public int getLastY(){
    return this.lastY;
  }
  public String getCor(){
    return this.cor;
  }
  public boolean estaVivo(){
    return this.vivo;
  }
  public int getNumMovimentos(){
	return this.num_movimentos;
  }

  //todos os sets e variações
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void morrer(){
    this.vivo = false;
  }
  
  public void setNumMovimentos(){
	  this.num_movimentos = num_movimentos;
  }

  public void mover(String direcao) throws MovimentoInvalidoException{
    if (vivo){
		num_movimentos++;
		
      lastX = x;
      lastY = y;
      
      if(direcao.equalsIgnoreCase("up")){
        if (y == 3){
          throw new MovimentoInvalidoException(x, y + 1);
        }
        else{
          this.y++;
        }
      }else if(direcao.equalsIgnoreCase("down")){
        if (y == 0){
          throw new MovimentoInvalidoException(x, y - 1);
        }
        else{
          this.y--;
        }
      }else if(direcao.equalsIgnoreCase("right")){
        if (x == 3){
          throw new MovimentoInvalidoException(x + 1, y);
        }
        else{
          this.x++;
        }
      }else if(direcao.equalsIgnoreCase("left")){
        if (x == 0){
          throw new MovimentoInvalidoException(x - 1, y);
        }
        else{
          this.x--;
        }
      }
      else{
        throw new MovimentoInvalidoException();
      }
    }
  }


public void mover(int direcao) throws MovimentoInvalidoException{
  if (vivo){
    if (direcao == 1){
      mover("up");
    }
    else if (direcao == 2){
      mover("down");
    }
    else if (direcao == 3){
      mover("right");
    }
    else if (direcao == 4){
      mover("left");
    }
    else {
      throw new MovimentoInvalidoException();
    }
  }
}

boolean verificarAlimento(int xAlimento, int yAlimento){
  if (x == xAlimento && y == yAlimento){
    vivo = false; //sim ele morre quando acha a comida
    return true;
  }
  return false;
}
  
}
