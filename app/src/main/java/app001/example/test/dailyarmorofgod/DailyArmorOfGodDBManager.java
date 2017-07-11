package app001.example.test.dailyarmorofgod;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DailyArmorOfGodDBManager {

// --------------------------------------------------------------------------------------------------------
    static final String DB_DailyArmorOfGod = "DB_DailyArmorOfGod.db";
    static final String TABLE_QtDateAndVerse = "QtDateAndVerse";
    static  final int DB_VERSION = 1;
// --------------------------------------------------------------------------------------------------------
    Context mContext = null;

    private static DailyArmorOfGodDBManager mDbManager;
    private SQLiteDatabase mDatabase;

// --------------------------------------------------------------------------------------------------------
    public static  DailyArmorOfGodDBManager getInstance(Context context) {
        log("DailyArmorOfGodDBManager getInstance 생성자 실행");

        if(mDbManager == null) {
            log("1");
            mDbManager = new DailyArmorOfGodDBManager(context);
            log("2");
        }
        return mDbManager;

    } // DailyArmorOfGodDBManager getInstance

// --------------------------------------------------------------------------------------------------------
    private DailyArmorOfGodDBManager(Context context) {
        log("3");
        mContext = context;
        log("4");

        // 1. DB Manager를 생성할 때 DB를 생성하고 열기
        mDatabase =
                context.openOrCreateDatabase(DB_DailyArmorOfGod, Context.MODE_PRIVATE, null);
        log("5");
        // 2. DB에 테이블이 존재하지 않으면 생성
        /*mDatabase.execSQL
                ("CREATE TABLE QtDateAndVerse( " +
                        "_date INTEGER PRIMARY KEY, day_verse TEXT, night_verse TEXT)");*/
        /*mDatabase.execSQL
                ("CREATE TABLE \"QtVerseTable\" (\n" +
                        "\t`date`\tINTEGER,\n" +
                        "\t`day_verse`\tTEXT NOT NULL,\n" +
                        "\t`night_verse`\tTEXT NOT NULL,\n" +
                        "\tPRIMARY KEY(`date`)\n" +
                        ");\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (101,'마1장','창1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (102,'마2장','창4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (103,'마3장','창7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (104,'마4장','창10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (105,'마5:1-20','창13.14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (106,'마5:21-48','창16.17');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (107,'마6:1-18','창18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (108,'마6:19-34','창20.21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (109,'마7장','창23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (110,'마8:1-22','창25.26');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (111,'마8:23-34','창27.28');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (112,'마9:1-17','창29.30');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (113,'마9:18-38','창31.32');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (114,'마10:1-23','창33.34.35');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (115,'마10:24-42','창36.37.38');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (116,'마11장','창39.40.41');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (117,'마12:1-21','창42.43.44');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (118,'마12:22-50','창45.46');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (119,'마13:1-23','창47.48');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (120,'마13:24-58','창49.50');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (121,'마14장','출1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (122,'마15:1-20','출4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (123,'마15:21-39','출7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (124,'마16장','출10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (125,'마17장','출12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (126,'마18:1-14','출14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (127,'마18:15-35','출16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (128,'마19장','출19.20');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (129,'마20장','출21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (130,'마21:1-22','출23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (131,'마21:23-46','출25.26');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (201,'마22:1-22','출27.28');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (202,'마22:23-46','출29.30');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (203,'마23장','출31.32.33');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (204,'마24:1-28','출34.35');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (205,'마24:29-51','출36.37.38');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (206,'마25:1-30','출39.40.');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (207,'마25:31-46','레1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (208,'마26:1-13','레4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (209,'마26:14-35','레6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (210,'마26:36-56','레8.9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (211,'마26:57-75','레11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (212,'마27:1-10','레13장');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (213,'마27:11-31','레14장');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (214,'마27:32-56','레15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (215,'마27:57-66','레17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (216,'마28장','레19.20');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (217,'행1장','레21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (218,'행2:1-36','레23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (219,'행2:37-47','레25장');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (220,'행3장','레26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (221,'행4:1-22','민1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (222,'행4:23-37','민3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (223,'행5:1-16','민5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (224,'행5:17-42','민7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (225,'행6장','민9.10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (226,'행7:1-53','민12.13.14');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (227,'행7:54-60','민15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (228,'행8:1-25','민17.18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (229,'딤후2장','민20.21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (301,'행8:26-40','민23.24.25');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (302,'행9:1-31','민26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (303,'행9:32-43','민28.29.30');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (304,'행10:1-23','민31.32.33');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (305,'행10:24-48','민34.35.36');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (306,'행11장','신1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (307,'행12장','신3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (308,'행13:1-12','신5.6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (309,'행13:13-43','신8.9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (310,'행13:44-52','신11.12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (311,'행14장','신14.15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (312,'행15:1-21','신17.18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (313,'행15:22-41','신20.21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (314,'행16:1-10','신23.24.25');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (315,'행16:11-34','신26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (316,'행16:35-40','신28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (317,'행17:1-15','신30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (318,'행17:16-34','신32.33.34');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (319,'행18장','수1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (320,'행19:1-20','수4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (321,'행19:21-41','수7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (322,'행20:1-16','수10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (323,'행20:17-38','수13.14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (324,'행21:1-16','수16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (325,'행21:17-40','수19.20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (326,'행22장','수22.23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (327,'행23장','삿1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (328,'행24장','삿4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (329,'행25장','삿7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (330,'행26장','삿9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (331,'행27:1-26','삿11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (401,'행27:27-44','삿13.14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (402,'행28장','삿16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (403,'막1:1-20','삿19.20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (404,'막1:21-45','룻기전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (405,'막2장','삼상1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (406,'막3:1-19','삼상4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (407,'막3:20-35','삼상7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (408,'막4:1-20','삼상10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (409,'막4:21-41','삼상13.14');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (410,'막5:1-20','삼상15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (411,'막5:21-43','삼상17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (412,'막6:1-29','삼상19.20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (413,'막6:30-56','삼상22.23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (414,'막7장','삼상25.26');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (415,'막8:1-21','삼상27.28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (416,'막8:22-38','삼상30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (417,'막9:1-29','삼하1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (418,'막9:30-50','삼하3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (419,'막10:1-12','삼하6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (420,'막10:13-34','삼하9.10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (421,'막10:35-52','삼하12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (422,'막11장','삼하14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (423,'막12:1-17','삼하16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (424,'막12:18-34','삼하19.20');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (425,'막12:35-44','삼하21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (426,'막13:1-23','삼하23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (427,'막13:24-37','왕상1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (428,'막14:1-26','왕상3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (429,'막14:27-52','왕상6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (430,'막14:53-72','왕상8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (501,'막15:1-20','왕상10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (502,'막15:21-47','왕상12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (503,'막16장','왕상14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (504,'롬1:1-17','왕상16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (505,'롬1:18-32','왕상19.20');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (506,'롬2장','왕상21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (507,'롬3장','왕하1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (508,'롬4장','왕하4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (509,'롬5장','왕하7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (510,'롬6장','왕하10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (511,'롬7장','왕하13.14');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (512,'롬8:1-17','왕하15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (513,'롬8:18-39','왕하17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (514,'롬9장','왕하19.20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (515,'롬10장','왕하22.23');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (516,'롬11:1-24','왕하24.25');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (517,'롬11:25-36','대상1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (518,'롬12장','대상4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (519,'롬13장','대상7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (520,'롬14장','대상10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (521,'롬15:1-13','대상13.14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (522,'롬15:14-33','대상16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (523,'롬16장','대상19.20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (524,'고전1:1-17','대상22.23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (525,'고전1:18-31','대상25.26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (526,'고전2장','대상28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (527,'고전3장','대하1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (528,'고전4장','대하4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (529,'고전5장','대하7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (530,'고전6장','대하10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (531,'고전7:1-24','대하12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (601,'고전7:25-40','대하14.15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (602,'고전8장','대하17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (603,'고전9장','대하19.20');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (604,'고전10장','대하21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (605,'고전11장','대하23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (606,'고전12장','대하25.26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (607,'고전13장','대하28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (608,'고전14장','대하30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (609,'고전15:1-28','대하32.33');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (610,'고전15:29-58','대하34.35.36');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (611,'고전16장','스1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (612,'고후1장','스3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (613,'고후2장','스6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (614,'고후3장','스9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (615,'고후4장','느1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (616,'고후5장','느4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (617,'고후6장','느7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (618,'고후7장','느10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (619,'고후8장','느12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (620,'고후9장','에1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (621,'고후10장','에3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (622,'고후11장','에6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (623,'고후12장','에9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (624,'고후13장','욥1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (625,'눅1:1-25','욥3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (626,'눅1:26-56','욥5.6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (627,'눅1:57-80','욥8.9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (628,'눅2:1-20','욥11.12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (629,'눅2:21-52','욥14.15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (630,'눅3장','욥17.18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (701,'눅4:1-30','욥20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (702,'눅4:31-44','욥22.23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (703,'눅5:1-26','욥25.26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (704,'눅5:27-39','욥28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (705,'눅6:1-19','욥30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (706,'눅6:20-38','욥32.33');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (707,'눅6:39-49','욥34.35');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (708,'눅7:1-10','욥36.37');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (709,'눅7:11-23','욥38.39.40');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (710,'눅7:24-35','욥41.42');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (711,'눅7:36-50','시1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (712,'눅8:1-21','시4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (713,'눅8:22-39','시7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (714,'눅8:40-56','시10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (715,'눅9:1-17','시13.14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (716,'눅9:18-36','시16.17');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (717,'눅9:37-62','시18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (718,'눅10:1-24','시20.21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (719,'눅10:25-42','시23.24.25');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (720,'눅11:1-28','시26.27.28');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (721,'눅11:29-54','시29.30');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (722,'눅12:1-34','시31.32');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (723,'눅12:35-59','시33.34');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (724,'눅13:1-21','시35.36');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (725,'눅13:22-35','시37.38.39');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (726,'눅14:1-24','시40.41.42');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (727,'눅14:25-35','시43.44.45');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (728,'눅15장','시46.47.48');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (729,'눅16장','시49.50');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (730,'눅17:1-19','시51.52.53');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (731,'눅17:20-37','시54.55.56');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (801,'눅18:1-17','시57.58.59');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (802,'눅18:18-43','시60.61.62');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (803,'눅19:1-27','시63.64.65');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (804,'눅19:28-48','시66.67');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (805,'눅20:1-26','시68.69');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (806,'눅20:27-47','시70.71');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (807,'눅21:1-19','시72.73');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (808,'눅21:20-38','시74.75.76');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (809,'눅22:1-38','시77.78');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (810,'눅22:39-71','시79.80');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (811,'눅23:1-25','시81.82.83');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (812,'눅23:26-56','시84.85.86');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (813,'눅24:1-35','시87.88');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (814,'눅24:36-53','시89.90');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (815,'갈1장','시91.92.93');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (816,'갈2:1-10','시94.95.96');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (817,'갈2:11-21','시97.98.99');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (818,'갈3','시100.101.102');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (819,'갈4:1-11','시103.104');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (820,'갈4:12-31','시105.106');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (821,'갈5장','시107.108.109');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (822,'갈6장','시110.111.112');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (823,'엡1장','시113.114.115');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (824,'엡2장','시116.117.118');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (825,'엡3장','시119:1-88');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (826,'엡4:1-16','시119:89-176');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (827,'엡4:17-32','시120.121.122');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (828,'엡5:1-14','시123.124.125');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (829,'엡5:15-33','시126.127.128');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (830,'엡6장','시129.130.131');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (831,'빌1장','시132.133.134');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (901,'빌2:1-11','시135.136');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (902,'빌2:12-30','시137.138.139');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (903,'빌3장','시140.141.142');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (904,'빌4장','시143.144.145');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (905,'골1장','시146.147.148');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (906,'골2장','시149.150');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (907,'골3장','잠1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (908,'골4장','잠3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (909,'살전1장','잠6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (910,'살전2장','잠8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (911,'살전3장','잠10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (912,'살전4장','잠13.14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (913,'살전5장','잠16.17.18');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (914,'살후1장','잠19.20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (915,'살후2장','잠22.23.24');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (916,'살후3장','잠25.26');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (917,'딤전1장','잠27.28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (918,'딤전2장','잠30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (919,'딤전3장','전1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (920,'딤전4장','전4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (921,'딤전5장','전7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (922,'딤전6장','전10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (923,'딤후1장','아1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (924,'딤후2장','아4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (925,'딤후3장','아6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (926,'딤후4장','사1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (927,'딛1장','사3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (928,'딛2장','사5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (929,'딛3장','사7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (930,'빌레몬서','사9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1001,'요1:1-28','사11.12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1002,'요1:29-51','사14.15.16');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1003,'요2장','사17.18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1004,'요3장','사20.21.22');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1005,'요4:1-26','사23.24.25');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1006,'요4:27-54','사26.27');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1007,'요5:1-29','사28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1008,'요5:30-47','사30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1009,'요6:1-21','사32.33');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1010,'요6:22-51','사34.35.36');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1011,'요6:52-71','사37.38');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1012,'요7:1-13','사39.40');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1013,'요7:14-36','사41.42');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1014,'요7:37-53','사43.44');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1015,'요8:1-30','사45.46');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1016,'요8:31-59','사47.48.49');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1017,'요9장','사50.51.52');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1018,'요10:1-21','사53.54.55');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1019,'요10:22-42','사56.57.58');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1020,'요11:1-44','사59.60.61');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1021,'요11:45-57','사62.63.64');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1022,'요12:1-19','사65.66');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1023,'요12:20-50','렘1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1024,'요13:1-20','렘3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1025,'요13:21-38','렘6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1026,'요14장','렘9.10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1027,'요15장','렘12.13.14');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1028,'요16장','렘15.16.17');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1029,'요17장','렘18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1030,'요18:1-18','렘20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1031,'요18:19-40','렘22.23');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1101,'요19:1-22','렘24.25.26');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1102,'요19:23-42','렘27.28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1103,'요20장','렘30.31');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1104,'요21장','렘32.33');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1105,'히1장','렘34.35.36');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1106,'히2장','렘37.38.39');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1107,'히3장','렘40.41.42');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1108,'히4장','렘43.44.45');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1109,'히5장','렘46.47');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1110,'히6장','렘48.49');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1111,'히7장','렘50');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1112,'히8장','렘51.52');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1113,'히9장','애1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1114,'히10장','애3.4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1115,'히11:1-16','겔1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1116,'히11:17-40','겔3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1117,'히12장','겔5.6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1118,'히13장','겔8.9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1119,'약1:1-11','겔11.12.13');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1120,'약1:12-27','겔14.15');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1121,'약2장','겔16.17');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1122,'약3장','겔18.19');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1123,'약4장','겔20.21');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1124,'약5장','겔22.23');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1125,'벧전1장','겔24.25.26');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1126,'벧전2장','겔27.28.29');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1127,'벧전3장','겔30.31.32');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1128,'벧전4장','겔33.34');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1129,'벧전5장','겔35.36');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1130,'벧후1장','겔37.38.39');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1201,'벧후2장','겔40-41장');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1202,'벧후3장','겔42.43.44');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1203,'요일1장','겔45.46');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1204,'요일2장','겔47.48');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1205,'요일3장','단1.2');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1206,'요일4장','단3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1207,'요일5장','단5.6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1208,'요한이서','단8.9.10');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1209,'요한삼서','단11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1210,'유다서','호1.2.3.4');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1211,'계1장','호5.6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1212,'계2장','호9.10.11');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1213,'계3장','호12.13.14');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1214,'계4장','요엘전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1215,'계5장','암1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1216,'계6장','암4.5.6');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1217,'계7장','암7.8.9');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1218,'계8장','오바댜전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1219,'계9장','요나전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1220,'계10장','미1.2.3');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1221,'계11장','미4.5');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1222,'계12장','미6.7');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1223,'계13장','나훔전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1224,'계14.15','하박국전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1225,'계16장','스바냐전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1226,'계17장','학개전체');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1227,'계18장','슥1-4장');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1228,'계19장','슥5.6.7.8');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1229,'계20장','슥9.10.11.12');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1230,'계21장','슥13.14');\n" +
                        "INSERT INTO `QtVerseTable` (date,day_verse,night_verse) VALUES (1231,'계22장','말라기전체');");*/
        log("6");

        query();

    } // DailyArmorOfGodDBManager( )
// --------------------------------------------------------------------------------------------------------
        // 테이블 내용을 EditText에 출력하기(쿼리)

        public Cursor query() {
            log("query( ) 실행");

            String[] columns = new String[]{"date", "day_verse", "night_verse"};

            Cursor c = mDatabase.query("QtVerseTable", columns, null, null, null, null, null);

            if (c != null) {

                SecondQtDateSettingMain.mShowET.setText("");

                log("while 실행 전");
                while (c.moveToNext()) {
                    String date = c.getString(0);
                    log("String date = c.getString(0);");

                    String day_verse = c.getString(1);
                    log("String day_verse = c.getString(1);");

                    String night_verse = c.getString(2);
                    log("String night_verse = c.getString(2);");

                    SecondQtDateSettingMain.mShowET.append(
                            "date : " + date + "\n" +
                                    "day_verse : " + day_verse + "\n" +
                                    "night_verse :" + night_verse + "\n" +
                                    "---------------------------------------");

                    SecondQtDateSettingMain.mShowET.append("\n Total : " + c.getCount());

                } // while
                log("while 실행 종료");



                c.close();

            } // if (c != null)

            return c;

        } // Cursor query()

// --------------------------------------------------------------------------------------------------------

    // 사용자 정의 함수
    // 1. Log
    public static void log(String msg) {
        Log.v("mylog", msg);
    }

} // class QtDateAndVerseDBManager

// --------------------------------------------------------------------------------------------------------

    /*// 데이터베이스가 존재하지만 버전이 다를 때 호출
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 쿼리 함수
    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("SELECT * FROM QtDateAndVerse", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0) + " :  date " + cursor.getString(1) + "\n"
                    + " : day_verse " + cursor.getInt(2) + " : night_verse " + "\n";
        }
        return str;

    } // PrintData()*/
