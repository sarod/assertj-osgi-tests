# How To 

To reproduce the assertj osgi issue #1160

## Install latest assertj-core 3.10 into your local maven repository

* Checkout assertj-core
* run `./mvnw`

## Build a p2 site and expose it locally:

* checkout this repository
* in directory assertj-p2
* Run `mvn p2:site && mvn jetty:run`

This will:
* build a p2 repository containing assertj-core:3.8.0 and assertj-core:3.10.0-SNAPSHOT.
* Run a jetty to expose it at http://localhost:8080/site

Note that the update site does not expose assertj-core 3.9 because I wasn't able to make p2-maven-plugin work for this version

## Run the repro case

* Modify /assertj-tycho-lotr.tests/META-INF/MANIFEST.MF to use the assertj version you want to test (that must be a version exposed by the p2 site above)
** Use org.assertj.core;bundle-version="3.10.0", to use assert j 3.10.0
** Use org.assertj.core;bundle-version="3.8.0", to use assert j 3.8.0
* Run `mvn clean install`
** This compile and execute the tests in assertj-tycho-tests/assertj-tycho-lotr.tests/src using an osgi environment.



