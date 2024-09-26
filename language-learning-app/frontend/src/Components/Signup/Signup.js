import { useState } from "react"
import { useSignup } from "../../hooks/useSignup"
import { useNavigate } from "react-router-dom"
import zxcvbn from "zxcvbn"
import "./signup.css"
import { useTranslation } from "react-i18next"

const Signup = () => {
    const [email, setEmail] = useState("")
    const [emailError, setEmailError] = useState("")
    const [password, setPassword] = useState("")
    const [score, setScore] = useState(0)
    const { signup, error, isLoading } = useSignup()
    const navigate = useNavigate()

    const { t } = useTranslation()

    const handleSubmit = async (e) => {
        e.preventDefault()
        await signup(email, password)
        navigate("/")
        window.location.reload()
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value)
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/
        if (emailRegex.test(e.target.value)) {
            setEmailError("success")
        } else {
            setEmailError("fail")
        }
    }

    const handlePasswordChange = (e) => {
        setPassword(e.target.value)
        const score = zxcvbn(e.target.value)
        setScore(score.score)
    }

    return (
        <form className="signup-container" onSubmit={handleSubmit}>
            <h3>{t("signup.signup")}</h3>

            <label htmlFor="email">{t("signup.email")}</label>
            <input
                type="email"
                value={email}
                autoComplete="email"
                onChange={handleEmailChange}
                className={emailError}
            ></input>
            <label htmlFor="password">{t("signup.password")}</label>
            <input
                type="password"
                value={password}
                autoComplete="new-password"
                onChange={handlePasswordChange}
                className={score === 0 ? "" : score > 2 ? "success" : "fail"}
            />
            <meter max="4" value={score} low={3} high={4} optimum={4} />
            <span
                style={
                    password.length === 0
                        ? { visibility: "hidden" }
                        : { visibility: "visible" }
                }
            >
                {score === 0 && "Very weak"}
                {score === 1 && "Weak"}
                {score === 2 && "Meh"}
                {score === 3 && "Okay"}
                {score === 4 && "Strong"}
            </span>
            {error && <p className="error-msg">{error}</p>}
            <button
                type="submit"
                className="signup-button"
                disabled={isLoading || score < 3 || emailError === "fail"}
            >
                {t("signup.signup")}
            </button>
        </form>
    )
}

export default Signup
