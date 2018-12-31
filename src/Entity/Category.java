package Entity;

public class Category {
    private int id;
    private String name;
    private int num;
    private float sum;
    private float upperBound;

    public void setId(int id){this.id = id;}
    public int getId(){return this.id;}
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    public void setNum(int num){this.num = num;}
    public int getNum(){return this.num;}
    public void addNum(int cnt){this.num += cnt;}
    public void setSum(float sum){this.sum = sum;}
    public float getSum(){return this.sum;}
    public void addSum(float delta){this.sum += delta;}
    public void setUpperBound(float upperBound){this.upperBound = upperBound;}
    public float getUpperBound(){return this.upperBound;}

    //不写的话在categoryComboBoxModel里无法正常显示名字
    @Override
    public String toString(){
        return name;
    }
}
