#!/bin/bash

mvn clean test -Dbase.url="https://otus.home.kartushin.su" -Dbrowser="chrome" -Dbrowser.version="124.0" -DremoteServerUrl="http://193.104.57.173/wd/hub"

