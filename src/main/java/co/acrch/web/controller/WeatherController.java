package co.acrch.web.controller;

import co.acrch.common.annotation.Log;
import co.acrch.common.controller.BaseController;
import co.acrch.common.domain.ResponseBo;
import co.acrch.common.util.AcrchConstant;
import co.acrch.common.util.HttpUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.acrch.common.annotation.Log;
import co.acrch.common.controller.BaseController;
import co.acrch.common.domain.ResponseBo;
import co.acrch.common.util.HttpUtils;
import co.acrch.common.util.AcrchConstant;

@Controller
public class WeatherController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Log("获取天气信息")
    @RequestMapping("weather")
    @RequiresPermissions("weather:list")
    public String weather() {
        return "web/weather/weather";
    }

    @RequestMapping("weather/query")
    @ResponseBody
    public ResponseBo queryWeather(String areaId) {
        try {
            String data = HttpUtils.sendPost(AcrchConstant.MEIZU_WEATHER_URL, "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("查询天气失败", e);
            return ResponseBo.error("查询天气失败，请联系网站管理员！");
        }
    }
}
