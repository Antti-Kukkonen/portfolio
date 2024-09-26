const mongoose = require("mongoose")
const bcrypt = require("bcrypt")

const userSchema = new mongoose.Schema({
    email: { type: String, required: true, unique: true },
    password: { type: String, required: true },
    createdAt: { type: Date, default: Date.now },
    updatedAt: { type: Date, default: Date.now },
    quizScores: [
        {
            score: { type: Number, required: true },
            quizId: { type: String, required: true },
            quizName: { type: String, required: true },
            date: { type: Date, default: Date.now },
        },
    ],
    openedLessons: [
        {
            lessonNr: Number,
            title: { type: String, required: true },
            path: { type: String, required: true },
            dateOpened: { type: Date, default: Date.now },
        },
    ],
})

// static signup method
userSchema.statics.signup = async function (email, password) {
    // validation
    if (!email || !password) {
        throw Error("All fields must be filled")
    }
    // email validation should happen with confirmation email

    const exists = await this.findOne({ email })

    if (exists) {
        throw Error("Email already in use")
    }

    const hash = await bcrypt.hash(password, 10)

    const user = await this.create({ email, password: hash })

    return user
}

// static login method
userSchema.statics.login = async function (email, password) {
    if (!email || !password) {
        throw Error("All fields must be filled")
    }

    const user = await this.findOne({ email })
    if (!user) {
        throw Error("Incorrect email")
    }

    const match = await bcrypt.compare(password, user.password)
    if (!match) {
        throw Error("Incorrect password")
    }

    return user
}

module.exports = mongoose.model("User", userSchema)
