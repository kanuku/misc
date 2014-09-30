#!/usr/bin/env bash


# Install Jenkins
echo "### Getting the key"
	wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -

echo "### Adding jenkins to source list"
	sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
	sudo apt-get update


	sudo apt-get -y install jenkins

## Ansible

echo "### software-properties-common"
	sudo apt-get install software-properties-common

echo "### add repository ansible"
	sudo apt-add-repository ppa:ansible/ansible
	sudo apt-get update


	sudo apt-get -y install ansible

