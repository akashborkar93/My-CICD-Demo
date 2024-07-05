# My-CICD-Demo

Pre-requisite for this set up is below

1.Ubuntu EC2 instance keep t2.medium as a source
2.Create IAM user jenkins add policy (Registryfullaccess, ECSfullaccess)
3.Create Private ECR repository

Step-1
1.Once EC2 instance login first install Jenkins below are the details

Commands

sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian/jenkins.io-2023.key
echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins

sudo apt update
sudo apt install fontconfig openjdk-17-jre
java -version
openjdk version "17.0.8" 2023-07-18
OpenJDK Runtime Environment (build 17.0.8+7-Debian-1deb12u1)
OpenJDK 64-Bit Server VM (build 17.0.8+7-Debian-1deb12u1, mixed mode, sharing)

sudo systemctl enable jenkins
sudo systemctl start jenkins
sudo systemctl status jenkins

Step-2
1. Install Docker engine
   # Add Docker's official GPG key:
sudo apt-get update
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

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo docker run hello-world

IMPORTANT - Add user to docker group because it work on that ways
cmd - sudo usermod -aG docker jenkins
      reboot
once login back try to run "docker images" without sudo in this way we confirm that docker is allowing user.

Step-3
1.Install AWS CLI

sudo apt update
sudo apt install unzip -y
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
aws --version

=================================================================================================================================================================================================
Coming to the Jenkins tool

Add the tool
MAVEN3 & OracleJDK17

JDK
OracleJDK17
/usr/lib/jvm/java-1.17.0-openjdk-amd64

MVN
MAVEN3

Now add aws crendential on security->credentials and add access and secreate key

List of plugin need to install
1.stage view
2.Docker pipeline
3.CloudBees Docker Build and PublishVersion
4.Amazon credentials
5.Amazon ECRVersion
=================================================================================================================================================================================================

Final stage-
Now run the pipelines





