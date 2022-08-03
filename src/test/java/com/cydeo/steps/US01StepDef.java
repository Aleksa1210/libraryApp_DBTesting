package com.cydeo.steps;

import com.cydeo.pages.LoginPage;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class US01StepDef {
    LoginPage loginPage = new LoginPage();


    @Given("Establish the database connection")
    public void establish_the_database_connection() {
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DB_Util.runQuery("select * from users");

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
    DB_Util.runQuery("select count(id) from users");
    String actual = DB_Util.getFirstRowFirstColumn();

    DB_Util.runQuery("select distinct count(full_name) from users\n" +
            "where full_name is not null");
    String expected = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expected, actual);

    }
     List<String> actualColumnNames;
    //GIVEN
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");
        actualColumnNames = DB_Util.getAllColumnNamesAsList();


    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> dataTable) {
       Assert.assertEquals(dataTable,actualColumnNames);

    }

}
