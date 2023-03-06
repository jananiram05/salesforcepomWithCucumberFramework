package com.runner;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;


import io.cucumber.testng.CucumberOptions;
@Listeners(com.utility.TestEventListnersutility.class)

@CucumberOptions(features = { "src/test/resources/salesforce.feature" }, glue = { "com.steps" })
public class RunnerForSalesforce extends AbstractTestNGCucumberTests {

}
