includeTargets << grailsScript("_GrailsPackage" )
includeTargets << grailsScript("_GrailsWar")
includeTargets << grailsScript("_GrailsSettings")

target('main': "The description of the script goes here!") {
	depends(checkVersion, war, classpath)
	
	def host = config.grails.plugins.rsyncdeploy.host
	def username = config.grails.plugins.rsyncdeploy.username 
	def sourcePath = grailsSettings.projectWarExplodedDir //"${grailsSettings.projectWorkDir}/stage"
	def deployPath = config.grails.plugins.rsyncdeploy.deployPath
	
//	assert host
//	assert username
//	assert sourcePath
//	assert deployPath
//	
	publish(host, username, sourcePath, deployPath)
}

setDefaultTarget('main')

def publish (host, username, sourcePath, deployPath) {
	
	event('StatusUpdate',["Publishing: rsync -e ssh -Cavz --delete --progress ${sourcePath}/ ${username}@${host}:/${deployPath}"])
//	grailsConsole.log("Publishing: rsync -e ssh -Cavz --delete --progress ${sourcePath}/ ${username}@${host}:/${deployPath}")
	
	ant.exec(outputproperty:"cmdOut", executable:"rsync"){
		ant.arg(line: "-e ssh -Cavz --delete --progress ${sourcePath}/ ${username}@${host}:/${deployPath}")
	}
	
	grailsConsole.log(ant.project.properties.cmdOut)
	
	event('StatusFinal', ['Publish finished'])
}
