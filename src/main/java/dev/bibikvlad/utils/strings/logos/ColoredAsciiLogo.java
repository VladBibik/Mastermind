package dev.bibikvlad.utils.strings.logos;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class ColoredAsciiLogo {
    public static String getLogo(LogoColorsBundle logoColorsBundle) {
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
                .replace("<borderColor>", logoColorsBundle.getLogoBorderColor().getCode())
                .replace("<mainColor>",  logoColorsBundle.getLogoMainColor().getCode())
                .replace("<accentColor>", logoColorsBundle.getLogoAccentColor().getCode())
                .replace("<background>", logoColorsBundle.getLogoBackgroundColor().getCode())
                .replace("<reset>", ConsoleColor.RESET.getCode());
    }
}
