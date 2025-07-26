package dev.bibikvlad.mastermind.localization.core;

import java.util.ResourceBundle;

public interface MessageFactory<T> {
    T create(ResourceBundle resourceBundle);
}
