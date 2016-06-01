JSF Spring Boot Starter
=============================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter)
[![Build Status](https://travis-ci.org/persapiens/jsf-spring-boot-starter.svg?branch=master)](https://travis-ci.org/persapiens/jsf-spring-boot-starter)
[![Coverage Status](https://coveralls.io/repos/github/persapiens/jsf-spring-boot-starter/badge.svg?branch=master)](https://coveralls.io/github/persapiens/jsf-spring-boot-starter?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/573daf0bce8d0e004505e961/badge.svg?style=flat)](https://www.versioneye.com/user/projects/573daf0bce8d0e004505e961)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project enables JSF usage inside JAR packaged Spring Boot Application. 

It autoconfigures [PrimeFaces](http://primefaces.org/), [OmniFaces](http://omnifaces.org/), [Mojarra](https://javaserverfaces.java.net/) and [MyFaces](http://myfaces.apache.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io) servlet containers.

It also aims to solve JSF and Spring Boot integration features. Current version includes JSF and CDI annotations support and Spring Security JSF Facelet Tag support.


## How to use

[Jsf Spring Boot Starter Example](https://github.com/persapiens/jsf-spring-boot-starter-example) shows JSF Spring Boot Starter usage.

## Key Features

### Six JSF Spring Boot Starters available

Starter | JSF Implementation | Servlet Container
------------ | ------------- | -------------
jsf-spring-boot-starter | [Mojarra](https://javaserverfaces.java.net/) | [Tomcat](http://tomcat.apache.org/)
jsf-jetty-spring-boot-starter | [Mojarra](https://javaserverfaces.java.net/) | [Jetty](http://www.eclipse.org/jetty)
jsf-undertow-spring-boot-starter | [Mojarra](https://javaserverfaces.java.net/) | [Undertow](http://undertow.io)
jsf-myfaces-spring-boot-starter | [MyFaces](http://myfaces.apache.org/) | [Tomcat](http://tomcat.apache.org/)
jsf-jetty-myfaces-spring-boot-starter | [MyFaces](http://myfaces.apache.org/) | [Jetty](http://www.eclipse.org/jetty)
jsf-undertow-myfaces-spring-boot-starter | [MyFaces](http://myfaces.apache.org/) | [Undertow](http://undertow.io)

### Primefaces, Primefaces-Extensions, Primefaces-All-Themes, OmniFaces, Mojarra and CDI-api libraries included

Library | Dependency
------------ | -------------
primefaces | [5.3](http://search.maven.org/#artifactdetails\|org.primefaces\|primefaces\|5.3\|jar)
primefaces-extensions | [4.0.0](http://search.maven.org/#artifactdetails\|org.primefaces.extensions\|primefaces-extensions\|4.0.0\|jar)
primefaces-all-themes | [1.0.8](http://search.maven.org/#artifactdetails\|org.primefaces.extensions\|all-themes\|1.0.8\|jar)
omnifaces | [1.13](http://search.maven.org/#artifactdetails\|org.omnifaces\|omnifaces\|1.13\|jar)
mojarra | [2.2.13](http://search.maven.org/#artifactdetails\|org.glassfish\|javax.faces\|2.2.13\|jar) 
myfaces | [2.2.10](http://search.maven.org/#artifactdetails\|org.apache.myfaces.core\|myfaces-bundle\|2.2.10\|jar)
cdi-api | [1.2](http://search.maven.org/#artifactdetails\|javax.enterprise\|cdi-api\|1.2\|jar)

### JSF properties configuration via application.properties or application.yml

Library | Namespace | Example
------------ | ------------- | ---------
standard (javax.faces) | jsf | jsf.PROJECT_STAGE=Development
primefaces | jsf.primefaces | jsf.primefaces.theme=overcast
omnifaces | jsf.omnifaces | jsf.omnifaces.FACES_VIEWS_ENABLED=true
mojarra (com.sun.faces) | jsf.mojarra | jsf.mojarra.preferXHTML=true
myfaces (org.apache.myfaces) | jsf.myfaces | jsf.myfaces.PRETTY_HTML=true

Additional information in [#22](https://github.com/persapiens/jsf-spring-boot-starter/issues/22)

### JSF and CDI annotations support automatically

Annotation |
------- | ----
[@NoneScoped](http://docs.oracle.com/javaee/7/api/javax/faces/bean/NoneScoped.html) |
[@RequestScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/RequestScoped.html) |
[@ViewScoped](http://docs.oracle.com/javaee/7/api/javax/faces/view/ViewScoped.html) |
[@SessionScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/SessionScoped.html) |
[@ApplicationScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/ApplicationScoped.html) |

### Spring Security JSF Facelet Tag support 

Namespace: **http://www.springframework.org/security/tags**

Tag | Description
------------ | -------------
authorize | perform Spring Security authorization decisions, using attributes: ifAnyGranted, ifAllGranted, ifNotGranted, access, url, method, var.
anonymous | verify if the user is anonymous.
authenticated | verify if the user is not anonymous.
fullyAuthenticated | verify if the is not an anonymous or a remember-me user.

Additional information in [#29](https://github.com/persapiens/jsf-spring-boot-starter/issues/29)

## System Requirements

JSF Spring Boot Starter requires [Java 8](http://java.com) and [Spring Boot 1.3.5](http://projects.spring.io/spring-boot/).

## Getting Help

* Report questions and bugs at [github.com/jsf-spring-boot-starter/issues](https://github.com/persapiens/jsf-spring-boot-starter/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/jsf-spring-boot-starter/issues](https://github.com/persapiens/jsf-spring-boot-starter/issues).
* Pull requests are welcome.
