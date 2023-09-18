const Blog = require('../models/blog')

const getAllBlogs = async (req, res) => {
    try {
        const blogs = await Blog.find()
        res.status(200).json(blogs)
    } catch (err) {
        res.status(500).json({ message: "The blogs information could not be retrieved" })
    }
}

const getBlogById = async (req, res) => {
    try {
        const id = req.params.id
        const blog = await Blog.findById(id)

        if (!blog) {
            return res.status(404).json({ message: "No blog found with the specified ID" })
        }

        res.status(200).json(blog);
    } catch (err) {
        res.status(500).json({ message: "The blog information could not be retrieved" })
    }
}

const createBlog = async (req, res) => {
    try {
        const { title, body, author } = req.body

        if (!title || !body || !author) {
            return res.status(400).json({ message: "Please provide title, body and author for the blog" })
        }

        const blog = new Blog({
            title,
            body,
            author,
        })

        const savedBlog = await blog.save()
        res.status(201).json(savedBlog)
    } catch (err) {
        res.status(500).json({ message: "There was an internal server error while creating the blog" })
    }
}

const updateBlogById = async (req, res) => {
    try {
        const { title, body, author } = req.body
        const id = req.params.id

        if (!title || !body || !author) {
            return res.status(400).json({ message: "Please provide title, body and author for the blog" })
        }

        const updatedBlog = await Blog.findByIdAndUpdate(id, req.body, { new: true })
        res.status(200).json(updatedBlog)
    } catch (err) {
        res.status(500).json({ message: "The blog information could not be modified" })
    }
}

const deleteBlogById = async (req, res) => {
    try {
        const id = req.params.id
        const deletedBlog = await Blog.findByIdAndRemove(id)
        res.status(200).json(deletedBlog)
    } catch (err) {
        res.status(500).json({ message: "The blog could not be removed" })
    }
}

module.exports = {
    getAllBlogs,
    getBlogById,
    createBlog,
    deleteBlogById,
    updateBlogById
}