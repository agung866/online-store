package com.example.demo.config.constant;

/**
 * @author HermanW [hwahyudi@xl.co.id]
 * created at 31/01/2020 9:48
 */
public class ApplicationConstant {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String SPRINTASIA_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_MIDNIGHT = "00:00";
    public static final String DATE_TIME_FORMAT_CUTOFF = "HH:mm";
    public static final String DATE_TIME_ISO_MS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    String JDBC_MULTIPAYMENT = "jdbc-multipayment";

    public interface APP {
        String INDEX_NAME = "va-payment";
        String CONFIG_NAME = "va-payment";
        String PREFIX_UUID = "VAP-";
    }

    public interface MESSAGE {
        String DEFAULT_ERROR_MESSAGE = "Timeout/System Error";
    }

    public interface BEAN {
        String PREFIXMSISDN = "prefix-property";

        String TASKEXECUTOR_THREAD_POOL = "TaskExecutor-AX";
        String CHACED_THREAD_POOL = "CacheExecutor-AX";

        String DS_MULTIPAYMENT = "ds-multipayment";
        String DS_MULTIPAYMENT_POSTGRES = "ds-multipaymentPostgres";
        String JDBC_MULTIPAYMENT = "jdbc-multipayment";
        String JDBC_MULTIPAYMENT_POSTGRES = "jdbc-multipaymentPostgres";
//        String COMPONENT_MULTIPAYMENT = "multipayment";
//        String COMPONENT_MULTIPAYMENT_POSTGRES = "multipayment-postgres";
        String BEAN_DS_MULTIPAYMENT_READONLY = "ds-multipaymentPostgres-readonly";
         String BEAN_JDBC_MULTIPAYMENT_READONLY = "jdbc-multipaymentPostgres-readonly";


        String DS_MASTERDATA = "ds-masterdata";
        String JDBC_MASTERDATA = "jdbc-masterdata";

        String DS_BSSPROD = "ds-bssprod";
        String JDBC_BSSPROD = "jdbc-bssprod";

        String OKHTTP_XENDIT = "okhttp-xendit";
        String OKHTTP_COMMON = "okhttp-common";
    }

    public interface RULEFACT {
        String VALIDATION = "fact-validation";
        String SERVICE_PROPERTIES = "fact-serviceproperties";
        String MULTIPAYMENT_TRX = "fact-multipaymenttrx";
        String TRX_EXPIRATION = "fact-trxexpiration";
    }

    public interface EVENTADDINFO {
        String CP_KEY = "cp-key";
    }

    public enum COMPLETION_STATUS {
        SUCCESS,
        BUSINESS_ERROR,
        SYSTEM_ERROR
    }
    
    
    /**
     * multipayment profile 
     * 
     * @author adi p
     */
    public interface DB_PROFILES_MULTIPAYMENT {
        String POSTGRES = "postgres";
        String ORACLE = "oracle";
    }
}
