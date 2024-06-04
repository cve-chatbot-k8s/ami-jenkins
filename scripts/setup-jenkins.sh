#!/bin/bash

# Update the package list
sudo apt update

# Install JDK 17
sudo apt install openjdk-17-jre -y

# Install Jenkins From Official Repository
sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins -y

# Write environment variables to properties file
{
  echo "JENKINS_USERNAME=${JENKINS_USERNAME}"
  echo "JENKINS_PASSWORD=${JENKINS_PASSWORD}"
} | sudo tee /var/lib/jenkins/env.properties

# Create init.groovy.d directory if it doesn't exist
sudo mkdir -p /var/lib/jenkins/init.groovy.d/
# Groovy script for user creation
sudo mv /tmp/create_user.groovy /var/lib/jenkins/init.groovy.d/

# Disable initial setup wizard
sudo mkdir -p /etc/systemd/system/jenkins.service.d/
{
  echo "[Service]"
  echo "Environment=\"JAVA_OPTS=-Djava.awt.headless=true -Djenkins.install.runSetupWizard=false\""
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