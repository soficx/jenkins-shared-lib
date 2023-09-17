#!/usr/bin/env groovy
package org.demo

//  just to make sure we saved the status of the execution whenever the pipeline get paused or resumed 
class Docker implements Serializable {

    def script
    Docker(script) {
        this.script = script
    }

    // you can paramtrize this function to change the way you package your artifacts
    def buildJar() {
        script.echo "Build the jar  artifact...."
        script.sh 'mvn package'
    }
    
    def buildAndPushImage(Map config = [:]) {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'USER', passwordVariable: 'TOKEN')]){
            script.sh "docker build -t $config.url/$config.imageName:$config.imageTag ."
            script.sh "echo $script.TOKEN | docker login -u $script.USER --password-stdin"
            script.sh "docker push $config.url/$config.imageName:$config.imageTag"
        } 
    }
}