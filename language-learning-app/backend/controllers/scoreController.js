const User = require('../models/users');

const postScore = async (req, res) => {
    const { score, quizId, quizName } = req.body;
    
    if (score === undefined || !quizId || !quizName) {
        return res.status(400).json({ error: "All fields must be filled" });
    }

    try {
        const user = await User.findById(req.user._id);
        if (!user) {
            return res.status(404).json({ error: "User not found" });
        }

        user.quizScores.push({ score, quizId, quizName, date: new Date() });
        await user.save();

        res.status(200).json({ message: "Score saved", scoreData: { score, quizId, quizName } });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
};

module.exports = { postScore };
