package com.chinaunicom.devlab.support.group.big19

class TaskProgress {

    static belongsTo = TaskBasic                //从属于任务；
    static hasOne = [task:TaskBasic]

    String                  month                           //月
    TaskTeam                team                            //成员
    UserPerson              createPerson                    //创建者
    UserPerson              updatePerson                    //改写者
    String                  type                            //类型：['牵头','配合']
    String                  status = '未填报'               //任务状态['未填报','进行中','已完成','滞后']
    String                  methord                         //落实方案及举措
    String                  job                             //进度安排
    String                  plan                            //后续计划
    String                  promise                         //承诺目标
    String                  promiseKr                       //承诺目标KR
    String                  vision                          //愿景目标
    String                  visionKr                        //愿景目标KR
    Date                    createDate = new Date()         //创建日期
    Date                    lastUpdateDate = new Date()     //最后修改时间

    static constraints = {
        type inList: ['牵头','配合']
        status inList: ['未填报','进行中','已完成','滞后']
        createPerson nullable:true
        updatePerson nullable:true
        methord nullable:true
        job nullable:true
        plan nullable:true
        promise nullable:true
        vision nullable:true
        promiseKr nullable:true
        visionKr nullable:true
    }

    static mapping = {
        methord sqlType:'text'
        job sqlType:'text'
        plan sqlType:'text'
        promise sqlType:'text'
        vision sqlType:'text'
        promiseKr sqlType:'text'
        visionKr sqlType:'text'
    }
}
