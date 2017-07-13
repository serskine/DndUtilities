package com.soupthatisthick.encounterbuilder.logic;

public enum Level {
	LVL_1(		25,			50,			75,			100),
	LVL_2(		50,			100,		150,		200),
	LVL_3(		75,			150,		225,		300),
	LVL_4(		125,		250,		375,		500),
	LVL_5(		250,		500,		750,		1100),
	LVL_6(		300,		600,		900,		1400),
	LVL_7(		350,		750,		1100,		1700),
	LVL_8(		450,		900,		1400,		2100),
	LVL_9(		550,		1100,		1600,		2400),
	LVL_10(		600,		1200,		1900,		2800),
	LVL_11(		800,		1600,		2400,		3600),
	LVL_12(		1000,		2000,		3000,		4500),
	LVL_13(		1100,		2200,		3400,		5100),
	LVL_14(		1250,		2500,		3800,		5700),
	LVL_15(		1400,		2800,		4300,		6400),
	LVL_16(		1600,		3200,		4800,		7200),
	LVL_17(		2000,		3900,		5900,		8800),
	LVL_18(		2100,		4200,		6300,		9500),
	LVL_19(		2400,		4900,		7300,		10900),
	LVL_20(		2800,		5700,		8500,		12700);
	
	public final int easy, medium, hard, deadly;
	
	private Level(int easy, int medium, int hard, int deadly) {
		this.easy = easy;
		this.medium = medium;
		this.hard = hard;
		this.deadly = deadly;
	}
}
