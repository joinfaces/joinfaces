JoinFaces
=============================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.joinfaces/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.joinfaces/jsf-spring-boot-starter)
[![Build Status](https://travis-ci.org/joinfaces/joinfaces.svg?branch=master)](https://travis-ci.org/joinfaces/joinfaces)
[![Coverage Status](https://coveralls.io/repos/github/joinfaces/joinfaces/badge.svg?branch=master)](https://coveralls.io/github/joinfaces/joinfaces?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57914d6251500e0049408026/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57914d6251500e0049408026)
[![Javadocs](http://javadoc.io/badge/org.joinfaces/jsf-spring-boot-build.svg)](http://javadoc.io/doc/org.joinfaces/jsf-spring-boot-build)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project enables [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) usage inside JAR packaged [Spring Boot](http://projects.spring.io/spring-boot/) Application.

It autoconfigures [PrimeFaces](http://primefaces.org/), [PrimeFaces Extensions](http://primefaces-extensions.github.io/), [BootsFaces](http://bootsfaces.net/), [ButterFaces](http://butterfaces.org/), [RichFaces](https://github.com/richfaces/richfaces), [OmniFaces](http://omnifaces.org/), [AngularFaces](http://angularfaces.net/), [Mojarra](https://javaserverfaces.java.net/) and [MyFaces](http://myfaces.apache.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io) servlet containers.

It also aims to solve [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) and [Spring Boot](http://projects.spring.io/spring-boot/) integration features. Current version includes [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) and [CDI](http://www.cdi-spec.org/) annotations support and [Spring Security](http://projects.spring.io/spring-security/) JSF Facelet Tag support.


## How to use

[JoinFaces Example](https://github.com/joinfaces/joinfaces-maven-jar-example) shows JSF Spring Boot Starter usage. It may help you to choose the JSF Spring Boot Starter that fits your needs.

You can find more examples [here.](https://github.com/joinfaces/joinfaces/wiki/Examples-of-JSF-Spring-Boot-Starter-usage)

## Key Features

### Twenty four JSF Spring Boot Starters available

There are twenty four JSF Spring Boot Starters available. Each starter autoconfigures one Servlet Container, one JSF Implementation and compatible JSF Libraries.

See detailed starters [here](https://github.com/joinfaces/joinfaces/wiki/JSF-Spring-Boot-Starters).

Note that all starters include the following libraries

Library | Dependency | Description
------------ | ------------- | -------------
[omnifaces](http://omnifaces.org/) | [1.14](http://search.maven.org/#artifactdetails\|org.omnifaces\|omnifaces\|1.14\|jar) | Utility library for JSF 2
[cdi-api](http://www.cdi-spec.org/) | [1.2](http://search.maven.org/#artifactdetails\|javax.enterprise\|cdi-api\|1.2\|jar) | Api of CDI (Contexts and Dependency Injection)

### JSF properties configuration via application.properties or application.yml

Library | Namespace | Example
------------ | ------------- | ---------
standard (javax.faces) | jsf | `jsf.project-stage=development`
[primefaces](http://primefaces.org/) | jsf.primefaces | `jsf.primefaces.theme=overcast`
[bootsfaces](http://bootsfaces.net/) | jsf.bootsfaces | `jsf.bootsfaces.usetheme=true`
[butterfaces](http://butterfaces.org/) | jsf.butterfaces | `jsf.butterfaces.provide-j-query=true`
[richfaces](https://github.com/richfaces/richfaces) | jsf.richfaces | `jsf.richfaces.resource-default-ttl=86400`
[omnifaces](http://omnifaces.org/) | jsf.omnifaces | `jsf.omnifaces.faces-views-enabled=true`
[angularfaces](http://angularfaces.net/) | jsf.angularfaces | `jsf.angularfaces.add-labels=true`
[mojarra](https://javaserverfaces.java.net/) | jsf.mojarra | `jsf.mojarra.prefer-xhtml=true`
[myfaces](http://myfaces.apache.org/) | jsf.myfaces | `jsf.myfaces.pretty-html=true`
[jetty](http://www.eclipse.org/jetty) | jsf.jetty | `jsf.jetty.class-path-resource=META-INF/resources`
[undertow](http://undertow.io) | jsf.undertow | `jsf.undertow.class-path-resource=META-INF/resources`

Look full [application.yml](https://github.com/joinfaces/joinfaces/blob/master/jsf-spring-boot-autoconfigure/src/test/resources/application.yml) example.

Additional information in [#22](https://github.com/joinfaces/joinfaces/issues/22)

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

Additional information in [#29](https://github.com/joinfaces/joinfaces/issues/29)

## System Requirements

By default, JSF Spring Boot Starter requires [Java 7](http://java.com) and [Spring Boot 1.5.7](http://projects.spring.io/spring-boot/) or above. 

Additionnal information [here.](https://github.com/joinfaces/joinfaces/wiki/System-Requirements)

## Getting Help

* Take a look at [wiki](https://github.com/joinfaces/joinfaces/wiki).
* Report questions and bugs at [github.com/joinfaces/joinfaces/issues](https://github.com/joinfaces/joinfaces/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/joinfaces/joinfaces/issues](https://github.com/joinfaces/joinfaces/issues).
* Pull requests are welcome.
