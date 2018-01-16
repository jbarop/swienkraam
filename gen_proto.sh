#!/usr/bin/env bash

protoc --java_out=src/main/java src/main/protobuf/tree.proto
