const Favorite = require('../models/favoriteModel');


// @desc    Get user's favorite locations
// @route   GET /api/favorites
// @access  Private
const getFavorites = async (req, res) => {
  try {
    // Fetch favorite locations for the authenticated user (via userId)
    const favorites = await Favorite.find({ userId: req.user._id });

    res.status(200).json(favorites);
  } catch (error) {
    res.status(500).json({error: error.message});
  }
};

// @desc    Add a new favorite location
// @route   POST /api/favorites
// @access  Private
const setFavorite = async (req, res) => {
  try {
    const { locationName, latitude, longitude } = req.body;

    const newLocation = {
      locationName,
      latitude,
      longitude,
    };

    const duplicate = await Favorite.findOne({
      userId: req.user._id,
      'locations.locationName': locationName,
    });

    if (duplicate) {
      return res.status(400).json({ error: 'Location already added' });
    }

    const favorite = await Favorite.findOneAndUpdate(
      { userId: req.user._id },
      { $push: { locations: newLocation } },
      { new: true, upsert: true }
    );

    res.status(201).json(favorite);
  } catch (error) {
    res.status(500).json({error: error.message});
  }
};

// @desc    Delete a favorite location by ID
// @route   DELETE /api/favorites/:id
// @access  Private
const deleteFavorite = async (req, res) => {
  try {
    const favorite = await Favorite.findOneAndUpdate(
      { userId: req.user._id },
      { $pull: { locations: { _id: req.params.id } } },
      { new: true }
    );
    
    if (!favorite) {
      return res.status(404).json({ error: 'Favorite location not found' });
    }
    res.status(200).send(favorite);
  } catch (error) {
    res.status(500).json({error: error.message});
  }
};

module.exports = {
  getFavorites,
  setFavorite,
  deleteFavorite,
};
