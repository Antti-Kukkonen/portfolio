import React from "react"
import { Link } from "react-router-dom"
import { useTranslation } from "react-i18next"

const LessonLink = () => {
    const { t } = useTranslation()
    return (
        <Link to="/lessons" className="lesson-back-navigation">
            <h2>{t("lesson.back")}</h2>
        </Link>
    )
}

export default LessonLink
