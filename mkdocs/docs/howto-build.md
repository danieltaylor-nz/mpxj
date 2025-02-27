# Building MPXJ
Although MPXJ can be downloaded as a complete package from Maven, GitHub and
SourceForge, the development of the library continues between releases and is
driven by user requests for new functionality and bug fixes being applied to
existing features. Many MPXJ users will work with and ship software based on 
intermediate versions of MPXJ built from the code on GitHub in order to take
advantage of these enhancements before they become available in an official 
release. This approach is supported by the fact that code is only pushed to  the
master branch on GitHub if the suite of regression tests have been completed
successfully:  therefore the quality of the code taken from GitHub at any point
can normally be guaranteed  to be as good as that in an official release.

In order to take advantage of MPXJ functionality from GitHub, you will need to 
understand how to build the library, whether you are using it in the form
of a Java JAR or a .NET DLL. The following sections explain how to do this.

## Obtaining the source
The first step in the process of building your own version of MPXJ is to obtain 
the latest source from [GitHub](https://github.com/joniles/mpxj).
Instructions for cloning the repository can be found on this page.

## Building the Java JAR
MPXJ is built using Maven. Once you have a cloned copy of the MPXJ repository,
you may wish to update the `groupId`, `artifactId` or `version` attributes in
`pom.xml`. This will ensure that there is no confusion between the version of
MPXJ you build and the official distributions.

If you have a copy of Maven installed, you can issue the following command to
build MPXJ:

```
mvn -DskipTests=true -Dmaven.javadoc.skip=true -Dsource.skip=true package
```

This will generate the `mpxj.jar` for you in the Maven target directory, and
copies MPXJ's dependencies to the `lib` directory. Note that for convenience
this skips running the unit tests, javadoc generation and source packaging. 

If you are using Maven to manage dependencies for your own project, you can
install your newly built version of MPXJ in a local Maven repository:

```
mvn -DskipTests=true -Dmaven.javadoc.skip=true -Dsource.skip=true install
```

## Building the .NET DLLs
Building the .NET versions of MPXJ uses an Ant script to first
run Maven to create the Java version, then run IKVM to create a .Net Framework
and a .Net Core version.

* Download [.Net Framework version of IKVM](http://www.ikvm.net/)
  and unzip the files into a convenient directory
* Change directory to the MPXJ folder.
* Edit the `build.xml` file and ensure that the property named `ikvm.net45.dir`
  is set to point to the location where you have unzipped IKVM.

You can now issue the following command:

```
ant archive
```

The Ant script will recognise that IKVM is present and build the .NET Framework 
version of MPXJ, with the results found in the `src.net\lib\net45` folder.

## Generating the JAXB code
In order to read and write various XML file formats, MPXJ relies on code
generated by the JAXB tool `xjc` from the XML schema for each file format.
Normally you will not need to regenerate this source, but if you are changing 
the JAXB implementation, or modifying the use of JAXB in some way, then you may
need to regenerate this code. 

Where I have created an XML schema to support a particular file format, I have
included it in the MPXJ distribution in the `jaxb` directory. For XML schemas
published by product vendors, I have included a note on the home page indicating
where these can be located. 

If you obtain a copy of the XML schema file you want to work with, you can
update   the JAXB source using the `xjc` target found in the ant `build.xml`
file.  Note that the `xjc` target is platform specific, you will need to  change
the name of `xjc` tool to be `xjc.bat`, `xjc.exe`, or `xjc.sh`  depending on
your operating system. You will also need to set  the properties indicated in
`build.xml` to tell it where to  find `xjc` and the XML schema file. If you are
only regenerating source for one of the XML schemas, you can comment out the
others in the Ant script to avoid unnecessary work.
