public class MovimentoInvalidoException extends Exception{
  public String toString(){
    return "Movimento invalido";
  }

  public MovimentoInvalidoException(){
    super("Erro de digitacao: movimento inexistente");
  }
  
  public MovimentoInvalidoException(int x, int y){
    super("Movimento para " + x + ", " + y + " e invalido");
  }
}