package com.patrones.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.google.common.reflect.ClassPath;
import com.patrones.factory.anotaciones.MiComponente;
import com.patrones.factory.anotaciones.MiLogger;
import com.patrones.factory.proxy.LoggerInvocationHandler;

public class FactoryImpl implements Factory {

	private Map<String, Class<?>> componentes = new HashMap<>();
	private Map<String, Object> singleton = new HashMap<>();

	private Object crearComponente(String nombre, Class<?> cls) throws Exception {
		var cto = cls.getConstructor();
		Object obj = cto.newInstance();

		var logger = obj.getClass().getAnnotation(MiLogger.class);

		if (logger != null) {
			InvocationHandler handler = new LoggerInvocationHandler(obj);
			return Proxy.newProxyInstance(FactoryImpl.class.getClassLoader(), cls.getInterfaces(), handler);
		} else {
			return obj;
		}
	}

	@Override
	public void init(String pkgName) {
		try {
			ClassPath classPath = ClassPath.from(FactoryImpl.class.getClassLoader());
			var classes = classPath.getTopLevelClassesRecursive(pkgName);

			for (var it : classes) {
				var miComp = it.load().getAnnotation(MiComponente.class);

				if (miComp != null) {
					var cls = it.load();
					componentes.put(miComp.name(), cls);

					if (miComp.singleton()) {
						var instancia = crearComponente(miComp.name(), cls);
						singleton.put(miComp.name(), instancia);
					}
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException("Error al inicializar la fábrica.", ex);
		}

		// Singletons
		singleton.forEach((key, value) -> {
			if (value instanceof Inicializar tmp) {
				tmp.onInit(this);
			}
		});
	}

	@Override
	public <T> T create(String name) {
		var value = componentes.get(name);

		if (value == null) {
			throw new RuntimeException("Componente " + name + " no registrado.");
		}

		var miComp = value.getAnnotation(MiComponente.class);

		if (miComp.singleton()) {
			return (T) singleton.get(name);
		} else {
			try {
				var obj = crearComponente(name, value);
				// No singleton
				if (obj instanceof Inicializar tmp) {
					tmp.onInit(this);
				}
				return (T) obj;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

}
