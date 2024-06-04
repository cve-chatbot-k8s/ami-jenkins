variable "instance_type" {
  description = "The type of instance to use"
  default     = "t3.micro"
}

variable "aws_profile" {
  description = "The AWS profile to use"
  default     = "default"
}

variable "region" {
  description = "The AWS region to use"
  default     = "us-east-1"
}

variable "jenkins_username" {
  description = "The username for the Jenkins setup"
  type        = string
  default     = "admin"
}

variable "jenkins_password" {
  description = "The password for the Jenkins setup"
  type        = string
  default     = "admin"
}

variable "deployment_env" {
  description = "The deployment environment"
  type        = string
  default     = "dev"
}