package dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

@Builder
@ApiModel("Driver Check Dto")
public class DriverCheck implements Check {
    private long id;
    private long identityId;
    private State state;
    private Status status;
    long startTime;
    long endTime;

    @Override
    public long getIdentityId() {
        return this.identityId;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public long getStartTime() {
        return this.startTime;
    }

    @Override
    public long getEndTime() {
        return this.endTime;
    }

}
