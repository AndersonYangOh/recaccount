package com.chinaunicom.devlab.support.group.big19

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/login/auth")         //index 改为了 /login/auth
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}