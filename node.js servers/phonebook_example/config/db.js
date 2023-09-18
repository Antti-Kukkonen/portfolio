const dotenv = require('dotenv');
const mongoose = require('mongoose');
dotenv.config();

const MONGO_URI = process.env.MONGO_URI;

const connectDB = async () => {
    const conn = await mongoose.connect(MONGO_URI);
    console.log(`Connected to the database`);
}

module.exports = connectDB;