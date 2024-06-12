import jenkins.model.Jenkins
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.CredentialsScope
import hudson.util.Secret

// Load properties file
def env = new Properties()
env.load(new FileInputStream("/var/lib/jenkins/env.properties"))

def appId = env.getProperty("GH_APP_ID")
def privateKey = env.getProperty("GH_APP_PRIVATE_KEY")

instance = Jenkins.instance
domain = Domain.global()
store = instance.getExtensionList(
        "com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0].getStore()

githubAppCredentials = new UsernamePasswordCredentialsImpl(
        CredentialsScope.GLOBAL,
        "jenkins-release",
        "GitHub App Credentials",
        appId,
        privateKey
)

store.addCredentials(domain, githubAppCredentials)
