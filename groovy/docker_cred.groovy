import jenkins.model.*
import hudson.util.Secret
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*

// Load properties file
def env = new Properties()
env.load(new FileInputStream("/var/lib/jenkins/env.properties"))


// def dockerUsername = System.getenv('DOCKER_USERNAME')
// def dockerPassword = System.getenv('DOCKER_PASSWORD')

def dockerUsername = env.getProperty('DOCKER_USERNAME')
def dockerPassword = env.getProperty('DOCKER_PASSWORD')
def credentialsId = 'dockerhub-creds'

// Create new username and password credentials
def usernameAndPassword = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL, credentialsId, 'DockerHub Credentials', dockerUsername, dockerPassword
)

def jenkinsInstance = Jenkins.getInstance()
def domain = Domain.global()
def store = jenkinsInstance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

store.addCredentials(domain, usernameAndPassword)
jenkinsInstance.save()
