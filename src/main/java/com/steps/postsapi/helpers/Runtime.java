package com.steps.postsapi.helpers;

public class Runtime {

    private Long runtime;
    private String unit;

    public Runtime(Long runtime, String unit) {
        this.runtime = runtime;
        this.unit = unit;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
