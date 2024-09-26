FROM node

ENV PORT=5000 \
    MONGO_URI=mongodb+srv://japyap:japYap-App5150@japyap-app.yno9k5i.mongodb.net/?retryWrites=true&w=majority&appName=japyap-app \
    TEST_MONGO_URI=mongodb+srv://language_test_db:lHeo0vSUgO8SUR7X@languageapptests.yftl7vs.mongodb.net/ \
    JWT_SECRET=8db4000959503252b8c960e14c5a60856bb4f8263afea6ccb6db04c441ed85b3 \
    REACT_APP_API_KEY=0ce1952691af9072f674d7cde74e58ba=value

RUN npm install -g nodemon

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 5000

CMD ["npm", "run", "dev"]

