const Contact = require('../models/contact')

const getAllContacts = async (req, res) => {
    try {
        const Contacts = await Contact.find()
        res.status(200).json(Contacts)
    } catch (err) {
        res.status(500).json({ message: "The contacts information could not be retrieved" })
    }
}

const getContactById = async (req, res) => {
    try {
        const id = req.params.id
        const contact = await Contact.findById(id)

        if (!contact) {
            return res.status(404).json({ message: "No contact found with the specified ID" })
        }

        res.status(200).json(contact)
    } catch (err) {
        res.status(500).json({ message: "The contact information could not be retrieved" })
    }
}

const getContactByName = async (req, res) => {
    try {
        const name = req.params.name
        const results = await Contact.find({ name })

        if (!results) {
            return res.status(404).json({ message: 'No contact found with the specified name' });
        }

        res.status(200).json(results)
    } catch (error) {
        res.status(500).json({ error: 'Internal Server Error' })
    }
}


const getContactByEmail = async (req, res) => {
    try {
        const email = req.params.email
        const results = await Contact.find({ email })

        if (!results) {
            return res.status(404).json({ message: 'No contact found with the specified email' });
        }

        res.status(200).json(results)
    } catch (error) {
        res.status(500).json({ error: 'Internal Server Error' })
    }
}

const getContactByPhoneNumber = async (req, res) => {
    try {
        const phoneNumber = req.params.phoneNumber
        const results = await Contact.find({ phoneNumber: phoneNumber })
        

        if (!results) {
            return res.status(404).json({ message: 'No contact found with the specified phone number' });
        }

        res.status(200).json(results)
    } catch (error) {
        res.status(500).json({ error: 'Internal Server Error' })
    }
}

const createContact = async (req, res) => {
    try {
        const { name, phoneNumber, email, address, notes} = req.body

        if (!name || !phoneNumber) {
            return res.status(400).json({ message: "Please provide a name and phone number for the contact" })
        }

        const contact = new Contact({
            name,
            phoneNumber,
            email,
            address,
            notes
        })

        const savedContact = await contact.save()
        res.status(201).json(savedContact)
    } catch (err) {
        res.status(500).json({ message: "There was an internal server error while creating the contact" })
    }
}

const updateContactById = async (req, res) => {
    try {
        const { name, phoneNumber} = req.body
        const id = req.params.id

        if (!name || !phoneNumber) {
            return res.status(400).json({ message: "Please provide a name and phone number for the contact" })
        }

        const updatedContact = await Contact.findByIdAndUpdate(id, req.body, { new: true })
        res.status(200).json(updatedContact)
    } catch (err) {
        res.status(500).json({ message: "The contact information could not be modified" })
    }
}

const deleteContactById = async (req, res) => {
    try {
        const id = req.params.id
        const deletedContact = await Contact.findByIdAndRemove(id)
        res.status(200).json(deletedContact)
    } catch (err) {
        res.status(500).json({ message: "The contact could not be removed" })
    }
}

module.exports = {
    getAllContacts,
    getContactById,
    getContactByName,
    getContactByEmail,
    getContactByPhoneNumber,
    createContact,
    deleteContactById,
    updateContactById
}