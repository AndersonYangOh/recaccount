// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.password.algorithm = 'MD5'
grails.plugin.springsecurity.password.hash.iterations = 1
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.chinaunicom.devlab.support.group.big19.UserPerson'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.chinaunicom.devlab.support.group.big19.UserPersonUserRole'
grails.plugin.springsecurity.authority.className = 'com.chinaunicom.devlab.support.group.big19.UserRole'
grails.plugin.springsecurity.requestMap.className = 'com.chinaunicom.devlab.support.group.big19.UserRequestMap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/login/**', 		 access: ['permitAll']]
]

//grails.assets.minifyJs = false
//grails.assets.minifyOptions = [
//		languageMode: 'ES6',
//		targetLanguage: 'ES6', //Can go from ES6 to ES5 for those bleeding edgers
//		optimizationLevel: 'SIMPLE' //Or ADVANCED or WHITESPACE_ONLY
//]

grails.mail.host = "hq.smtp.chinaunicom.cn"
grails.mail.port = 25
grails.mail.username = "hqs-rdcloud"
grails.mail.password = "xxxxxxxx"
grails.mail.from = 'hqs-rdcloud@chinaunicom.cn'
grails.mail.props = ["mail.smtp.auth":"true"]

delivery_app {  //交付产品设置
	//grails.config.locations = ["file:${basedir}/grails-app/conf/app.groovy"]    //"classpath:app.properties"
	service.name = "研究院工时系统"
	service.version = "0.1"                 //版本号
	service.host = "localhost"              //服务域名或ip地址  leadyoungtec.com 101.200.144.180
}
