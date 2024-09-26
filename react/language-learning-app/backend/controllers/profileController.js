const User = require('../models/users');

const getQuizScores = async (req, res) => {
    try {
        const user = await User.findById(req.user._id).select('quizScores');
        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }
        res.json(user.quizScores);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Server error' });
    }
};

const getOpenedLessons = async (req, res) => {
    try {
      const user = await User.findById(req.user._id).select('openedLessons');
      if (!user) {
        return res.status(404).json({ message: 'User not found' });
      }
      res.json(user.openedLessons);
    } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Server error' });
    }
  };

module.exports = {
    getOpenedLessons,
    getQuizScores,
};
