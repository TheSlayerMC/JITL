package net.jitl.core.helper;

public enum JDimension {

    OVERWORLD("overworld"),
    END("end"),
    NETHER("nether"),
    FROZEN("frozen"),
    EUCA("euca");

    private String dim;

    JDimension(String name) {
        this.dim = name;
    }

    public String getDim() {
        return dim;
    }
}