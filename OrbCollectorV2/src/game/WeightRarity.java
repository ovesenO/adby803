package game;


 //Generic class weight rarity class, used instead of a tuple for extensibility
public class WeightRarity<EntityName, Weight, WeightSum>
{
    private EntityName name;
    private WeightSum weightSum;
    private Weight weight;

    public WeightRarity(EntityName name, Weight weight, WeightSum sum)
    {
        this.name = name;
        this.weight = weight;
        this.weightSum = sum;
    }
    // ----- Getters -----
    public EntityName GetEntityName() { return name; }
    public WeightSum GetWeightSum() { return weightSum; }
    public Weight GetWeight() { return weight; }
}