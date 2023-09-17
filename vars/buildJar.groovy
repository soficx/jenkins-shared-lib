#!/usr/bin/env groovy

import org.demo.Docker

def call() {
    return new Docker(this).buildJar()
}
