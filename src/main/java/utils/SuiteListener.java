package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {
    public void onStart(ISuite suite) {
        Authorization.loginToJIRA();
    }

    public void onFinish(ISuite suite) {

    }
}