package roksard.datetime.T5064;

import roksard.datetime.T5064.testpojo.ExaminationApplication;
import roksard.datetime.T5064.testpojo.ExaminationRequest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class T5064 {
    public static void test1(int name) throws ParseException {
        System.out.println("test:" + name);
        HolidayService holidayService = new HolidayService();
        Date now = new Date();
        ExaminationApplication examinationApplication = new ExaminationApplication();
        examinationApplication.setExaminationStartDate(new java.sql.Date(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("15:00:00 29.01.2020").getTime()));
        examinationApplication.setSubmissionDate(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("15:00:00 28.01.2020").getTime()));
        Calendar examinationStart = Calendar.getInstance();
        examinationStart.setTime(examinationApplication.getExaminationStartDate());

        List<ExaminationRequest> infoRequests = new ArrayList<ExaminationRequest>() {{
            ExaminationRequest request = new ExaminationRequest();
            request.setCreatedDateTimeRequest(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("18:00:00 29.01.2020").getTime()));
            request.setCreatedDateTimeResponse(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("16:00:00 30.01.2020").getTime()));
            add(request);
        }};
        Set<LocalDate> suspendedDays = new HashSet<>();
        infoRequests.forEach(request -> {
            Calendar start = Calendar.getInstance();
            start.setTime(request.getCreatedDateTimeRequest());
            Calendar end = Calendar.getInstance();
            end.setTime(request.getCreatedDateTimeResponse() != null ? request.getCreatedDateTimeResponse() : now);
            Calendar selector = Calendar.getInstance();
            selector.setTime(start.getTime());
            while (selector.compareTo(end) <= 0) {
                DateFormat f = SimpleDateFormat.getDateTimeInstance();
                String sform = "%20.20s | %20.20s | %20.20s | %20.20s | %20.20s \n";
                System.out.format(sform, "examinationStart", "TimeRequest", "timeResponse", "selector", "isSel Holiday?");
                System.out.format(sform, f.format(examinationStart.getTime()), f.format(start.getTime()), f.format(end.getTime()),
                        f.format(selector.getTime()), String.valueOf(holidayService.isHoliday(selector.getTime()))
                );
                if (!holidayService.isHoliday(selector.getTime()) && (selector.compareTo(examinationStart) >= 0)) {
                    suspendedDays.add(LocalDate.of(
                            selector.get(Calendar.YEAR),
                            selector.get(Calendar.MONTH)+1,
                            selector.get(Calendar.DAY_OF_MONTH)));
                }
                selector.add(Calendar.DAY_OF_MONTH, 1);
            }
        });

        System.out.println("suspendedDays: " + suspendedDays.size() + " : " + suspendedDays );

        examinationApplication.setExaminationEndPlanDate(new java.sql.Date(
                holidayService.addWorkDayToDate(
                        examinationApplication.getSubmissionDate(),
                        10 + suspendedDays.size()
                ).getTime()));
    }

    public static void test2(int name) throws ParseException {
        System.out.println("test:" + name);
        HolidayService holidayService = new HolidayService();
        Date now = new Date();
        ExaminationApplication examinationApplication = new ExaminationApplication();
        examinationApplication.setExaminationStartDate(new java.sql.Date(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("15:00:00 29.01.2020").getTime()));
        examinationApplication.setSubmissionDate(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("15:00:00 28.01.2020").getTime()));
        LocalDate examinationStart = Instant.ofEpochMilli(examinationApplication.getExaminationStartDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        List<ExaminationRequest> infoRequests = new ArrayList<ExaminationRequest>() {{
            ExaminationRequest request = new ExaminationRequest();
            request.setCreatedDateTimeRequest(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("18:00:00 29.01.2020").getTime()));
            request.setCreatedDateTimeResponse(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("16:00:00 30.01.2020").getTime()));
            add(request);
        }};
        Set<LocalDate> suspendedDays = new HashSet<>();
        infoRequests.forEach(request -> {
            LocalDate start = roksard.date_periods.DateUtils.asLocalDate(request.getCreatedDateTimeRequest());
            LocalDate end = roksard.date_periods.DateUtils.asLocalDate(
                    request.getCreatedDateTimeResponse() != null ? request.getCreatedDateTimeResponse() : now);
            LocalDate selector = roksard.date_periods.DateUtils.asLocalDate(request.getCreatedDateTimeRequest());
            while (selector.compareTo(end) <= 0) {
                Date selectorDate = roksard.date_periods.DateUtils.asDate(selector);
                String sform = "%20.20s | %20.20s | %20.20s | %20.20s | %20.20s \n";
                System.out.format(sform, "examinationStart", "TimeRequest", "timeResponse", "selector", "isSel Holiday?");
                System.out.format(sform, examinationStart, start, end,
                        selector, String.valueOf(holidayService.isHoliday(selectorDate))
                );
                if (!holidayService.isHoliday(selectorDate) && (selector.compareTo(examinationStart) >= 0)) {
                    suspendedDays.add(selector);
                }
                selector = selector.plusDays(1);
            }
        });

        System.out.println("suspendedDays: " + suspendedDays.size() + " : " + suspendedDays );

        examinationApplication.setExaminationEndPlanDate(new java.sql.Date(
                holidayService.addWorkDayToDate(
                        examinationApplication.getSubmissionDate(),
                        10 + suspendedDays.size()
                ).getTime()));
    }

    public static void test3(int name) throws ParseException {
        System.out.println("test:" + name);
        HolidayService holidayService = new HolidayService();
        Date now = new Date();
        ExaminationApplication examinationApplication = new ExaminationApplication();
        examinationApplication.setExaminationStartDate(new java.sql.Date(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("15:00:00 29.01.2020").getTime()));
        examinationApplication.setSubmissionDate(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("15:00:00 28.01.2020").getTime()));
        Calendar examinationStart = Calendar.getInstance();
        examinationStart.setTime(examinationApplication.getExaminationStartDate());

        List<ExaminationRequest> infoRequests = new ArrayList<ExaminationRequest>() {{
            ExaminationRequest request = new ExaminationRequest();
            request.setCreatedDateTimeRequest(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("18:00:00 29.01.2020").getTime()));
            request.setCreatedDateTimeResponse(new Timestamp(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse("16:00:00 30.01.2020").getTime()));
            add(request);
        }};
        Set<LocalDate> suspendedDays = new HashSet<>();
        infoRequests.forEach(request -> {
            Calendar start = Calendar.getInstance();
            start.setTime(request.getCreatedDateTimeRequest());
            start.clear(Calendar.HOUR);
            start.clear(Calendar.HOUR_OF_DAY);
            start.clear(Calendar.MINUTE);
            start.clear(Calendar.SECOND);
            start.clear(Calendar.MILLISECOND);
            Calendar end = Calendar.getInstance();
            end.setTime(request.getCreatedDateTimeResponse() != null ? request.getCreatedDateTimeResponse() : now);
            end.clear(Calendar.HOUR);
            end.clear(Calendar.HOUR_OF_DAY);
            end.clear(Calendar.MINUTE);
            end.clear(Calendar.SECOND);
            end.clear(Calendar.MILLISECOND);
            Calendar selector = Calendar.getInstance();
            selector.setTime(start.getTime());
            selector.clear(Calendar.HOUR);
            selector.clear(Calendar.HOUR_OF_DAY);
            selector.clear(Calendar.MINUTE);
            selector.clear(Calendar.SECOND);
            selector.clear(Calendar.MILLISECOND);
            while (selector.compareTo(end) <= 0) {
                DateFormat f = SimpleDateFormat.getDateTimeInstance();
                String sform = "%20.20s | %20.20s | %20.20s | %20.20s | %20.20s \n";
                System.out.format(sform, "examinationStart", "TimeRequest", "timeResponse", "selector", "isSel Holiday?");
                System.out.format(sform, f.format(examinationStart.getTime()), f.format(start.getTime()), f.format(end.getTime()),
                        f.format(selector.getTime()), String.valueOf(holidayService.isHoliday(selector.getTime()))
                );
                if (!holidayService.isHoliday(selector.getTime()) && (selector.compareTo(examinationStart) >= 0)) {
                    suspendedDays.add(LocalDate.of(
                            selector.get(Calendar.YEAR),
                            selector.get(Calendar.MONTH)+1,
                            selector.get(Calendar.DAY_OF_MONTH)));
                }
                selector.add(Calendar.DAY_OF_MONTH, 1);
            }
        });

        System.out.println("suspendedDays: " + suspendedDays.size() + " : " + suspendedDays );

        examinationApplication.setExaminationEndPlanDate(new java.sql.Date(
                holidayService.addWorkDayToDate(
                        examinationApplication.getSubmissionDate(),
                        10 + suspendedDays.size()
                ).getTime()));
    }

    public static void main(String[] args) throws ParseException {
        test1(1);
        test2(2);
        test3(3);
    }
}
