package dev.bibikvlad.utils.strings.AsciiLogo;

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
                .replace("<borderColor>", ConsoleColors.ExtendedAnsiForeground.ORCHID)
                .replace("<mainColor>",  ConsoleColors.ExtendedAnsiForeground.ORANGE)
                .replace("<accentColor>", ConsoleColors.BrightForeground.RED)
                .replace("<background>", ConsoleColors.Background.BLACK)
                .replace("<reset>", ConsoleColors.RESET);
    }
}
