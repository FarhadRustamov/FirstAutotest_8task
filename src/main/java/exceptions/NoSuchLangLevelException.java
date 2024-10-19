package exceptions;

public class NoSuchLangLevelException extends RuntimeException {

  public NoSuchLangLevelException() {
    super("Level is not defined");
  }
}
