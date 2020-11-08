#!/bin/bash
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)