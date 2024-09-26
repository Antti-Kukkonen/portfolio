const fs = require("fs")
const { exec } = require("child_process")

exec("eslint -f html src -o eslint-report.html", (error, stdout, stderr) => {
    if (error) {
        console.error(`Error running ESLint: ${error.message}`)
        return
    }
    if (stderr) {
        console.error(`ESLint encountered an error: ${stderr}`)
        return
    }
    htmlContent = fs.readFileSync("eslint-report.html", "utf8")
    // Replace absolute paths with relative paths to make the report more portable
    const search = process.cwd()
    const replace = "."
    const updatedContent = htmlContent.replaceAll(search, replace)
    fs.writeFileSync("eslint-report.html", updatedContent)
})
