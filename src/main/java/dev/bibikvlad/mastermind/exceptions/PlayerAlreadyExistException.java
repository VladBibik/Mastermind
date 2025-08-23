package dev.bibikvlad.mastermind.exceptions;

public class PlayerAlreadyExistException extends Exception {
  public PlayerAlreadyExistException(String message) {
    super(message);
  }
}
