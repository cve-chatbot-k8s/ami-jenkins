import jenkins.model.Jenkins
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.CredentialsScope


// Load properties file
def env = new Properties()
env.load(new FileInputStream("/var/lib/jenkins/env.properties"))

def username = env.getProperty("GH_USERNAME")
def accessKey = env.getProperty("GH_ACCESS_KEY")

instance = Jenkins.instance
domain = Domain.global()
store = instance.getExtensionList(
        "com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0].getStore()

githubCredentials = new UsernamePasswordCredentialsImpl(
        CredentialsScope.GLOBAL,
        "github-credentials", // This is the ID used to reference these credentials
        "GitHub Credentials", // This is a description
        username, // Replace with your GitHub username
        accessKey // Replace with your GitHub access token
)

store.addCredentials(domain, githubCredentials)