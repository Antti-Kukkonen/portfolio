const express = require('express')
const router = express.Router()

const Controller = require('../controllers/controller')

router.post('/', Controller.createBlog)
router.get('/', Controller.getAllBlogs)
router.get('/:id', Controller.getBlogById)
router.delete('/:id', Controller.deleteBlogById)
router.put('/:id', Controller.updateBlogById)

module.exports = router