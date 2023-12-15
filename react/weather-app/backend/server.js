const express = require('express');
const cors = require('cors');
require('dotenv').config();
const { errorHandler } = require('./middleware/errorMiddleware');
const connectDB = require('./config/db');
const port = process.env.PORT || 5000;
connectDB();

const swaggerUI = require("swagger-ui-express");
const swaggerSpec = require("./swagger.json");

const app = express();

app.use((req, res, next) => {
  console.log(req.path, req.method);
   next();
});

app.use(cors());

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use('/api/favorites', require('./routes/favoriteRoutes'));
app.use('/api/users', require('./routes/userRoutes'));
app.use("/api-docs", swaggerUI.serve, swaggerUI.setup(swaggerSpec));

app.get('/', (req, res) => res.send('<a href="/api-docs">API Documentation</a>'));

app.use(errorHandler);

app.listen(port, () => console.log(`Server started on port ${port}`));
