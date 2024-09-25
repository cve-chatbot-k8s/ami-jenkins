# Jenkins Master AMI Builder


This project uses Packer to build an Amazon Machine Image (AMI) with a Jenkins Server.

## Prerequisites

- Packer
- AWS Account

## Configuration

The Packer configuration is defined in the `packer/packer.pkr.hcl` file. 

The configuration includes the following main sections:

- `source`: Defines the type of machine image to be built and the details of the build process.
- `build`: Defines the provisioners that install and configure software within running machines prior to turning them into machine images.

## Usage

1. Set your AWS credentials in your environment or AWS credentials file.
2. Update the `packer/packer.pkr.hcl` file with your specific values.
3. Run `packer build -var "jenkins_username=$JENKINS_USERNAME" -var "jenkins_password=$JENKINS_PASSWORD" -var "deployment_env=$DEPLOYMENT_ENV" .` to start the build process.
  
## Built With

- [Packer](https://www.packer.io/) - Open source tool for creating identical machine images for multiple platforms from a single source configuration.



## To Add a new JenkinsJob to the AMI use the following steps:
1. Create a new DSL job and place it in the /groovy folder
2. Add the job file into the packer/packer.pkr.hcl file in the provisioners section
3. in the setup-jenkins.sh file move the job to /var/lib/jenkins folder and change the ownership to jenkins user
