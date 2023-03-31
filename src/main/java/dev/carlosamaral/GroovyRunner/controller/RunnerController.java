package dev.carlosamaral.GroovyRunner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import groovy.lang.GroovyShell;
import groovy.lang.GroovyClassLoader;
import groovy.util.GroovyScriptEngine;

@RestController("/test")
public class RunnerController {

	@GetMapping(path = "/shell")
	public void runWithGroovyShell() throws Exception {

		new GroovyShell().parse(new File("/home/garlos/Documentos/test.groovy")).invokeMethod("hello_world", null);

	}

	@GetMapping(path = "/loader")
	public void runWithGroovyClassLoader() throws Exception {

		// Declaring a class to conform to a java interface class would get rid of
		// a lot of the reflection here
		Class scriptClass = new GroovyClassLoader().parseClass(new File("/home/garlos/Documentos/test.groovy"));
		Object scriptInstance = scriptClass.newInstance();
		scriptClass.getDeclaredMethod("hello_world", new Class[] {}).invoke(scriptInstance, new Object[] {});

	}

	@GetMapping(path = "/engine")
	public void runWithGroovyScriptEngine() throws Exception {

		// Declaring a class to conform to a java interface class would get rid of
		// a lot of the reflection here
		Class scriptClass = new GroovyScriptEngine(".").loadScriptByName("/home/garlos/Documentos/test.groovy");
		Object scriptInstance = scriptClass.newInstance();
		scriptClass.getDeclaredMethod("hello_world", new Class[] {}).invoke(scriptInstance, new Object[] {});

	}

}
