package com.harlequinmettle.gaetool.CRON1;

public interface NYSE {
	String[] YY = {
			"A",
			"AA",
			"AAI",
			"AAN",
			"AAP",
			"AAR",
			"AAT",
			"AAV",
			"AB"};

	String[] Ymed = {
			"A",
			"AA",
			"AAI",
			"AAN",
			"AAP",
			"AAR",
			"AAT",
			"AAV",
			"AB",
"ABA",
"ABB",
"ABBV",
"ABC",
"ABD",
"ABG",
"ABK",
"ABM",
"ABR",
"ABT",
"ABV",
"ABVT",
"ABX",
"ACC",
"ACCO",
"ACE",
"ACG",
"ACH",
"ACI",
"ACL",
"ACM",
"ACMP",
"ACN",
"ACO",
"ACP",
"ACRE",
"ACTV",
"ACV",
"ACW",
"ADC",
"ADI",
"ADM",
"ADS",
"ADT",
"ADX",
"ADY",
"AEA",
"AEB",
"AEC",
"AED",
"AEE",
"AEF",
"AEG",
"AEH",
"AEK",
"AEL",
"AEM",
"AEO",
"AEP",
"AER",
"AES",
"AET",
"AEV",
"AF",
"AFA",
"AFB",
"AFC",
"AFE",
"AFF",
"AFG",
"AFL",
"AFM",
"AFQ",
"AFSD",
"AFT",
"AFW",
"AG",
"AGC",
"AGCO",
"AGD",
"AGL",
"AGM",
"AGN",
"AGO",
"AGP",
"AGRO",
"AGU",
"AH",
"AHC",
"AHD",
"AHL",
"AHS",
"AHT",
"AI",
"AIB",
"AIG",
"AIN",
"AIQ",
"AIR",
"AIT",
"AIV",
"AIZ",
"AJG",
"AKF",
"AKP",
"AKR",
"AKS",
"AKT",
"AL",
"ALB",
"ALC",
"ALDW",
"ALE",
"ALEX",
"ALF",
"ALG",
"ALJ",
"ALK",
"ALL",
"ALM",
"ALQ",
"ALR",
"ALSN",
"ALU",
"ALV",
"ALX",
"ALY",
"ALZ",
"AM",
"AMB",
"AMBO",
"AMD",
"AME",
"AMG",
"AMID",
"AMN",
"AMP",
"AMR",
"AMRC",
"AMRE",
"AMT",
"AMTD",
"AMTG",
"AMX",
"AN",
"ANF",
"ANFI",
"ANH",
"ANN",
"ANR",
"ANW",
"AOB",
"AOD",
"AOI",
"AOL",
"AON",
"AOS",
"AP",
"APA",
"APB",
"APC",
"APD",
"APF",
"APH",
"APL",
"APO",
"APU",
"APX",
"ARB",
"ARC",
"ARCO",
"ARDC",
"ARE",
"ARG",
"ARI",
"ARJ",
"ARK",
"ARL",
"ARM",
"ARN",
"ARO",
"ARP",
"ARR",
"ARSD",
"ART",
"ARU",
"ARW",
"ARX",
"ARY",
"ASA",
"ASF",
"ASG",
"ASGN",
"ASH",
"ASI",
"ASP",
"ASR",
"ASX",
"AT",
"ATE",
"ATI",
"ATK",
"ATLS",
"ATO",
"ATR",
"ATT",
"ATU",
"ATV",
"ATW",
"AU",
"AUO",
"AUQ",
"AUY",
"AV",
"AVA",
"AVB",
"AVD",
"AVF",
"AVG",
"AVK",
"AVP",
"AVT",
"AVV",
"AVX",
"AVY",
"AWC",
"AWF",
"AWH",
"AWI",
"AWK",
"AWP",
"AWR",
"AXE",
"AXL",
"AXP",
"AXR",
"AXS",
"AYE",
"AYI",
"AYN",
"AYR",
"AZN",
"AZO",
"AZZ",
"B",
"BA",
"BAC",
"BAF",
"BAH",
"BAK",
"BALT",
"BAM",
"BAP",
"BAS",
"BAX",
"BBD",
"BBDO",
"BBF",
"BBG",
"BBK",
"BBL",
"BBN",
"BBT",
"BBVA",
"BBW",
"BBX",
"BBY",
"BC",
"BCA",
"BCE",
"BCEI",
"BCF",
"BCH",
"BCO",
"BCR",
"BCS",
"BCX",
"BDC",
"BDF",
"BDJ",
"BDN",
"BDT",
"BDV",
"BDX",
"BEAM",
"BEC",
"BEE",
"BEN",
"BERY",
"BEZ",
"BFK",
"BFO",
"BFR",
"BFS",
"BFZ",
"BG",
"BGB",
"BGC",
"BGCA",
"BGG",
"BGH",
"BGP",
"BGR",
"BGS",
"BGT",
"BGX",
"BGY",
"BH"
	};
String[] Y = {
		"A",
		"AA",
		"AAI",
		"AAN",
		"AAP",
		"AAR",
		"AAT",
		"AAV",
		"AB",
"ABA",
"ABB",
"ABBV",
"ABC",
"ABD",
"ABG",
"ABK",
"ABM",
"ABR",
"ABT",
"ABV",
"ABVT",
"ABX",
"ACC",
"ACCO",
"ACE",
"ACG",
"ACH",
"ACI",
"ACL",
"ACM",
"ACMP",
"ACN",
"ACO",
"ACP",
"ACRE",
"ACTV",
"ACV",
"ACW",
"ADC",
"ADI",
"ADM",
"ADS",
"ADT",
"ADX",
"ADY",
"AEA",
"AEB",
"AEC",
"AED",
"AEE",
"AEF",
"AEG",
"AEH",
"AEK",
"AEL",
"AEM",
"AEO",
"AEP",
"AER",
"AES",
"AET",
"AEV",
"AF",
"AFA",
"AFB",
"AFC",
"AFE",
"AFF",
"AFG",
"AFL",
"AFM",
"AFQ",
"AFSD",
"AFT",
"AFW",
"AG",
"AGC",
"AGCO",
"AGD",
"AGL",
"AGM",
"AGN",
"AGO",
"AGP",
"AGRO",
"AGU",
"AH",
"AHC",
"AHD",
"AHL",
"AHS",
"AHT",
"AI",
"AIB",
"AIG",
"AIN",
"AIQ",
"AIR",
"AIT",
"AIV",
"AIZ",
"AJG",
"AKF",
"AKP",
"AKR",
"AKS",
"AKT",
"AL",
"ALB",
"ALC",
"ALDW",
"ALE",
"ALEX",
"ALF",
"ALG",
"ALJ",
"ALK",
"ALL",
"ALM",
"ALQ",
"ALR",
"ALSN",
"ALU",
"ALV",
"ALX",
"ALY",
"ALZ",
"AM",
"AMB",
"AMBO",
"AMD",
"AME",
"AMG",
"AMID",
"AMN",
"AMP",
"AMR",
"AMRC",
"AMRE",
"AMT",
"AMTD",
"AMTG",
"AMX",
"AN",
"ANF",
"ANFI",
"ANH",
"ANN",
"ANR",
"ANW",
"AOB",
"AOD",
"AOI",
"AOL",
"AON",
"AOS",
"AP",
"APA",
"APB",
"APC",
"APD",
"APF",
"APH",
"APL",
"APO",
"APU",
"APX",
"ARB",
"ARC",
"ARCO",
"ARDC",
"ARE",
"ARG",
"ARI",
"ARJ",
"ARK",
"ARL",
"ARM",
"ARN",
"ARO",
"ARP",
"ARR",
"ARSD",
"ART",
"ARU",
"ARW",
"ARX",
"ARY",
"ASA",
"ASF",
"ASG",
"ASGN",
"ASH",
"ASI",
"ASP",
"ASR",
"ASX",
"AT",
"ATE",
"ATI",
"ATK",
"ATLS",
"ATO",
"ATR",
"ATT",
"ATU",
"ATV",
"ATW",
"AU",
"AUO",
"AUQ",
"AUY",
"AV",
"AVA",
"AVB",
"AVD",
"AVF",
"AVG",
"AVK",
"AVP",
"AVT",
"AVV",
"AVX",
"AVY",
"AWC",
"AWF",
"AWH",
"AWI",
"AWK",
"AWP",
"AWR",
"AXE",
"AXL",
"AXP",
"AXR",
"AXS",
"AYE",
"AYI",
"AYN",
"AYR",
"AZN",
"AZO",
"AZZ",
"B",
"BA",
"BAC",
"BAF",
"BAH",
"BAK",
"BALT",
"BAM",
"BAP",
"BAS",
"BAX",
"BBD",
"BBDO",
"BBF",
"BBG",
"BBK",
"BBL",
"BBN",
"BBT",
"BBVA",
"BBW",
"BBX",
"BBY",
"BC",
"BCA",
"BCE",
"BCEI",
"BCF",
"BCH",
"BCO",
"BCR",
"BCS",
"BCX",
"BDC",
"BDF",
"BDJ",
"BDN",
"BDT",
"BDV",
"BDX",
"BEAM",
"BEC",
"BEE",
"BEN",
"BERY",
"BEZ",
"BFK",
"BFO",
"BFR",
"BFS",
"BFZ",
"BG",
"BGB",
"BGC",
"BGCA",
"BGG",
"BGH",
"BGP",
"BGR",
"BGS",
"BGT",
"BGX",
"BGY",
"BH",
		"BHD",
		"BHE",
		"BHI",
		"BHK",
		"BHL",
		"BHLB",
		"BHP",
		"BHS",
		"BHY",
		"BID",
		"BIE",
		"BIF",
		"BIG",
		"BIN",
		"BIO",
		"BIP",
		"BITA",
		"BJ",
		"BJZ",
		"BK",
		"BKD",
		"BKE",
		"BKH",
		"BKI",
		"BKK",
		"BKN",
		"BKS",
		"BKT",
		"BKU",
		"BKW",
		"BLC",
		"BLH",
		"BLK",
		"BLL",
		"BLOX",
		"BLT",
		"BLU",
		"BLW",
		"BLX",
		"BMA",
		"BME",
		"BMI",
		"BMO",
		"BMR",
		"BMS",
		"BMT",
		"BMY",
		"BNA",
		"BNE",
		"BNJ",
		"BNNY",
		"BNS",
		"BNY",
		"BOE",
		"BOH",
		"BORN",
		"BOX",
		"BOXC",
		"BP",
		"BPI",
		"BPK",
		"BPL",
		"BPO",
		"BPP",
		"BPT",
		"BPZ",
		"BQH",
		"BQR",
		"BR",
		"BRC",
		"BRE",
		"BRFS",
		"BRO",
		"BRP",
		"BRS",
		"BRT",
		"BRY",
		"BSAC",
		"BSBR",
		"BSD",
		"BSE",
		"BSI",
		"BSL",
		"BSMX",
		"BSP",
		"BSX",
		"BT",
		"BTA",
		"BTE",
		"BTF",
		"BTH",
		"BTM",
		"BTO",
		"BTT",
		"BTU",
		"BTZ",
		"BUD",
		"BUI",
		"BVN",
		"BW",
		"BWA",
		"BWC",
		"BWF",
		"BWG",
		"BWP",
		"BWS",
		"BX",
		"BXC",
		"BXG",
		"BXP",
		"BXS",
		"BYD",
		"BYI",
		"BYM",
		"BZ",
		"BZH",
		"BZMD",
		"BZT",
		"BZU",
		"C",
		"CAB",
		"CACI",
		"CAE",
		"CAF",
		"CAG",
		"CAH",
		"CAJ",
		"CALX",
		"CAM",
		"CAP",
		"CAR",
		"CAS",
		"CASC",
		"CAT",
		"CATO",
		"CB",
		"CBB",
		"CBC",
		"CBD",
		"CBE",
		"CBG",
		"CBI",
		"CBK",
		"CBL",
		"CBM",
		"CBR",
		"CBS",
		"CBT",
		"CBU",
		"CBZ",
		"CCC",
		"CCE",
		"CCG",
		"CCH",
		"CCI",
		"CCJ",
		"CCK",
		"CCL",
		"CCM",
		"CCO",
		"CCS",
		"CCSC",
		"CCT",
		"CCU",
		"CCV",
		"CCW",
		"CCZ",
		"CDE",
		"CDI",
		"CDR",
		"CE",
		"CEA",
		"CEB",
		"CEC",
		"CEE",
		"CEG",
		"CEL",
		"CEM",
		"CEO",
		"CEU",
		"CF",
		"CFI",
		"CFN",
		"CFR",
		"CFX",
		"CGA",
		"CGI",
		"CGO",
		"CGV",
		"CGX",
		"CHA",
		"CHC",
		"CHD",
		"CHE",
		"CHG",
		"CHH",
		"CHI",
		"CHK",
		"CHKM",
		"CHKR",
		"CHL",
		"CHMT",
		"CHN",
		"CHS",
		"CHSP",
		"CHT",
		"CHU",
		"CHW",
		"CHY",
		"CI",
		"CIA",
		"CIB",
		"CIE",
		"CIF",
		"CIG",
		"CII",
		"CIM",
		"CIR",
		"CIS",
		"CIT",
		"CIX",
		"CJES",
		"CJS",
		"CJT",
		"CKH",
		"CKP",
		"CL",
		"CLB",
		"CLC",
		"CLD",
		"CLDT",
		"CLF",
		"CLGX",
		"CLH",
		"CLI",
		"CLNY",
		"CLP",
		"CLR",
		"CLS",
		"CLU",
		"CLW",
		"CLX",
		"CM",
		"CMA",
		"CMC",
		"CMG",
		"CMI",
		"CMK",
		"CML",
		"CMLP",
		"CMM",
		"CMN",
		"CMO",
		"CMP",
		"CMRE",
		"CMS",
		"CMU",
		"CNA",
		"CNC",
		"CNCO",
		"CNH",
		"CNI",
		"CNK",
		"CNL",
		"CNO",
		"CNP",
		"CNQ",
		"CNS",
		"CNU",
		"CNW",
		"CNX",
		"CO",
		"CODE",
		"CODI",
		"COF",
		"COG",
		"COH",
		"COL",
		"COO",
		"COP",
		"COR",
		"CORR",
		"COT",
		"COV",
		"COY",
		"CP",
		"CPA",
		"CPAC",
		"CPB",
		"CPC",
		"CPE",
		"CPF",
		"CPK",
		"CPL",
		"CPN",
		"CPO",
		"CPP",
		"CPT",
		"CPV",
		"CPX",
		"CPY",
		"CQB",
		"CR",
		"CRH",
		"CRI",
		"CRK",
		"CRL",
		"CRM",
		"CRP",
		"CRR",
		"CRS",
		"CRT",
		"CRU",
		"CRY",
		"CS",
		"CSA",
		"CSC",
		"CSE",
		"CSFS",
		"CSH",
		"CSI",
		"CSL",
		"CSP",
		"CSQ",
		"CSR",
		"CSS",
		"CSU",
		"CSV",
		"CSX",
		"CT",
		"CTB",
		"CTC",
		"CTL",
		"CTQ",
		"CTR",
		"CTS",
		"CTU",
		"CTV",
		"CTW",
		"CTX",
		"CUB",
		"CUBE",
		"CUK",
		"CUZ",
		"CV",
		"CVA",
		"CVB",
		"CVC",
		"CVD",
		"CVE",
		"CVG",
		"CVH",
		"CVI",
		"CVO",
		"CVRR",
		"CVS",
		"CVX",
		"CW",
		"CWF",
		"CWH",
		"CWHN",
		"CWHO",
		"CWT",
		"CWZ",
		"CX",
		"CXE",
		"CXH",
		"CXO",
		"CXS",
		"CXW",
		"CYD",
		"CYE",
		"CYH",
		"CYN",
		"CYS",
		"CYT",
		"CZZ",
		"D",
		"DAC",
		"DAL",
		"DAN",
		"DANG",
		"DAR",
		"DB",
		"DBD",
		"DBL",
		"DCA",
		"DCE",
		"DCI",
		"DCM",
		"DCO",
		"DCS",
		"DCT",
		"DD",
		"DDD",
		"DDE",
		"DDF",
		"DDR",
		"DDS",
		"DDT",
		"DE",
		"DEG",
		"DEI",
		"DEL",
		"DEO",
		"DEP",
		"DEX",
		"DEXO",
		"DF",
		"DFG",
		"DFP",
		"DFS",
		"DFT",
		"DFY",
		"DG",
		"DGF",
		"DGI",
		"DGW",
		"DGX",
		"DHF",
		"DHG",
		"DHI",
		"DHM",
		"DHR",
		"DHT",
		"DHX",
		"DIN",
		"DIS",
		"DK",
		"DKC",
		"DKF",
		"DKI",
		"DKK",
		"DKL",
		"DKP",
		"DKQ",
		"DKS",
		"DKT",
		"DKW",
		"DL",
		"DLB",
		"DLM",
		"DLPH",
		"DLR",
		"DLX",
		"DM",
		"DMD",
		"DMO",
		"DNB",
		"DNI",
		"DNP",
		"DNR",
		"DNY",
		"DO",
		"DOLE",
		"DOM",
		"DOV",
		"DOW",
		"DOX",
		"DPD",
		"DPG",
		"DPL",
		"DPM",
		"DPO",
		"DPS",
		"DPZ",
		"DQ",
		"DRC",
		"DRD",
		"DRE",
		"DRH",
		"DRI",
		"DRL",
		"DRP",
		"DRQ",
		"DRU",
		"DSM",
		"DST",
		"DSU",
		"DSW",
		"DSX",
		"DTE",
		"DTF",
		"DTG",
		"DTK",
		"DTQ",
		"DTT",
		"DTZ",
		"DUA",
		"DUC",
		"DUF",
		"DUK",
		"DUKH",
		"DV",
		"DVA",
		"DVD",
		"DVF",
		"DVM",
		"DVN",
		"DVR",
		"DW",
		"DWRE",
		"DX",
		"DXB",
		"DY",
		"DYN",
		"DYP",
		"E",
		"EAA",
		"EAB",
		"EAT",
		"EBF",
		"EBR",
		"EBS",
		"EC",
		"ECA",
		"ECL",
		"ECT",
		"ED",
		"EDD",
		"EDE",
		"EDF",
		"EDG",
		"EDI",
		"EDN",
		"EDR",
		"EDT",
		"EDU",
		"EE",
		"EEA",
		"EEP",
		"EEQ",
		"EFC",
		"EFM",
		"EFR",
		"EFT",
		"EFX",
		"EGF",
		"EGL",
		"EGN",
		"EGO",
		"EGP",
		"EGY",
		"EHA",
		"EHB",
		"EHI",
		"EIG",
		"EIX",
		"EJ",
		"EK",
		"EL",
		"ELA",
		"ELB",
		"ELJ",
		"ELLI",
		"ELN",
		"ELP",
		"ELS",
		"ELT",
		"ELX",
		"ELY",
		"EM",
		"EMC",
		"EMD",
		"EME",
		"EMF",
		"EMN",
		"EMO",
		"EMQ",
		"EMR",
		"EMS",
		"EMZ",
		"ENB",
		"END",
		"ENH",
		"ENI",
		"ENJ",
		"ENL",
		"ENP",
		"ENR",
		"ENS",
		"ENV",
		"ENZ",
		"EOC",
		"EOD",
		"EOG",
		"EOI",
		"EOS",
		"EOT",
		"EP",
		"EPAM",
		"EPB",
		"EPD",
		"EPE",
		"EPL",
		"EPR",
		"EQM",
		"EQR",
		"EQS",
		"EQT",
		"EQU",
		"EQY",
		"ERF",
		"ERJ",
		"ES",
		"ESC",
		"ESD",
		"ESE",
		"ESI",
		"ESL",
		"ESS",
		"ESV",
		"ET",
		"ETB",
		"ETE",
		"ETG",
		"ETH",
		"ETJ",
		"ETM",
		"ETN",
		"ETO",
		"ETP",
		"ETR",
		"ETV",
		"ETW",
		"ETY",
		"EV",
		"EVC",
		"EVER",
		"EVF",
		"EVG",
		"EVN",
		"EVR",
		"EVT",
		"EW",
		"EXAM",
		"EXBD",
		"EXC",
		"EXD",
		"EXG",
		"EXH",
		"EXK",
		"EXL",
		"EXM",
		"EXP",
		"EXPR",
		"EXR",
		"F",
		"FAC",
		"FAF",
		"FAM",
		"FAV",
		"FBC",
		"FBHS",
		"FBN",
		"FBP",
		"FBR",
		"FC",
		"FCF",
		"FCH",
		"FCJ",
		"FCN",
		"FCS",
		"FCT",
		"FCX",
		"FCY",
		"FCZ",
		"FDI",
		"FDO",
		"FDP",
		"FDS",
		"FDX",
		"FE",
		"FEI",
		"FENG",
		"FEO",
		"FET",
		"FF",
		"FFA",
		"FFC",
		"FFD",
		"FFG",
		"FGB",
		"FGC",
		"FGE",
		"FGF",
		"FGI",
		"FGP",
		"FHI",
		"FHN",
		"FHO",
		"FHY",
		"FICO",
		"FIF",
		"FIG",
		"FII",
		"FIO",
		"FIS",
		"FIX",
		"FJA",
		"FL",
		"FLC",
		"FLO",
		"FLR",
		"FLS",
		"FLT",
		"FLTX",
		"FLY",
		"FMC",
		"FMD",
		"FMN",
		"FMO",
		"FMR",
		"FMS",
		"FMX",
		"FMY",
		"FN",
		"FNB",
		"FNF",
		"FNP",
		"FNV",
		"FO",
		"FOE",
		"FOF",
		"FOR",
		"FPO",
		"FPT",
		"FR",
		"FRA",
		"FRB",
		"FRC",
		"FRF",
		"FRM",
		"FRO",
		"FRT",
		"FRX",
		"FRZ",
		"FSC",
		"FSCE",
		"FSD",
		"FSL",
		"FSM",
		"FSR",
		"FSS",
		"FST",
		"FT",
		"FTE",
		"FTI",
		"FTK",
		"FTO",
		"FTR",
		"FTT",
		"FUL",
		"FUN",
		"FUR",
		"FVE",
		"FWF",
		"FXCM",
		"G",
		"GA",
		"GAB",
		"GAJ",
		"GAM",
		"GAP",
		"GAR",
		"GAS",
		"GAT",
		"GB",
		"GBAB",
		"GBE",
		"GBL",
		"GBX",
		"GCA",
		"GCAP",
		"GCF",
		"GCH",
		"GCI",
		"GCO",
		"GCV",
		"GD",
		"GDF",
		"GDI",
		"GDL",
		"GDO",
		"GDOT",
		"GDP",
		"GDV",
		"GE",
		"GEA",
		"GEB",
		"GEC",
		"GED",
		"GEF",
		"GEG",
		"GEJ",
		"GEL",
		"GEO",
		"GEP",
		"GEQ",
		"GER",
		"GES",
		"GET",
		"GF",
		"GFA",
		"GFC",
		"GFF",
		"GFI",
		"GFIG",
		"GFW",
		"GFY",
		"GFZ",
		"GG",
		"GGB",
		"GGC",
		"GGE",
		"GGG",
		"GGP",
		"GGS",
		"GGT",
		"GHI",
		"GHL",
		"GHY",
		"GIB",
		"GIL",
		"GIM",
		"GIS",
		"GJD",
		"GJE",
		"GJH",
		"GJI",
		"GJJ",
		"GJK",
		"GJL",
		"GJM",
		"GJN",
		"GJO",
		"GJP",
		"GJR",
		"GJS",
		"GJT",
		"GJV",
		"GJW",
		"GKK",
		"GKM",
		"GLF",
		"GLOG",
		"GLP",
		"GLT",
		"GLW",
		"GM",
		"GMA",
		"GME",
		"GMED",
		"GMK",
		"GMR",
		"GMT",
		"GMXR",
		"GNC",
		"GNE",
		"GNI",
		"GNK",
		"GNRC",
		"GNT",
		"GNW",
		"GOF",
		"GOL",
		"GOM",
		"GOV",
		"GPC",
		"GPI",
		"GPK",
		"GPM",
		"GPN",
		"GPS",
		"GPX",
		"GR",
		"GRA",
		"GRB",
		"GRM",
		"GRO",
		"GRR",
		"GRS",
		"GRT",
		"GRX",
		"GS",
		"GSE",
		"GSF",
		"GSH",
		"GSI",
		"GSJ",
		"GSK",
		"GSL",
		"GT",
		"GTI",
		"GTN",
		"GTS",
		"GTY",
		"GU",
		"GUA",
		"GUL",
		"GUT",
		"GVA",
		"GWAY",
		"GWF",
		"GWR",
		"GWRE",
		"GWRU",
		"GWW",
		"GXP",
		"GY",
		"GYA",
		"GYB",
		"GYC",
		"GZT",
		"H",
		"HAE",
		"HAL",
		"HAR",
		"HAS",
		"HAV",
		"HBC",
		"HBI",
		"HBM",
		"HCA",
		"HCC",
		"HCF",
		"HCH",
		"HCI",
		"HCLP",
		"HCN",
		"HCP",
		"HCS",
		"HD",
		"HDB",
		"HDY",
		"HE",
		"HEI",
		"HEK",
		"HEP",
		"HEQ",
		"HES",
		"HF",
		"HFC",
		"HGG",
		"HGH",
		"HGR",
		"HGT",
		"HHC",
		"HHS",
		"HHY",
		"HI",
		"HIF",
		"HIG",
		"HIH",
		"HII",
		"HIL",
		"HIO",
		"HIS",
		"HIT",
		"HIW",
		"HIX",
		"HJA",
		"HJG",
		"HJJ",
		"HJL",
		"HJN",
		"HJO",
		"HJR",
		"HJT",
		"HJV",
		"HK",
		"HL",
		"HLF",
		"HLS",
		"HLX",
		"HMA",
		"HMC",
		"HME",
		"HMH",
		"HMN",
		"HMY",
		"HNI",
		"HNP",
		"HNR",
		"HNT",
		"HNZ",
		"HOC",
		"HOG",
		"HON",
		"HOS",
		"HOT",
		"HOV",
		"HOVU",
		"HP",
		"HPF",
		"HPI",
		"HPP",
		"HPQ",
		"HPS",
		"HPT",
		"HPY",
		"HQH",
		"HQL",
		"HR",
		"HRB",
		"HRC",
		"HRG",
		"HRL",
		"HRS",
		"HRZ",
		"HS",
		"HSA",
		"HSC",
		"HSH",
		"HSM",
		"HSP",
		"HST",
		"HSY",
		"HT",
		"HTA",
		"HTB",
		"HTD",
		"HTF",
		"HTGC",
		"HTGY",
		"HTGZ",
		"HTH",
		"HTN",
		"HTR",
		"HTS",
		"HTSI",
		"HTY",
		"HTZ",
		"HUM",
		"HUN",
		"HVB",
		"HVT",
		"HW",
		"HWD",
		"HXL",
		"HXM",
		"HY",
		"HYB",
		"HYC",
		"HYF",
		"HYH",
		"HYI",
		"HYK",
		"HYL",
		"HYM",
		"HYT",
		"HYV",
		"HYY",
		"HZD",
		"HZK",
		"HZO",
		"IAE",
		"IAG",
		"IBA",
		"IBI",
		"IBM",
		"IBN",
		"ICA",
		"ICB",
		"ICE",
		"ICO",
		"ICS",
		"ID",
		"IDA",
		"IDE",
		"IDG",
		"IDT",
		"IEP",
		"IEX",
		"IFF",
		"IFN",
		"IFT",
		"IGA",
		"IGD",
		"IGI",
		"IGK",
		"IGR",
		"IGT",
		"IHC",
		"IHD",
		"IHG",
		"IHS",
		"IIC",
		"IID",
		"IIF",
		"IIM",
		"IIT",
		"IKJ",
		"IKL",
		"IKM",
		"IKR",
		"IL",
		"IM",
		"IMAX",
		"IMC",
		"IMF",
		"IMN",
		"IMPV",
		"IMS",
		"IMT",
		"IN",
		"INB",
		"IND",
		"INF",
		"INFY",
		"ING",
		"INGR",
		"INN",
		"INT",
		"INVN",
		"INXN",
		"INZ",
		"IO",
		"IOC",
		"IP",
		"IPG",
		"IPHI",
		"IPI",
		"IQC",
		"IQI",
		"IQM",
		"IQN",
		"IQT",
		"IR",
		"IRC",
		"IRE",
		"IRET",
		"IRF",
		"IRL",
		"IRM",
		"IRR",
		"IRS",
		"ISD",
		"ISF",
		"ISG",
		"ISH",
		"ISM",
		"ISP",
		"ISS",
		"IT",
		"ITC",
		"ITG",
		"ITT",
		"ITUB",
		"ITW",
		"IVC",
		"IVN",
		"IVR",
		"IVZ",
		"IX",
		"JAG",
		"JAH",
		"JAS",
		"JBI",
		"JBJ",
		"JBK",
		"JBL",
		"JBN",
		"JBO",
		"JBR",
		"JBT",
		"JCE",
		"JCG",
		"JCI",
		"JCP",
		"JDD",
		"JE",
		"JEC",
		"JEF",
		"JEQ",
		"JFC",
		"JFR",
		"JGG",
		"JGT",
		"JGV",
		"JHI",
		"JHP",
		"JHS",
		"JHX",
		"JKS",
		"JLA",
		"JLL",
		"JLS",
		"JMF",
		"JMI",
		"JMP",
		"JMT",
		"JNJ",
		"JNPR",
		"JNS",
		"JNY",
		"JOE",
		"JOF",
		"JOY",
		"JPC",
		"JPG",
		"JPI",
		"JPM",
		"JPS",
		"JPZ",
		"JQC",
		"JRI",
		"JRN",
		"JRO",
		"JSD",
		"JSM",
		"JSN",
		"JTA",
		"JTD",
		"JTP",
		"JTX",
		"JWF",
		"JWN",
		"JZC",
		"JZH",
		"JZJ",
		"JZK",
		"JZL",
		"JZS",
		"JZT",
		"JZV",
		"K",
		"KAI",
		"KAMN",
		"KAP",
		"KAR",
		"KB",
		"KBH",
		"KBR",
		"KBW",
		"KCC",
		"KCG",
		"KCI",
		"KCP",
		"KCT",
		"KCW",
		"KDN",
		"KED",
		"KEF",
		"KEG",
		"KEI",
		"KEM",
		"KEP",
		"KEX",
		"KEY",
		"KF",
		"KFH",
		"KFI",
		"KFN",
		"KFS",
		"KFT",
		"KFY",
		"KG",
		"KGC",
		"KH",
		"KHI",
		"KID",
		"KIM",
		"KKD",
		"KKR",
		"KMB",
		"KMF",
		"KMG",
		"KMI",
		"KMM",
		"KMP",
		"KMPR",
		"KMR",
		"KMT",
		"KMX",
		"KND",
		"KNL",
		"KNM",
		"KNO",
		"KNR",
		"KNX",
		"KO",
		"KOB",
		"KOF",
		"KOG",
		"KOP",
		"KORS",
		"KOS",
		"KR",
		"KRA",
		"KRC",
		"KRG",
		"KRH",
		"KRJ",
		"KRO",
		"KS",
		"KSA",
		"KSK",
		"KSM",
		"KSP",
		"KSS",
		"KST",
		"KSU",
		"KT",
		"KTF",
		"KTH",
		"KTN",
		"KTP",
		"KTV",
		"KTX",
		"KUB",
		"KVF",
		"KVR",
		"KVW",
		"KW",
		"KWK",
		"KWN",
		"KWR",
		"KYE",
		"KYN",
		"KYO",
		"L",
		"LAB",
		"LAD",
		"LAS",
		"LAZ",
		"LBF",
		"LCC",
		"LCM",
		"LDF",
		"LDK",
		"LDL",
		"LDP",
		"LDR",
		"LEA",
		"LEE",
		"LEG",
		"LEN",
		"LEO",
		"LF",
		"LFC",
		"LFL",
		"LFT",
		"LG",
		"LGF",
		"LGI",
		"LGP",
		"LH",
		"LHO",
		"LII",
		"LIZ",
		"LL",
		"LLL",
		"LLY",
		"LM",
		"LMI",
		"LMT",
		"LNC",
		"LND",
		"LNKD",
		"LNN",
		"LNT",
		"LO",
		"LOCK",
		"LOR",
		"LOW",
		"LPI",
		"LPL",
		"LPR",
		"LPS",
		"LPX",
		"LRE",
		"LRN",
		"LRY",
		"LSE",
		"LSI",
		"LTC",
		"LTD",
		"LTM",
		"LUB",
		"LUK",
		"LUV",
		"LUX",
		"LVB",
		"LVLT",
		"LVS",
		"LXFR",
		"LXK",
		"LXP",
		"LXU",
		"LYB",
		"LYG",
		"LYV",
		"LZ",
		"LZB",
		"M",
		"MA",
		"MAA",
		"MAC",
		"MAG",
		"MAIN",
		"MAN",
		"MANU",
		"MAR",
		"MAS",
		"MATX",
		"MAV",
		"MAY",
		"MBI",
		"MBT",
		"MCA",
		"MCC",
		"MCD",
		"MCI",
		"MCK",
		"MCN",
		"MCO",
		"MCP",
		"MCQ",
		"MCR",
		"MCS",
		"MCY",
		"MD",
		"MDC",
		"MDP",
		"MDR",
		"MDS",
		"MDT",
		"MDU",
		"MDZ",
		"ME",
		"MED",
		"MEE",
		"MEG",
		"MEI",
		"MEN",
		"MET",
		"MF",
		"MFA",
		"MFB",
		"MFC",
		"MFD",
		"MFE",
		"MFG",
		"MFL",
		"MFM",
		"MFO",
		"MFT",
		"MFV",
		"MFW",
		"MG",
		"MGA",
		"MGF",
		"MGI",
		"MGM",
		"MGR",
		"MGU",
		"MHD",
		"MHF",
		"MHI",
		"MHK",
		"MHN",
		"MHNA",
		"MHNB",
		"MHO",
		"MHP",
		"MHR",
		"MHS",
		"MHY",
		"MI",
		"MIC",
		"MIG",
		"MIL",
		"MILL",
		"MIM",
		"MIN",
		"MIR",
		"MITT",
		"MIY",
		"MJF",
		"MJH",
		"MJI",
		"MJN",
		"MJT",
		"MJV",
		"MJY",
		"MKC",
		"MKL",
		"MKS",
		"MKV",
		"MLG",
		"MLI",
		"MLM",
		"MLP",
		"MLR",
		"MLU",
		"MM",
		"MMC",
		"MMD",
		"MMM",
		"MMP",
		"MMR",
		"MMS",
		"MMT",
		"MMU",
		"MN",
		"MNE",
		"MNI",
		"MNP",
		"MNR",
		"MO",
		"MOD",
		"MOH",
		"MON",
		"MOS",
		"MOT",
		"MOV",
		"MPA",
		"MPC",
		"MPG",
		"MPJ",
		"MPLX",
		"MPO",
		"MPR",
		"MPV",
		"MPW",
		"MPX",
		"MQT",
		"MQY",
		"MR",
		"MRC",
		"MRF",
		"MRH",
		"MRK",
		"MRO",
		"MRT",
		"MRX",
		"MS",
		"MSA",
		"MSB",
		"MSCI",
		"MSD",
		"MSF",
		"MSI",
		"MSJ",
		"MSK",
		"MSM",
		"MSO",
		"MSP",
		"MSY",
		"MSZ",
		"MT",
		"MTA",
		"MTB",
		"MTC",
		"MTCN",
		"MTD",
		"MTDR",
		"MTE",
		"MTG",
		"MTH",
		"MTL",
		"MTN",
		"MTOR",
		"MTP",
		"MTR",
		"MTRN",
		"MTS",
		"MTT",
		"MTU",
		"MTW",
		"MTX",
		"MTZ",
		"MUA",
		"MUC",
		"MUE",
		"MUH",
		"MUI",
		"MUJ",
		"MUR",
		"MUS",
		"MUSA",
		"MUX",
		"MVC",
		"MVO",
		"MVT",
		"MW",
		"MWA",
		"MWE",
		"MWG",
		"MWO",
		"MWR",
		"MWV",
		"MWW",
		"MX",
		"MXE",
		"MXF",
		"MXL",
		"MXT",
		"MY",
		"MYC",
		"MYD",
		"MYE",
		"MYF",
		"MYI",
		"MYJ",
		"MYM",
		"MYN",
		"MZF",
		"N",
		"NAC",
		"NAD",
		"NAI",
		"NAL",
		"NAN",
		"NAT",
		"NAV",
		"NAZ",
		"NBB",
		"NBD",
		"NBG",
		"NBHC",
		"NBL",
		"NBR",
		"NC",
		"NCA",
		"NCI",
		"NCL",
		"NCO",
		"NCP",
		"NCR",
		"NCS",
		"NCT",
		"NCV",
		"NCZ",
		"NDN",
		"NDP",
		"NDRO",
		"NDZ",
		"NE",
		"NED",
		"NEE",
		"NEM",
		"NEU",
		"NEV",
		"NFG",
		"NFJ",
		"NFP",
		"NFX",
		"NGG",
		"NGL",
		"NGLS",
		"NGS",
		"NGT",
		"NGVC",
		"NGZ",
		"NHF",
		"NHI",
		"NHP",
		"NI",
		"NID",
		"NIE",
		"NIF",
		"NIM",
		"NIO",
		"NJ",
		"NJR",
		"NKA",
		"NKE",
		"NL",
		"NLC",
		"NLS",
		"NLSN",
		"NLY",
		"NM",
		"NMA",
		"NMD",
		"NMFC",
		"NMI",
		"NMM",
		"NMO",
		"NMP",
		"NMR",
		"NMT",
		"NMY",
		"NNA",
		"NNC",
		"NNF",
		"NNI",
		"NNJ",
		"NNN",
		"NNP",
		"NNY",
		"NOA",
		"NOAH",
		"NOC",
		"NOK",
		"NOR",
		"NOV",
		"NOW",
		"NP",
		"NPC",
		"NPD",
		"NPF",
		"NPI",
		"NPK",
		"NPM",
		"NPO",
		"NPP",
		"NPT",
		"NPTN",
		"NPV",
		"NPX",
		"NPY",
		"NQ",
		"NQC",
		"NQI",
		"NQJ",
		"NQM",
		"NQN",
		"NQP",
		"NQS",
		"NQU",
		"NR",
		"NRC",
		"NRF",
		"NRG",
		"NRGM",
		"NRGP",
		"NRGY",
		"NRP",
		"NRT",
		"NRU",
		"NS",
		"NSC",
		"NSH",
		"NSL",
		"NSM",
		"NSP",
		"NSR",
		"NST",
		"NTC",
		"NTE",
		"NTG",
		"NTI",
		"NTL",
		"NTT",
		"NTX",
		"NTZ",
		"NU",
		"NUC",
		"NUE",
		"NUM",
		"NUN",
		"NUO",
		"NUS",
		"NUV",
		"NUW",
		"NVC",
		"NVE",
		"NVN",
		"NVO",
		"NVR",
		"NVS",
		"NWE",
		"NWL",
		"NWN",
		"NWY",
		"NX",
		"NXC",
		"NXN",
		"NXP",
		"NXQ",
		"NXR",
		"NXY",
		"NYB",
		"NYCB",
		"NYM",
		"NYT",
		"NYX",
		"NZ",
		"NZT",
		"O",
		"OAK",
		"OAS",
		"OB",
		"OC",
		"OCN",
		"OCR",
		"ODC",
		"ODP",
		"OEH",
		"OFC",
		"OFG",
		"OGE",
		"OHI",
		"OI",
		"OIA",
		"OIB",
		"OIBR",
		"OIC",
		"OII",
		"OILT",
		"OIS",
		"OKE",
		"OKS",
		"OLN",
		"OLP",
		"OMC",
		"OME",
		"OMG",
		"OMI",
		"OMN",
		"OMX",
		"ONB",
		"ONE",
		"OPK",
		"OPY",
		"ORA",
		"ORB",
		"ORI",
		"ORN",
		"OSG",
		"OSK",
		"OSM",
		"OWW",
		"OXF",
		"OXM",
		"OXY",
		"OZM",
		"P",
		"PAA",
		"PAC",
		"PACD",
		"PAG",
		"PAI",
		"PAM",
		"PANW",
		"PAR",
		"PAY",
		"PB",
		"PBA",
		"PBF",
		"PBH",
		"PBI",
		"PBNY",
		"PBR",
		"PBT",
		"PBY",
		"PBYI",
		"PC",
		"PCF",
		"PCG",
		"PCH",
		"PCK",
		"PCL",
		"PCM",
		"PCN",
		"PCP",
		"PCQ",
		"PCS",
		"PCX",
		"PDE",
		"PDH",
		"PDI",
		"PDM",
		"PDS",
		"PDT",
		"PEB",
		"PEG",
		"PEI",
		"PEO",
		"PEP",
		"PER",
		"PES",
		"PFD",
		"PFE",
		"PFG",
		"PFH",
		"PFK",
		"PFL",
		"PFN",
		"PFO",
		"PFS",
		"PFX",
		"PG",
		"PGH",
		"PGI",
		"PGN",
		"PGP",
		"PGR",
		"PH",
		"PHA",
		"PHD",
		"PHG",
		"PHH",
		"PHI",
		"PHK",
		"PHM",
		"PHR",
		"PHT",
		"PHX",
		"PIA",
		"PII",
		"PIJ",
		"PIKE",
		"PIM",
		"PIR",
		"PIS",
		"PIY",
		"PJA",
		"PJC",
		"PJE",
		"PJH",
		"PJI",
		"PJJ",
		"PJL",
		"PJR",
		"PJS",
		"PJT",
		"PJZ",
		"PKD",
		"PKE",
		"PKG",
		"PKH",
		"PKI",
		"PKJ",
		"PKK",
		"PKM",
		"PKO",
		"PKX",
		"PKY",
		"PL",
		"PLA",
		"PLD",
		"PLL",
		"PLOW",
		"PLP",
		"PLS",
		"PLT",
		"PLV",
		"PM",
		"PMC",
		"PMF",
		"PMI",
		"PML",
		"PMM",
		"PMO",
		"PMT",
		"PMX",
		"PNC",
		"PNF",
		"PNG",
		"PNH",
		"PNI",
		"PNK",
		"PNM",
		"PNR",
		"PNU",
		"PNW",
		"PNX",
		"PNY",
		"POH",
		"POL",
		"POM",
		"POR",
		"POST",
		"POT",
		"PPC",
		"PPD",
		"PPG",
		"PPL",
		"PPO",
		"PPP",
		"PPR",
		"PPS",
		"PPT",
		"PQ",
		"PRA",
		"PRD",
		"PRE",
		"PRGN",
		"PRI",
		"PRIS",
		"PRLB",
		"PRM",
		"PRO",
		"PRS",
		"PRU",
		"PRX",
		"PRY",
		"PSA",
		"PSB",
		"PSE",
		"PSF",
		"PSO",
		"PSS",
		"PSW",
		"PSX",
		"PSY",
		"PT",
		"PTC",
		"PTGI",
		"PTI",
		"PTP",
		"PTR",
		"PTV",
		"PTY",
		"PUK",
		"PULS",
		"PVA",
		"PVD",
		"PVG",
		"PVH",
		"PVR",
		"PVTD",
		"PVX",
		"PWE",
		"PWR",
		"PX",
		"PXD",
		"PXP",
		"PYA",
		"PYB",
		"PYC",
		"PYG",
		"PYI",
		"PYJ",
		"PYK",
		"PYL",
		"PYN",
		"PYO",
		"PYS",
		"PYT",
		"PYV",
		"PYY",
		"PZB",
		"PZC",
		"PZE",
		"PZN",
		"Q",
		"QEP",
		"QIHU",
		"QRE",
		"QTM",
		"QUAD",
		"QXM",
		"R",
		"RA",
		"RAD",
		"RAH",
		"RAI",
		"RAS",
		"RATE",
		"RAX",
		"RBA",
		"RBC",
		"RBN",
		"RBS",
		"RBV",
		"RC",
		"RCI",
		"RCL",
		"RCS",
		"RDC",
		"RDK",
		"RDN",
		"RDY",
		"RE",
		"REG",
		"REN",
		"RENN",
		"REP",
		"RES",
		"RESI",
		"REV",
		"REX",
		"RF",
		"RFI",
		"RFP",
		"RGA",
		"RGC",
		"RGP",
		"RGR",
		"RGS",
		"RH",
		"RHB",
		"RHI",
		"RHP",
		"RHT",
		"RIG",
		"RIO",
		"RIOM",
		"RIT",
		"RJD",
		"RJF",
		"RKT",
		"RKUS",
		"RL",
		"RLD",
		"RLGY",
		"RLH",
		"RLI",
		"RLJ",
		"RM",
		"RMD",
		"RMT",
		"RNDY",
		"RNE",
		"RNF",
		"RNO",
		"RNP",
		"RNR",
		"ROC",
		"ROG",
		"ROK",
		"ROL",
		"ROP",
		"ROYT",
		"RPAI",
		"RPM",
		"RPT",
		"RQI",
		"RRC",
		"RRI",
		"RRMS",
		"RRR",
		"RRTS",
		"RS",
		"RSE",
		"RSG",
		"RSH",
		"RSO",
		"RST",
		"RT",
		"RTI",
		"RTN",
		"RUK",
		"RVI",
		"RVT",
		"RWT",
		"RXN",
		"RY",
		"RYL",
		"RYN",
		"RZ",
		"RZA",
		"S",
		"SA",
		"SAB",
		"SAH",
		"SAI",
		"SAM",
		"SAN",
		"SAP",
		"SAR",
		"SB",
		"SBH",
		"SBR",
		"SBS",
		"SBW",
		"SBX",
		"SBY",
		"SCCO",
		"SCD",
		"SCG",
		"SCHW",
		"SCI",
		"SCL",
		"SCM",
		"SCR",
		"SCS",
		"SCU",
		"SCX",
		"SD",
		"SDLP",
		"SDR",
		"SDRL",
		"SDT",
		"SE",
		"SEE",
		"SEH",
		"SEM",
		"SEMG",
		"SEP",
		"SF",
		"SFB",
		"SFD",
		"SFE",
		"SFG",
		"SFI",
		"SFL",
		"SFN",
		"SFUN",
		"SFY",
		"SGF",
		"SGK",
		"SGL",
		"SGU",
		"SGY",
		"SGZ",
		"SHAW",
		"SHG",
		"SHI",
		"SHO",
		"SHP",
		"SHS",
		"SHW",
		"SI",
		"SID",
		"SIG",
		"SIR",
		"SIX",
		"SJI",
		"SJM",
		"SJR",
		"SJT",
		"SJW",
		"SKH",
		"SKM",
		"SKS",
		"SKT",
		"SKX",
		"SKY",
		"SLA",
		"SLB",
		"SLCA",
		"SLE",
		"SLF",
		"SLG",
		"SLH",
		"SLM",
		"SLRA",
		"SLS",
		"SLT",
		"SLW",
		"SM",
		"SMA",
		"SMF",
		"SMFG",
		"SMG",
		"SMI",
		"SMLP",
		"SMM",
		"SMP",
		"SMS",
		"SN",
		"SNA",
		"SNE",
		"SNF",
		"SNH",
		"SNHN",
		"SNI",
		"SNN",
		"SNP",
		"SNV",
		"SNX",
		"SNY",
		"SO",
		"SOA",
		"SOL",
		"SON",
		"SOR",
		"SPA",
		"SPB",
		"SPE",
		"SPF",
		"SPG",
		"SPH",
		"SPLP",
		"SPN",
		"SPP",
		"SPR",
		"SPW",
		"SQM",
		"SQNS",
		"SR",
		"SRC",
		"SRE",
		"SRF",
		"SRI",
		"SRT",
		"SRV",
		"SRX",
		"SRZ",
		"SSCC",
		"SSD",
		"SSI",
		"SSL",
		"SSP",
		"SSS",
		"SSTK",
		"SSW",
		"ST",
		"STAG",
		"STC",
		"STD",
		"STE",
		"STI",
		"STJ",
		"STK",
		"STL",
		"STM",
		"STN",
		"STNG",
		"STO",
		"STON",
		"STP",
		"STR",
		"STRI",
		"STT",
		"STU",
		"STV",
		"STWD",
		"STZ",
		"SU",
		"SUG",
		"SUI",
		"SUN",
		"SUP",
		"SUR",
		"SUSP",
		"SUSS",
		"SVM",
		"SVN",
		"SVR",
		"SVU",
		"SWC",
		"SWFT",
		"SWI",
		"SWJ",
		"SWK",
		"SWM",
		"SWN",
		"SWS",
		"SWU",
		"SWX",
		"SWY",
		"SWZ",
		"SXC",
		"SXCP",
		"SXE",
		"SXI",
		"SXL",
		"SXT",
		"SYA",
		"SYK",
		"SYSW",
		"SYT",
		"SYX",
		"SYY",
		"SZC",
		"T",
		"TAC",
		"TAHO",
		"TAI",
		"TAL",
		"TAM",
		"TAOM",
		"TAP",
		"TARO",
		"TBH",
		"TBI",
		"TBL",
		"TC",
		"TCAP",
		"TCB",
		"TCC",
		"TCCA",
		"TCH",
		"TCI",
		"TCK",
		"TCL",
		"TCM",
		"TCO",
		"TCP",
		"TD",
		"TDA",
		"TDC",
		"TDE",
		"TDF",
		"TDG",
		"TDI",
		"TDJ",
		"TDS",
		"TDW",
		"TDY",
		"TE",
		"TEF",
		"TEG",
		"TEI",
		"TEL",
		"TEN",
		"TEO",
		"TER",
		"TEU",
		"TEVA",
		"TEX",
		"TFC",
		"TFG",
		"TFX",
		"TG",
		"TGH",
		"TGI",
		"TGP",
		"TGS",
		"TGT",
		"TGX",
		"THC",
		"THG",
		"THI",
		"THO",
		"THR",
		"THS",
		"TI",
		"TIE",
		"TIF",
		"TIN",
		"TISI",
		"TJX",
		"TK",
		"TKC",
		"TKF",
		"TKR",
		"TLB",
		"TLI",
		"TLK",
		"TLLP",
		"TLM",
		"TLP",
		"TLYS",
		"TM",
		"TMH",
		"TMK",
		"TMM",
		"TMO",
		"TMS",
		"TMX",
		"TNB",
		"TNC",
		"TNE",
		"TNH",
		"TNK",
		"TNL",
		"TNP",
		"TNS",
		"TOD",
		"TOL",
		"TOO",
		"TOT",
		"TOWR",
		"TPC",
		"TPL",
		"TPX",
		"TPZ",
		"TR",
		"TRC",
		"TREX",
		"TRF",
		"TRGP",
		"TRH",
		"TRI",
		"TRK",
		"TRLA",
		"TRN",
		"TRNO",
		"TROX",
		"TRP",
		"TRQ",
		"TRR",
		"TRU",
		"TRV",
		"TRW",
		"TS",
		"TSI",
		"TSL",
		"TSM",
		"TSN",
		"TSO",
		"TSP",
		"TSS",
		"TSU",
		"TTC",
		"TTF",
		"TTI",
		"TTM",
		"TTO",
		"TTP",
		"TTT",
		"TU",
		"TUC",
		"TUMI",
		"TUP",
		"TV",
		"TVC",
		"TVE",
		"TVL",
		"TW",
		"TWC",
		"TWI",
		"TWN",
		"TWO",
		"TWX",
		"TX",
		"TXI",
		"TXN",
		"TXT",
		"TY",
		"TYC",
		"TYG",
		"TYL",
		"TYN",
		"TYW",
		"TYY",
		"TZF",
		"TZK",
		"UA",
		"UAL",
		"UAM",
		"UAN",
		"UBA",
		"UBP",
		"UBS",
		"UDR",
		"UFI",
		"UFS",
		"UGI",
		"UGP",
		"UHS",
		"UHT",
		"UIL",
		"UIS",
		"UL",
		"UMC",
		"UMH",
		"UN",
		"UNF",
		"UNH",
		"UNM",
		"UNP",
		"UNS",
		"UNT",
		"UPL",
		"UPS",
		"URI",
		"URS",
		"USA",
		"USAC",
		"USB",
		"USG",
		"USM",
		"USNA",
		"USPH",
		"USU",
		"UTA",
		"UTF",
		"UTI",
		"UTL",
		"UTR",
		"UTX",
		"UVV",
		"UXG",
		"UZA",
		"UZV",
		"V",
		"VAC",
		"VAL",
		"VALE",
		"VAR",
		"VBF",
		"VC",
		"VCI",
		"VCO",
		"VCRA",
		"VCV",
		"VE",
		"VFC",
		"VG",
		"VGI",
		"VGM",
		"VGR",
		"VHI",
		"VHS",
		"VIA",
		"VIM",
		"VIP",
		"VIPS",
		"VIT",
		"VIV",
		"VKQ",
		"VLO",
		"VLT",
		"VLY",
		"VMC",
		"VMI",
		"VMO",
		"VMW",
		"VNO",
		"VNOD",
		"VNR",
		"VNTV",
		"VNV",
		"VOC",
		"VOL",
		"VOQ",
		"VPG",
		"VPV",
		"VQ",
		"VR",
		"VRS",
		"VRX",
		"VSH",
		"VSI",
		"VTA",
		"VTJ",
		"VTN",
		"VTR",
		"VVC",
		"VVI",
		"VVR",
		"VZ",
		"WAB",
		"WAC",
		"WAG",
		"WAGE",
		"WAIR",
		"WAL",
		"WAT",
		"WBC",
		"WBD",
		"WBK",
		"WBS",
		"WCC",
		"WCG",
		"WCN",
		"WCO",
		"WD",
		"WDAY",
		"WDC",
		"WDR",
		"WEA",
		"WEC",
		"WEN",
		"WES",
		"WF",
		"WFC",
		"WFR",
		"WFT",
		"WG",
		"WGL",
		"WGO",
		"WGP",
		"WH",
		"WHG",
		"WHR",
		"WHX",
		"WHZ",
		"WIA",
		"WIT",
		"WIW",
		"WL",
		"WLK",
		"WLL",
		"WLP",
		"WLT",
		"WM",
		"WMB",
		"WMC",
		"WMG",
		"WMK",
		"WMS",
		"WMT",
		"WNC",
		"WNI",
		"WNR",
		"WNS",
		"WOR",
		"WPC",
		"WPI",
		"WPK",
		"WPO",
		"WPP",
		"WPX",
		"WPZ",
		"WR",
		"WRB",
		"WRC",
		"WRD",
		"WRE",
		"WRI",
		"WRS",
		"WRT",
		"WSF",
		"WSH",
		"WSM",
		"WSO",
		"WSR",
		"WST",
		"WTI",
		"WTM",
		"WTR",
		"WTS",
		"WTU",
		"WTW",
		"WU",
		"WWAV",
		"WWE",
		"WWW",
		"WX",
		"WXS",
		"WY",
		"WYN",
		"X",
		"XAA",
		"XCJ",
		"XCO",
		"XEC",
		"XEL",
		"XFB",
		"XFD",
		"XFH",
		"XFJ",
		"XFP",
		"XFR",
		"XIN",
		"XJT",
		"XKE",
		"XKK",
		"XKN",
		"XKO",
		"XL",
		"XLS",
		"XNY",
		"XOM",
		"XOXO",
		"XPO",
		"XRM",
		"XRS",
		"XRX",
		"XUE",
		"XVG",
		"XYL",
		"Y",
		"YELP",
		"YGE",
		"YOKU",
		"YPF",
		"YSI",
		"YUM",
		"YZC",
		"ZA",
		"ZEP",
		"ZF",
		"ZLC",
		"ZMH",
		"ZNH",
		"ZQK",
		"ZTR",
		"ZX",
		"ZZ",
		"ZZC"
};
}
