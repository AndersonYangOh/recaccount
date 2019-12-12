# recaccount
台账管理系统


## 一、使用说明
该系统最初为集团技术部开发，用于各单位等级事务开展进度，周期为1个月1次，管理者导出excel表进一步上报。一条台账有一个牵头单位和多个配合单位，每个单位设置联系人、填报人、负责人。
该项目采用grails框架开发，项目结构如下：

assets/**					项目前台资产，包括css，js，images等。

conf/**					项目配置文件所在目录，application.yml为应用主配置。

controllers/**				web控制器所在目录，WorklogController.groovy为web入口。

domain/**	 			数据库映射代码，每一个*.groovy文件映射一张数据库表。

i18n/**	 				国际化翻译文件目录，messages_zh_CN.properties为中文。

init/**					项目启动文件所在目录。

jobs/**					自动执行任务所在目录，AutoCompleteJob.groovy用于自动回填前月份的台账。

services/**				服务所在目录。

views/**					web模板所在目录，前台页面都通过模板显示。



## 二、实现功能

是一个完整的web项目，实现多层数据条目的展现，在此基础上分配填报者权限，指定的填报者可以登陆并按月填报任务进度。管理者则可以选择月份并导出全部任务完成信息。
