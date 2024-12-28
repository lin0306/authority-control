package com.lin.authoritycontrol;

import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.base.Result;
import com.lin.authoritycontrol.common.enums.resp.ResultEnum;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 响应内容检查
 *
 * @author 林维家
 * @since 2024/12/28 下午5:33
 */
public class RespCheck {

    public static void checkCode(Result<?> result, boolean isSuccess) {
        assert result.getCode().equals(ResultEnum.SUCCESS.getCode()) == isSuccess;
    }

    public static void checkData(Result<?> result, boolean isSuccess) {
        if (isSuccess) {
            success(result);
        } else {
            failed(result);
        }
    }

    private static void success(Result<?> result) {
        checkCode(result, true);
        if (result.getData() instanceof List) {
            checkList((List<?>) result.getData(), true);
        } else if (result.getData() instanceof PageVO) {
            checkPage((PageVO<?>) result.getData(), true);
        } else {
            checkObj(result.getData(), true);
        }
    }

    private static void failed(Result<?> result) {
        if (result.getCode().equals(ResultEnum.FAILED.getCode())) {
            assert true;
        } else {
            if (result.getData() instanceof List) {
                checkList((List<?>) result.getData(), false);
            } else if (result.getData() instanceof PageVO) {
                checkPage((PageVO<?>) result.getData(), false);
            } else {
                checkObj(result.getData(), false);
            }
        }
    }

    private static void checkList(List<?> list, boolean hasData) {
        assert !CollectionUtils.isEmpty(list) == hasData;
    }

    private static void checkPage(PageVO<?> pageVO, boolean hasData) {
        if (hasData) {
            assert pageVO.getTotal() > 0;
            assert pageVO.getRecords() != null;
            assert !CollectionUtils.isEmpty(pageVO.getRecords());
        } else {
            assert pageVO.getTotal() == 0;
        }
    }

    private static void checkObj(Object obj, boolean hasData) {
        assert Objects.nonNull(obj) == hasData;
    }

}
