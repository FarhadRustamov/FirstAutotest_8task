#!/bin/bash

BASE_URL=$1
BROWSER=$2
BROWSER_VERSION=$3

mvn clean test -Dbase.url="$BASE_URL" -Dbrowser="$BROWSER" -Dbrowser.version="$BROWSER_VERSION"

