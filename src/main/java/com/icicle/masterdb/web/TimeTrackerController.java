package com.icicle.masterdb.web;

import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.manager.CacheManager;
import com.icicle.masterdb.pojo.bo.TimeTrace;
import com.icicle.masterdb.service.ProductImageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author wangyuling
 * @version 2018/7/6 15:12
 */
@RestController
@RequestMapping("/time")
public class TimeTrackerController {
    @Resource
    private ProductImageService productImageService;

    @Resource
    private ImageController imageController;

    @GetMapping("/getstarttime")
    @ResponseBody
    public Result getStartTime() {
        String id = UUID.randomUUID().toString();
        TimeTrace timeTrace = new TimeTrace();
        timeTrace.setStartTime(System.currentTimeMillis());
        CacheManager.setData(id, timeTrace, 43200);
        return ResultGenerator.genSuccessResult(id);
    }

    @GetMapping("/getendtime")
    @ResponseBody
    public Result getEndTime(@RequestParam("id") String id) {
        TimeTrace timeTrace = CacheManager.getData(id);
        if (timeTrace != null) {
            timeTrace.setEndTime(System.currentTimeMillis());
            long value = timeTrace.getEndTime().longValue() - timeTrace.getStartTime().longValue();
            productImageService.getServerTime(value, "[timetrace]上传图片完成总用时:");
        }
        CacheManager.clear(id);
        return ResultGenerator.genSuccessResult();
    }
}
