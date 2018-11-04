package co.acrch.system.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 勤怠表用DTO
 *
 * @author ksou
 */
public class AttendanceDto {

    /* （頭）月度 */
    private String monthOfWork;
    /* お客様名 */
    private String customer;
    /* 作業者名 */
    private String userName;
    /* 作業者ID */
    private String userId;
    /* 作業場所（勤務地） */
    private String placeOfWork;
    /* 責任者名 */
    private String leaderName;
    /* 出勤日数 */
    private double workDays;
    /* 有給日数 */
    private double hasPaidHoliday;
    /* 調整休日日数 */
    private double adjustmentHoliday;
    /* 欠勤日数 */
    private double absenceDays;
    /* 交通費合計 */
    private String totalOfTraffic;
    /* 経費合計 */
    private String totalOfOthers;
    /* 備考 */
    private String memo;

    private List<AttendanceDetailDto> details = new ArrayList<>();

    /**
     * （頭）月度を取得する
     *
     * @return monthOfWork （頭）月度
     */
    public String getMonthOfWork() {
        return monthOfWork;
    }

    /**
     * （頭）月度を設定する
     *
     * @param monthOfWork （頭）月度
     */
    public void setMonthOfWork(String monthOfWork) {
        this.monthOfWork = monthOfWork;
    }

    /**
     * お客様名を取得する
     *
     * @return customer お客様名
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * お客様名を設定する
     *
     * @param customer お客様名
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * 作業者名を取得する
     *
     * @return userName 作業者名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 作業者名を設定する
     *
     * @param userName 作業者名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 作業者IDを取得する
     *
     * @return userId 作業者ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 作業者IDを設定する
     *
     * @param userId 作業者ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 作業場所（勤務地）を取得する
     *
     * @return placeOfWork 作業場所（勤務地）
     */
    public String getPlaceOfWork() {
        return placeOfWork;
    }

    /**
     * 作業場所（勤務地）を設定する
     *
     * @param placeOfWork 作業場所（勤務地）
     */
    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    /**
     * 責任者名を取得する
     *
     * @return leaderName 責任者名
     */
    public String getLeaderName() {
        return leaderName;
    }

    /**
     * 責任者名を設定する
     *
     * @param leaderName 責任者名
     */
    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    /**
     * 出勤日数を取得する
     *
     * @return workDays 出勤日数
     */
    public double getWorkDays() {
        return workDays;
    }

    /**
     * 出勤日数を設定する
     *
     * @param workDays 出勤日数
     */
    public void setWorkDays(double workDays) {
        this.workDays = workDays;
    }

    /**
     * 有給日数を取得する
     *
     * @return hasPaidHoliday 有給日数
     */
    public double getHasPaidHoliday() {
        return hasPaidHoliday;
    }

    /**
     * 有給日数を設定する
     *
     * @param hasPaidHoliday 有給日数
     */
    public void setHasPaidHoliday(double hasPaidHoliday) {
        this.hasPaidHoliday = hasPaidHoliday;
    }

    /**
     * 調整休日日数を取得する
     *
     * @return adjustmentHoliday 調整休日日数
     */
    public double getAdjustmentHoliday() {
        return adjustmentHoliday;
    }

    /**
     * 調整休日日数を設定する
     *
     * @param adjustmentHoliday 調整休日日数
     */
    public void setAdjustmentHoliday(double adjustmentHoliday) {
        this.adjustmentHoliday = adjustmentHoliday;
    }

    /**
     * 欠勤日数を取得する
     *
     * @return absenceDays 欠勤日数
     */
    public double getAbsenceDays() {
        return absenceDays;
    }

    /**
     * 欠勤日数を設定する
     *
     * @param absenceDays 欠勤日数
     */
    public void setAbsenceDays(double absenceDays) {
        this.absenceDays = absenceDays;
    }

    /**
     * 交通費合計を取得する
     *
     * @return totalOfTraffic 交通費合計
     */
    public String getTotalOfTraffic() {
        return totalOfTraffic;
    }

    /**
     * 交通費合計を設定する
     *
     * @param totalOfTraffic 交通費合計
     */
    public void setTotalOfTraffic(String totalOfTraffic) {
        this.totalOfTraffic = totalOfTraffic;
    }

    /**
     * 経費合計を取得する
     *
     * @return totalOfOthers 経費合計
     */
    public String getTotalOfOthers() {
        return totalOfOthers;
    }

    /**
     * 経費合計を設定する
     *
     * @param totalOfOthers 経費合計
     */
    public void setTotalOfOthers(String totalOfOthers) {
        this.totalOfOthers = totalOfOthers;
    }

    /**
     * 備考を取得する
     *
     * @return memo 備考
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 備考を設定する
     *
     * @param memo 備考
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<AttendanceDetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<AttendanceDetailDto> details) {
        this.details = details;
    }
}
