grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.8
grails.project.source.level = 1.8

grails.project.fork = [
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true], // configure settings for the test-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256], // configure settings for the run-app JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256], // configure settings for the run-war JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256] // configure settings for the Console UI JVM
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes "ehcache"
    }
    log "error" // log level of Ivy resolver, either "error", "warn", "info", "debug" or "verbose"
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        mavenLocal()
        mavenRepo ("http://nexus.ala.org.au/content/groups/public/") {
            updatePolicy "daily"
        }
    }

    dependencies {
        compile("au.org.ala:ala-name-matching:2.4.0") {
            excludes "lucene-core", "lucene-analyzers-common", "lucene-queryparser", "simmetrics"
        }
        compile(
            "commons-httpclient:commons-httpclient:3.1",
            "org.codehaus.jackson:jackson-core-asl:1.8.6",
            "org.codehaus.jackson:jackson-mapper-asl:1.8.6"
        )
        runtime(
            "org.jsoup:jsoup:1.7.2"
        )
    }

    plugins {
        build(":release:3.0.1",
                ":rest-client-builder:2.0.3") {
            export = false
        }
        // plugins for the build system only
        build ":tomcat:7.0.70"

        runtime ":rest:0.8"

        compile ":asset-pipeline:2.14.1"
        compile ":cache:1.1.8"
        compile ":jquery:1.11.1"
        compile ":images-client-plugin:0.7.9-SNAPSHOT"
        compile ":elurikkus-charts:1.3"
        compile ":elurikkus-commons:0.2-SNAPSHOT"
    }
}
