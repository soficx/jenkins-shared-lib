#!/usr/bin/env groovy
import org.demo.Docker
def call(Map config ) {
    echo "Creating and pushing a docker image..."
    return new Docker(this).pushImage(config)
}
