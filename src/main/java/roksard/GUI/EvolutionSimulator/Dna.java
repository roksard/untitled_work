package roksard.GUI.EvolutionSimulator;

public class Dna {
    double speed;
    double size;
    double senseRadius;
    boolean isHunter;

    public Dna(double speed, double size, double senseRadius, boolean isHunter) {
        this.speed = speed;
        this.size = size;
        this.senseRadius = senseRadius;
        this.isHunter = isHunter;
    }

    public Dna(Dna other) {
        this.speed = other.speed;
        this.size = other.size;
        this.senseRadius = other.senseRadius;
        this.isHunter = other.isHunter;
    }
}
