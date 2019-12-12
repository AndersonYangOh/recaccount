package com.chinaunicom.devlab.support.group.big19

import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST

class SsoController {

    def springSecurityService

    def clientId  = 'big'
    def clientSecret = 'TDJ2ClGey7eWHERkOU6EBwMMKZNUUURHcSMUijZx'
    def callBackUrl = 'http://10.0.48.2:3880/big/sso/callback'

    def index() {
        redirect(url: "http://10.249.218.156:7731/service/auth2/auth?response_type=code&scope=web&state=1" +
                "&client_id=" + clientId +
                "&redirect_uri=" + callBackUrl)
        return
    }

    def callback() {
        if (springSecurityService.isLoggedIn()) {
            redirect(controller: 'worklog')
            return
        }
        def code = params.code
        //def state = params.state

        //获取token
        def tokenHttp = new HTTPBuilder('http://10.249.218.156:7731/service/auth2/token')
        def token = ''
        tokenHttp.request(POST, JSON) {
            body=[client_id:'big',client_secret:clientSecret,redirect_uri:callBackUrl,grant_type:'authorization_code',code:code]
            requestContentType=URLENC
            response.success={resp, json->
                println('调用成功')
                println request.toString()
                println(json)
                token = json.access_token
            }
            response.failure={resp, json->
                println('调用失败')
                println(json)
                println resp.status
                println request.toString()
            }
        }

        //获取用户信息
        def userInfoHttp = new HTTPBuilder('http://10.249.218.156:7731/service/auth2/userInfo?access_token=' + token)
        userInfoHttp.request(GET, JSON) {
            response.success ={resp, json->
                println('调用成功')
                println request.toString()
                println(json)
                def username = json.login
                def user = UserPerson.findByUsername(username)
                if (user == null) {
                    user = new UserPerson(username: username)
                    user.company = TaskCompany.findOrCreateByName('非填报').save(flush:true)
                }
                user.name = json.user_name
                user.email = json.user_email
                //user.mobile = ''
                user.password = 'SuperLiLongLive_'
                user.save(flush: true)
                UserPersonUserRole.create(user, UserRole.get(1), true)
                springSecurityService.reauthenticate(username)     //强制登陆
                //session["_current_user"] = user       //打开会出错
            }
            response.failure={resp, json ->
                println('调用失败')
                println(json)
                println resp.status
                println request.toString()
            }
        }
        redirect(controller: 'worklog')
        return
    }
}
