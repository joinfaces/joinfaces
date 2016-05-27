JSF Spring Boot Starter
=============================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter)
[![Build Status](https://travis-ci.org/persapiens/jsf-spring-boot-starter.svg?branch=master)](https://travis-ci.org/persapiens/jsf-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/573daf0bce8d0e004505e961/badge.svg?style=flat)](https://www.versioneye.com/user/projects/573daf0bce8d0e004505e961)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project enables JSF usage inside JAR packaged Spring Boot Application.

It autoconfigures [Primefaces](http://primefaces.org/), [Omnifaces](http://omnifaces.org/), [Mojarra](https://javaserverfaces.java.net/) and [Myfaces](http://myfaces.apache.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io) servlet containers.

## How to use

[Jsf Spring Boot Starter Example](https://github.com/persapiens/jsf-spring-boot-starter-example) shows jsf spring boot starter usage.

## Key Features

- Choose one of three spring boot starters availables:

Starter | Servlet Container
------------ | -------------
jsf-spring-boot-starter | [Tomcat](http://tomcat.apache.org/)
jsf-jetty-spring-boot-starter | [Jetty](http://www.eclipse.org/jetty)
jsf-undertow-spring-boot-starter | [Undertow](http://undertow.io)

- Includes Primefaces, Primefaces-Extensions, Primefaces-All-Themes, OmniFaces, Mojarra and CDI-api dependency libraries. Note that MyFaces is optional and **should not** be used with Mojarra if you prefer MyFaces.

Library | Dependency
------------ | -------------
primefaces | [5.3](http://search.maven.org/#artifactdetails\|org.primefaces\|primefaces\|5.3\|jar)
primefaces-extensions | [4.0.0](http://search.maven.org/#artifactdetails\|org.primefaces.extensions\|primefaces-extensions\|4.0.0\|jar)
primefaces-all-themes | [1.0.8](http://search.maven.org/#artifactdetails\|org.primefaces.extensions\|all-themes\|1.0.8\|jar)
omnifaces | [1.13](http://search.maven.org/#artifactdetails\|org.omnifaces\|omnifaces\|1.13\|jar)
mojarra | [2.2.13](http://search.maven.org/#artifactdetails\|org.glassfish\|javax.faces\|2.2.13\|jar) 
myfaces | [2.2.10](http://search.maven.org/#artifactdetails\|org.apache.myfaces.core\|myfaces-bundle\|2.2.10\|jar)
cdi-api | [1.2](http://search.maven.org/#artifactdetails\|javax.enterprise\|cdi-api\|1.2\|jar)

- Enable JSF properties configuration via application.properties or application.yml according discussion in [#22](https://github.com/persapiens/jsf-spring-boot-starter/issues/22)

Library | Namespace | Example
------------ | ------------- | ---------
standard (javax.faces) | jsf | jsf.PROJECT_STAGE=Development
primefaces | jsf.primefaces | jsf.primefaces.theme=overcast
omnifaces | jsf.omnifaces | jsf.omnifaces.FACES_VIEWS_ENABLED=true
mojarra (com.sun.faces) | jsf.mojarra | jsf.mojarra.preferXHTML=true
myfaces (org.apache.myfaces) | jsf.myfaces | jsf.myfaces.PRETTY_HTML=true

- Enable JSF and CDI annotations automatically, including [@RequestScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/RequestScoped.html), [@ViewScoped](https://javaserverfaces.java.net/docs/2.2/javadocs/javax/faces/view/ViewScoped.html) and  [@SessionScoped](http://docs.oracle.com/javaee/7/api/javax/enterprise/context/SessionScoped.html).

## Getting Help

* Report questions and bugs at [github.com/jsf-spring-boot-starter/issues](https://github.com/persapiens/jsf-spring-boot-starter/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/jsf-spring-boot-starter/issues](https://github.com/persapiens/jsf-spring-boot-starter/issues).
* Pull requests are welcome.



