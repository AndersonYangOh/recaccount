package com.chinaunicom.devlab.support.group.big19

class AutoCompleteJob {

    def worklogService

    static triggers = {
        //simple name: 'simpleTrigger', startDelay: 10000, repeatInterval: 30000, repeatCount: 10
        cron name:   'cronTrigger',   startDelay: 10000, cronExpression: '0 0 1 * * ? *'     //每天凌晨1点
    }

    def execute() {
        System.out.println("自动设置任务完成任务执行")
        def day = new Date().getAt(Calendar.DAY_OF_MONTH)
        if (day == 1) {
            worklogService.autoComplete()
        }
    }
}
