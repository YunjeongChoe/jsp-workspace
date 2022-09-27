package com.study.util;

import java.io.IOException;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupAppPython {
	@EventListener()
	public void startPythonServer(ContextRefreshedEvent event) {
		System.out.println("========================================================");
		Runtime rt = Runtime.getRuntime();
		
		Process proc;
		try {
			proc = rt.exec("/home/pc31/anaconda3/envs/bigdata1/bin/python //home/pc31/PycharmProjects/pythonProject/day22/ex_api/ex_restapi02.py");
			System.out.println(proc.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

