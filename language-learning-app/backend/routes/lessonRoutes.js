const express = require('express');
const { protect } = require('../middleware/authMiddleware');
require('../controllers/lessonController');
const router = express.Router();
const { recordLessonOpen } = require('../controllers/lessonController');

router.post('/open', protect, recordLessonOpen);

module.exports = router;