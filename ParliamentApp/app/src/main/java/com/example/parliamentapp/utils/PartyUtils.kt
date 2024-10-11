package com.example.parliamentapp.utils

import androidx.compose.ui.graphics.Color

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Utility function for handling party data.
 * Raw data from the API is converted to displayable format.
 */
fun partyRawToDisplay(party: String): String {
    when (party) {
        "sd" -> return "SDP"

        "kok" -> return "Kok."

        "vihr" -> return "Vihr."

        "kesk" -> return "Kesk."

        "vas" -> return "Vas."

        "r" -> return "RKP"

        "ps" -> return "PS"

        "kd" -> return "KD"

        "liik" -> return "Liik."

        else -> return party
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Utility function for displaying party colors.
 */
fun partyRawToColor(party: String): Color {
    when (party) {
        "sd" -> return Color(0xfff44d4e)

        "kok" -> return Color(0xff066286)

        "vihr" -> return Color(0xff62bf35)

        "kesk" -> return Color(0xff0b9550)

        "vas" -> return Color(0xffbe2028)

        "r" -> return Color(0xffffe8ba)

        "ps" -> return Color(0xffffdf65)

        "kd" -> return Color(0xff2d66c5)

        "liik" -> return Color(0xffb32077)

        else -> return Color(0x00000000)
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Utility function for getting the color of text on a party color surface.
 */
fun partyRawToTextColor(party: String): Color {
    when (party) {
        "sd" -> return Color(0xffffffff)

        "kok" -> return Color(0xffffffff)

        "vihr" -> return Color(0xffffffff)

        "kesk" -> return Color(0xffffffff)

        "vas" -> return Color(0xffffffff)

        "r" -> return Color(0xff000000)

        "ps" -> return Color(0xff000000)

        "kd" -> return Color(0xffffffff)

        "liik" -> return Color(0xffffffff)

        else -> return Color(0xffffffff)
    }
}