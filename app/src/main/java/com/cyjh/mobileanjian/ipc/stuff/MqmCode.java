package com.cyjh.mobileanjian.ipc.stuff;

/* loaded from: classes.dex */
public class MqmCode {
    public static final int MQM_RUNNER_ERROR_APP_WEB_CHECK_CHECK_APP_ERR = 111;
    public static final int MQM_RUNNER_ERROR_APP_WEB_CHECK_CHECK_TOKEN_INVALID_ERR = 112;
    public static final int MQM_RUNNER_ERROR_APP_WEB_CHECK_CHECK_TOKEN_KICKED_ERR = 110;
    public static final int MQM_RUNNER_ERROR_APP_WEB_CHECK_GENERATE_TOKEN_ERR = 109;
    public static final int MQM_RUNNER_ERROR_CHECK_APP_ABNORMAL = 108;
    public static final int MQM_RUNNER_ERROR_DECRYPT_FAILED = 104;
    public static final int MQM_RUNNER_ERROR_OPEN_FILE_FAILED = 102;
    public static final int MQM_RUNNER_ERROR_TRIAL_EXPIRED = 106;
    public static final int MQM_RUNNER_EXCEPTION = 1002;
    public static final int MQM_RUNNER_FINISH_SCRIPT = 0;
    public static final int MQM_RUNNER_NO_ROOT_DAEMON = 1003;
    public static final int MQM_RUNNER_ROOT_REFUSED = 1001;
    public static final int MQM_RUNNER_STOP_SCRIPT = 105;

    public static String getMessageFromCode(int i) {
        if (i == 104) {
            return "脚本验证失败";
        }
        switch (i) {
            case 108:
                return "服务器数据格式异常";
            case 109:
                return "脚本注册失败";
            case 110:
                return "其它端登陆";
            case 111:
                return "服务器数据内容错误";
            case 112:
                return "网络连接断开";
            default:
                switch (i) {
                    case 1002:
                        return "脚本进程异常终止";
                    case 1003:
                        return "脚本进程未启动";
                    default:
                        return "UNKOWN";
                }
        }
    }
}
