#!/bin/bash

./gradlew installDist && cd ./build/install/ls/bin && mv ls jls && cd -
