package com.chinaunicom.devlab.support.group.big19

import groovy.sql.Sql
import pl.touk.excel.export.WebXlsxExporter

class WorklogController {

    def worklogService
    def dataSource
    def springSecurityService

    def index() {
        def nowMonth = new Date().format('yyyyMM')
        if (!params.month) params.month = nowMonth
        def tasks = TaskBasic.findAll()
//        for (task in tasks) {
//            if ((task.lastStatusMonth == null)||(task.lastStatusMonth <  nowMonth)) {       //如果当月状态未创建
//                def tp = TaskProgress.findOrCreateByTaskAndMonthAndTypeAndTeam(task, nowMonth,'牵头', task.leadTeam)
//                //tp.createPerson = springSecurityService.getCurrentUser()
//                task.lastStatus = tp.status
//                task.lastStatusMonth = tp.month
//                tp.save(flush:true)
//                task.save(flush:true)
//            }
//        }
        def allProgress = TaskProgress.findAllByMonthAndType(params.month, '牵头')
        respond tasks, model:[monthMap: worklogService.getAvailableMonthMap(), progressList: allProgress]
    }

    def task(TaskBasic task) {
        def nowMonth = new Date().format('yyyyMM')
        if (!params.month) params.month = nowMonth
        def teamList = task.cooperateTeams
        for (cm in teamList) {
            def tp = TaskProgress.findOrCreateByTaskAndMonthAndTypeAndTeam(task, params.month, '配合', cm)
            tp.save(flush:true)
            //System.out.println(tp.errors)
        }
        def cooperateProgressList = TaskProgress.findAllByTaskAndMonthAndType(task, params.month, '配合')
        def leadProgress = TaskProgress.findOrCreateByTaskAndMonthAndTypeAndTeam(task, params.month, '牵头', task.leadTeam).save()
        respond task, model:[monthMap: worklogService.getAvailableMonthMap(), teamList:teamList, leadProgress:leadProgress, cooperateProgressList:cooperateProgressList]
    }

    def update(TaskProgress progress) {
        def now = new Date()
        def nowMonth = now.format('yyyyMM')
        progress.save(flush: true)
        if ((params.leader == 'true')&&(progress.month == nowMonth)) {      //牵头单位修改项目最终状态
            progress.task.lastStatus = progress.status
            progress.task.lastStatusMonth = nowMonth
            progress.task.save(flush: true)
        }
        redirect(action: 'task', params: [id: progress.task.id, month: params.month])
    }

    //导出全部
    def exportAll() {
        if (!params.month) params.month = new Date().format('yyyyMM')
//        def headers = ['任务序号', '任务名称', '牵头部门','进度','落实方案及举措','进展情况（'+ new Date().format('yyyy-MM-dd') + '）','后续安排']
//        def withProperties = ['code', 'name','company','status','methord','job','plan']
        def headers = ['任务序号', '牵头部门', '承诺目标','承诺目标KR','愿景目标','愿景目标KR','落实方案及举措','进展情况（'+ new Date().format('yyyy-MM-dd') + '）','后续安排']
        def withProperties = ['code', 'company', 'promise', 'promiseKr', 'vision', 'visionKr', 'methord', 'job', 'plan']
        Sql dataSql = new Sql(dataSource)
        String sql = "SELECT \n" +
                "  task_progress.`month` month,\n" +
                "  task_basic.code code,\n" +
                "  task_basic.name name,\n" +
                "  task_company.name company,\n" +
                "  task_progress.`status` status,\n" +
                "  task_progress.promise promise,\n" +
                "  task_progress.vision vision,\n" +
                "  task_progress.promise_kr promiseKr,\n" +
                "  task_progress.vision_kr visionKr,\n" +
                "  task_progress.methord methord,\n" +
                "  task_progress.job job,\n" +
                "  task_progress.plan plan\n" +
                "FROM\n" +
                "  task_basic\n" +
                "  LEFT JOIN task_progress ON (task_basic.id = task_progress.task_id)\n" +
                "  LEFT JOIN task_team ON (task_basic.lead_team_id = task_team.id)\n" +
                "  LEFT JOIN task_company ON (task_team.company_id = task_company.id)\n" +
                "WHERE \n" +
                "(task_progress.type='牵头' OR task_progress.type is NULL)\n" +
                "AND (month='" + params.month + "' OR month is NULL)\n" +
                "ORDER BY code"
        def tasks = dataSql.rows(sql)
        for (task in tasks) task.code = task.code.replaceAll('分解任务','')
        def xlsxExporter = new WebXlsxExporter(servletContext.getRealPath('/exportAll.xlsx'))
        xlsxExporter.setWorksheetName('月度导出')
        xlsxExporter.with {
            setResponseHeaders(response, 'All_'+ params.month + '.xlsx')
            //putCellValue(100,100,'test')
            fillHeader(headers)
            add(tasks, withProperties)
            save(response.outputStream)
        }
    }

    def exportSelf() {
        if (!params.month) params.month = new Date().format('yyyyMM')
        def company = springSecurityService.getCurrentUser().company
        def teamList = TaskTeam.findAllByCompany(company)
        def progress = TaskProgress.findAllByMonthAndTeamInList(params.month, teamList)
        def myTasks = []
        for (task in TaskBasic.list([sort: 'code'])) {
            if (task.leadTeam in teamList) {
                myTasks.add(task)
                continue
            }
            for (coTeam in task.cooperateTeams) {
                if (coTeam in teamList) {
                    myTasks.add(task)
                    continue
                }
            }
        }
        def headers = ['月','牵头进度','任务序号', '任务名称', '牵头部门','填报部门','承诺目标','承诺目标KR','愿景目标','愿景目标KR','落实方案及举措','进展情况','后续安排']
        def xlsxExporter = new WebXlsxExporter(servletContext.getRealPath('/exportSelf.xlsx'))
        def lineNum = 1
        xlsxExporter.setWorksheetName('月度导出')
        xlsxExporter.with {
            setResponseHeaders(response, 'Self_'+ params.month + '.xlsx')
            fillHeader(headers)
            for (p in progress) {
                fillRow([p.month,TaskProgress.findByTaskAndMonthAndTeam(p.task, params.month, p.task.leadTeam).status, p.task.code, p.task.name, p.task.leadTeam.company.name, p.team.company.name, p.promise, p.promiseKr, p.vision, p.visionKr, p.methord, p.job, p.plan],lineNum++)
            }
            for (t in myTasks) {
                if (t.id in progress*.task.id) continue
                fillRow([params.month, TaskProgress.findByTaskAndMonthAndTeam(t, params.month, t.leadTeam)?.status, t.code, t.name, t.leadTeam.company.name, ''/*t.cooperateTeams*.company.name*/, '', '', '', '', '', '', ''],lineNum++)
            }
            save(response.outputStream)
        }
    }

    def remind(TaskBasic task) {
        println(params.month)
        def ret = new HashMap()
        def month = params.month
        if (month == null) month = new Date().format('yyyyMM')
        def sends = 0
        def curretnUserCompanyId = springSecurityService.getCurrentUser().company?.id
        if (task) {
            if (curretnUserCompanyId<= 5)
                sends = worklogService.remindTask(task, month)
        }
        else {
            if (curretnUserCompanyId== 5)
                sends = worklogService.remindAll(month)
        }
        ret.sendCount = sends
        respond ret
    }

}
