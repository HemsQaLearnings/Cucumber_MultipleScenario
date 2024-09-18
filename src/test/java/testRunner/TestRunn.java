package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
//@CucumberOptions(features = {".//features"},glue="stepDefinitions") //.feature means it will execute all the files which are inside the folder
@CucumberOptions(
		// features= {".//Features/"},
		features= {".//Features/FirstLogin.feature"}, //datadrivern testing
		//features = { ".//Features/DataTable_Loginn.feature" },

		//features= {"@target/rerun.txt"}, //it work like retryAnnalyser like how we have
		// implemented in hybrid framework
		glue = { "stepDefinitions"}, plugin = { "pretty", "html:reports/myreport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		//"rerun:target/rerun.txt",
 
		},

		dryRun = false, // checks mapping between scenario steps and step definition methods by default it will be false
		monochrome = true, // to avoid junk characters in output by default it will be true
		publish = true, // to publish report in cucumber server

//tags="@sanity" // this will execute scenarios tagged with @sanity
//tags = "@RegressionTesting"
tags="@FuntionalTesting"
// tags="@sanity and @regression" //Scenarios tagged with both @sanity and
// @regression
// tags="@sanity and not @regression" //Scenarios tagged with @sanity but not
// tagged with @regression: it will execute only with @sanity
// tags="@sanity or @regression" //Scenarios tagged with either @sanity or
// @regression) //it will run only one feature file i.e login.featurefile
)
public class TestRunn {

}
