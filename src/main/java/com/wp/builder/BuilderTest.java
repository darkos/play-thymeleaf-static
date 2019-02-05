package com.wp.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.wp.model.Contact;

public class BuilderTest {
	
	public static void main(String[] args) throws IOException {
		
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//		FileTemplateResolver resolver = new FileTemplateResolver();
		resolver.setTemplateMode("XHTML");
		resolver.setSuffix(".html");
		resolver.setPrefix("/templates/webplease.net/");
		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		
		Context context = new Context(Locale.FRANCE);
		
		String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		context.setVariable("date", now);
		
		context.setVariable("contact",new Contact("John","Doe"));
		
		
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("Darko","Stefanovic"));
		contacts.add(new Contact("Mirjana","Stefanovic"));
		context.setVariable("contacts",contacts);
		
		
		StringWriter writer = new StringWriter();
		
		engine.process("home", context, writer);
		System.out.println("StringWriter:---------------------------------------");
		System.out.println(writer.getBuffer().toString());
		
//		System.out.println("OutputStreamWriter:---------------------------------------");
//		Writer consoleWriter = new OutputStreamWriter(System.out);
//		engine.process("home", context, consoleWriter);
//		
		System.out.println("FileWriter:---------------------------------------");
		Writer fileWriter = new FileWriter(new File("output.html"));
		engine.process("home", context, fileWriter);
		
	}

}
