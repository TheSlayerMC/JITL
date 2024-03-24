package net.jitl.core.init.internal;

public enum JDimension {

    OVERWORLD("overworld"),
    END("end"),
    NETHER("nether"),
    FROZEN("frozen"),
    BOIL("boil"),
    EUCA("euca"),
    DEPTHS("depths"),
    CORBA("corba"),
    TERRAINIA("terrania"),
    CLOUDIA("cloudia"),
    SENTERIAN("senterian"),
    BOSS("boss")
    ;

    private final String dim;

    JDimension(String name) {
        this.dim = name;
    }

    public String getDim() {
        return dim;
    }
}