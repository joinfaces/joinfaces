JSF Spring Boot Starter
=============================
[![Build Status](https://travis-ci.org/persapiens/jsf-spring-boot-starter.svg?branch=master)](https://travis-ci.org/persapiens/jsf-spring-boot-starter)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.persapiens/jsf-spring-boot-starter/)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)



This spring boot starter enables JSF usage inside JAR packaged Spring Boot Application.

It autoconfigures [Mojarra](https://javaserverfaces.java.net/), [Primefaces](http://primefaces.org/) and [Omnifaces](http://omnifaces.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/).

Key Features

- Includes mojarra, primefaces and omnifaces dependency libraries.

- Includes spring-boot-starter-web, tomcat-embed-jasper and jstl dependencies transitively.

- Enable configuration properties to javax.faces, com.sun.faces, primefaces and omnifaces via application.properties or application.yml

- Enable CDI annotations usage, including View scope implementation. Examples:  @ViewScoped, @SessionScoped

[Jsf Spring Boot Starter Sample](https://github.com/persapiens/jsf-spring-boot-starter-example) shows jsf spring boot starter usage.
