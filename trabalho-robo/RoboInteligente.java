public class RoboInteligente extends Robo{
  public RoboInteligente(String cor){
    super(cor);
  }

  public void mover(String direcao) throws MovimentoInvalidoException{
    if (vivo){
      lastX = x;
      lastY = y;
      
      if(direcao.equalsIgnoreCase("up")){
        if (y == 3){
          this.y--;
        }
        else{
          this.y++;
        }
      }else if(direcao.equalsIgnoreCase("down")){
        if (y == 0){
          this.y++;
        }
        else{
          this.y--;
        }
      }else if(direcao.equalsIgnoreCase("right")){
        if (x == 0){
          this.x--;
        }
        else{
          this.x++;
        }
      }else if(direcao.equalsIgnoreCase("left")){
        if (x == 0){
          this.x++;
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
}
