const supertest = require("supertest")
const mongoose = require("mongoose")
const app = require("../app.js")
const User = require("../models/users.js")
const expect = require("expect.js")

const request = supertest(app)

describe("User API Tests", () => {
    before(async () => {
        await mongoose.connect(process.env.TEST_MONGO_URI)
    })

    after(async () => {
        await mongoose.connection.close()
    })

    const testUser = {
        email: "test@test.com",
        password: "TestPassword1!",
        quizScores: [
            { score: 90, quizId: "quiz1", quizName: "Quiz 1" },
            { score: 85, quizId: "quiz2", quizName: "Quiz 2" },
        ],
        openedLessons: [
            { lessonNr: 1, title: "Lesson 1", path: "/lessons/lesson1" },
            { lessonNr: 2, title: "Lesson 2", path: "/lessons/lesson2" },
        ],
    }

    describe("signup tests", () => {
        beforeEach(async () => {
            await User.deleteMany({})
        })

        it("should create a new user with valid credentials", async () => {
            const response = await request
                .post("/api/user/signup")
                .send({
                    email: "test@test.com",
                    password: "TestPassword1!",
                })
                .expect(201)

            expect(response.body.email).to.equal("test@test.com")
            expect(response.body).to.have.property("token")
        })

        it("should return an error when fields are missing", async () => {
            const response = await request
                .post("/api/user/signup")
                .send({
                    password: "TestPassword1!",
                })
                .expect(400)

            expect(response.body.error).to.equal("All fields must be filled")

            const response2 = await request
                .post("/api/user/signup")
                .send({
                    email: "test@test.com",
                })
                .expect(400)

            expect(response2.body.error).to.equal("All fields must be filled")
        })

        it("should return an error when the email is already in use", async () => {
            await User.create({
                email: "existing@example.com",
                password: "testPassword",
            })

            const response = await request
                .post("/api/user/signup")
                .send({
                    email: "existing@example.com",
                    password: "testPassword",
                })
                .expect(400)

            expect(response.body.error).to.equal("Email already in use")
        })
    })

    describe("login tests", () => {
        beforeEach(async () => {
            await User.deleteMany({})
            const response = await request
                .post("/api/user/signup")
                .send({
                    email: "logintest@test.com",
                    password: "TestPassword1!",
                })
                .expect(201)
        })

        it("should log in a user with valid credentials", async () => {
            const response = await request
                .post("/api/user/login")
                .send({
                    email: "logintest@test.com",
                    password: "TestPassword1!",
                })
                .expect(200)

            expect(response.body).to.have.property("token")
            expect(response.body.email).to.equal("logintest@test.com")
        })

        it("should return an error when fields are missing", async () => {
            const response = await request
                .post("/api/user/login")
                .send({
                    password: "TestPassword1!",
                })
                .expect(400)

            expect(response.body.error).to.equal("All fields must be filled")

            const response2 = await request
                .post("/api/user/login")
                .send({
                    email: "test@example.com",
                })
                .expect(400)

            expect(response2.body.error).to.equal("All fields must be filled")
        })

        it("should return an error when the email is incorrect", async () => {
            const response = await request
                .post("/api/user/login")
                .send({
                    email: "notanemailaddress.fi",
                    password: "TestPassword1!",
                })
                .expect(400)

            expect(response.body.error).to.equal("Incorrect email")
        })

        it("should return an error when the password is incorrect", async () => {
            const response = await request
                .post("/api/user/login")
                .send({
                    email: "logintest@test.com",
                    password: "incorrectPassword",
                })
                .expect(400)

            expect(response.body.error).to.equal("Incorrect password")
        })
    })

    describe("quizScores tests", () => {
        beforeEach(async () => {
            await User.deleteMany({})
        })

        it("should add quiz scores to the user", async () => {
            const newUser = await User.create(testUser)

            const fetchedUser = await User.findOne({ email: testUser.email })

            expect(fetchedUser.quizScores).to.have.length(
                testUser.quizScores.length
            )
            testUser.quizScores.forEach((expectedScore, index) => {
                const fetchedScore = fetchedUser.quizScores[index]
                expect(fetchedScore.score).to.equal(expectedScore.score)
                expect(fetchedScore.quizId).to.equal(expectedScore.quizId)
                expect(fetchedScore.quizName).to.equal(expectedScore.quizName)
            })
        })
    })

    describe("openedLessons tests", () => {
        beforeEach(async () => {
            await User.deleteMany({})
        })

        it("should add opened lessons to the user", async () => {
            const newUser = await User.create(testUser)
            const fetchedUser = await User.findOne({ email: testUser.email })

            expect(fetchedUser.openedLessons).to.have.length(
                testUser.openedLessons.length
            )
            testUser.openedLessons.forEach((expectedLesson, index) => {
                const fetchedLesson = fetchedUser.openedLessons[index]
                expect(fetchedLesson.lessonNr).to.equal(expectedLesson.lessonNr)
                expect(fetchedLesson.title).to.equal(expectedLesson.title)
                expect(fetchedLesson.path).to.equal(expectedLesson.path)
            })
        })
    })
})
