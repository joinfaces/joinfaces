JoinFaces
=============================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.joinfaces/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.joinfaces/jsf-spring-boot-starter)
[![Build Status](https://travis-ci.org/joinfaces/joinfaces.svg?branch=master)](https://travis-ci.org/joinfaces/joinfaces)
[![codecov](https://codecov.io/gh/joinfaces/joinfaces/branch/master/graph/badge.svg)](https://codecov.io/gh/joinfaces/joinfaces)
[![](http://img.shields.io/badge/javadoc-stable-green.svg)](https://docs.joinfaces.org/current/api)
[![](http://img.shields.io/badge/reference%20guide-stable-green.svg)](https://docs.joinfaces.org/current/reference)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project enables [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) usage inside [Spring Boot](http://projects.spring.io/spring-boot/) Application.

It autoconfigures [PrimeFaces](http://primefaces.org/), [PrimeFaces Extensions](http://primefaces-extensions.github.io/), [AdminFaces](https://adminfaces.github.io/site/), [BootsFaces](http://bootsfaces.net/), [ButterFaces](http://butterfaces.org/), [IceFaces](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf), [RichFaces](https://github.com/richfaces/richfaces), [OmniFaces](http://omnifaces.org/), [AngularFaces](http://angularfaces.net/), [Mojarra](https://javaserverfaces.java.net/) and [MyFaces](http://myfaces.apache.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io) servlet containers. It autoconfigures [Weld](http://weld.cdi-spec.org) and [Rewrite](https://www.ocpsoft.org/rewrite/) too.

It also aims to solve [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) and [Spring Boot](http://projects.spring.io/spring-boot/) integration features. Current version includes [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) and [CDI](http://www.cdi-spec.org/) annotations support and [Spring Security](http://projects.spring.io/spring-security/) JSF Facelet Tag support.

## How to use

[JoinFaces Example](https://github.com/joinfaces/joinfaces-maven-jar-example) shows JSF Spring Boot Starter usage. It may help you to choose the JSF Spring Boot Starter that fits your needs.

### Official example projects

|Build tool \ packaging| `jar` | `war`
|---|---|---|
|[Maven](https://maven.apache.org/)|[maven-jar-example](https://github.com/joinfaces/joinfaces-maven-jar-example)|[maven-war-example](https://github.com/joinfaces/joinfaces-maven-war-example)|
|[Gradle](https://gradle.org/)|[gradle-jar-example](https://github.com/joinfaces/joinfaces-gradle-jar-example)|[gradle-war-example](https://github.com/joinfaces/joinfaces-gradle-war-example)|

## Key Features

Take a look at [Reference Guide](https://docs.joinfaces.org/master-SNAPSHOT/reference/) to see JoinFaces features: Starters, Servlet-Context Init Parameters, JSF and CDI scope-annotations support and Spring Security JSF Facelet Tag support.

## System Requirements

Joinfaces | Java | Spring Boot | JSF
----------|------|-------------|-----
`2.x`     |`1.6` to `1.8`|`1.x`|`2.0` to `2.2`
`3.0` to `3.2`|`1.8`|`2.0`|`2.0` to `2.3`
`4.0.x`   |`1.8` to `11` |`2.1`|`2.0` to `2.3`
`4.1.x`   |`1.8` to `12` |`2.2`|`2.0` to `2.3`

This are the combinations we have tested and expect to work, but depending on the features you are using, other combinations might work, too.
When using Java 9 or higher, make sure to use JoinFaces only on the classpath and not on the modulepath.

## Getting Help

* Take a look at [wiki](https://github.com/joinfaces/joinfaces/wiki).
* Report questions and bugs at [github.com/joinfaces/joinfaces/issues](https://github.com/joinfaces/joinfaces/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/joinfaces/joinfaces/issues](https://github.com/joinfaces/joinfaces/issues).
* Pull requests are welcome.
