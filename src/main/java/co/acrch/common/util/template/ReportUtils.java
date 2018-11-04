package co.acrch.common.util.template;

import net.sf.jett.transform.ExcelTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ReportUtils {

    /**
     * パラメータと、テンプレートファイル名を指定し、帳票変換を行う
     * @param params パラメータ
     * @param templateName テンプレートファイル名
     * @return 変換したWorkbook
     */
    public static Workbook toReport(Map<String, Object> params, String templateName) {
        Workbook workbook = null;
        InputStream is = null;
        try {
            is = ReportUtils.class.getResourceAsStream("/data/" + templateName);
            ExcelTransformer transformer = new ExcelTransformer();
            workbook = transformer.transform(is, params);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }
}
