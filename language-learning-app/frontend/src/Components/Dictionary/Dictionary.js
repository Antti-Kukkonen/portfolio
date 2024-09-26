import React, { useState } from "react"
import DictionaryItem from "./DictionaryItem"
import words1 from "../../word-data/words1.json"
import "./dictionary.css"
import { useTranslation } from "react-i18next"

const Dictionary = () => {
    const [search, setSearch] = useState("")
    const [words, setWords] = useState(words1)

    const { t } = useTranslation()

    const handleSearchInputChange = (event) => {
        const search = event.target.value
        setSearch(search)
        handleSearch(search)
    }

    const handleSearch = (search) => {
        const matchingContent = words1.filter((word) => {
            return (
                word.kanji.toLowerCase().includes(search.toLowerCase()) ||
                word.meaning.toLowerCase().includes(search.toLowerCase()) ||
                word.onyomi.toLowerCase().includes(search.toLowerCase()) ||
                word.kunyomi.toLowerCase().includes(search.toLowerCase())
            )
        })
        setWords(matchingContent)
    }

    return (
        <div id="dict">
            <div>
                <h1>{t("dictionary.title")}</h1>
                <input
                    type="text"
                    id="search"
                    placeholder={t("dictionary.searchplaceholder")}
                    onChange={handleSearchInputChange}
                    autoComplete="off"
                    value={search}
                />
            </div>
            <article id="dictionary">
                {words.map((word, index) => {
                    return <DictionaryItem key={index} {...word} />
                })}
            </article>
        </div>
    )
}

export default Dictionary
