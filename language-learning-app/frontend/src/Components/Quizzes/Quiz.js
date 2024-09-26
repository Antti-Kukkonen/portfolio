import React, { useState, useEffect } from "react"
import shuffleArray from "./shuffleArray"
import { useTranslation } from "react-i18next"
import correctSound from "./Right.mp3"
import incorrectSound from "./Wrong.mp3"
import "./Quiz.css"
import { useAuthContext } from "../../hooks/useAuthContext"

function Quiz() {
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0)
    const [questions, setQuestions] = useState([])
    const [score, setScore] = useState(0)
    const [showScore, setShowScore] = useState(false)
    const { t, i18n } = useTranslation()
    const { user } = useAuthContext()
    const [selectedQuiz, setSelectedQuiz] = useState(null)

    useEffect(() => {
        let quizData
        if (i18n.language === "en") {
            quizData = require("./QuizDataSets_en").allDataSets
        } else if (i18n.language === "jp") {
            quizData = require("./QuizDataSets_jp").allDataSets
        } else if (i18n.language === "ar") {
            quizData = require("./QuizDataSets_ar").allDataSets
        } else if (i18n.language === "fi") {
            quizData = require("./QuizDataSets_fi").allDataSets
        } else if (i18n.language === "sv") {
            quizData = require("./QuizDataSets_sv").allDataSets
        } else {
            quizData = require("./QuizDataSets_en").allDataSets
        }

        const categoryKeys = Object.keys(quizData)
        const randomCategoryKey =
            categoryKeys[Math.floor(Math.random() * categoryKeys.length)]
        const randomDatasetArray = quizData[randomCategoryKey]

        const selectedQuizSet =
            randomDatasetArray[
                Math.floor(Math.random() * randomDatasetArray.length)
            ]

        if (selectedQuizSet) {
            setSelectedQuiz({
                id: selectedQuizSet.id,
                name: selectedQuizSet.name,
            })

            const questionsClone = JSON.parse(
                JSON.stringify(selectedQuizSet.questions)
            )
            shuffleArray(questionsClone)

            questionsClone.forEach((question) => {
                shuffleArray(question.options)
            })

            setQuestions(questionsClone)
        }
    }, [i18n.language])

    const sendScoreToBackend = async (finalScore, quizId, quizName) => {
        const token = user?.token

        if (!user) {
            console.error("User must be logged in to send score")
            return
        }

        try {
            const response = await fetch("http://localhost:5000/api/score", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({ score: finalScore, quizId, quizName }),
            })

            const data = await response.json()
            if (response.ok) {
                console.log("Score saved", data)
            } else {
                throw new Error(data.error)
            }
        } catch (error) {
            console.error("Error saving score", error)
        }
    }

    const handleOptionClick = async (isCorrect) => {
        const sound = new Audio(isCorrect ? correctSound : incorrectSound)
        sound.play()

        const updatedScore = isCorrect ? score + 1 : score

        const nextQuestion = currentQuestionIndex + 1
        if (nextQuestion < questions.length) {
            setScore(updatedScore)
            setCurrentQuestionIndex(nextQuestion)
        } else {
            setShowScore(true)
            if (selectedQuiz && selectedQuiz.id && selectedQuiz.name) {
                await sendScoreToBackend(
                    updatedScore,
                    selectedQuiz.id,
                    selectedQuiz.name
                )
            } else {
                console.error("No quiz selected or quiz lacks ID/name")
            }
        }
    }

    return (
        <div className="quiz">
            {!showScore && <h2>{selectedQuiz?.name}</h2>}
            {showScore ? (
                <div className="score-section">
                    <h3 className="quiz-name">{selectedQuiz?.name}</h3>
                    <p className="quiz-score">
                        {t("quiz.score")
                            .replace("%a", score)
                            .replace("%b", questions.length)}
                    </p>
                </div>
            ) : (
                questions.length > 0 && (
                    <>
                        <div className="question-section">
                            <div className="question-count">
                                <span>
                                    {t("quiz.question")}{" "}
                                    {currentQuestionIndex + 1}
                                </span>
                                /{questions.length}
                            </div>
                            <div className="question-text">
                                {questions[currentQuestionIndex].question}
                            </div>
                        </div>
                        <div className="option-section">
                            {questions[currentQuestionIndex].options.map(
                                (option, index) => (
                                    <button
                                        key={index}
                                        onClick={() =>
                                            handleOptionClick(
                                                option ===
                                                    questions[
                                                        currentQuestionIndex
                                                    ].correctAnswer
                                            )
                                        }
                                    >
                                        {option}
                                    </button>
                                )
                            )}
                        </div>
                    </>
                )
            )}
        </div>
    )
}

export default Quiz
