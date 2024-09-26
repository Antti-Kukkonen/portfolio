import React from "react"
import { Link } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import { useAuthContext } from "../../hooks/useAuthContext"
import { useTranslation } from "react-i18next"
import "./Lessons.css"

export const lessonList = [
    {
        nr: 0,
        title: "Intro - The Japanese language",
        title_path: "lesson.content.zero.title",
    },
    {
        nr: 1,
        title: "Kana",
        title_path: "lesson.content.one.title",
    },
    {
        nr: 2,
        title: "Kanji",
        title_path: "lesson.content.two.title",
    },
    {
        nr: 3,
        title: "Numbers and counting",
        title_path: "lesson.content.three.title",
    },
    {
        nr: 4,
        title: "Particles",
        title_path: "lesson.content.four.title",
    },
    {
        nr: 5,
        title: "Verbs",
        title_path: "lesson.content.five.title",
    },
    {
        nr: 6,
        title: "Adjectives",
        title_path: "lesson.content.six.title",
    },
]

export const recordLessonOpen = async (lessonNr, user) => {
    const token = user?.token
    const lessonData = lessonList.find((lesson) => lesson.nr === lessonNr)

    if (!lessonData) {
        console.error("Lesson data not found for lesson number:", lessonNr)
        return
    }

    try {
        await fetch("http://localhost:5000/api/lesson/open", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
                lessonNr,
                title: lessonData.title,
                path: lessonData.title_path,
            }),
        })
    } catch (error) {
        console.error("Error recording lesson open", error)
    }
}

const AllLessons = () => {
    const navigate = useNavigate()
    const { t } = useTranslation()
    const { user } = useAuthContext()

    const handleLessonClick = (lessonNr, user) => {
        navigate(`/lessons/${lessonNr}`)
        recordLessonOpen(lessonNr, user)
    }

    return (
        <section id="lesson-list">
            {lessonList.map((lesson) => (
                <Link
                    to={`/lessons/${lesson.nr}`}
                    className="lesson-list-component-title"
                    onClick={() => handleLessonClick(lesson.nr, user)}
                >
                    <article key={lesson.nr} className="lesson-list-component">
                        <header>
                            <h3 className="lesson-list-component-nr">{`[${lesson.nr}]`}</h3>
                        </header>
                        <p>{t(lesson.title_path)}</p>
                    </article>
                </Link>
            ))}
        </section>
    )
}

export default AllLessons
