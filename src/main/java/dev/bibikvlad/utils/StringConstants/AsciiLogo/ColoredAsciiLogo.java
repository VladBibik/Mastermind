package dev.bibikvlad.utils.StringConstants.AsciiLogo;

import dev.bibikvlad.utils.StringConstants.ConsoleColors;

public class ColoredAsciiLogo {
    public static String getLogo() {
        return """
                <borderColor>████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
                █▌<mainColor> ██████   ██████  █████████    █████████  ███████████ ██████████ ███████████  ██████   ██████ ███  █████  <accentColor>░<mainColor>████  █████████  <borderColor>▐█
                █▌<accentColor>░░<mainColor>██████ ██████  ███<accentColor>░░░░░<mainColor>███  ███<accentColor>░░░░░<mainColor>███<accentColor>░<mainColor>█<accentColor>░░░<mainColor>███<accentColor>░░░<mainColor>█<accentColor>░░<mainColor>███<accentColor>░░░░░<mainColor>█<accentColor>░░<mainColor>███<accentColor>░░░░░<mainColor>███ <accentColor>░<mainColor>██████ ██████  <accentColor>░░░ ░<mainColor>██████ <accentColor>░░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░░░░<mainColor>███ <borderColor>▐█
                █▌<accentColor> ░<mainColor>███<accentColor>░<mainColor>█████<accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░░░ ░   ░<mainColor>███  <accentColor>░  ░<mainColor>███  █ <accentColor>░  ░<mainColor>███    <accentColor>░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░<mainColor>█████<accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███   <accentColor>░░<mainColor>███<borderColor>▐█
                █▌<accentColor> ░<mainColor>███<accentColor>░░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███████████ <accentColor>░░<mainColor>█████████     <accentColor>░<mainColor>███     <accentColor>░<mainColor>██████    <accentColor>░<mainColor>██████████  <accentColor>░<mainColor>███<accentColor>░░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░░<mainColor>███<accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███<borderColor>▐█
                █▌<accentColor> ░<mainColor>███ <accentColor>░░░  ░<mainColor>███ <accentColor>░<mainColor>███<accentColor>░░░░░<mainColor>███  <accentColor>░░░░░░░░<mainColor>███    <accentColor>░<mainColor>███     <accentColor>░<mainColor>███<accentColor>░░<mainColor>█    <accentColor>░<mainColor>███<accentColor>░░░░░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░░░  <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░░<mainColor>██████ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███<borderColor>▐█
                █▌<accentColor> ░<mainColor>███      <accentColor>░<mainColor>███ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███  ███    <accentColor>░<mainColor>███    <accentColor>░<mainColor>███     <accentColor>░<mainColor>███ <accentColor>░   <mainColor>█ <accentColor>░<mainColor>███    <accentColor>░<mainColor>███ <accentColor>░<mainColor>███      <accentColor>░<mainColor>███ <accentColor>░<mainColor>███ <accentColor>░<mainColor>███  <accentColor>░░<mainColor>█████ <accentColor>░<mainColor>███    ███ <borderColor>▐█
                █▌<accentColor>░<mainColor>█████    <accentColor>░<mainColor>██████████  <accentColor>░<mainColor>█████<accentColor>░░<mainColor>█████████     <accentColor>░<mainColor>█████   <accentColor>░<mainColor>██████████<accentColor>░<mainColor>█████  <accentColor>░<mainColor>█████████    <accentColor>░<mainColor>█████<accentColor>░<mainColor>███ <accentColor>░<mainColor>████  <accentColor>░░<mainColor>████ <accentColor>░<mainColor>█████████  <borderColor>▐█
                █▌<accentColor>░░░░░     ░░░░░░░░░░   ░░░░░  ░░░░░░░░░     ░░░░░    ░░░░░░░░░░ ░░░░░   ░░░░░░░░░░     ░░░░░░░░░░░░░░░    ░░░░░░░░░░░░░░░   <borderColor>▐█
                ████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████<reset>
                """
                .replace("<borderColor>", ConsoleColors.ExtendedAnsiForeground.VIOLET)
                .replace("<mainColor>",  ConsoleColors.ExtendedAnsiForeground.ORANGE)
                .replace("<accentColor>", ConsoleColors.BrightForeground.RED)
                .replace("<reset>", ConsoleColors.RESET);
    }
}
