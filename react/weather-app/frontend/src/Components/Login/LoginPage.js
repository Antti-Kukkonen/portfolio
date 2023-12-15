import React, { useState } from 'react';
import './LoginPage.css';
import { useLogin } from '../../Hooks/useLogin';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const { login, isLoading, error} = useLogin();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();


    const handleSubmit = async (e) => {
        e.preventDefault();
        const res = await login(email, password);
        if (!res) navigate("/");
    };

return (
    <div className='login-container'>
        <form onSubmit={handleSubmit} className='login-form'>
            <label htmlFor='email'>Email</label>
            <input
            type="text"
            placeholder="Email"
            autoComplete='email'
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            />  

            <label htmlFor='password'>Password</label>
            <input
            type="password"
            placeholder="Password"
            autoComplete="new-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            />
            {error && (<p style={{color: "red"}} className='error-msg'>{error}</p>)}

            <button type='submit' disabled={isLoading}>Login</button>
        </form>
    </div>
);


};

export default LoginPage;