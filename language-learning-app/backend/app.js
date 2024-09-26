require('dotenv').config();

const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors'); // Require the cors package
const userRoutes = require('./routes/userRoutes');
const scoreRoutes = require('./routes/scoreRoutes');
const profileRoutes = require('./routes/profileRoutes');
const lessonRoutes = require('./routes/lessonRoutes');
const morgan = require('morgan');


const app = express();

app.use(cors()); // Use cors middleware for all routes
app.use(express.json());
app.use(morgan('dev'));


app.use('/api/user', userRoutes);
app.use('/api/score', scoreRoutes);
app.use('/api/profile', profileRoutes);
app.use('/api/lesson', lessonRoutes);


module.exports = app;
