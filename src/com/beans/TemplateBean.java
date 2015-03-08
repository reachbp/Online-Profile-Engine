package com.beans;

import java.util.Map;

import freemarker.template.SimpleHash;

public class TemplateBean {
   SimpleHash root=new SimpleHash();
   String templateName="";

public String getTemplateName() {
	return templateName;
}
public void setTemplateName(String template_name) {
	this.templateName = template_name;
}
public SimpleHash getRoot() {
	return root;
}
public void setRoot(SimpleHash root) {
	this.root = root;
}
}
