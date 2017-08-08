package ua.gwm.elite_dangerous.eddb_java_api.api;

public enum EDCommodityCategory {

    SLAVERY,
    CONSUMER_ITEMS,
    LEGAL_DRUGS,
    METALS,
    MINERALS,
    MACHINERY,
    WASTE,
    CHEMICALS,
    TEXTILES,
    SALVAGE,
    WEAPONS,
    MEDICINES,
    TECHNOLOGY,
    INDUSTRIAL_MATERIALS,
    FOODS,
    UNKNOWN;

    public static EDCommodityCategory byName(String name) {
        if (name == null || name.equals("")) {
            return UNKNOWN;
        }
        switch (name) {
            case "Slavery": return SLAVERY;
            case "Consumer Items": return CONSUMER_ITEMS;
            case "Legal Drugs": return LEGAL_DRUGS;
            case "Metals": return METALS;
            case "Minerals": return MINERALS;
            case "Machinery": return MACHINERY;
            case "Waste": return WASTE;
            case "Chemicals": return CHEMICALS;
            case "Textiles": return TEXTILES;
            case "Salvage": return SALVAGE;
            case "Weapons": return WEAPONS;
            case "Medicines": return MEDICINES;
            case "Technology": return TECHNOLOGY;
            case "Industrial Materials": return INDUSTRIAL_MATERIALS;
            case "Foods": return FOODS;
            default: return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case SLAVERY: return "Slavery";
            case CONSUMER_ITEMS: return "Consumer Items";
            case LEGAL_DRUGS: return "Legal Drugs";
            case METALS: return "Metals";
            case MINERALS: return "Minerals";
            case MACHINERY: return "Machinery";
            case WASTE: return "Waste";
            case CHEMICALS: return "Chemicals";
            case TEXTILES: return "Textiles";
            case SALVAGE: return "Salvage";
            case WEAPONS: return "Weapons";
            case MEDICINES: return "Medicines";
            case TECHNOLOGY: return "Technology";
            case INDUSTRIAL_MATERIALS: return "Industrial Materials";
            case FOODS: return "Foods";
            default: return "UNKNOWN";
        }
    }
}
