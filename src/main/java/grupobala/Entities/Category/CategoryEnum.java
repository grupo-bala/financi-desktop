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

    public static CategoryEnum getCategory(String categoryName) {
        switch (categoryName.toLowerCase()) {
            case "comida":
                return CategoryEnum.FOOD;
            case "roupa":
                return CategoryEnum.CLOTHING;
            case "saúde":
                return CategoryEnum.HEALTH;
            case "entretenimento":
                return CategoryEnum.ENTERTAINMENT;
            case "pagamentos":
                return CategoryEnum.PAYMENTS;
            default:
                return CategoryEnum.OTHERS;
        }
    }
}
