package com.chinaunicom.devlab.support.group.big19

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

//@EnableEurekaClient
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}