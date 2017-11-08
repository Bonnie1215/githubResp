package com.buleocean_health.springboot.common.em;

public enum TimeType {

	yyyy_MM_dd_HH_00_00("yyyy-MM-dd HH:00:00", 1),
	yyyy_MM_dd_HH_mm_00("yyyy-MM-dd HH:mm:00", 1),
	yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss", 1),
	yyyy_MM_dd_HH_mm_ss_SSS("yyyy-MM-dd HH:mm:ss.SSS", 1),
	yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS", 1),

	yyyy("yyyy", 2),
	yyyy_MM_dd("yyyy-MM-dd", 2),
	yyyyMMdd("yyyy.MM.dd", 2),

	HH("HH", 3),
    HH_mm_ss("HH:mm:ss", 3),

    ;

    TimeType(String res, int type){
        this.res = res;
        this.type = type;
    }

    private String res;
    private int type; //1:day+time 2:day 3:time
    public String value(){
        return res;
    }
    public int type(){
        return type;
    }
}
