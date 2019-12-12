package com.chinaunicom.devlab.support.group.big19

import grails.gorm.transactions.Transactional

@Transactional
class WorklogService {
    def mailService
    def startMonth = Date.parse('yyyy-MM-dd','2019-01-01')

    def getAvailableMonthMap() {
        def nowMonth = new Date().clearTime()
        def monthMap = new LinkedHashMap()
        while (startMonth < nowMonth) {
            monthMap.put(nowMonth.format('yyyyMM'), nowMonth.format('yyyy年MM月'))
            nowMonth.set(month: nowMonth.getAt(Calendar.MONTH)-1)
        }
        return monthMap
    }

    //发送提醒邮件，全部任务
    def remindAll(String month) {
        def count = 0
        for (task in TaskBasic.findAll()) {
            count += remindTask(task, month)
        }
        return count
    }

    //发送提醒邮件，单个任务
    def remindTask(TaskBasic task, String month) {
        def count = 0
        count += remindTeam(task, task.leadTeam, month)
        for (team in task.cooperateTeams) count += remindTeam(task, team, month)
        return count
    }

    private remindTeam(TaskBasic task, TaskTeam team, String month) {
        def progress = TaskProgress.findByTaskAndTeamAndMonth(task, team, month)
        if ((progress == null) || (progress.job == null) || (progress.plan == null) || (progress.methord == null)
                || (progress.job.trim().length() <= 0) || (progress.plan.trim().length() <= 0) || (progress.methord.trim().length() <= 0)) {
            return sendRemindMail(task, month, team.contacts)
        }
    }

    private sendRemindMail(TaskBasic task, String month, Set<UserPerson> users) {
        def count = 0
        for (user in users) {
            if (!user.email.contains('@')) continue
//            println(user.email)
            String mailContent = user.name + "您好：<br><br>" +
                    "在任务台账中，您是【" + task.code + "：" + task.name + "】任务的联系人，请尽快完成该任务【" + month + "】月份的台账填报。<br>" +
                    "（任务台账位置：集团OA -> 应用中心 -> 支撑类 -> 科技创新平台 -> 任务台账）<br><br>" +
                    "RD-CLOUD"
            mailService.sendMail {
                from 'hqs-rdcloud@chinaunicom.cn'
                to user.email
                subject "RD-CLOUD：请尽快完成[" + month + "]任务台账填报"
                html mailContent
            }
            count ++
        }
        return count
    }

    def autoComplete() {
        def month = new Date().format('yyyyMM')
        //def tasks = TaskBasic.findAllByCodeInList(['分解任务21.2-07'])
        def tasks = TaskProgress.findAllByStatusAndType('已完成','牵头')*.task.unique()          //以往已完成的
        for (task in tasks) {
//                def teamList = task.cooperateTeams
//                for (cm in teamList) {
//                    def tp = TaskProgress.findOrCreateByTaskAndMonthAndTypeAndTeamAndStatus(task, month, '配合', cm, '已完成')
//                    tp.plan = '已完成'
//                    tp.job = '已完成'
//                    tp.methord = '已完成'
//                    println(tp.validate())
//                    tp.save(flush: true)
//                }
            def leadProgress = TaskProgress.findOrCreateByTaskAndMonthAndType(task, month, '牵头')
            def op = TaskProgress.findAllByTaskAndType(task, '牵头',['sort':'month', 'order':'desc'])[0]
            leadProgress.team = task.leadTeam
            leadProgress.status = '已完成'
            leadProgress.plan = op.plan
            leadProgress.job = op.job
            leadProgress.methord = op.methord
//                println(leadProgress.validate())
            leadProgress.save(flush: true)
        }
    }
}
