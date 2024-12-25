package com.lin.authoritycontrol.common.base;

import com.lin.authoritycontrol.common.enums.resp.ResultEnum;
import lombok.Data;

/**
 * 响应体
 *
 * @author 林维家
 * @since 2024/12/25 下午9:12
 */
@Data
public class Result<T> {

    private static final Result<Void> success = new Result<>(ResultEnum.SUCCESS);

    /**
     * 结果码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public Result(String code) {
        this.code = code;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
    }

    public void SetCode(String code) {
        this.code = code;
    }

    public void SetCode(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
    }

    /**
     * 操作成功
     *
     * @return 不返回任何数据
     */
    public static Result<Void> success() {
        return success;
    }

    /**
     * 操作成功
     *
     * @param data 需要响应的数据
     * @return 将需要响应的数据返还给前端
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 操作失败，业务异常
     *
     * @param message 失败消息
     * @return 操作失败，将失败消息响应给前端
     */
    public static Result<Void> failed(String message) {
        Result<Void> result = new Result<>(ResultEnum.FAILED);
        result.setMsg(message);
        return result;
    }
}
