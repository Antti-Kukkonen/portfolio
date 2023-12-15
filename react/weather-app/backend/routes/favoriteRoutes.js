const express = require('express');
const router = express.Router();
const {
    getFavorites,
    setFavorite,
    deleteFavorite,
} = require('../controllers/favoriteController');
const { protect } = require('../middleware/authMiddleware.js');

router.get('/', protect, getFavorites);
router.post('/', protect, setFavorite);
router.delete('/:id', protect, deleteFavorite);

module.exports = router;