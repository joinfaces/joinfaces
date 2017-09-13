# Contributing to Joinfaces

## Project Structure

This software is multi module [Maven](http://maven.apache.org) project and follows [Spring Boot Reference](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html) to create JSF Auto Configuration. [See what's inside each submodule.](https://github.com/joinfaces/joinfaces/wiki/Structure-of-the-software)

## Branching

This repository follows the same [branching model](https://github.com/spring-projects/spring-boot/wiki/Working-with-Git-branches) like the [_Spring Framework_](https://github.com/spring-projects/spring-framework) and [_Spring Boot_](https://github.com/spring-projects/spring-boot) repositories:

- The `master`-branch always contains the latest code, targeting the latest _Spring Boot_ version.
- Additional branches, which are named like version numbers contain the code of older, but still supported version series.

The `master`-branch could contain Joinfaces 3.x which targets the upcoming _Spring Boot 2.0.0_ while the `2.x`-branch
contains Joinfaces 2.x which targets _Spring Boot 1.5.x_.
(The exact version numbers and branch names will change over time, but the principle stays the same.)

If you wan't to contribute to Joinfaces, make sure you start your work on the head of the correct branch.
Make also sure, to select the same branch as _base_ when creating your pull request.

## Code Conventions and Housekeeping
> These section is mainly copied from https://github.com/spring-projects/spring-boot/blob/master/CONTRIBUTING.adoc

None of these is essential for a pull request, but they will all help.  They can also be
added after the original pull request but before a merge.

* Make sure all new `.java` files to have a simple Javadoc class comment with at least an
  `@author` tag identifying you, and preferably at least a paragraph on what the class is for.
* Add the license header comment to all new `.java` files (copy from existing files in the project)
* Add yourself as an `@author` to the `.java` files that you modify substantially (more than cosmetic changes).
* Add some Javadocs.
* A few unit tests would help a lot as well -- someone has to do it.
* When writing a commit message please follow [these conventions](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html).
