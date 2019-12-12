package com.chinaunicom.devlab.support.group.big19


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class UserPerson implements Serializable {

    private static final long serialVersionUID = 1

    String          name
    String          mobile
    String          email
    TaskCompany     company              //所属单位

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<UserRole> getAuthorities() {
        (UserPersonUserRole.findAllByUserPerson(this) as List<UserPersonUserRole>)*.userRole as Set<UserRole>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        mobile nullable: true
        email nullable: true
        company nullable: true
    }

    static mapping = {
	    password column: '`password`'
    }
}
