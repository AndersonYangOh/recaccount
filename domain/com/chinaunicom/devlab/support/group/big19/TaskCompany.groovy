package com.chinaunicom.devlab.support.group.big19

class TaskCompany {

    String          name        //名称
    String          fullName    //全称
    Integer         level       //级别

    static constraints = {
        level nullable: true
        fullName nullable: true
    }
}
