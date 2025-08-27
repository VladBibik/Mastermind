package dev.bibikvlad.utils.strings.logos;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.utils.strings.ConsoleColors;

public class ColoredAsciiLogo {
    public static String getLogo() {
        return """
                <background><borderColor>████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████<reset>
                <background><borderColor>█▌<mainColor> ██████   ██████  █████████    █████████  ███████████ ██████████ ███████████  ██████   ██████ ███  █████  <accentColor>░<mainColor>████  █████████  <borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor>░░<mainColor>██████ ██████  ███<accentColor>░░░░░<mainColor>███  ███<accentColor>░░░░░<mainColor>███<accentColor>░<mainColor>█<accentColor>░░░<mainColor>███<accentColor>░░░<mainColor>█<accentColor>░░<mainColor>███<accentColor>░░░░░<mainColor>█<accentColor>░░<mainColor>███<accentColor>░░░░░<mainColor>███ <accentColor>░<mainColor>██████ ██████  <accentColor>░░░ ░<mainColor>██████ <accentColor>░░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░░░░<mainColor>███ <borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor> ░<mainColor>███<accentColor>░<mainColor>█████<accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░░░ ░   ░<mainColor>███  <accentColor>░  ░<mainColor>███  █ <accentColor>░  ░<mainColor>███    <accentColor>░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░<mainColor>█████<accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███   <accentColor>░░<mainColor>███<borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor> ░<mainColor>███<accentColor>░░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███████████ <accentColor>░░<mainColor>█████████     <accentColor>░<mainColor>███     <accentColor>░<mainColor>██████    <accentColor>░<mainColor>██████████  <accentColor>░<mainColor>███<accentColor>░░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░░<mainColor>███<accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███<borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor> ░<mainColor>███ <accentColor>░░░  ░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░░░░░<mainColor>███  <accentColor>░░░░░░░░<mainColor>███    <accentColor>░<mainColor>███     <accentColor>░<mainColor>███<accentColor>░░<mainColor>█    <accentColor>░<mainColor>███<accentColor>░░░░░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░░░  <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░░<mainColor>██████ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███<borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor> ░<mainColor>███      <accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███  ███    <accentColor>░<mainColor>███    <accentColor>░<mainColor>███     <accentColor>░<mainColor>███ <accentColor>░   <mainColor>█ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███ <accentColor>░<mainColor>███      <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███  <accentColor>░░<mainColor>█████ <accentColor>░<mainColor>███    ███ <borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor>░<mainColor>█████    <accentColor>░<mainColor>██████████  <accentColor>░<mainColor>█████<accentColor>░░<mainColor>█████████    <accentColor>░<mainColor>█████    <accentColor>░<mainColor>██████████<accentColor>░<mainColor>█████  <accentColor>░<mainColor>█████████    <accentColor>░<mainColor>█████<accentColor>░<mainColor>███ <accentColor>░<mainColor>████  <accentColor>░░<mainColor>████ <accentColor>░<mainColor>█████████  <borderColor>▐█<reset>
                <background><borderColor>█▌<accentColor>░░░░░     ░░░░░░░░░░   ░░░░░  ░░░░░░░░░     ░░░░░    ░░░░░░░░░░ ░░░░░   ░░░░░░░░░░     ░░░░░░░░░░░░░░░    ░░░░░░░░░░░░░░░   <borderColor>▐█<reset>
                <background><borderColor>████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████<reset>
                """
                .replace("<borderColor>", ConsoleColor.ORCHID.getCode())
                .replace("<mainColor>",  ConsoleColor.ORANGE.getCode())
                .replace("<accentColor>", ConsoleColor.BRIGHT_RED.getCode())
                .replace("<background>", ConsoleColor.BACKGROUND_BLACK.getCode())
                .replace("<reset>", ConsoleColor.RESET.getCode());
    }
}
