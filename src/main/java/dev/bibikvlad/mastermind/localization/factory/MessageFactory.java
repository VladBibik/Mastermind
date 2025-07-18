package dev.bibikvlad.mastermind.localization.factory;

import java.util.ResourceBundle;

public interface MessageFactory<T> {
    T create(ResourceBundle resourceBundle);
}
