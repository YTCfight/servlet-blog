package frank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    // 是否请求成功
    private boolean success;

    // 错误码
    private String code;

    // 错误消息
    private String message;

    // 返回数据
    private Object data;
}
