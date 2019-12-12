package com.chinaunicom.devlab.support.group.big19

class TaskTeam {

    String                  name
    TaskCompany             company             //所属单位
    String                  duty                //类型：['牵头','配合']
    String                  matter              //事项
    static hasMany = [principals:UserPerson/*责任人*/,contacts:UserPerson/*联系人*/,participants:UserPerson/*参与人*/]    //---

    static constraints = {
        principals nullable: true
        contacts nullable: true
        participants nullable: true
        name nullable: true
    }
}