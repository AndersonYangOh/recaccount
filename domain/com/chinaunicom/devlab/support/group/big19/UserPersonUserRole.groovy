package com.chinaunicom.devlab.support.group.big19

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UserPersonUserRole implements Serializable {

	private static final long serialVersionUID = 1

	UserPerson userPerson
	UserRole userRole

	@Override
	boolean equals(other) {
		if (other instanceof UserPersonUserRole) {
			other.userPersonId == userPerson?.id && other.userRoleId == userRole?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (userPerson) {
            hashCode = HashCodeHelper.updateHash(hashCode, userPerson.id)
		}
		if (userRole) {
		    hashCode = HashCodeHelper.updateHash(hashCode, userRole.id)
		}
		hashCode
	}

	static UserPersonUserRole get(long userPersonId, long userRoleId) {
		criteriaFor(userPersonId, userRoleId).get()
	}

	static boolean exists(long userPersonId, long userRoleId) {
		criteriaFor(userPersonId, userRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long userPersonId, long userRoleId) {
		UserPersonUserRole.where {
			userPerson == UserPerson.load(userPersonId) &&
			userRole == UserRole.load(userRoleId)
		}
	}

	static UserPersonUserRole create(UserPerson userPerson, UserRole userRole, boolean flush = false) {
		def instance = new UserPersonUserRole(userPerson: userPerson, userRole: userRole)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(UserPerson u, UserRole r) {
		if (u != null && r != null) {
			UserPersonUserRole.where { userPerson == u && userRole == r }.deleteAll()
		}
	}

	static int removeAll(UserPerson u) {
		u == null ? 0 : UserPersonUserRole.where { userPerson == u }.deleteAll() as int
	}

	static int removeAll(UserRole r) {
		r == null ? 0 : UserPersonUserRole.where { userRole == r }.deleteAll() as int
	}

	static constraints = {
	    userPerson nullable: false
		userRole nullable: false, validator: { UserRole r, UserPersonUserRole ur ->
			if (ur.userPerson?.id) {
				if (UserPersonUserRole.exists(ur.userPerson.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['userPerson', 'userRole']		//lihui可以考虑去掉，是因为自动生成管理代码不支持联合主键
		version false
	}
}
