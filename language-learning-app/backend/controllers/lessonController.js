const User = require("../models/users")

const recordLessonOpen = async (req, res) => {
    const { lessonNr, title, path } = req.body

    console.log("Recording lesson open:", req.body)

    try {
        const user = await User.findById(req.user._id)
        if (!user) {
            console.error("User not found")
            return res.status(404).json({ error: "User not found" })
        }

        const lessonAlreadyOpened = user.openedLessons.some(
            (lesson) => lesson.lessonNr === lessonNr
        )
        if (!lessonAlreadyOpened) {
            user.openedLessons.push({ lessonNr, title, path })
            await user.save()
            console.log(`Lesson ${lessonNr} opened for user ${req.user._id}`)
        }

        res.status(200).json({ message: "Lesson open recorded" })
    } catch (error) {
        if (error.name === "ValidationError") {
            return res.status(400).json({ error: error.message })
        }
        console.error("Error recording lesson open:", error)
        res.status(500).json({ error: error.message })
    }
}

module.exports = { recordLessonOpen }
