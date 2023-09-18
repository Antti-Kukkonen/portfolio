import React, { useState } from 'react'
import zxcvbn from 'zxcvbn'

const SignupPage = () => {
    const [email, setEmail] = useState('')
    const [emailError, setEmailError] = useState('')
    const [password, setPassword] = useState('')
    const [score, setScore] = useState(0)

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log('email', email)
        console.log('password', password)
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value)
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (emailRegex.test(e.target.value)) {
            setEmailError('success')
        } else {
            setEmailError('fail')
        }
    }

    const handlePasswordChange = (e) => {
        setPassword(e.target.value)
        const score = zxcvbn(e.target.value)
        setScore(score.score)
    }

    return (
        <div className='signup-container'>
            <form onSubmit={handleSubmit} className='signup-form'>
                <label htmlFor='email'>Email</label>
                <input
                type="text"
                placeholder="Email"
                autoComplete='email'
                value={email}	
                onChange={handleEmailChange}
                className={emailError}
                />
                <label htmlFor='password'>Password</label>
                <input
                type="password"
                placeholder="Password"
                autoComplete="new-password"
                value={password}
                onChange={handlePasswordChange}
                className={score === 0 ? '' : score > 2 ? 'success' : 'fail'}
                />
                <meter max="4" value={score} low={3} high={4} optimum={4}/>
                <span style={
                    password.length === 0 ? {visibility: 'hidden'} : {visibility: 'visible'}
                }>
                    {score === 0 && 'Very weak'}
                    {score === 1 && 'Weak'}
                    {score === 2 && 'Meh'}
                    {score === 3 && 'Okay'}
                    {score === 4 && 'Strong'}
                </span>
                <button type='submit' className='signup-button'>Sign Up</button>
            </form>
        </div>
    )
}

export default SignupPage