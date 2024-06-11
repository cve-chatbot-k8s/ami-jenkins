#!/bin/bash

# Update the package list
sudo apt update

# Install JDK 17
sudo apt install openjdk-17-jre -y

# Install Node.js v22
curl -fsSL https://deb.nodesource.com/setup_22.x | sudo -E bash -
sudo apt-get install -y nodejs

# Install Jenkins From Official Repository
sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins -y

# Install Jenkins Plugins
wget --quiet https://github.com/jenkinsci/plugin-installation-manager-tool/releases/download/2.12.17/jenkins-plugin-manager-2.12.17.jar

# Install plugins with jenkins-plugin-manager tool:
sudo java -jar ./jenkins-plugin-manager-2.12.17.jar --war /usr/share/java/jenkins.war \
  --plugin-download-directory /var/lib/jenkins/plugins --plugin-file plugins.txt

# Update users and group permissions to `jenkins` for all installed plugins:
cd /var/lib/jenkins/plugins/ || exit
sudo chown jenkins:jenkins ./*

# Move Jenkins files to Jenkins home
cd /home/ubuntu/ || exit
sudo mv jcasc.yaml /var/lib/jenkins/
sudo mv webapp_build.groovy /var/lib/jenkins/
sudo mv /tmp/webapp_commit_check.groovy /var/lib/jenkins/
sudo mv /tmp/helm_webapp.groovy /var/lib/jenkins/

# Update file ownership
cd /var/lib/jenkins/ || exit
sudo chown jenkins:jenkins jcasc.yaml ./*.groovy

# Write environment variables to properties file
{
  echo "JENKINS_USERNAME=${JENKINS_USERNAME}"
  echo "JENKINS_PASSWORD=${JENKINS_PASSWORD}"
  echo "GH_USERNAME=${GH_USERNAME}"
  echo "GH_ACCESS_KEY=${GH_ACCESS_KEY}"
  echo "DOCKER_USERNAME=${DOCKER_USERNAME}"
  echo "DOCKER_PASSWORD=${DOCKER_PASSWORD}"

} | sudo tee /var/lib/jenkins/env.properties

# Create init.groovy.d directory if it doesn't exist
sudo mkdir -p /var/lib/jenkins/init.groovy.d/

# Groovy script for user creation and docker credentials
sudo mv /tmp/create_user.groovy /var/lib/jenkins/init.groovy.d/
# Groovy script for credentials
sudo mv /tmp/credentials.groovy /var/lib/jenkins/init.groovy.d/
sudo mv /tmp/docker_cred.groovy /var/lib/jenkins/init.groovy.d/


# Disable initial setup wizard
sudo mkdir -p /etc/systemd/system/jenkins.service.d/
{
  echo "[Service]"
  echo "Environment=\"JAVA_OPTS=-Djava.awt.headless=true -Djenkins.install.runSetupWizard=false -Dcasc.jenkins.config=/var/lib/jenkins/jcasc.yaml\""
} | sudo tee /etc/systemd/system/jenkins.service.d/override.conf

# Reload systemd daemon and restart Jenkins service
sudo systemctl daemon-reload
sudo systemctl restart jenkins

# Start Jenkins
sudo systemctl enable jenkins
sudo systemctl start jenkins

# Install Caddy
sudo apt install -y debian-keyring debian-archive-keyring apt-transport-https curl
curl -1sLf 'https://dl.cloudsmith.io/public/caddy/stable/gpg.key' | sudo gpg --dearmor -o /usr/share/keyrings/caddy-stable-archive-keyring.gpg
curl -1sLf 'https://dl.cloudsmith.io/public/caddy/stable/debian.deb.txt' | sudo tee /etc/apt/sources.list.d/caddy-stable.list
sudo apt update
sudo apt install caddy=2.7.6 -y

# Configure Caddy
if [ "$DEPLOYMENT_ENV" = "prod" ]
then
  echo "jenkins.missionk8s.xyz {
            reverse_proxy localhost:8080
        }" | sudo tee /etc/caddy/Caddyfile
else
  echo "jenkins.missionk8s.xyz {
            tls {
                ca https://acme-staging-v02.api.letsencrypt.org/directory
            }
            reverse_proxy localhost:8080
        }" | sudo tee /etc/caddy/Caddyfile
fi

sudo iptables -I INPUT -p tcp --dport 80 -j ACCEPT
sudo iptables -I INPUT -p tcp --dport 443 -j ACCEPT
# Restart Caddy
sudo systemctl restart caddy
sudo systemctl daemon-reload

# Install Docker
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

sudo usermod -a -G docker jenkins

sudo systemctl enable docker
sudo systemctl start docker

#install helm
sudo apt-get update
curl https://baltocdn.com/helm/signing.asc | gpg --dearmor | sudo tee /usr/share/keyrings/helm.gpg >/dev/null
sudo apt-get install apt-transport-https -y
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/helm.gpg] https://baltocdn.com/helm/stable/debian/ all main" |
  sudo tee /etc/apt/sources.list.d/helm-stable-debian.list
sudo apt-get update && sudo apt-get install helm
