const express = require('express');
const bodyParser = require('body-parser');

const {
  findPrimeFactors
} = require('./prime-factors');

const app = express();
app.use(bodyParser.json());

app.post('/', function (req, res) {
  const result = req.body.numbers.map(function (input) {
    return {
      number: input,
      factors: findPrimeFactors(input)
    }
  });
  res.send(result);
})

app.listen(3000, () => {
  console.log("Listening on port 3000…");
});