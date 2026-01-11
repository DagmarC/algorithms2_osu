public enum Category {
    MEAL("Meal"),
    ELECTRONICS("Electronics"),
    DRUGS("Drugs"),
    OTHER("Other");

    private final String label;

    Category (String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
