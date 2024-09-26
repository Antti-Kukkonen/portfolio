import React from "react"
import "./LandingPage.css"
import { Link } from "react-router-dom"
import { useTranslation } from "react-i18next"

const LandingPage = () => {
    const { t } = useTranslation()

    return (
        <div className="container">
            <div className="main">
                <Link to="/lessons">
                    <button className="Btn">{t("landingpage.lessons")}</button>
                </Link>
                <Link to="/flashcards">
                    <button className="Btn">
                        {t("landingpage.flashcards")}
                    </button>
                </Link>
                <Link to="/quizzes">
                    <button className="Btn">{t("landingpage.quizzes")}</button>
                </Link>
                <Link to="/dictionary">
                    <button className="Btn">
                        {t("landingpage.dictionary")}
                    </button>
                </Link>
            </div>
        </div>
    )
}

export default LandingPage
