package com.patrones;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Ejemplo {
	void test();
	void algo();
}

class TestImp implements Ejemplo {
	@Override
	public void test() {
		System.out.println("***EjemploImp.test");	
	}

	@Override
	public void algo() {
		System.out.println("***EjemploImp.algo");	
	}
}

//class TestProxyImp implements Ejemplo {
//	TestImp servicio = new TestImp();
//	
//	@Override
//	public void test() {
//		System.out.println("proxy antes");
//		servicio.test();	
//		System.out.println("proxy despues");
//	}
//
//	@Override
//	public void algo() {
//		// TODO Auto-generated method stub
//		
//	}
//}

class TestDynamicProxy implements InvocationHandler {
	TestImp obj = new TestImp();
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("PROXY: invocando metodo: " + method.getName() );
		
		var ret = method.invoke(obj, args);
		
		return ret;
	}
}

public class PrincipalCrudProxy {

	public static void main(String[] args) throws Exception {
		
		//Ejemplo servicio = new TestProxyImp();
		
		//servicio.test();
		
		//--------------
		InvocationHandler handler = new TestDynamicProxy();
		
		Ejemplo srv = (Ejemplo )Proxy.newProxyInstance( 
				PrincipalCrudProxy.class.getClassLoader(), 
				new Class<?>[] { Ejemplo.class },
				handler
			);
		
		srv.test();
		srv.algo();
		
		

//		Factory fact = new FactoryImpl();
//		fact.init("com.patrones");
//				
//		ServicioLibros servicioConsultaLibros = fact.create("servicioLibros");
//
//		var libros = servicioConsultaLibros.listarTodos();
//		
//		libros.forEach(System.out::println);
//		
	}

}
