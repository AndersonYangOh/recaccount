package com.chinaunicom.devlab.support.group.big19

class TaskBasic {

    String          code                            //任务编号
    String          name                            //任务名称
    String          memo                            //任务描述
    String          big19Entry                      //19大21条
    String          itSecureEntry                   //网络安全和信息化
    String          omsAndSasacEntry                //科技部和国资委意见
    String          newUnicom                       //五新联通
    TaskTeam        leadTeam                        //牵头单位
    String          taskGroup = 'default'           //任务分组标记
    String          lastStatus  = '未填报'          //最后状态
    String          lastStatusMonth                 //最后状态的月份
    static hasMany = [cooperateTeams:TaskTeam/*配合单位*/, progress:TaskProgress/*进度*/, operators:UserPerson/*操作者*/]    //---

    static constraints = {
        lastStatus inList: ['未填报','进行中','已完成','滞后']
        lastStatusMonth nullable:true
        memo nullable:true
        big19Entry nullable:true
        itSecureEntry nullable:true
        omsAndSasacEntry nullable:true
        newUnicom nullable:true
        cooperateTeams nullable:true
        progress nullable:true
        operators nullable:true
    }

    static mapping = {
        memo sqlType:'text'
        big19Entry sqlType:'text'
        itSecureEntry sqlType:'text'
        omsAndSasacEntry sqlType:'text'
        newUnicom sqlType:'text'
    }
}
