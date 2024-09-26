import React, { useEffect, useState } from "react"
import "./Flashcard.css"
import Data from "../../word-data/words1.json"
import { useTranslation } from "react-i18next"

const randomizeCards = (array) => {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1))
        ;[array[i], array[j]] = [array[j], array[i]]
    }
    return array
}

const FlashcardList = () => {
    const [randomCards, setRandomCards] = useState([])
    const [currentCard, setCurrentCard] = useState(0)
    const [flipped, setFlipped] = useState(false)
    const [order, setOrder] = useState("meaning")

    const { t } = useTranslation()

    const handlePlayAudio = () => {
        const audio = new Audio(`./audio/${kanji}.mp3`)
        audio.currentTime = 0
        audio.play()
    }

    useEffect(() => {
        setRandomCards(randomizeCards([...Data]))
    }, [])

    const handleNextCard = () => {
        setCurrentCard((prevIndex) => (prevIndex + 1) % Data.length)
        setFlipped(false)
    }

    const handleFlip = () => {
        setFlipped(!flipped)
    }

    const handleLanguageOrder = () => {
        setOrder(order === "meaning" ? "kanji" : "meaning")
    }

    if (randomCards.length === 0) {
        return <div>Loading...</div>
    }

    const { kanji, meaning } = randomCards[currentCard]

    return (
        <div className="flashcard-container">
            <div
                className={`flashcard ${flipped ? "flipped" : ""}`}
                onClick={handleFlip}
            >
                <div className="card-content">
                    {flipped
                        ? order === "meaning"
                            ? t(`words.${meaning}`)
                            : kanji
                        : order === "kanji"
                        ? t(`words.${meaning}`)
                        : kanji}
                </div>
            </div>
            <div className="flashcard-buttons">
                <button onClick={handleNextCard}>
                    {t("flashcardlist.nextcard")}
                </button>
                <button onClick={handleLanguageOrder}>
                    {t("flashcardlist.switchlanguage")}
                </button>
                <button className="audio-btn" onClick={handlePlayAudio}>
                    {t("flashcardlist.audiobutton")}
                </button>
            </div>
        </div>
    )
}

export default FlashcardList
