import React, { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"
import { useAuthContext } from "../../hooks/useAuthContext"
import { useLogout } from "../../hooks/useLogout"
import "./navbar.css"
import { useTranslation } from "react-i18next"

const Navbar = () => {
    const { user } = useAuthContext()
    const navigate = useNavigate()
    const { logout } = useLogout()
    const { t, i18n } = useTranslation()

    const handleLogout = () => {
        const userLearningReportKey = `learningReport-${user.email}`
        localStorage.removeItem(userLearningReportKey)
        logout()
        navigate("/")
    }

    const locales = {
        en: { title: 'English' },
        jp: { title: '日本語' },
        fi: { title: 'Suomi' },
        sv: { title: 'Svenska' },
        ar: { title: 'العربية' }
    }

    const [selectedLanguage, setSelectedLanguage] = useState(localStorage.getItem('selectedLanguage') || 'en');

    const handleLanguageChange = (e) => {
        const selectedLocale = e.target.value;
        setSelectedLanguage(selectedLocale);
        localStorage.setItem('selectedLanguage', selectedLocale);

        i18n.changeLanguage(selectedLocale);
        window.location.reload();
    }

    useEffect(() => {
        const storedLanguage = localStorage.getItem('selectedLanguage');
        if (storedLanguage) {
            setSelectedLanguage(storedLanguage);
        }
    }, []);


    return (
        <div className="navbar-container">
            <Link to="/" className="titlelink">
                <div className="title-container">
                    <h1 className="Title">Japan Egg</h1>
                    <h2 className="Subtitle">{t('navbar.subtitle')}</h2>
                </div>
            </Link>
            <div className="login-logout-container">
                {user && (
                    <div className="email-logout">
                        <p>{user.email}</p>
                        <Link to="/profilePage">{t('navbar.profile')}</Link>
                        <button onClick={handleLogout}>{t('navbar.logout')}</button>
                    </div>
                )}
                {!user && (
                    <div className="login-signup">
                        <Link to="/login">{t('navbar.login')}</Link>
                        <Link to="/signup">{t('navbar.signup')}</Link>
                    </div>
                )}
            </div>
            <div className="language-switch-container">
                <select onChange={handleLanguageChange} value={selectedLanguage}>
                    {Object.keys(locales).map((locale) => (
                        <option key={locale} value={locale}>
                            {locales[locale].title}
                        </option>
                    ))}
                </select>
            </div>
        </div>
    )
}

export default Navbar
