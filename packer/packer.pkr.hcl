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


  provisioner "shell" {

    script = "../scripts/setup-jenkins.sh"
  }
}
