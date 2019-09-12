const express = require('express');
const bodyParser = require('body-parser');

const { findPrimeFactors } = require('./prime-factors');

const app = express();
app.use(bodyParser.json());

app.post('/', function (req, res) {
  const result = req.body.input.map(input => findPrimeFactors(input));
  res.send({ factors: result });
})

app.listen(3000, () => {
  console.log("Listening on port 3000…");
});
