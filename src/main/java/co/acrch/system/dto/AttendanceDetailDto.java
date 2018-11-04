package co.acrch.system.dto;

/**
 * 勤怠表明細用Dto
 *
 * @author ksou
 */
public class AttendanceDetailDto {

    /* 日付：曜日 OR 祝日 */
    private String kanjiOfDay;
    /* 開始時刻 */
    private String startTime;
    /* 終了時刻 */
    private String endTime;
    /* 休憩時間 */
    private String restTime;
    /* 標準時間 */
    private String standardTime;
    /* 超過時間 */
    private String overTime;
    /* （日）合計時間 */
    private String subTotalTime;
    /* 作業区分 */
    private String workKbn;
    /* 特記事項 */
    private String specialMemo;

    public String getKanjiOfDay() {
        return kanjiOfDay;
    }

    public void setKanjiOfDay(String kanjiOfDay) {
        this.kanjiOfDay = kanjiOfDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(String standardTime) {
        this.standardTime = standardTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getSubTotalTime() {
        return subTotalTime;
    }

    public void setSubTotalTime(String subTotalTime) {
        this.subTotalTime = subTotalTime;
    }

    public String getWorkKbn() {
        return workKbn;
    }

    public void setWorkKbn(String workKbn) {
        this.workKbn = workKbn;
    }

    public String getSpecialMemo() {
        return specialMemo;
    }

    public void setSpecialMemo(String specialMemo) {
        this.specialMemo = specialMemo;
    }
}
