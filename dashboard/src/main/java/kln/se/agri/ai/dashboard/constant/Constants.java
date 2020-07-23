package kln.se.agri.ai.dashboard.constant;

/**
 * Created by Prasad on 12/15/19.
 */

public class Constants {

    private Constants() { }

    public final class AgriConstants{
        public static final String DATABASE_URL = "spring.datasource.url";
        private AgriConstants(){}
    }

    public final class Database {
        public static final String DATABASE_DRIVER_DATASOURCE_CLAZZ = "spring.datasource.driver-class-name";
        public static final String DATABASE_URL = "spring.datasource.url";
        public static final String DATABASE_USERNAME = "spring.datasource.username";
        public static final String DATABASE_PWD = "spring.datasource.password";
        public static final String DATABASE_CONNECTION_TEST_QUERY = "spring.datasource.connection.test.query";
        public static final String DATABASE_MAX_POOL_SIZE = "spring.datasource.hikari.maximum-pool-size";

        private Database() {
        }

    }

    public final class SuccessMessageCodes {
        public static final String IMAGE_UPLOAD_SUCCESSFULLY_CODE = "image.uploaded.successfully.code";
        public static final String IMAGE_UPLOAD_SUCCESSFULLY_MESSAGE = "image.uploaded.successfully.message";
    }

    public final class ErrorMessageCodes {
        public static final String IMAGE_UPLOAD_ERROR_CODE = "image.uploaded.error.code";
        public static final String IMAGE_UPLOAD_ERROR_MESSAGE = "image.uploaded.error.message";
    }

}
