import jenkins.model.*
import hudson.security.*
import java.util.Properties

def jenkins = Jenkins.getInstance()

// Load properties file
def env = new Properties()
env.load(new FileInputStream("/var/lib/jenkins/env.properties"))

def username = env.getProperty("JENKINS_USERNAME")
def password = env.getProperty("JENKINS_PASSWORD")

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(username, password)
jenkins.setSecurityRealm(hudsonRealm)

jenkins.save()