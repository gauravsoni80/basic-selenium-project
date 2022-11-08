package org.selenium.constants;

public interface ISeleniumXPath {
    public static interface IFacebook{
        public String USER = "//*[@name=\"email\"]";
        public String PASSWORD = "//*[@name=\"pass\"]";
        public String SUBMIT = "//*[@name=\"login\"]";
    }

    public static interface IAmazon{
        public String SEARCH = "//input[@id=\"twotabsearchtextbox\"]";
        public String SUBMIT = "//input[@id=\"nav-search-submit-button\"]";
    }
}
