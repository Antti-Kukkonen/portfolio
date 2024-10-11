package com.example.parliamentapp.fake

import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Fake data source for testing purposes.
 */
object FakeDataSource {
    val fakeParliamentMembers = listOf(
        ParliamentMember(
            hetekaId = 1,
            seatNumber = 1,
            firstname = "Matti",
            lastname = "Meikäläinen",
            party = "kesk",
            minister = false,
            pictureUrl = "https://avoindata.eduskunta.fi/tiedostot/kuvat/123"
        ),
        ParliamentMember(
            hetekaId = 2,
            seatNumber = 2,
            firstname = "Maija",
            lastname = "Meikäläinen",
            party = "kok",
            minister = false,
            pictureUrl = "https://avoindata.eduskunta.fi/tiedostot/kuvat/456"
        ),
        ParliamentMember(
            hetekaId = 3,
            seatNumber = 3,
            firstname = "Liisa",
            lastname = "Meikäläinen",
            party = "sd",
            minister = false,
            pictureUrl = "https://avoindata.eduskunta.fi/tiedostot/kuvat/789"
        ),
        ParliamentMember(
            hetekaId = 4,
            seatNumber = 4,
            firstname = "Kalle",
            lastname = "Meikäläinen",
            party = "ps",
            minister = false,
            pictureUrl = "https://avoindata.eduskunta.fi/tiedostot/kuvat/101112"
        )
    )

    val fakeParliamentMemberExtras = listOf(
        ParliamentMemberExtra(
            hetekaId = 1,
            twitter = "https://twitter.com/mattimeikäläinen",
            bornYear = 1970,
            constituency = "Helsinki"
        ),
        ParliamentMemberExtra(
            hetekaId = 2,
            twitter = "https://twitter.com/maijameikäläinen",
            bornYear = 1980,
            constituency = "Uusimaa"
        ),
    )

    var fakeParliamentMemberNotes = mutableListOf(
        ParliamentMemberNote(
            hetekaId = 1,
            comment = "Hyvä tyyppi",
            rating = 5
        ),
        ParliamentMemberNote(
            hetekaId = 2,
            comment = "Kamala",
            rating = 1
        ),
    )
}
