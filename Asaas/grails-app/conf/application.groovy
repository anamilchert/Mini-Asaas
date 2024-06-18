    grails.plugin.springsecurity.password.algorithm = 'bcrypt'

    grails.plugin.springsecurity.userLookup.userDomainClassName = 'asaas.User'
    grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'asaas.UserRole'
    grails.plugin.springsecurity.authority.className = 'asaas.Role'

    grails.plugin.springsecurity.logout.postOnly = false
    grails.plugin.springsecurity.logout.filterProcessesUrl = '/logout'

    grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
    grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/payer/list'

    grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/login/auth',     access: ['permitAll']],
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

