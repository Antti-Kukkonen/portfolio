import React from "react"
import { useTranslation } from "react-i18next"

const DictionaryItem = (props) => {
    const audio = new Audio(`./audio/${props.kanji}.mp3`)

    const { t } = useTranslation()

    const handlePlayAudio = () => {
        audio.currentTime = 0
        audio.play()
    }

    return (
        <section id={props.kanji}>
            <h2>{props.kanji}</h2>
            <dl>
                <dt>
                    <i>{t("dictionaryitem.meaning")}</i>
                </dt>
                <dd>{t(`words.${props.meaning}`)}</dd>
                <dt>
                    <i>On'yomi</i>
                </dt>
                <dd>{props.onyomi}</dd>
                <dt>
                    <i>Kun'yomi</i>
                </dt>
                <dd>{props.kunyomi}</dd>
            </dl>
            <button className="audio-btn" onClick={handlePlayAudio}>
                {t("dictionaryitem.audiobutton")}
            </button>
        </section>
    )
}

export default DictionaryItem
