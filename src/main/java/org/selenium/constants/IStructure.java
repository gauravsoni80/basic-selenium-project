package org.selenium.constants;

public interface IStructure {
    public interface IFacebook {
        void initializeComponent();

        void mainTest(String user,String pass);

        void closeWebDriver();
        void flushReport();
    }

    public interface IAmazon {
        void initializeComponent();

        void mainTest(String search);

        void closeWebDriver();
        void flushReport();
    }
}
