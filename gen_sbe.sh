#!/usr/bin/env bash

java \
    -Dsbe.output.dir=src/main/java \
    -Dsbe.java.generate.interfaces=true \
    -jar `find ~/.m2/repository -name 'sbe-all*[[:digit:]].jar' | head -n 1` \
     src/main/sbe/tree.xml
