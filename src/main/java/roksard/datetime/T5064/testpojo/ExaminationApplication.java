package roksard.datetime.T5064.testpojo;

import java.sql.Date;
import java.sql.Timestamp;

public class ExaminationApplication {


    private Timestamp submissionDate;
    private Date examinationStartDate;
    private Date      examinationEndPlanDate;


    public ExaminationApplication() {}



    public Timestamp getSubmissionDate() {
        return this.submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Date getExaminationStartDate() {
        return this.examinationStartDate;
    }

    public void setExaminationStartDate(Date examinationStartDate) {
        this.examinationStartDate = examinationStartDate;
    }


    public void setExaminationEndPlanDate(Date examinationEndPlanDate) {
        this.examinationEndPlanDate = examinationEndPlanDate;
    }
}
