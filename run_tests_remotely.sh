#!/bin/bash

BASE_URL=""
BROWSER=""
VERSION=""
SERVER_URL=""

while [[ $# -gt 0 ]]; do
  case $1 in
    --base_url)
      BASE_URL=$2
      shift 2
      ;;
    --browser)
      BROWSER=$2
      shift 2
      ;;
    --version)
      VERSION=$2
      shift 2
      ;;
    --server_url)
      SERVER_URL=$2
      shift 2
      ;;
    *)
      echo "Error argument $2 not supported"
      exit 1
    esac
done

mvn clean test -Dbase.url="$BASE_URL" -Dbrowser="$BROWSER" -Dbrowser.version="$VERSION" -DremoteServerUrl="$SERVER_URL"

