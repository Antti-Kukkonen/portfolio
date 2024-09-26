const express = require('express');
const { protect } = require('../middleware/authMiddleware');
const { getQuizScores, getOpenedLessons } = require('../controllers/profileController');
const router = express.Router();

router.get('/scores', protect, getQuizScores);
router.get('/openedLessons', protect, getOpenedLessons);

module.exports = router;
