## Jenkins Shared Library 

### Overview 
Jenkins Shared Library are a way to create, share, and reuse custom pipeline code across multiple Jenkins pipelines, they allow you to encapsulate common tasks, functions, and logic into reusable scripts <br>
In this shared library, we encapsulate the logic for the following essential Docker image-related operations:
1. Building Docker images
2. Authentication with Docker Registry
3. Pushing Images to a Specified Registry

### Prerequisites
Before using this shared library, ensure you have the following prerequisites:
- Jenkins-Server
- Docker: Docker should be installed on the Jenkins server 

### Usage
1. Configure the shared lib
    - In the jenkins web interface, navigate to "Manage Jenkins" -> " Configure System" 
    - Under "Global Pipeline Libraries," add your library by specifying a name, a vcs, and repo URL

2. Import and Use the Library in Your Jenkinsfile
    - use the @Library annotation to import the shared library by its configured name
    ```groovy
    @Library('your_shared_libr') _
    ```
You can also retrieve a jenkins lib from a remote repo such as GitHub. and to do this you can use the following statement:  
```groovy
library identifier: 'name_of_your_libr@branch_name',
             retriever: modernSCM([
               $class: 'GitSCMSource',
        //     remote: 'https://github.com/soficx/shared-lib.git'
               remote: 'https://github.com/your-repo/jenkins-shared-library.git',
               credentialsId: 'cred_id'
             ])
```
**credentialsId: 'cred_id'** should match the ID of the Jenkins credentials that store the username and password or token required to access the Git repository.

3. Call the shared lib function in your jenkinsfile 
    - you can call your desired functions 

```groovy
pipeline {
    agent any
//  ....
    stages {
        stage('Build') {
            steps {
                script {
                    def imageDetails = [
                        imageName: 'myapp',          // Name of your Docker image
                        imageTag: 'v1.0',           // Tag for the Docker image
                        dockerfilePath: 'Dockerfile', // Path to your Dockerfile
                        registryUrl: 'registry.example.com', // Docker registry URL
                    ]
                createAndPushImage(imageDetails)
                }
            }
        }
//      ...
    }
}
```

4. Execute Your Jenkins Pipeline: Trigger your Jenkins pipeline, and it will use the shared library functions to build and push your Docker image to the specified registry with the provided tag.

### License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
