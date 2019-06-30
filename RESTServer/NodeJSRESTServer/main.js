const http = require('http');

const port = 8085;

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

server.listen(port, () => {
	//console.log(`Server running at http://${hostname}:${port}/`);
	console.log(`Server running at port ${port}`);
});