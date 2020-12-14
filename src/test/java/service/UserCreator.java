package service;

import model.User;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = "testdata.user.email";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";
    public static final String TESTDATA_INCORRECT_EMAIL = "testdata.user.incorrect.email";
    public static final String TESTDATA_INCORRECT_PASSWORD = "testdata.user.incorrect.password";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData("testdata.user.email"),
                TestDataReader.getTestData("testdata.user.password"));
    }

    public static User withIncorrectEmail(){
        return new User(TestDataReader.getTestData(TESTDATA_INCORRECT_EMAIL), TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyPassword(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME), "");
    }

    public static User withIncorrectPassword(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME), TestDataReader.getTestData(TESTDATA_INCORRECT_PASSWORD));
    }

}
