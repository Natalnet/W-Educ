dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/weduc?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
            username = "weduc"
            password = "r]:{-(3C1;k/Y5N"
            properties {
                maxActive = 50
                maxIdle = 25
                minIdle = 5
                initialSize = 5
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                maxWait = 10000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = true
            }
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/weduc?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
            username = "weduc"
            password = "r]:{-(3C1;k/Y5N" 
                        properties {
                maxActive = 50
                maxIdle = 25
                minIdle = 5
                initialSize = 5
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                maxWait = 10000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = true
            }
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/weduc?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
            username = "weduc"
            password = "r]:{-(3C1;k/Y5N"
             properties {
                maxActive = 50
                maxIdle = 25
                minIdle = 5
                initialSize = 5
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                maxWait = 10000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = true
            }
        }
    }
}
