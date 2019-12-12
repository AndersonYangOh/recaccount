package com.chinaunicom.devlab.support.group.big19

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.*
import pl.touk.excel.export.WebXlsxExporter
//import grails.util.Holders

class TestController {

    def mailService
    def springSecurityService
    def worklogService

    def index() {

    }

    //邮件测试
    def mail() {
        String mailContent = "您好，这是系统发送的测试。"
        mailService.sendMail {
            from 'hqs-rdcloud@chinaunicom.cn'
            to 'lihui5@chinaunicom.cn'
            subject "系统测试"
            html mailContent
        }
    }

    //excel导出测试
    def excelExport() {
        def headers = ['项目名称', '项目编号', '部门']
        def withProperties = ['name', 'code', 'department']
        def projects = ProjectBasic.findAll()
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(projects, withProperties)
            save(response.outputStream)
        }
    }

    @Transactional
    def importTeam() {
        //def jstr = params.json
        def jstr = '[\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-01",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "裴小燕",\n' +
                '   "contacts": "邢建兵",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "陈红",\n' +
                '   "contacts": "",\n' +
                '   "participants": "陈红"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "张瑜",\n' +
                '   "contacts": "",\n' +
                '   "participants": "张瑜"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "软研院",\n' +
                '   "principals": "王春佳",\n' +
                '   "contacts": "王春佳",\n' +
                '   "participants": "成刚,王迪"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "曹侃",\n' +
                '   "contacts": "巫灵珊",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-02",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "王靖宇",\n' +
                '   "contacts": "吴化民",\n' +
                '   "participants": "周晓霞,邢建兵,程莹"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "李东艳,徐雷",\n' +
                '   "contacts": "",\n' +
                '   "participants": "李东艳,赵鑫,邵奇,邢雨桐"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-03",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "王靖宇",\n' +
                '   "contacts": "吴化民,周允",\n' +
                '   "participants": "周晓敏,邢建兵,程莹"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "李仲侠",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "文博",\n' +
                '   "contacts": "文博",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "软研院",\n' +
                '   "principals": "李洪宾",\n' +
                '   "contacts": "王春佳",\n' +
                '   "participants": "李世春,李艳红"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "巫灵珊",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-04",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "严斌峰",\n' +
                '   "contacts": "侯玉华",\n' +
                '   "participants": "李兴新"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-05",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "马铮",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-06",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "秦蓁",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.1-07",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "裴小燕",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-01",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "裴小燕",\n' +
                '   "contacts": "周晓敏,王芃,贾国材",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "张云勇,钱蓓力",\n' +
                '   "contacts": "徐雷",\n' +
                '   "participants": "马书惠"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "朱常波,唐雄燕",\n' +
                '   "contacts": "杨剑键",\n' +
                '   "participants": "文博"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "软研院",\n' +
                '   "principals": "娄瑜",\n' +
                '   "contacts": "李洪宾 ",\n' +
                '   "participants": "王春佳,宁丹扬"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "王延红",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-10",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓敏"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-10",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "魏进武",\n' +
                '   "participants": "张金玲"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-11",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "王芃"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-11",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "安岗",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-12",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "裴小燕"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-12",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "严斌峰",\n' +
                '   "participants": "侯玉华"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-14",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓敏"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-14",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "吕华章",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-15",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓霞"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-15",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "张雪贝",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-16",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓霞"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-16",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "赵良",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-17",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓敏"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-17",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "晁昆",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-18",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓霞"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-18",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "张沛",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-19",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓霞"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-19",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "邵岩",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-02",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "裴小燕",\n' +
                '   "contacts": "邢建兵",\n' +
                '   "participants": "吴化民,周晓敏"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "陈红",\n' +
                '   "contacts": "",\n' +
                '   "participants": "杨爽"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "朱爱华",\n' +
                '   "contacts": "",\n' +
                '   "participants": "朱爱华"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "软研院",\n' +
                '   "principals": "王春佳",\n' +
                '   "contacts": "成刚",\n' +
                '   "participants": "薛芳,郁淙,巩金明"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "曹侃",\n' +
                '   "contacts": "巫灵珊",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-20",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "贾雪琴",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-21",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓敏"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-21",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "软研院",\n' +
                '   "principals": "董宁",\n' +
                '   "contacts": "董宁",\n' +
                '   "participants": "刘金财,夏睿,张申,王涛,李岳"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-26",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "王延红",\n' +
                '   "contacts": "董文宇",\n' +
                '   "participants": "董文宇,钱鹏,钱国忠"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-27",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "赵良",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-28",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "张沛",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-29",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "刘思聪",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-03",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "王靖宇",\n' +
                '   "contacts": "翟婧",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "李腾",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "张瑜",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "软研院",\n' +
                '   "principals": "李洪宾",\n' +
                '   "contacts": "王迪",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "巫灵珊",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-30",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "贾雪琴",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-31",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "韩玉辉",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-32",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "杨磊",\n' +
                '   "contacts": "李鹏飞",\n' +
                '   "participants": "李慧,赵彬平"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-33",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "杨磊",\n' +
                '   "contacts": "李鹏飞",\n' +
                '   "participants": "李慧,赵彬平"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-34",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "谢仁艿",\n' +
                '   "contacts": "赵伟光",\n' +
                '   "participants": "张远文,尤鸿,黄亚楠"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-35",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "物联网研究院",\n' +
                '   "principals": "胡振平",\n' +
                '   "contacts": "鲍中",\n' +
                '   "participants": "申歆楠,邓洁馨"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-04",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "王芃"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-04",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "严斌峰",\n' +
                '   "participants": "仇剑书"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-05",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "王芃"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-05",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "严斌峰",\n' +
                '   "participants": "王湘宁"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-09",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "",\n' +
                '   "contacts": "",\n' +
                '   "participants": "周晓敏"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.2-09",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "魏进武",\n' +
                '   "participants": "张正明"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-01",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "王明会",\n' +
                '   "contacts": "程莹",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "向学余",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-01",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "冯建民",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-10",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "冯建民",\n' +
                '   "participants": "韩潇,任驰"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-11",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "冯建民",\n' +
                '   "participants": "郭爱鹏,苗杰,郑毅,周伟"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-12",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "冯建民",\n' +
                '   "participants": "沈世奎"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-13",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "冯建民",\n' +
                '   "participants": "贾雪琴"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-14",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "冯建民",\n' +
                '   "participants": "高枫"\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-02",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "王明会",\n' +
                '   "contacts": "周晓霞",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "向学余",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-02",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "冯建民",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-03",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "集团技术部",\n' +
                '   "principals": "王明会",\n' +
                '   "contacts": "赵爽",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "向学余",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-03",\n' +
                '   "duty": "配合",\n' +
                '   "companyName": "网研院",\n' +
                '   "principals": "冯建民",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-04",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "加雄伟",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-05",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "王鑫",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-06",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "",\n' +
                '   "contacts": "周晶",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-07",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "安岗",\n' +
                '   "contacts": "陶蒙华",\n' +
                '   "participants": ""\n' +
                ' },\n' +
                ' {\n' +
                '   "taskCode": "分解任务21.3-08",\n' +
                '   "duty": "牵头",\n' +
                '   "companyName": "研究院",\n' +
                '   "principals": "徐雷",\n' +
                '   "contacts": "",\n' +
                '   "participants": ""\n' +
                ' }\n' +
                ']'
        def jsonObj = JSON.parse(jstr)
        for (line in jsonObj) {
            def team = new TaskTeam()
            team.name = line.taskCode
            team.company = TaskCompany.findByName(line.companyName)
            if (!team.company) {System.out.println('没找到公司: ' + line.companyName); throw new Exception()}
            team.duty = line.duty
            team.matter = line.duty + ": " + line.taskCode
            def principals=[],contacts=[],participants=[]
            if (line.principals) principals = line.principals.split(",")            /*责任人*/
            if (line.contacts) contacts = line.contacts.split(",")                  /*联系人*/
            if (line.participants) participants = line.participants.split(",")      /*参与人*/
            for (p in principals) {
                def person = UserPerson.findByName(p)
                if (!person) {System.out.println('没找到人: ' + p); throw new Exception()}
                team.addToPrincipals(person)
            }
            for (p in contacts) {
                def person = UserPerson.findByName(p)
                if (!person) {System.out.println('没找到人: ' + p); throw new Exception()}
                team.addToContacts(person)
            }
            for (p in participants) {
                def person = UserPerson.findByName(p)
                if (!person) {System.out.println('没找到人: ' + p); throw new Exception()}
                team.addToParticipants(person)
            }
            team.save(flash:true)
        }
        respond jsonObj
    }

    @Transactional
    def importTask() {
        def jstr = '[\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-01",\n' +
                '   "name": "国家项目申报、在研项目的实施与验收"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-02",\n' +
                '   "name": "科技创新能力指数发布"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-03",\n' +
                '   "name": "研发项目后评价和激励"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-04",\n' +
                '   "name": "安全手机研发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-05",\n' +
                '   "name": "中国联通网络安全运维和服务能力提升"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-06",\n' +
                '   "name": "中国联通网研院创新孵化基地"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "一、党组重点工作任务：\\n（五）向生态发展要机会\\n6.全面梳理提出运营商之间在市场经营、移动语音解决方案、2G减频、统一支付等各方面合作事项，推进运营商行业深度合作\\n二、党组专项工作任务\\n（四）新动能 \\n45.统筹四大研究院、子公司、省级公司研发资源，减少重复研发、加强顶层设计规划与基层自主创新相结合\\n（五）新生态\\n49.深入落实行业内企业间合作事宜，系统归类，全面梳理，提出运营商之间开展深度合作事项\\n三、网信工作重点任务台账（标注）\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。\\n",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "omsAndSasacEntry": "21.1加大技术创新力度，围绕国家重大科技项目，发挥企业主体作用，加大信息通信的关键共性技术、前沿引领技术和颠覆性技术创新。结合公司科技战略规划，积极承担国家重大项目，提升公司自主创新能力。",\n' +
                '   "code": "分解任务21.1-07",\n' +
                '   "name": "统筹研发资源，顶层设计与基层自主创新结合"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-01",\n' +
                '   "name": "科技发展规划"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-02",\n' +
                '   "name": "研发项目备案（含研发费用加计扣除）和供需对接支撑"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-03",\n' +
                '   "name": "提升专利管理水平，培育高价值专利"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-04",\n' +
                '   "name": "eSIM和eID技术研发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-05",\n' +
                '   "name": "无人机技术研发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-09",\n' +
                '   "name": "中国联通官网门户网站IPV6改造及支撑运营"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-10",\n' +
                '   "name": "数据能力服务平台"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-11",\n' +
                '   "name": "产业互联网关键技术和创新应用研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-12",\n' +
                '   "name": "智能终端OS及安全手机研发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-14",\n' +
                '   "name": "中国联通固移融合多接入边缘计算技术及平台研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-15",\n' +
                '   "name": "CUBE-NET2.0网络转型"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-16",\n' +
                '   "name": "人工智能在运维故障溯源中的应用"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-17",\n' +
                '   "name": "基于大数据的大气污染防控研究与应用"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-18",\n' +
                '   "name": "中国联通智能家庭网关C操作系统研发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-19",\n' +
                '   "name": "固网网络终端开发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-20",\n' +
                '   "name": "基于边缘计算的工业互联网测试床项目（万向合作）"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-27",\n' +
                '   "name": "IPRAN网络故障智能运维技术研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-28",\n' +
                '   "name": "5G融媒体产品研发及应用部署"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-29",\n' +
                '   "name": "中国联通5G港口自动化改造"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-30",\n' +
                '   "name": "基于工业互联网标识的运营商数据赋能试点"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-31",\n' +
                '   "name": "城市规划大数据平台"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-21",\n' +
                '   "name": "中国联通新客服科技创新项目合作研发"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-26",\n' +
                '   "name": "工业物联网应用场景设计研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-32",\n' +
                '   "name": "新型智慧城市建设方案研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-33",\n' +
                '   "name": "物联网智库建设"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-34",\n' +
                '   "name": "公网对讲POC模组低功耗单芯片方案研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n4.加强核心技术自主创新。加强核心技术自主创新，完善技术创新体系，专利成果、5G研发、IT领域、人工智能、区块链新技术与应用、沃Phone系统等领域实现重点突破。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。前沿领域做好5G网络和业务创新、SDN/NFV、网络智能机器人研究试验，基础领域做好人工智能、云计算、大数据、安全、基础网络研究。",\n' +
                '   "omsAndSasacEntry": "21.2在前沿领域做好5G网络和业务创新，在基础领域做好人工智能、云计算、大数据、安全、基础网络研究，为客户提供可信赖的消费互联网、家庭互联网、产业互联网服务。",\n' +
                '   "code": "分解任务21.2-35",\n' +
                '   "name": "物联网在数字供应链中的应用研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-01",\n' +
                '   "name": "不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-02",\n' +
                '   "name": "积极参加行业标准跟踪研究"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-03",\n' +
                '   "name": "不断加强企业标准体系建设"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-04",\n' +
                '   "name": "人工智能与区块链标准化工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-05",\n' +
                '   "name": "终端技术标准化工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-06",\n' +
                '   "name": "5G终端标准化工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-07",\n' +
                '   "name": "民用无人机国际标准化工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-08",\n' +
                '   "name": "云计算国际标准、国家标准、行业标准制定和开源社区跟踪"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-10",\n' +
                '   "name": "5G标准化工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-11",\n' +
                '   "name": "网络虚拟化、通信云标准工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-12",\n' +
                '   "name": "传输标准化工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-13",\n' +
                '   "name": "物联网领域国际标准相关工作"\n' +
                ' },\n' +
                ' {\n' +
                '   "big19Entry": "三、网信工作重点任务台账\\n7.积极助力提升网络空间国际话语权。拓展网络空间技术标准参与领域，提升在标准组织和开源社区的影响力，提升技术标准主导能力，在载波聚合、智能终端用户体验、云计算、SDN、物联网、城域综合业务承载、信息安全等优势标准化领域实现系列标准布局。",\n' +
                '   "itSecureEntry": "六、坚决防范化解科技领域重大风险\\n10.持续加大技术创新力度。持续强化在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设。",\n' +
                '   "omsAndSasacEntry": "21.3不断强化中国联通在国际标准工作中的主导作用，提升科技创新与竞争话语权。积极参加行业标准跟踪研究，不断加强企业标准体系建设",\n' +
                '   "code": "分解任务21.3-14",\n' +
                '   "name": "网络与信息安全标准化"\n' +
                ' }\n' +
                ']'
        def jsonObj = JSON.parse(jstr)
        for (line in jsonObj) {
            def task = new TaskBasic()
            task.big19Entry = line.big19Entry
            task.itSecureEntry = line.itSecureEntry
            task.omsAndSasacEntry = line.omsAndSasacEntry
            task.newUnicom = line.newUnicom
            task.memo = line.memo
            task.code = line.code
            task.name = line.name
            task.leadTeam = TaskTeam.findByNameAndDuty(line.code, '牵头')
            if (!task.leadTeam) {System.out.println('没找到牵头团队: ' + line.code); throw new Exception()}
            def cooperateTeams = TaskTeam.findAllByNameAndDuty(line.code,'配合')
            if (cooperateTeams.size() == 0) System.out.println('没找到配合团队: ' + line.code)
            task.cooperateTeams = cooperateTeams
            task.save(flush: true)
        }
        respond jsonObj
    }

    def createRole() {
        for (user in UserPerson.list()) {
            UserPersonUserRole.create(user, UserRole.get(1), true)
        }
        render 'ok'
    }

    def forceLogin() {
        System.out.println(springSecurityService.getPrincipal().getProperties())
        springSecurityService.reauthenticate('ycc')
        //def prin = springSecurityService.getPrincipal().getProperties()
        System.out.println(springSecurityService.getPrincipal().getProperties())
        redirect(url: '/worklog')
    }

    def httpc() {
        def http = new HTTPBuilder('http://10.249.218.156:7731/service/auth2/userInfo?access_token=TDJ2ClGey7eWHERkOU6EBwMMKZNUUURHcSMUijZx')
        http.request(POST) {
            body=[name:'berdy']
            requestContentType=URLENC
            response.success={resp, json->
                println('调用成功')
                println(json)
                println	resp.statusLine.statusCode
                println request.toString()
            }
            response.failure={resp, json->
                println('调用失败')
                println(json)
                println resp.status
                println request.toString()
            }
        }
        render('ok')
    }

    def receive() {
        println(params)
        render('ok')
    }

    //补充联系人数据
    @Transactional
    def checkPerson() {
        def tasks = TaskBasic.findAll()
        for (task in tasks) {
            if (task.leadTeam.contacts.size() <= 0) {
                println("============[")
                println(task.name)
                println(task.leadTeam.principals*.name)
                println(task.leadTeam.participants*.name)
                println("============]")
                task.leadTeam.addToContacts(task.leadTeam.principals[0])
                println(task.leadTeam.errors)
                task.save(flash:true)
                println(task.errors)
            }
            for (t in task.cooperateTeams) {
                if (t.contacts.size() <= 0) {
                    println("============[")
                    println(task.name)
                    println(t.principals*.name)
                    println(t.participants*.name)
                    println("============]" + t.principals.size())
                    if (t.principals.size() == 1) t.addToContacts(t.principals[0])
                    if (t.principals.size() == 0) t.addToContacts(t.participants[0])
                    if (t.principals.size() == 2) t.addToContacts(t.participants[0])
                    task.save(flash:true)
                    println(task.errors)
                }
            }
        }
        render('ok')
    }

    def remindTest() {
        worklogService.remindTask(TaskBasic.get(6) ,'201810')
        render('ok')
    }

    def autoComplete() {
        worklogService.autoComplete()
        render('ok')
    }
}
