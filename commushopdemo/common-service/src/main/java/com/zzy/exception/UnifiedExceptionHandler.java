package com.zzy.exception;

import com.zzy.util.ResultVOUtil;
import com.zzy.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerException(Exception e){
        log.info("服务器内部异常，{}",e.getMessage());//打一个日志
//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(-1);
//        resultVO.setMsg("失败");
//        resultVO.setData(e.getMessage());
//        return resultVO;
        return ResultVOUtil.failMsg(e.getMessage());//代码复用
        //统一异常处理机制：log的信息给后端看，返给前端的ResultVO格式的信息给前端看
    }

}
