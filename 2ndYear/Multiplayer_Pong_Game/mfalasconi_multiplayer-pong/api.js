const express = require('express');
const path = require('path');
const api = express();

api.use(express.static('public'));

api.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

module.exports = api;