const connectDB = require('./config/db')
const express = require('express')
const contactsRouter = require('./routers/contacts')
const app = express()
const PORT = 3001;

connectDB()

app.use(express.json())

app.use('/api/contacts', contactsRouter)

app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).json({ message: "Internal server error" });
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
})