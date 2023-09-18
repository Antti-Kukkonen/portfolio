const express = require('express')
const router = express.Router()

const Controller = require('../controllers/controller')

router.post('/', Controller.createContact)
router.get('/', Controller.getAllContacts)
router.get('/:id', Controller.getContactById)
router.get('/name/:name', Controller.getContactByName)
router.get('/email/:email', Controller.getContactByEmail)
router.get('/phone/:phoneNumber', Controller.getContactByPhoneNumber)
router.delete('/:id', Controller.deleteContactById)
router.put('/:id', Controller.updateContactById)

module.exports = router