const request = require('request');
const fs = require('fs');

// dd if=/dev/zero of=file.txt count=1024 bs=1024

var id = 0;
var interval = parseInt(process.argv[2]);
var sendFile = "dummyfile40mb";
console.log("Sending file: " + sendFile);

function send() {
  var ts = Date.now();
  fs.createReadStream(sendFile).pipe(
    request
      .put('http://13.81.58.48:5600')
      .on('response', function (res) {
        res.body = "";
        res.on('data', function (chunk) {
          res.body += chunk;
        });
        res.on('end', function () {
          var body = JSON.parse(res.body);
          console.log("ID:", id.toString().padEnd(2), "|  Travel time:         ".padEnd(10), body.timeBefore - ts, "ms");
          console.log("       |  Writing to file time:", body.timeAfter - body.timeBefore, "ms");
          id += 1;
        });
      })
      .on('error', function (err) {
        console.error(err)
      })
  );
}

send();
setInterval(send, interval);