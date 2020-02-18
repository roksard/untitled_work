package roksard.datetime.T5064.testpojo;

import java.sql.Timestamp;

public class ExaminationRequest {

    private Timestamp createdDateTimeRequest;
    private Timestamp createdDateTimeResponse;

    public ExaminationRequest() {}


    public Timestamp getCreatedDateTimeRequest() {
        return this.createdDateTimeRequest;
    }

    public void setCreatedDateTimeRequest(Timestamp createdDateTimeRequest) {
        this.createdDateTimeRequest = createdDateTimeRequest;
    }

    public Timestamp getCreatedDateTimeResponse() {
        return this.createdDateTimeResponse;
    }

    public void setCreatedDateTimeResponse(Timestamp createdDateTimeResponse) {
        this.createdDateTimeResponse = createdDateTimeResponse;
    }
}

