import { useState } from "react"
import { useLogin } from "../../hooks/useLogin"
import { useNavigate } from "react-router-dom"
import "./login.css"
import { useTranslation } from "react-i18next"

const Login = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const { login, error } = useLogin()
    const navigate = useNavigate()

    const { t } = useTranslation()

    const handleSubmit = async (e) => {
        e.preventDefault()
        const response = await login(email, password)
        if (!response) {
            navigate("/")
            window.location.reload()
        }
    }

    return (
        <form className="login-container" onSubmit={handleSubmit}>
            <h3>{t("login.login")}</h3>

            <label htmlFor="email">{t("login.email")}</label>
            <input
                type="email"
                onChange={(e) => setEmail(e.target.value)}
                value={email}
            ></input>
            <label htmlFor="password">{t("login.password")}</label>
            <input
                type="password"
                onChange={(e) => setPassword(e.target.value)}
                value={password}
            ></input>

            <button
                type="submit"
                disabled={email === ""}
                className="login-button"
            >
                {t("login.login")}
            </button>
            {error && <div className="error">{error}</div>}
        </form>
    )
}

export default Login
