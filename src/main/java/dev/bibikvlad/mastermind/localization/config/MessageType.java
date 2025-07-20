package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum MessageType {
    GAME(0, "i18n.game_messages", GameMessages.class);

    private final int index;
    private final String resourceBundleName;
    private final Class<?> messageType;

    private static final Map<Class<?>, MessageType> BY_MESSAGE_CLASS = new HashMap<>();
    private static final Map<Integer, MessageType> BY_INDEX = new HashMap<>();

    static {
        for (MessageType messageType : MessageType.values()) {
            BY_MESSAGE_CLASS.put(messageType.messageType, messageType);
            BY_INDEX.put(messageType.index, messageType);
        }
    }


    MessageType(int index, String resourceBundleName, Class<?> messageType) {
        this.index = index;
        this.resourceBundleName = resourceBundleName;
        this.messageType = messageType;
    }

    public int getIndex() {
        return index;
    }

    public String getResourceBundleName() {
        return resourceBundleName;
    }

    public Class<?> getMessageType() {
        return messageType;
    }

    public static MessageType getByMessageType(Class<?> messageType) {
        return Optional.ofNullable(BY_MESSAGE_CLASS.get(messageType)).orElseThrow(
                () -> new IllegalArgumentException("No corresponding Message Type for provided class: " + messageType));
    }

    public static MessageType getByIndex(int index) {
        return Optional.ofNullable(BY_INDEX.get(index)).orElseThrow(
                () -> new IllegalArgumentException("No corresponding Message Type for provided index: " + index));
    }
}
