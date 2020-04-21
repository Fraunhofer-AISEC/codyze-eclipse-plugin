<p align="center">
  <br>
  <img src="https://github.com/Fraunhofer-AISEC/codyze-eclipse-plugin/workflows/Maven%20Package/badge.svg">
  <img src="https://img.shields.io/github/last-commit/Fraunhofer-AISEC/codyze-eclipse-plugin.svg?style=popout">
  <a href="https://github.com/Fraunhofer-AISEC/codyze-eclipse-plugin/blob/master/LICENSE"><img alt="undefined" src="https://img.shields.io/github/license/Fraunhofer-AISEC/codyze-eclipse-plugin.svg?style=popout"></a>

  <br><br><br>
</p>

# Codyze LSP plugin for Eclipse

This project is a simple Language Server Protocol (LSP) connector to integrate the Codyze static code analyzer into the Eclipse IDE. Codyze analyzes Java and C/C++ source files for vulnerabilities, especially for the incorrect usage of cryptograhic libraries, such as Bouncycastle and Botan. To integrate the Codyze analysis server, this plugin sends source files opened in Eclipse over LSP to the analysis server and displays any findings as "problem markers" in Eclipse.

## Building the project

### Build using maven

To build an unsigned jar, simply run:

```
mvn package
```

To build a signed jar, enable the `sign` profile and pass keystore parameters: 

```
mvn install -Psign -Dsign.alias=<alias of signing key> -Dsign.storepass=<password> -Dsign.keypass=<password> -Dsign.keystore=<path to keystore file>
```

### Publish to eclipse update site

The maven wagon plugin will deploy the build artifacts of the `codyze-eclipse-update` module to an Eclipse P2 update site. To define the credentials to login to the server you can add an entry fo the user credentials to your local `~/m2/settings.xml` file:

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository/>
  <interactiveMode/>
  <usePluginRegistry/>
  <offline/>
  <pluginGroups/>
  <servers>
    <server>
      <id>p2Repo</id>
      <username>youruser</username>
      <password>yourpassword</password>
    </server>
  </servers>
  <mirrors/>
  <proxies/>
  <profiles/>
  <activeProfiles/>
</settings>
```

### Creating a new release version

After releasing an application or several plug-ins, the version number should be increased. If you do not use pomless builds, there are two locations where version numbers are defined. On the one hand the pom.xml file and on the other hand the MANIFEST.MF file.

This can be done easily by using the ﻿Tycho Versions Plugin:

```
# setting the version in pom.xml and MANIFEST.MF files
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z -Dtycho.mode=maven
```


Eclipse icon made by [Swifticons](https://www.flaticon.com/authors/swifticons) from [Flaticon](https://www.flaticon.com/).
