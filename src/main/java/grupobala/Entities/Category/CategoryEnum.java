package grupobala.Entities.Category;

public enum CategoryEnum {
    FOOD("Comida", "comida"),
    CLOTHING("Roupa", "roupa"),
    HEALTH("Saúde", "saúde"),
    ENTERTAINMENT("Entretenimento", "entretenimento"),
    PAYMENTS("Pagamentos", "pagamentos"),
    OTHERS("Outros", "outros");

    public final String displayedName;
    public final String databaseName;

    private CategoryEnum(String displayedName, String databaseName) {
        this.displayedName = displayedName;
        this.databaseName = databaseName;
    }
}
