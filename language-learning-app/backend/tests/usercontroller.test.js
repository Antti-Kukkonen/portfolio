const assert = require('assert');
const sinon = require('sinon');
const jwt = require('jsonwebtoken');
const UserController = require('../controllers/userController');
const User = require('../models/users');

describe('UserController', () => {
    describe('signupUser', () => {
        it('should return status 201 and a token when signup is successful', async () => {
            const req = { body: { email: 'test@example.com', password: 'password' } };
            const res = {
                status: sinon.stub().returnsThis(),
                json: sinon.stub()
            };
            const user = { _id: 'someUserId', email: 'test@example.com' };
            sinon.stub(User, 'signup').resolves(user);
            sinon.stub(jwt, 'sign').returns('someToken');

            await UserController.signupUser(req, res);

            sinon.assert.calledWith(res.status, 201);
            sinon.assert.calledWith(res.json, { email: 'test@example.com', token: 'someToken' });

            User.signup.restore();
            jwt.sign.restore();
        });

        it('should return status 400 and an error message when signup fails', async () => {
            const req = { body: { email: 'test@example.com', password: 'password' } };
            const res = {
                status: sinon.stub().returnsThis(),
                json: sinon.stub()
            };
            sinon.stub(User, 'signup').rejects(new Error('Signup failed'));

            await UserController.signupUser(req, res);

            sinon.assert.calledWith(res.status, 400);
            sinon.assert.calledWith(res.json, { error: 'Signup failed' });

            User.signup.restore();
        });
    });

    describe('loginUser', () => {
        it('should return status 200 and a token when login is successful', async () => {
            const req = { body: { email: 'test@example.com', password: 'password' } };
            const res = {
                status: sinon.stub().returnsThis(),
                json: sinon.stub()
            };
            const user = { _id: 'someUserId', email: 'test@example.com' };
            sinon.stub(User, 'login').resolves(user);
            sinon.stub(jwt, 'sign').returns('someToken');

            await UserController.loginUser(req, res);

            sinon.assert.calledWith(res.status, 200);
            sinon.assert.calledWith(res.json, { email: 'test@example.com', token: 'someToken' });

            User.login.restore();
            jwt.sign.restore();
        });

        it('should return status 400 and an error message when login fails', async () => {
            const req = { body: { email: 'test@example.com', password: 'password' } };
            const res = {
                status: sinon.stub().returnsThis(),
                json: sinon.stub()
            };
            sinon.stub(User, 'login').rejects(new Error('Login failed'));

            await UserController.loginUser(req, res);

            sinon.assert.calledWith(res.status, 400);
            sinon.assert.calledWith(res.json, { error: 'Login failed' });

            User.login.restore();
        });
    });
});
