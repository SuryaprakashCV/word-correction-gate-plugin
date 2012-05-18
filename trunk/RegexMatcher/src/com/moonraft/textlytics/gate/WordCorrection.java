/*
 *  WordCorrection.java
 *
 *
 * Copyright (c) 2012, Moonraft Innovation Labs 
 * (http://www.moonraft.com)
 * 
 *
 */
package com.moonraft.textlytics.gate;

import java.util.*; 
import gate.*; 
import gate.creole.*; 
import gate.creole.metadata.CreoleResource;
import gate.util.*; 
import static gate.Utils.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;
import java.net.URL;
import gate.Annotation;
import gate.AnnotationSet;
import gate.Resource;
import gate.creole.AbstractLanguageAnalyser;
@CreoleResource(name = "WordCorrection", 
	       comment = "Correction using Regex") 
public @SuppressWarnings("all") class WordCorrection extends AbstractLanguageAnalyser implements ProcessingResource 
{
	URL model;	
	StringBuilder strBufFileContent = new StringBuilder();
	Logger log = Logger.getLogger("log_file");
	
	private static final long serialVersionUID = 1L;
	String inputASName;
	

	public String getInputASName() {
		return inputASName;
	}
	
	public void setInputASName(String inputASName) {
		this.inputASName = inputASName;
	}
	
	public void setModel(URL model) {		
		this.model = model;		
	}
	
	public URL getModel() {
		return model;
	}
	
	public void execute() 
	{
		try
		{			
			AnnotationSet bindings = document.getAnnotations(inputASName);
			AnnotationSet tokAnnots = bindings.get("Token");	
			List<Annotation>  tokList= new ArrayList<Annotation>(tokAnnots);
			String[] strArrayFileContent = this.strBufFileContent.toString().split("(\n)");
			for(int index=0; index<strArrayFileContent.length; index++)
			{
				String[] regexArray = strArrayFileContent[index].toString().split("(=)");
				for (int tokIndex=0; tokIndex < tokList.size(); tokIndex++) 
				{	
					Annotation tok = tokList.get(tokIndex);
					String strTokenContent = tok.getFeatures().get("string").toString();
					java.util.regex.Pattern regexPattern = java.util.regex.Pattern.compile(regexArray[0].toString());
					java.util.regex.Matcher regexMatcher = regexPattern.matcher(strTokenContent);
					boolean bMatchFound = regexMatcher.find();	  
					if(bMatchFound)
					{
						tok.getFeatures().put("string",regexArray[1].toString());     
					}
				}
		    }
		}
		catch(Exception e)
		{
		   e.printStackTrace();
		}
	
	}
	@Override
	public Resource init() throws ResourceInstantiationException {
		try{
			    String strEncoding = "UTF-8";
			    String strFileName = model.toString().replaceAll(("(file:)"), "");
			    String strLineSeperator = System.getProperty("line.separator");
			    Scanner scanner = new Scanner(new FileInputStream(strFileName), strEncoding);
			    try
			    {
			    	while (scanner.hasNextLine()){
			    		this.strBufFileContent.append(scanner.nextLine() + strLineSeperator);
				    }
				log.info("regex file uploaded: "+strFileName);
			    	  
				}
			    finally{
			        scanner.close();
			      }
	    }
		catch (Exception e) {
			e.printStackTrace();
		}
	return this;
	}
	@Override
	public void reInit() throws ResourceInstantiationException {		
		init();		
	}
}


