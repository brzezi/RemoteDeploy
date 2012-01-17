//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//


updateConfig()
private void updateConfig() {
	def appDir = "$basedir/grails-app"
	def configFile = new File(appDir, 'conf/Config.groovy')
	if (configFile.exists()) {
		configFile.withWriterAppend {
			it.writeLine '\n// Added by the Rsync Deploy plugin:'
			it.writeLine "grails.plugins.rsyncdeploy.host = 'someHost'"
			it.writeLine "grails.plugins.rsyncdeploy.username = 'someUser'"
			it.writeLine "grails.plugins.rsyncdeploy.deployPath = '/opt/tomcat/webapps'"
		}
	}
	
	def buildConfigFile = new File(appDir, 'conf/BuildConfig.groovy')
	if (buildConfigFile.exists()) {
		buildConfigFile.withWriterAppend {
			it.writeLine '\n// Added by the Rsync Deploy plugin:'
			it.writeLine "grails.war.exploded = true"
		}
	}
}