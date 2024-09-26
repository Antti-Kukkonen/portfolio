const express = require('express');
const { protect } = require('../middleware/authMiddleware');
const router = express.Router();
const { postScore } = require('../controllers/scoreController');

router.post('/', protect, postScore);

module.exports = router;
