package br.uff.qet.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resource/"
		,glue = "stepDefinition"
		,format = {"pretty"}
		)
public class CadastroRunner {
	
}
