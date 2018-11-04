package co.acrch.common.util.template;

import co.acrch.common.util.DateUtil;
import co.acrch.system.dto.AttendanceDetailDto;
import co.acrch.system.dto.AttendanceDto;
import co.acrch.system.dto.Invoice;
import co.acrch.system.dto.InvoiceDetail;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReportUtilsTest {

    private static BigDecimal TAX_RATE = new BigDecimal(0.08);

    @Test
    public void toReportDemoTest() {
        Invoice inv = new Invoice();
        inv.setInvoiceNo("INV-00000001");
        inv.setClientPostCode("〒123-3333");
        inv.setClientAddress("東京都品川区東五反田１丁目６−３ 東京建物五反田ビル 108F");
        inv.setClientName("株式会社 松上電気");
        inv.setSalesRep("営業 太郎");
        inv.setInvoiceDate(new Date());

        // 明細行は5行にしておく
        for (int idx = 1; idx <= 5; idx++) {
            InvoiceDetail dtl = new InvoiceDetail();
            dtl.setItemName("サンプル明細ですよ " + idx);
            dtl.setUnitCost(BigDecimal.valueOf(10000));
            dtl.setQuantity(Double.valueOf(idx));
            dtl.setAmt(dtl.getUnitCost().multiply(//
                    BigDecimal.valueOf(dtl.getQuantity())));
            inv.getDetails().add(dtl);
        }
        BigDecimal total = BigDecimal.ZERO;
        for (InvoiceDetail dtl : inv.getDetails()) {
            total = total.add(dtl.getAmt());
        }
        // 立替金
        inv.setAdvancePaid(BigDecimal.valueOf(10800));
        // 税額
        inv.setTaxAmt(total.multiply(TAX_RATE));
        // 請求額（税込）
        inv.setInvoiceAmtTaxin(total.add(inv.getTaxAmt()).add(inv.getAdvancePaid()));
        // 備考
        inv.setNote("これは備考です、サンプルとして備考を記述し、"
                + "そして帳票に出力をしてみました。"
                + "折り返してくれるといいのですが、どうでしょうか");

        // 帳票変換
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("inv", inv);

        Workbook workbook = ReportUtils.toReport(map, "template_invoice.xlsx");

        // ファイル出力
        final String outPath = "output_invoice.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(outPath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toReportTest() throws ParseException {
        AttendanceDto ad = new AttendanceDto();
        ad.setMonthOfWork("2018/11/01");
        ad.setCustomer("日立製作所");
        ad.setUserName("テスター");
        ad.setUserId("Test0001");
        ad.setPlaceOfWork("東京都新宿区XXXXXXXビル３ 27F 2701室");
        ad.setLeaderName("テストリーダー");
        ad.setWorkDays(31.0);
        ad.setHasPaidHoliday(0.0);
        ad.setAdjustmentHoliday(0.0);
        ad.setAbsenceDays(0.0);
        ad.setTotalOfTraffic("¥ 8500 円");
        ad.setTotalOfOthers("¥ 1200 円");

        // 明細行は31行にしておく
        for (int idx = 1; idx <= 31; idx++) {
            AttendanceDetailDto add = new AttendanceDetailDto();
            String str = "";
            if (idx % 7 == 1) {
                str = "月";
            } else if (idx % 7 == 2) {
                str = "火";
            } else if (idx % 7 == 3) {
                str = "水";
            } else if (idx % 7 == 4) {
                str = "木";
            } else if (idx % 7 == 5) {
                str = "金";
            } else if (idx % 7 == 6) {
                str = "土";
            } else {
                str = "日";
            }
            add.setKanjiOfDay(str);
            add.setStartTime("9:00");
            add.setEndTime("18:30");
            add.setRestTime("1:15");
            add.setStandardTime("7:45");
            add.setOverTime("0:00");
            add.setSubTotalTime("8:00");
            add.setWorkKbn("要件定義");
            add.setSpecialMemo("特になし");
            ad.getDetails().add(add);
        }

        // 帳票変換
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", ad);

        Workbook workbook = ReportUtils.toReport(map, "template_attendance.xlsx");

        // ファイル出力
        final String outPath = "output_attendance.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(outPath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}