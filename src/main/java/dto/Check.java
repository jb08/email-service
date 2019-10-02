package dto;

public interface Check {

    long getIdentityId();

    State getState();

    Status getStatus();

    long getStartTime();

    long getEndTime();

    public enum State {
        IL, CO, CA;
    }

    public enum Status {
        CLEAR, WARNING, SEROUS_PROBLEM
    }
}
