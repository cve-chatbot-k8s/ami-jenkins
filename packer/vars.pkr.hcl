variable "instance_type" {
  description = "The type of instance to use"
  default     = "t2.micro"
}

variable "aws_profile" {
  description = "The AWS profile to use"
  default     = "default"
}

variable "region" {
  description = "The AWS region to use"
  default     = "us-east-1"
}