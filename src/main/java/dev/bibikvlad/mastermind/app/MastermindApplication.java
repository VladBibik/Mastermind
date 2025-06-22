package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindGameChat;

public class MastermindApplication {
    public static void main(String[] args) {
        MastermindGameChat game = new MastermindGameChat();

        game.play();
    }
}
