# blogger

# For Deploying the war in Jboss:
issue: http://metecikler.com/bj/2015/03/03/jboss-as7-hibernate-java-lang-nosuchmethoderror-javax-persistence-table-indexes/
This happens because JBoss AS7 is based on module classloading. JBoss automaticly uses hibernate-jpa-2.0-api-1.0.1.Final.jar (modules/javax/persistence/api/main) but one of my dependencies needs hibernate-jpa-2.1-api-1.0.0.final.jar.
One of the solutions to this problem is to replace old jar with the new jar and update module.xml and index file accordingly. You need to restart JBoss.