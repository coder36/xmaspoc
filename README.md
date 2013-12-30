CHRISTMAS 2013 POC
===============================

Primary Aims:

* Generate a QR bar code
* Generate a PDF file with embedded QR bar code

Secondary Aims:

* Investigate Spring boot
* Integrate Spring boot and JSF
* Integrate Groovy into a java project.
* Setup a gradle project


QR Code
-------
What is a QR code ?  See [here](http://en.wikipedia.org/wiki/QR_code) for the wikipedia entry.  A QR barcode looks like one of these:

![QR Barcode](http://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Wikipedia_mobile_en.svg/200px-Wikipedia_mobile_en.svg.png)

Encoded in those dots is a message.  It can store a lot more information than standard stripey bar code.
I'm going to use [GRGen](https://github.com/kenglxn/QRGen) which is an API built on top of [ZXing](https://code.google.com/p/zxing).  The plan is to wrap this in a servlet.


PDF from HTML
-------------

To do this I found a really good library with the interesting name of [Flying Saucer](https://code.google.com/p/flying-saucer/).  Pretty much you give it a URL and it will convert the HTML to PDF and write the corresponding PDF byte data to a stream.  Again this will be handled via a servlet.

The PDF data type is:  `application/pdf`


Spring boot
-----------
First off I want to say how cool spring boot is.  The spring guys are really trying to make life easy for us!

In a nutshell spring boot provide a way to rapidly prototype a spring application.  It uses lot of convention over configuration to achieve a web application in a few lines (This was why I also looked at groovy as this could reduce the line count down further!).  I see spring boot as a replacement for web.xml, war files and the whole web deployment process!!!  You run a spring boot project from the command line and it fires up your web app inside an embedded container.  It's a much simpler world.
To get started on spring boot take a look at:

[Spring 4.0 and Intellij IdEA 13](http://www.youtube.com/watch?v=84UD9Xk_Jkw) - This is a great tutorial and where I started.

[Spring website](http://projects.spring.io/spring-boot/)

[Spring boot github](https://github.com/spring-projects/spring-boot)

[Spring boot samples](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples)


Spring boot JSF integration?
-----------------------------------------------------------
First off, I did not get this working but for the record here is what I tried

1) Added JSF dependencies

        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-api</artifactId>
            <version>2.2.4</version>
        </dependency>
        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-impl</artifactId>
            <version>2.2.4</version>
        </dependency>

2) Added spring boot servlet mapping

    @Bean
    public ServletRegistrationBean jsfServlet() {
        return new ServletRegistrationBean( new javax.faces.webapp.FacesServlet(), "*.jsf" );
    }

When I try and hit a JSF page I get:

    java.lang.IllegalStateException: Could not find backup for factory javax.faces.context.FacesContextFactory.
            at javax.faces.FactoryFinder$FactoryManager.getFactory(FactoryFinder.java:1135)
            at javax.faces.FactoryFinder.getFactory(FactoryFinder.java:379)
            at javax.faces.webapp.FacesServlet.init(FacesServlet.java:350)
            at org.apache.catalina.core.StandardWrapper.initServlet(StandardWrapper.java:1280)
            at org.apache.catalina.core.StandardWrapper.allocate(StandardWrapper.java:885)


Groovy
------

Groovy integration is as simple as adding a build plugin to the maven pom file.  You can then mix .groovy with your .java files.

    <plugin>
       <artifactId>maven-compiler-plugin</artifactId>
       <version>3.1</version>
       <configuration>
           <source>1.7</source>
           <target>1.7</target>

           <compilerId>groovy-eclipse-compiler</compilerId>
       </configuration>
       <dependencies>

           <dependency>
               <groupId>org.codehaus.groovy</groupId>
               <artifactId>groovy-eclipse-compiler</artifactId>
               <version>2.8.0-01</version>
           </dependency>
           <dependency>
               <groupId>org.codehaus.groovy</groupId>
               <artifactId>groovy-eclipse-batch</artifactId>
               <version>2.1.8-01</version>
           </dependency>
         </dependencies>
   </plugin>



Putting it all together
=======================

Using maven:

    git clone http://github.com/coder36/xmaspoc
    cd xmaspoc
    mvn clean install
    java -jar target/xmaspoc-0.1-SNAPSHOT.jar

Using gradle

    git clone http://github.com/coder36/xmaspoc
    cd xmaspoc
    gradlew build
    java -jar build/libs/xmaspoc-0.1.jar


Navigate to http://localhost:8080/

To see groovy in action, navigate to http://localhost:8080