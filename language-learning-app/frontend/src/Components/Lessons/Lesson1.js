import React from "react"
import { Link } from "react-router-dom"
import { ReactComponent as KanaDevSVG } from "./assets/1-kana_dev_chart.svg"
import HiraganaKatakanaChart from "../HiraganaKatakana/HiraganaKatakanaChart"
import LessonLink from "./LessonLink"
import { useTranslation } from "react-i18next"
import recordLessonOpen from "./AllLessons"
import { useAuthContext } from "../../hooks/useAuthContext"

const Lesson1 = () => {
    const { t } = useTranslation()
    const { user } = useAuthContext()
    return (
        <div className="lesson-container">
            <LessonLink />
            <h2>[1] {t("lesson.content.one.title")}</h2>
            <p>{t("lesson.content.one.c1-1")}</p>
            <figure>
                <KanaDevSVG />
                <figcaption>
                    {t("lesson.content.one.f1")}
                    <a href="https://commons.wikimedia.org/wiki/File:FlowRoot3824.svg">
                        https://commons.wikimedia.org/wiki/File:FlowRoot3824.svg
                    </a>
                </figcaption>
            </figure>
            <p>{t("lesson.content.one.c1-2")}</p>
            <HiraganaKatakanaChart />
            <h3>{t("lesson.content.one.h2")}</h3>
            <h3>{t("lesson.content.one.h3")}</h3>
            <Link
                to="/lessons/2"
                className="lesson-next"
                onClick={() => {
                    recordLessonOpen(1, user)
                    window.scrollTo({ top: 0, behavior: "smooth" })
                }}
            >
                {t("lesson.next")}
            </Link>
        </div>
    )
}

export default Lesson1
