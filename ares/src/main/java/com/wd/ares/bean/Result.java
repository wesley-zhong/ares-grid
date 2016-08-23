package com.wd.ares.bean;
public class Result {
private String fIsSucess;

private String ffield;

private String fValue;

private String fDescription;

private String fTranType;

private String fBillNo;

private String fLineNo;

public void setFIsSucess(String fIsSucess){
this.fIsSucess = fIsSucess;
}
public String getFIsSucess(){
return this.fIsSucess;
}
public void setFfield(String ffield){
this.ffield = ffield;
}
public String getFfield(){
return this.ffield;
}
public void setFValue(String fValue){
this.fValue = fValue;
}
public String getFValue(){
return this.fValue;
}
public void setFDescription(String fDescription){
this.fDescription = fDescription;
}
public String getFDescription(){
return this.fDescription;
}
public void setFTranType(String fTranType){
this.fTranType = fTranType;
}
public String getFTranType(){
return this.fTranType;
}
public void setFBillNo(String fBillNo){
this.fBillNo = fBillNo;
}
public String getFBillNo(){
return this.fBillNo;
}
public void setFLineNo(String fLineNo){
this.fLineNo = fLineNo;
}
public String getFLineNo(){
return this.fLineNo;
}

}