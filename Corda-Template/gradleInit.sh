#!/bin/bash

BASEDIR=$(dirname "$0")

echo
echo "Para 'Select type of project to generate:' selecione 'basic'"
echo "Para 'Select build script DSL:' selecione 'Groovy'"
echo

gradle init

cat "${BASEDIR}/gradleInit_main.config" > ./build.gradle

MODULES=( contracts workflows clients)

for module in "${MODULES[@]}"
do
  mkdir -p ${module}/src/main/kotlin
  mkdir -p ${module}/src/test/kotlin
  cat "${BASEDIR}/gradleInit_${module}.config" > ${module}/build.gradle
  echo "include '${module}'" >> settings.gradle
done






