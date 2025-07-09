#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

DIRNAME=$(dirname "$0")
APP_BASE_NAME=$(basename "$0")

# Resolve the location of the gradle wrapper jar file
CLASSPATH=$DIRNAME/gradle/wrapper/gradle-wrapper.jar

# Execute Gradle wrapper
exec java -Dorg.gradle.appname="$APP_BASE_NAME" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
