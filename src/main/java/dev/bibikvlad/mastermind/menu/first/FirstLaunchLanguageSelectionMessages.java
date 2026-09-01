package dev.bibikvlad.mastermind.menu.first;

public class FirstLaunchLanguageSelectionMessages {
    public String getEmptyInputMessage() {
        return "Error: Input cannot be empty. Please enter a number corresponding to the menu option";
    }

    public String getInvalidInputMessage() {
        return "Error: Invalid input. Please enter a number corresponding to the menu option";
    }

    public String getMenuOptions() {
        return """
                Please select a language.
                Enter the number corresponding to your choice.""";
    }

    public String getTerminalTestString() {
        return """
                
                ⬤◍○✅❌🎉\u001B[91m⬤\u001B[92m⬤\u001B[93m⬤\u001B[94m⬤\u001B[95m⬤\u001B[97m⬤\u001B[0m
                If you see any issues with the symbols or colors above,
                we recommend choosing Compatible English.""";
    }

    public String getCompatibleEnglishExplanation() {
        return """
                You've selected Compatibility Mode.
                
                Compatibility Mode uses simplified symbols, disables clue and result colors,
                and makes the logo monochrome to ensure that the game is displayed correctly
                in terminals with limited support for these features.

                You can turn off Compatibility Mode at any time from either of these locations:
                Settings > Compatibility Mode
                or:
                Settings > Language > select any language other than Compatibility.

                Press 'Enter' to continue...""";
    }
}
