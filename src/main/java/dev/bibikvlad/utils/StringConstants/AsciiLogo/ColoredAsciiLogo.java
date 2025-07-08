package dev.bibikvlad.utils.StringConstants.AsciiLogo;

import dev.bibikvlad.utils.StringConstants.ConsoleColors;

public class ColoredAsciiLogo {
    public static String getLogo() {
        return """
                <violet>████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
                █▌<orange> ██████   ██████  █████████    █████████  ███████████ ██████████ ███████████  ██████   ██████ ███  █████  <red>░<orange>████  █████████  <violet>▐█
                █▌<red>░░<orange>██████ ██████  ███<red>░░░░░<orange>███  ███<red>░░░░░<orange>███<red>░<orange>█<red>░░░<orange>███<red>░░░<orange>█<red>░░<orange>███<red>░░░░░<orange>█<red>░░<orange>███<red>░░░░░<orange>███ <red>░<orange>██████ ██████  <red>░░░ ░<orange>██████ <red>░░<orange>███ <red>░<orange>███<red>░░░░<orange>███ <violet>▐█
                █▌<red> ░<orange>███<red>░<orange>█████<red>░<orange>███ <red>░<orange>███    <red>░<orange>███ <red>░<orange>███    <red>░░░ ░   ░<orange>███  <red>░  ░<orange>███  █ <red>░  ░<orange>███    <red>░<orange>███ <red>░<orange>███<red>░<orange>█████<red>░<orange>███ <red>░<orange>███ <red>░<orange>███<red>░<orange>███ <red>░<orange>███ <red>░<orange>███   <red>░░<orange>███<violet>▐█
                █▌<red> ░<orange>███<red>░░<orange>███ <red>░<orange>███ <red>░<orange>███████████ <red>░░<orange>█████████     <red>░<orange>███     <red>░<orange>██████    <red>░<orange>██████████  <red>░<orange>███<red>░░<orange>███ <red>░<orange>███ <red>░<orange>███ <red>░<orange>███<red>░░<orange>███<red>░<orange>███ <red>░<orange>███    <red>░<orange>███<violet>▐█
                █▌<red> ░<orange>███ <red>░░░  ░<orange>███ <red>░<orange>███<red>░░░░░<orange>███  <red>░░░░░░░░<orange>███    <red>░<orange>███     <red>░<orange>███<red>░░<orange>█    <red>░<orange>███<red>░░░░░<orange>███ <red>░<orange>███ <red>░░░  <red>░<orange>███ <red>░<orange>███ <red>░<orange>███ <red>░░<orange>██████ <red>░<orange>███    <red>░<orange>███<violet>▐█
                █▌<red> ░<orange>███      <red>░<orange>███ <red>░<orange>███    <red>░<orange>███  ███    <red>░<orange>███    <red>░<orange>███     <red>░<orange>███ <red>░   <orange>█ <red>░<orange>███    <red>░<orange>███ <red>░<orange>███      <red>░<orange>███ <red>░<orange>███ <red>░<orange>███  <red>░░<orange>█████ <red>░<orange>███    ███ <violet>▐█
                █▌<orange> █████     ██████████   █████<red>░░<orange>█████████     █████    ██████████ █████   ██████████     █████<red>░<orange>███ <red>░<orange>████  <red>░░<orange>████ <red>░<orange>█████████  <violet>▐█
                █▌<red>░░░░░     ░░░░░░░░░░   ░░░░░  ░░░░░░░░░     ░░░░░    ░░░░░░░░░░ ░░░░░   ░░░░░░░░░░     ░░░░░░░░░░░░░░░    ░░░░░░░░░░░░░░░   <violet>▐█
                ████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████<reset>
                """
                .replace("<violet>", ConsoleColors.ExtendedAnsiForeground.VIOLET)
                .replace("<orange>",  ConsoleColors.ExtendedAnsiForeground.ORANGE)
                .replace("<red>", ConsoleColors.BrightForeground.RED)
                .replace("<reset>", ConsoleColors.RESET);
    }
}
