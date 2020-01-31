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
    /**
     *
     * @param name name to be printed before test starts
     * @param examinationStart_ in format HH:mm:ss dd.MM.yyyy
     * @param submissionDate_ in format HH:mm:ss dd.MM.yyyy
     * @param requestDate_ in format HH:mm:ss dd.MM.yyyy
     * @param responseDate_ in format HH:mm:ss dd.MM.yyyy
     * @throws ParseException
     */
    public static void test1(String name, String examinationStart_, String submissionDate_, String requestDate_, String responseDate_) throws ParseException {
        System.out.println("\ntest1 with Calendar uncleared (no .clear()): " + name);
        final String DATE_FORMAT = "HH:mm:ss dd.MM.yyyy";
        HolidayService holidayService = new HolidayService();
        Date now = new Date();
        ExaminationApplication examinationApplication = new ExaminationApplication();
        examinationApplication.setExaminationStartDate(new java.sql.Date(new SimpleDateFormat(DATE_FORMAT).parse(examinationStart_).getTime()));
        examinationApplication.setSubmissionDate(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(submissionDate_).getTime()));
        Calendar examinationStart = Calendar.getInstance();
        examinationStart.setTime(examinationApplication.getExaminationStartDate());

        List<ExaminationRequest> infoRequests = new ArrayList<ExaminationRequest>() {{
            Random rand = new Random();
            ExaminationRequest request1 = new ExaminationRequest();
            request1.setCreatedDateTimeRequest(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(requestDate_).getTime()));
            request1.setCreatedDateTimeResponse(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request1.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            add(request1);
            ExaminationRequest request2 = new ExaminationRequest();
            request2.setCreatedDateTimeRequest(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request1.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            request2.setCreatedDateTimeResponse(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request2.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            add(request2);
            ExaminationRequest request3 = new ExaminationRequest();
            request3.setCreatedDateTimeRequest(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request2.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            request3.setCreatedDateTimeResponse(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request3.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            add(request3);
        }};
        Set<LocalDate> suspendedDays = new HashSet<>();
        infoRequests.forEach(request -> {
            Calendar start = Calendar.getInstance();
            start.setTime(request.getCreatedDateTimeRequest());
            Calendar end = Calendar.getInstance();
            end.setTime(request.getCreatedDateTimeResponse() != null ? request.getCreatedDateTimeResponse() : now);
            Calendar selector = Calendar.getInstance();
            selector.setTime(start.getTime());
            DateFormat f = SimpleDateFormat.getDateTimeInstance();
            String sform = "%20.20s | %20.20s | %20.20s | %20.20s | %20.20s | %20.20s \n";
            System.out.format(sform, "examinationStart", "TimeRequest", "timeResponse", "selector", "isSel Holiday?", "isAfterExam?");
            while (selector.compareTo(end) <= 0) {
                System.out.format(sform, f.format(examinationStart.getTime()), f.format(start.getTime()), f.format(end.getTime()),
                        f.format(selector.getTime()), String.valueOf(holidayService.isHoliday(selector.getTime())),
                        String.valueOf(selector.compareTo(examinationStart) >= 0));
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

    /**
     *
     * @param name name to be printed before test starts
     * @param examinationStart_ in format HH:mm:ss dd.MM.yyyy
     * @param submissionDate_ in format HH:mm:ss dd.MM.yyyy
     * @param requestDate_ in format HH:mm:ss dd.MM.yyyy
     * @throws ParseException
     */
    public static void test2(String name, String examinationStart_, String submissionDate_, String requestDate_) throws ParseException {
        System.out.println("\ntest2 LocalDate: " + name);
        final String DATE_FORMAT = "HH:mm:ss dd.MM.yyyy";
        HolidayService holidayService = new HolidayService();
        Date now = new Date();
        ExaminationApplication examinationApplication = new ExaminationApplication();
        examinationApplication.setExaminationStartDate(new java.sql.Date(new SimpleDateFormat(DATE_FORMAT).parse(examinationStart_).getTime()));
        examinationApplication.setSubmissionDate(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(submissionDate_).getTime()));
        LocalDate examinationStart = Instant.ofEpochMilli(examinationApplication.getExaminationStartDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        List<ExaminationRequest> infoRequests = new ArrayList<ExaminationRequest>() {{
            Random rand = new Random();
            ExaminationRequest request1 = new ExaminationRequest();
            request1.setCreatedDateTimeRequest(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(requestDate_).getTime()));
            request1.setCreatedDateTimeResponse(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request1.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            add(request1);
            ExaminationRequest request2 = new ExaminationRequest();
            request2.setCreatedDateTimeRequest(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request1.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            request2.setCreatedDateTimeResponse(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request2.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            add(request2);
            ExaminationRequest request3 = new ExaminationRequest();
            request3.setCreatedDateTimeRequest(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request2.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            request3.setCreatedDateTimeResponse(new Timestamp(roksard.datetime.T5064.HolidayService.addWorkDayToDate(request3.getCreatedDateTimeRequest(), rand.nextInt(5)).getTime()));
            add(request3);
        }};
        Set<LocalDate> suspendedDays = new HashSet<>();
        infoRequests.forEach(request -> {
            LocalDate start = roksard.date_periods.DateUtils.asLocalDate(request.getCreatedDateTimeRequest());
            LocalDate end = roksard.date_periods.DateUtils.asLocalDate(
                    request.getCreatedDateTimeResponse() != null ? request.getCreatedDateTimeResponse() : now);
            LocalDate selector = roksard.date_periods.DateUtils.asLocalDate(request.getCreatedDateTimeRequest());
            Date selectorDate = roksard.date_periods.DateUtils.asDate(selector);
            String sform = "%20.20s | %20.20s | %20.20s | %20.20s | %20.20s | %20.20s \n";
            System.out.format(sform, "examinationStart", "TimeRequest", "timeResponse", "selector", "isSel Holiday?", "selectorIsAfterExam?");
            System.out.format(sform, examinationStart, start, end,
                    "-", "-", "-");
            while (selector.compareTo(end) <= 0 && end.compareTo(examinationStart) >= 0) {

                System.out.format(sform, examinationStart, start, end,
                        selector, String.valueOf(holidayService.isHoliday(selectorDate)),
                        String.valueOf(selector.compareTo(examinationStart) >= 0));
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

    /**
     *
     * @param name name to be printed before test starts
     * @param examinationStart_ in format HH:mm:ss dd.MM.yyyy
     * @param submissionDate_ in format HH:mm:ss dd.MM.yyyy
     * @param requestDate_ in format HH:mm:ss dd.MM.yyyy
     * @param responseDate_ in format HH:mm:ss dd.MM.yyyy
     * @throws ParseException
     */
    public static void test3(String name, String examinationStart_, String submissionDate_, String requestDate_, String responseDate_) throws ParseException {
        System.out.println("\ntest3 with Calendar cleared (.clear()): " + name);
        final String DATE_FORMAT = "HH:mm:ss dd.MM.yyyy";
        HolidayService holidayService = new HolidayService();
        Date now = new Date();
        ExaminationApplication examinationApplication = new ExaminationApplication();
        examinationApplication.setExaminationStartDate(new java.sql.Date(new SimpleDateFormat(DATE_FORMAT).parse(examinationStart_).getTime()));
        examinationApplication.setSubmissionDate(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(submissionDate_).getTime()));
        Calendar examinationStart = Calendar.getInstance();
        examinationStart.setTime(examinationApplication.getExaminationStartDate());

        List<ExaminationRequest> infoRequests = new ArrayList<ExaminationRequest>() {{
            ExaminationRequest request = new ExaminationRequest();
            request.setCreatedDateTimeRequest(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(requestDate_).getTime()));
            request.setCreatedDateTimeResponse(new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(responseDate_).getTime()));
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
            DateFormat f = SimpleDateFormat.getDateTimeInstance();
            String sform = "%20.20s | %20.20s | %20.20s | %20.20s | %20.20s | %20.20s \n";
            System.out.format(sform, "examinationStart", "TimeRequest", "timeResponse", "selector", "isSel Holiday?", "isAfterExam?");
            while (selector.compareTo(end) <= 0) {
                System.out.format(sform, f.format(examinationStart.getTime()), f.format(start.getTime()), f.format(end.getTime()),
                        f.format(selector.getTime()), String.valueOf(holidayService.isHoliday(selector.getTime())),
                        String.valueOf(selector.compareTo(examinationStart) >= 0));
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

    /**
     *
     * @return random date in string format: "HH:mm:ss dd.MM.yyyy"
     */
    public static String randomDate() {
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        return builder.append(String.format("%02d:", rand.nextInt(24)))
                .append(String.format("%02d:", rand.nextInt(60)))
                .append(String.format("%02d ", rand.nextInt(60)))
                .append(String.format("%02d.", rand.nextInt(28)+1))
                .append(String.format("%02d.", rand.nextInt(12)+1))
                .append("2020")
                .toString();
    }

    public static void main(String[] args) throws ParseException {
        //name, examinationStart_, submissionDate_, requestDate_, responseDate_
//        test1("",
//                "15:00:00 29.01.2020", "15:00:00 28.01.2020", "18:00:00 29.01.2020", "16:00:00 30.01.2020");
//        test2("",
//                "15:00:00 29.01.2020", "15:00:00 28.01.2020", "18:00:00 29.01.2020");
//        test3("",
//                "15:00:00 29.01.2020", "15:00:00 28.01.2020", "18:00:00 29.01.2020", "16:00:00 30.01.2020");

        System.out.println("\nRandom tests: ");
        for (int i = 0; i < 50; i++) {
            test2("", randomDate(), randomDate(), randomDate());
        }
      //  test2("", "15:00:00 26.08.2020", "15:00:00 28.01.2020", "18:00:00 19.03.2020", "16:00:00 04.04.2020");

    }
}
