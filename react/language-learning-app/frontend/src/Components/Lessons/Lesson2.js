import React from "react"
import { Link } from "react-router-dom"
import LessonLink from "./LessonLink"
import { useTranslation } from "react-i18next"
import recordLessonOpen from "./AllLessons"
import { useAuthContext } from "../../hooks/useAuthContext"

const Lesson2 = () => {
    const { t } = useTranslation()
    const { user } = useAuthContext()
    return (
        <div className="lesson-container">
            <LessonLink />
            <h2>[2] {t("lesson.content.two.title")}</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <Link
                to="/lessons/3"
                className="lesson-next"
                onClick={() => {
                    recordLessonOpen(2, user)
                    window.scrollTo({ top: 0, behavior: "smooth" })
                }}
            >
                {t("lesson.next")}
            </Link>
        </div>
    )
}

export default Lesson2
