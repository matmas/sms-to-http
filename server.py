#!/usr/bin/env python2
from flask import Flask, request

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == "GET":
        return 'use POST instead: <form method="POST"><input type="submit" name="test"></form>'
    else:
        print str(request.form)
        return str(request.form)

if __name__ == "__main__":
    app.run("0.0.0.0", debug=True)
