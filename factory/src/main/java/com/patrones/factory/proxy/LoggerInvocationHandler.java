package com.patrones.factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.patrones.factory.anotaciones.MiLogger;

public class LoggerInvocationHandler implements InvocationHandler{

	private Object objOriginal;
	
	public LoggerInvocationHandler(Object objOriginal) {
		this.objOriginal = objOriginal;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    long startTime = System.currentTimeMillis(); // Registra el tiempo de inicio en milisegundos

	    System.out.printf("****Invocando metodo: %s.%s\n", objOriginal.getClass(), method.getName());

	    var ret = method.invoke(objOriginal, args);

	    long endTime = System.currentTimeMillis(); // Registra el tiempo de finalización en milisegundos

		String miLogger = objOriginal.getClass().getAnnotation(MiLogger.class).unidadTiempo();
	    double executionTimeInSeconds =0;
	    if(miLogger.equals("segundos")) {
	    	executionTimeInSeconds = (endTime - startTime) / 1000.0;
	    } else if (miLogger.equals("milisegundos")) {
	    	executionTimeInSeconds = (endTime - startTime);
	    } else if (miLogger.equals("microsegundos")) {
	    	executionTimeInSeconds = (endTime - startTime) * 1000.0;
	    } else {
	    	System.out.println("Unidad de Tiempo Invalido");
	    }

	    System.out.printf("*El metodo %s.%s tomo %.4f %s\n", objOriginal.getClass(), method.getName(), executionTimeInSeconds, miLogger);

	    return ret;
	}


}
