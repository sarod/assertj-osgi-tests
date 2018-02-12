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
 * Use org.assertj.core;bundle-version="3.10.0", to use assert j 3.10.0
 * Use org.assertj.core;bundle-version="3.8.0", to use assert j 3.8.0
* Run `mvn clean install`
 * This compile and execute the tests in assertj-tycho-tests/assertj-tycho-lotr.tests/src using an osgi environment.




Using assertj-core 3.8.0 this fails with a Test in error
```
customAssertionsShouldWorkInOsgiRuntime(org.assertj.examples.lotr.CustomSoftAssertionsTest)  Time elapsed: 0.088 sec  <<< ERROR!
org.assertj.core.internal.cglib.core.CodeGenerationException: java.lang.reflect.InvocationTargetException-->null
	at org.eclipse.osgi.internal.loader.BundleLoader.findClassInternal(BundleLoader.java:484)
	at org.eclipse.osgi.internal.loader.BundleLoader.findClass(BundleLoader.java:395)
	at org.eclipse.osgi.internal.loader.BundleLoader.findClass(BundleLoader.java:387)
	at org.eclipse.osgi.internal.loader.ModuleClassLoader.loadClass(ModuleClassLoader.java:150)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.assertj.core.internal.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:459)
	at org.assertj.core.internal.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:336)
	at org.assertj.core.internal.cglib.proxy.Enhancer.generate(Enhancer.java:492)
	at org.assertj.core.internal.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:93)
	at org.assertj.core.internal.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:91)
	at org.assertj.core.internal.cglib.core.internal.LoadingCache$2.call(LoadingCache.java:54)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at org.assertj.core.internal.cglib.core.internal.LoadingCache.createEntry(LoadingCache.java:61)
	at org.assertj.core.internal.cglib.core.internal.LoadingCache.get(LoadingCache.java:34)
	at org.assertj.core.internal.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:116)
	at org.assertj.core.internal.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:291)
	at org.assertj.core.internal.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
	at org.assertj.core.internal.cglib.proxy.Enhancer.create(Enhancer.java:324)
	at org.assertj.core.api.SoftProxies.create(SoftProxies.java:42)
	at org.assertj.core.api.AbstractSoftAssertions.proxy(AbstractSoftAssertions.java:31)
	at org.assertj.examples.lotr.assertions.MyProjectSoftAssertions.assertThat(MyProjectSoftAssertions.java:8)
	at org.assertj.examples.lotr.CustomSoftAssertionsTest.customAssertionsShouldWorkInOsgiRuntime(CustomSoftAssertionsTest.java:15)
```

And as of today it fails using assertj master (3.10.0) with a tycho error
```
[ERROR] Cannot resolve project dependencies:
[ERROR]   Software being installed: assertj-tycho-lotr.tests 1.0.0.qualifier
[ERROR]   Missing requirement: org.assertj.core 3.10.0.20180212123738 requires 'package org.assertj.core.internal.bytebuddy.jar.asm.tree 0.0.0' but it could not be found
[ERROR]   Cannot satisfy dependency: assertj-tycho-lotr.tests 1.0.0.qualifier depends on: bundle org.assertj.core 3.10.0
```
