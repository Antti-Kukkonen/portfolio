import React, { useState, useEffect } from "react"
import { useAuthContext } from "../../hooks/useAuthContext"
import "./ProfilePage.css"
import { lessonList } from "../Lessons/AllLessons"
import { useTranslation } from "react-i18next"

function ProfilePage() {
    const [quizScores, setQuizScores] = useState([])
    const [openedLessons, setOpenedLessons] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)
    const { user } = useAuthContext()

    const { t, i18n } = useTranslation()

    useEffect(() => {
        const fetchScoresAndLessons = async () => {
            setLoading(true)
            try {
                const [scoresResponse, lessonsResponse] = await Promise.all([
                    fetch("http://localhost:5000/api/profile/scores", {
                        headers: {
                            Authorization: `Bearer ${user.token}`,
                        },
                    }),
                    fetch("http://localhost:5000/api/profile/openedLessons", {
                        headers: {
                            Authorization: `Bearer ${user.token}`,
                        },
                    }),
                ])

                if (!scoresResponse.ok) {
                    throw new Error("Could not fetch the scores")
                }
                if (!lessonsResponse.ok) {
                    throw new Error("Could not fetch opened lessons")
                }

                const scoresData = await scoresResponse.json()
                const lessonsData = await lessonsResponse.json()
                setQuizScores(scoresData)
                setOpenedLessons(lessonsData)
            } catch (err) {
                setError(err.message)
            } finally {
                setLoading(false)
            }
        }

        if (user) {
            fetchScoresAndLessons()
        }
    }, [user])

    if (loading) {
        return <div className="loading">{t("profilepage.loading")}</div>
    }

    if (error) {
        return (
            <div className="error">
                {t("profilepage.error")} {error}
            </div>
        )
    }

    return (
        <div className="profile-container">
            <h1>{t("profilepage.profile-page")}</h1>

            {quizScores.length > 0 ? (
                <>
                    <h2>{t("profilepage.quiz-scores")}</h2>
                    <div className="scores-box">
                        <ul>
                            {quizScores.map((scoreObj, index) => (
                                <li key={index}>
                                    {t("profilepage.quiz")} {scoreObj.quizName},{" "}
                                    {t("profilepage.score")} {scoreObj.score},{" "}
                                    {t("profilepage.date")}{" "}
                                    {new Date(
                                        scoreObj.date
                                    ).toLocaleDateString(i18n.language)}
                                </li>
                            ))}
                        </ul>
                    </div>
                </>
            ) : (
                <p>{t("profilepage.no-completed-quizzes")}</p>
            )}
            {openedLessons.length > 0 ? (
                <>
                    <h2>{t("profilepage.completed-lessons")}</h2>
                    <div className="lessons-box">
                        <ul>
                            {openedLessons.map((lesson, index) => {
                                const lessonData = lessonList.find(
                                    (l) => l.nr === lesson.lessonNr
                                )
                                return (
                                    <li key={index}>
                                        {t("profilepage.lesson")}{" "}
                                        {lessonData
                                            ? t(lessonData.title_path)
                                            : "Unknown"}
                                        , {t("profilepage.opened")}{" "}
                                        {new Date(
                                            lesson.dateOpened
                                        ).toLocaleDateString(i18n.language)}
                                    </li>
                                )
                            })}
                        </ul>
                    </div>
                </>
            ) : (
                <p>{t("profilepage.no-completed-lessons")}</p>
            )}
        </div>
    )
}

export default ProfilePage
