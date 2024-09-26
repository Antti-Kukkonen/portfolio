import React from "react"
import { Link } from "react-router-dom"
import LessonLink from "./LessonLink"
import { useTranslation } from "react-i18next"
import recordLessonOpen from "./AllLessons"
import { useAuthContext } from "../../hooks/useAuthContext"

const Lesson0 = () => {
    const { t } = useTranslation()
    const { user } = useAuthContext()
    return (
        <div className="lesson-container">
            <LessonLink />
            <h2>[0] {t("lesson.content.zero.title")}</h2>
            <p>{t("lesson.content.zero.c1")}</p>
            <h3>{t("lesson.content.zero.h2")}</h3>
            <p>{t("lesson.content.zero.c2")}</p>
            <h4>{t("lesson.content.zero.h3")}</h4>
            <p>{t("lesson.content.zero.c3")}</p>
            <h4>{t("lesson.content.zero.h4")}</h4>
            <p>{t("lesson.content.zero.c4")}</p>
            <h3>{t("lesson.content.zero.h5")}</h3>
            <p>{t("lesson.content.zero.c5")}</p>
            <h3>{t("lesson.content.zero.h6")}</h3>
            <p>{t("lesson.content.zero.c6")}</p>
            <Link
                to="/lessons/1"
                className="lesson-next"
                onClick={() => {
                    recordLessonOpen(0, user)
                    window.scrollTo({ top: 0, behavior: "smooth" })
                }}
            >
                {t("lesson.next")}
            </Link>
        </div>
    )
}

export default Lesson0
