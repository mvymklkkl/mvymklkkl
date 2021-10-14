package boot.spring.pagemodel;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:返回结果模型
 * History：
 * ========================================
 * 2019/8/8 8:33       1.0       Created by shenzhanwang
 * ========================================
 */
public class ResultResponse {

    private HttpStatus status; //应用程序状态码

    private Integer code; //响应码

    private String message;//错误消息

    private String developerMessage;//错误堆栈消息

    private Map<String, Object> data = new HashMap<>();
    
    public ResultResponse(HttpStatus status, Integer code, String message, String developerMessage,
			Map<String, Object> data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
		this.data = data;
	}

	/**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the developerMessage
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    /**
     * @param developerMessage the developerMessage to set
     */
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
