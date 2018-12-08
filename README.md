JoinFaces
=============================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.joinfaces/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.joinfaces/jsf-spring-boot-starter)
[![Build Status](https://travis-ci.org/joinfaces/joinfaces.svg?branch=master)](https://travis-ci.org/joinfaces/joinfaces)
[![Coverage Status](https://coveralls.io/repos/github/joinfaces/joinfaces/badge.svg?branch=master)](https://coveralls.io/github/joinfaces/joinfaces?branch=master)
[![codecov](https://codecov.io/gh/joinfaces/joinfaces/branch/master/graph/badge.svg)](https://codecov.io/gh/joinfaces/joinfaces)
[![Javadocs](http://javadoc.io/badge/org.joinfaces/joinfaces.svg)](http://javadoc.io/doc/org.joinfaces/joinfaces)
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

### Many JoinFaces Starters available

There are many JSF Spring Boot Starters available: basic starters, utility starters, meta starter, component starters, theme starters and extra starters.

See detailed starters [here](https://github.com/joinfaces/joinfaces/wiki/JoinFaces-Starters-3.x).

### JSF properties configuration via application.properties or application.yml

Library | Namespace | Example
------------ | ------------- | ---------
[javax.faces](https://jcp.org/en/jsr/detail?id=372) | joinfaces.jsf | `joinfaces.jsf.project-stage=development`
[primefaces](http://primefaces.org/) | joinfaces.primefaces | `joinfaces.primefaces.theme=overcast`
[bootsfaces](http://bootsfaces.net/) | joinfaces.bootsfaces | `joinfaces.bootsfaces.usetheme=true`
[butterfaces](http://butterfaces.org/) | joinfaces.butterfaces | `joinfaces.butterfaces.provide-j-query=true`
[adminfaces](http://adminfaces.github.io/site/) | joinfaces.adminfaces | `joinfaces.adminfaces.skin=skin-blue`
[icefaces](http://adminfaces.github.io/site/) | joinfaces.icefaces | `joinfaces.icefaces.compressDom=true`
[richfaces](https://github.com/richfaces/richfaces) | joinfaces.richfaces | `joinfaces.richfaces.resource-default-ttl=86400`
[omnifaces](http://omnifaces.org/) | joinfaces.omnifaces | `joinfaces.omnifaces.faces-views-enabled=true`
[angularfaces](http://angularfaces.net/) | joinfaces.angularfaces | `joinfaces.angularfaces.add-labels=true`
[mojarra](https://javaserverfaces.java.net/) | joinfaces.mojarra | `joinfaces.mojarra.prefer-xhtml=true`
[myfaces](http://myfaces.apache.org/) | joinfaces.myfaces | `joinfaces.myfaces.pretty-html=true`
[jetty](http://www.eclipse.org/jetty) | joinfaces.jetty | `joinfaces.jetty.class-path-resource=META-INF/resources`
[undertow](http://undertow.io) | joinfaces.undertow | `joinfaces.undertow.class-path-resource=META-INF/resources`
[rewrite](https://www.ocpsoft.org/rewrite/) | joinfaces.rewrite | `joinfaces.rewrite.configReloading=true`

### JSF and CDI annotations support automatically

Annotation |
------- |
[@NoneScoped](http://docs.oracle.com/javaee/7/api/javax/faces/bean/NoneScoped.html) |
[@RequestScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/RequestScoped.html) |
[@ViewScoped](http://docs.oracle.com/javaee/7/api/javax/faces/view/ViewScoped.html) |
[@SessionScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/SessionScoped.html) |
[@ApplicationScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/ApplicationScoped.html) |

### Spring Security JSF Facelet Tag support

Namespace: **http://www.springframework.org/security/tags**

Tag | Description
------------ | -------------
`authorize` | perform Spring Security authorization decisions, using attributes: ifAnyGranted, ifAllGranted, ifNotGranted, access, url, method, var.
`anonymous` | verify if the user is anonymous.
`authenticated` | verify if the user is not anonymous.
`fullyAuthenticated` | verify if the is not an anonymous or a remember-me user.

Function | Description
------------ | -------------
`areAllGranted` | returns true if the user has all of of the given authorities.
`areAnyGranted` | returns true if the user has any of the given authorities.
`areNotGranted` | returns true if the user does not have any of the given authorities.
`isAllowed` | returns true if the user is allowed to access the given URL and HTTP method combination.
`isAnonymous` | returns true if user is anonymous.
`isAuthenticated` | returns true if the user is not anonymous.
`isFullyAuthenticated` | returns true if the is not an anonymous or a remember-me user.

## System Requirements

Java | Spring Boot | JSF
--------------|-----|------
`1.8` to `11` |`2.1`|`2.0` to `2.3`

This are the combinations we have tested and expect to work, but depending on the features you are using, other combinations might work, too.
When using Java 9 or higher, make sure to use JoinFaces only on the classpath and not on the modulepath.

## Getting Help

* Take a look at [wiki](https://github.com/joinfaces/joinfaces/wiki).
* Report questions and bugs at [github.com/joinfaces/joinfaces/issues](https://github.com/joinfaces/joinfaces/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/joinfaces/joinfaces/issues](https://github.com/joinfaces/joinfaces/issues).
* Pull requests are welcome.
