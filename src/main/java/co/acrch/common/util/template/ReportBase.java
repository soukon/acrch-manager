package co.acrch.common.util.template;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;

public interface ReportBase {

//    void read(ApplicationContext resourceLoader, String fileNameWithSuffix) throws Exception;

    void process(HashMap<String, Object> data) throws Exception;
}
