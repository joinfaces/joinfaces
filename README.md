JSF Spring Boot Starter
=============================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter/)
[![Build Status](https://travis-ci.org/persapiens/jsf-spring-boot-starter.svg?branch=master)](https://travis-ci.org/persapiens/jsf-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/573daf0bce8d0e004505e961/badge.svg?style=flat)](https://www.versioneye.com/user/projects/573daf0bce8d0e004505e961)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)



This spring boot starter enables JSF usage inside JAR packaged Spring Boot Application.

It autoconfigures [Primefaces](http://primefaces.org/), [Omnifaces](http://omnifaces.org/), [Mojarra](https://javaserverfaces.java.net/) and [Myfaces](http://myfaces.apache.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/).

#### Key Features

- Includes primefaces, omnifaces and mojarra dependency libraries. Note that myfaces is optional and should not be used with mojarra.

- Includes spring-boot-starter-web, tomcat-embed-jasper and jstl dependencies transitively.

- Enable jsf properties configuration via application.properties or application.yml according discussion in [#22](https://github.com/persapiens/jsf-spring-boot-starter/issues/22)

Library | Namespace | Example
------------ | ------------- | ---------
standard (javax.faces) | jsf | jsf.PROJECT_STAGE=Development
mojarra (com.sun.faces) | jsf.mojarra | jsf.mojarra.preferXHTML=true
primefaces | jsf.primefaces | jsf.primefaces.theme=overcast
omnifaces | jsf.omnifaces | jsf.omnifaces.FACES_VIEWS_ENABLED=true
myfaces (org.apache.myfaces) | jsf.myfaces | jsf.myfaces.PRETTY_HTML=true

- Enable CDI annotations usage, including View scope implementation. Examples:  @ViewScoped, @SessionScoped

[Jsf Spring Boot Starter Sample](https://github.com/persapiens/jsf-spring-boot-starter-example) shows jsf spring boot starter usage.
