packer {
  required_plugins {
    amazon = {
      version = ">= 1.2.8"
      source  = "github.com/hashicorp/amazon"
    }
  }
}

source "amazon-ebs" "jenkins-master" {
  ami_description = "Amazon Linux Image with Jenkins Server"
  ami_name        = "jenkins-master-{{timestamp}}"
  instance_type   = "${var.instance_type}"
  profile         = "${var.aws_profile}"
  region          = "${var.region}"
  source_ami      = "ami-04b70fa74e45c3917"
  ssh_username    = "ubuntu"
  tags = {
    "Name"       = "Jenkins Master"
    "OS_Version" = "Ubuntu 24.04 LTS"
    "Release"    = "Latest"
    "Created-by" = "Packer"
  }
}

build {
  name    = "jenkins-master"
  sources = ["source.amazon-ebs.jenkins-master"]

  provisioner "file" {
    source      = "../groovy/create_user.groovy"
    destination = "/tmp/create_user.groovy"
  }

  provisioner "file" {
    source      = "../groovy/credentials.groovy"
    destination = "/tmp/credentials.groovy"
  }

  provisioner "file" {
    source      = "../scripts/plugins.txt"
    destination = "/home/ubuntu/plugins.txt"
  }

  provisioner "file" {
    source      = "../groovy/jcasc.yaml"
    destination = "/home/ubuntu/jcasc.yaml"
  }

  provisioner "file" {
    source      = "../groovy/webapp_build.groovy"
    destination = "/home/ubuntu/webapp_build.groovy"
  }

  provisioner "shell" {
    script = "../scripts/setup-jenkins.sh"
    environment_vars = [
      "JENKINS_USERNAME=${var.jenkins_username}",
      "JENKINS_PASSWORD=${var.jenkins_password}",
      "DEPLOYMENT_ENV=${var.deployment_env}",
      "GH_USERNAME=${var.github_username}",
      "GH_ACCESS_KEY=${var.github_access_key}",
    ]
  }
}