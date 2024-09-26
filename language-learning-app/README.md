# Japan Egg

Japan Egg is a web-based language learning application that focuses on teaching the Japanese language built with the MERN stack. The app will focus on imparting the fundamentals of the writing system and core aspects of grammar, presenting basic vocabulary in an intuitive manner that reinforces overall comprehension.

The vision for our language learning app is to empower individuals interested in the Japanese language to embark on a seamless learning journey. Our app focuses on imparting the fundamentals of the writing system and core aspects of grammar, presenting basic vocabulary in an intuitive manner that reinforces overall comprehension.

The app incorporates features such as visible user progression tracking, custom lessons that comprehensively cover the fundamentals of the Japanese language, and supplementary tools like flashcards for effective review. Users can engage in quizzes to assess their understanding, and an in-app dictionary will be provided for quick reference to the vocabulary used in the lessons.

## Installation

A tool like [npm](https://nodejs.org/en/download) is required to install dependencies and run the service.

The project divided into a frontend and backend. You need to seperately install the dependencies and run them.

```bash
cd language-learning-app
npm install
npm start
```

```bash
cd backend
npm install
npm start
```

## Docker

Docker container can be built to include the backend and frontend:

```bash
docker-compose build
```

to run the container use:

```bash
docker-compose up
```

## Jenkins

Jenkinsfile can be used to run the tests and push the docker images to Docker hub. Set up the ID in jenkins then include your repo and ID in the Jenkinsfile

## Authors

-   [Arc4517](https://github.com/Arc4517)
-   [jommi123](https://github.com/jommi123)
-   [TaaviNat](https://github.com/TaaviNat)
-   [iikkamalmi](https://github.com/iikkamalmi)
