const http = require('http');

const hostname = '127.0.0.1';
const port = 3000;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'application/json');
  const result = {};
  result.data = 'Hello World';
  result.time = new Date();
  result['rest-service'] = 'NodeJS';
  result['server'] = 'NodeJS';
  result['java-home'] = '';
  res.end(JSON.stringify(result));
});

server.listen(hostname, port, () => {
	console.log(`Server running at http://${hostname}:${port}/`);
});