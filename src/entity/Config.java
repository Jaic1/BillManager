package entity;

public class Config {
    private int id;
    private String key;
    private String value;

    public void setId(int id){this.id = id;}
    public int getId(){return this.id;}
    public void setKey(String key){this.key = key;}
    public String getKey(){return this.key;}
    public void setValue(String value){this.value = value;}
    public String getValue(){return this.value;}
}
