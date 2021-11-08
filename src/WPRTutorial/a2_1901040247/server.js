const express = require("express");
const { MongoClient, Db, Collection } = require("mongodb");
const { ObjectID } = require("bson");
const app = express();

// serve static files
app.use(express.static(__dirname + "/public"));
app.use(express.json());

// db setup
const DB_NAME = "attempts";
const MONGO_URL = `mongodb://localhost:27017/${DB_NAME}`;

/** @type {Db} */
let db;
let id = null;

app.post("/attempts", async (_, res) => {
  const questions = [];
  id = ObjectID();

  // create a new Attempt with 10 random questions from the questions collection
  const data = await db.collection("questions").aggregate([{ $sample: { size: 10 }}]).toArray();

  data.forEach((ele, index) => {
    questions[index] = {
      _id: ele._id,
      answers: ele.answers,
      text: ele.text
    }
  });

  // create Attempt (contains attempt ID & an array of questions)
  const resBody = {
    _id: id,
    questions: questions,
    startedAt: new Date(),
    completed: false
  };
  console.log(data);
  return res.status(201).json(resBody).end();
});

// app.post("/attempts/:id/submit", async (req, res) => {
// });

const setupDb = async () => {
  const client = await MongoClient.connect(MONGO_URL);
  db = client.db();
};

app.listen(3000, async () => {
  await setupDb();
  console.log('Server running!');
});