import "./HiraganaKatakanaChart.css"
import React, { useState } from "react"

const HiraganaKatakanaChart = () => {
    const hiragana = [
        ["", "a", "i", "u", "e", "o"],
        ["k", "ka", "ki", "ku", "ke", "ko"],
        ["", "か", "き", "く", "け", "こ"],
        ["s", "sa", "shi", "su", "se", "so"],
        ["", "さ", "し", "す", "せ", "そ"],
        ["t", "ta", "chi", "tsu", "te", "to"],
        ["", "た", "ち", "つ", "て", "と"],
        ["n", "na", "ni", "nu", "ne", "no"],
        ["", "な", "に", "ぬ", "ね", "の"],
        ["h", "ha", "hi", "fu", "he", "ho"],
        ["", "は", "ひ", "ふ", "へ", "ほ"],
        ["m", "ma", "mi", "mu", "me", "mo"],
        ["", "ま", "み", "む", "め", "も"],
        ["y", "ya", "", "yu", "", "yo"],
        ["", "や", "", "ゆ", "", "よ"],
        ["r", "ra", "ri", "ru", "re", "ro"],
        ["", "ら", "り", "る", "れ", "ろ"],
        ["w", "wa", "", "", "", "wo"],
        ["", "わ", "", "", "", "を"],
    ]

    const katakana = [
        ["", "a", "i", "u", "e", "o"],
        ["k", "ka", "ki", "ku", "ke", "ko"],
        ["", "カ", "キ", "ク", "ケ", "コ"],
        ["s", "sa", "shi", "su", "se", "so"],
        ["", "サ", "シ", "ス", "セ", "ソ"],
        ["t", "ta", "chi", "tsu", "te", "to"],
        ["", "タ", "チ", "ツ", "テ", "ト"],
        ["n", "na", "ni", "nu", "ne", "no"],
        ["", "ナ", "ニ", "ヌ", "ネ", "ノ"],
        ["h", "ha", "hi", "fu", "he", "ho"],
        ["", "ハ", "ヒ", "フ", "ヘ", "ホ"],
        ["m", "ma", "mi", "mu", "me", "mo"],
        ["", "マ", "ミ", "ム", "メ", "モ"],
        ["y", "ya", "", "yu", "", "yo"],
        ["", "ヤ", "", "ユ", "", "ヨ"],
        ["r", "ra", "ri", "ru", "re", "ro"],
        ["", "ラ", "リ", "ル", "レ", "ロ"],
        ["w", "wa", "", "", "", "wo"],
        ["", "ワ", "", "", "", "ヲ"],
    ]

    const [currentData, setCurrentData] = useState(hiragana)
    const [active, setActive] = useState(1)

    const toggleData = () => {
        setActive(active === 1 ? 2 : 1)
        setCurrentData(active === 1 ? katakana : hiragana)
    }

    return (
        <div className="grid-container">
            <div className="switch-container" onClick={toggleData}>
                <div className={`switch-data ${active === 1 ? "active" : ""}`}>
                    {`Hiragana`}
                </div>
                <div className={`switch-data ${active === 2 ? "active" : ""}`}>
                    {`Katakana`}
                </div>
            </div>
            <table>
                <tbody>
                    {currentData.map((row, rowIndex) => (
                        <tr key={rowIndex}>
                            {row.slice(0).map((cell, columnIndex) => (
                                <td key={columnIndex}>{cell}</td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default HiraganaKatakanaChart
