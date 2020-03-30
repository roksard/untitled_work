package roksard.GUI.EvolutionSimulator;

public class Dna {
    double speed;
    double size;
    double senseRadius;

    public Dna(double speed, double size, double senseRadius) {
        this.speed = speed;
        this.size = size;
        this.senseRadius = senseRadius;
    }

    public Dna(Dna other) {
        this.speed = other.speed;
        this.size = other.size;
        this.senseRadius = other.senseRadius;
    }
}
