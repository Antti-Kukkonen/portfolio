const sinon = require("sinon")
const jwt = require("jsonwebtoken")
const assert = require("assert")

const authMiddleware = require("../middleware/authMiddleware")

describe("AuthMiddleware", () => {
    describe("protect", () => {
        afterEach(() => {
            sinon.restore()
        })

        it("should respond with correct error when no token is provided", async () => {
            const req = { headers: {} }
            const res = {
                status: sinon.stub().returnsThis(),
                json: sinon.stub(),
            }
            const next = sinon.stub()

            await authMiddleware.protect(req, res, next)

            assert(res.status.calledWith(401))
            assert(
                res.json.calledWith({ error: "authorization token required" })
            )
            assert(!next.called)
        })

        it("should respond with correct error when invalid token is provided", async () => {
            const req = { headers: { authorization: "Bearer invalid_token" } }
            const res = {
                status: sinon.stub().returnsThis(),
                json: sinon.stub(),
            }
            const next = sinon.stub()

            sinon.stub(jwt, "verify").throws(new Error("Invalid token"))

            await authMiddleware.protect(req, res, next)

            assert(res.status.calledWith(401))
            assert(res.json.calledWith({ error: "request is not authorized" }))
            assert(!next.called)
        })
    })
})
