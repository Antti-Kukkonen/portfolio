import React from "react"
import FlashcardList from "./Components/Flashcards/FlashcardList"
import LandingPage from "./Components/LandingPage/LandingPage"
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import Dictionary from "./Components/Dictionary/Dictionary"
import Quiz from "./Components/Quizzes/Quiz"
import Login from "./Components/Login/Login"
import Signup from "./Components/Signup/Signup"
import Navbar from "./Components/Navbar/Navbar"
import ProfilePage from "./Components/Profile/ProfilePage"

// lessons
import AllLessons from "./Components/Lessons/AllLessons"
import Lesson0 from "./Components/Lessons/Lesson0"
import Lesson1 from "./Components/Lessons/Lesson1"
import Lesson2 from "./Components/Lessons/Lesson2"

function App() {
    const lessonComponents = [Lesson0, Lesson1, Lesson2]

    return (
        <Router>
            <Navbar />
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/flashcards" element={<FlashcardList />} />
                <Route path="/dictionary" element={<Dictionary />} />
                <Route path="/quizzes" element={<Quiz />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/lessons" element={<AllLessons />} />
                <Route path="/profilePage" element={<ProfilePage />} />
                {lessonComponents.map((LessonComponent, index) => (
                    <Route
                        key={index}
                        path={`/lessons/${index}`}
                        element={<LessonComponent />}
                    />
                ))}
            </Routes>
        </Router>
    )
}

export default App
