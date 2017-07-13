package com.soupthatisthick.encounterbuilder.logic;


public enum Challenge {
	CR_0			("CR 0",	0F,			2, 	13,		1,		6,		0,		1,		13,		10),
	CR_EIGHTH		("CR 1/8",	0.125F,		2, 	13,		7,		35,		2,		3,		13,		25),
	CR_QUARTER		("CR 1/4",	0.25F,		2, 	13,		36,		49,		4,		5,		13,		50),
	CR_HALF			("CR 1/2",	0.5F,		2, 	13,		50,		70,		6,		8,		13,		100),
	CR_1			("CR 1",	1F,			2, 	13,		71,		85,		9,		14,		13,		200),
	CR_2			("CR 2",	2F,			2, 	13,		86,		100,	15,		20,		13,		450),
	CR_3			("CR 3",	3F,			2, 	13,		101,	115,	20,		26,		13,		700),
	CR_4			("CR 4",	4F,			2, 	14,		116,	130,	21,		32,		14,		1100),
	CR_5			("CR 5",	5F,			3, 	15,		131,	145,	27,		38,		15,		1800),
	CR_6			("CR 6",	6F,			3, 	15,		146,	160,	33,		44,		15,		2300),
	CR_7			("CR 7",	7F,			3, 	15,		161,	175,	39,		50,		15,		2900),
	CR_8			("CR 8",	8F,			3, 	16,		176,	190,	45,		56,		16,		3900),
	CR_9			("CR 9",	9F,			4, 	16,		191,	205,	51,		62,		16,		5000),
	CR_10			("CR 10",	10F,		4, 	17,		206,	220,	57,		68,		16,		5900),
	CR_11			("CR 11",	11F,		4, 	17,		221,	235,	63,		74,		17,		7200),
	CR_12			("CR 12",	12F,		4, 	17,		236,	250,	69,		80,		17,		8400),
	CR_13			("CR 13",	13F,		5, 	18,		251,	265,	75,		86,		18,		10000),
	CR_14			("CR 14",	14F,		5, 	18,		266,	280,	81,		92,		18,		11500),
	CR_15			("CR 15",	15F,		5, 	18,		281,	295,	87,		98,		18,		13000),
	CR_16			("CR 16",	16F,		5, 	18,		296,	310,	93,		104,	18,		15000),
	CR_17			("CR 17",	17F,		6, 	19,		311,	325,	99,		110,	19,		18000),
	CR_18			("CR 18",	18F,		6, 	19,		326,	340,	105,	116,	19,		20000),
	CR_19			("CR 19",	19F,		6, 	19,		341,	355,	111,	122,	19,		22000),
	CR_20			("CR 20",	20F,		6, 	19,		356,	400,	117,	140,	19,		25000),
	CR_21			("CR 21",	21F,		7, 	19,		401,	445,	123,	158,	20,		33000),
	CR_22			("CR 22",	22F,		7, 	19,		446,	490,	141,	176,	20,		41000),
	CR_23			("CR 23",	23F,		7, 	19,		491,	535,	159,	194,	20,		50000),
	CR_24			("CR 24",	24F,		7, 	19,		536,	580,	177,	212,	21,		62000),
	CR_25			("CR 25",	25F,		8, 	19,		581,	625,	195,	230,	21,		75000),
	CR_26			("CR 26",	26F,		8, 	19,		626,	670,	213,	248,	21,		90000),
	CR_27			("CR 27",	27F,		8, 	19,		671,	715,	249,	266,	22,		105000),
	CR_28			("CR 28",	28F,		8, 	19,		716,	760,	267,	284,	22,		120000),
	CR_29			("CR 29",	29F,		9, 	19,		761,	805,	285,	302,	22,		135000),
	CR_30			("CR 30",	30F,		9, 	19,		806,	850,	303,	320,	23,		155000);
	
	public final float	value;
	public final int 	prof;
	public final int 	maxAc;
	public final int 	minHp;
	public final int 	maxHp;
	public final int 	minDmgRound;
	public final int 	maxDmgRound;
	public final int 	saveDc;
	public final int 	xp;
	public final String	text;
	
	private Challenge(
		String	text,
		float	value,
		int 	prof,
		int 	maxAc,
		int 	minHp,
		int 	maxHp,
		int 	minDmgRound,
		int 	maxDmgRound,
		int 	saveDc,
		int 	xp
	) {
		this.value = value;
		this.prof = prof;
		this.maxAc = maxAc;
		this.minHp = minHp;
		this.maxHp = maxHp;
		this.minDmgRound = minDmgRound;
		this.maxDmgRound = maxDmgRound;
		this.saveDc = saveDc;
		this.xp = xp;
		
		this.text = text; 
	}
	
}















