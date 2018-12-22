package entity;

import java.util.Date;

public class Record {
    private int id;
    private int cid;
    private float cost;
    private String comment;
    private Date date;

    public void setId(int id){this.id = id;}
    public int getId(){return this.id;}
    public void setCid(int cid){this.cid = cid ;}
    public int getCid(){return this.cid;}
    public void setCost(float cost){this.cost = cost;}
    public float getCost(){return this.cost;}
    public void setComment(String comment){this.comment = comment;}
    public String getComment(){return this.comment;}
    public void setDate(Date date){this.date = date;}
    public Date getDate(){return this.date;}
}
