const mongoose = require('mongoose');

const locationSchema = new mongoose.Schema({
    locationName: {
        type: String,
        required: true,
    },
    latitude: {
        type: Number,
        required: true,
    },
    longitude: {
        type: Number,
        required: true,
    }
});

const favoriteSchema = new mongoose.Schema(
  {
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User', // Reference to the user who added this favorite
      required: true,
    },
    locations: [locationSchema]
  },
  { timestamps: true }
);

module.exports = mongoose.model('Favorite', favoriteSchema);
