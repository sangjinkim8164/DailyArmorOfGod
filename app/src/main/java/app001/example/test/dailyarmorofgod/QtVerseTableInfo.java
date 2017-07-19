package app001.example.test.dailyarmorofgod;

public class QtVerseTableInfo {

    private int _date;
    private String day_verse;
    private String night_verse;

    public QtVerseTableInfo(int _date, String day_verse, String night_verse) {
        this._date = _date;
        this.day_verse = day_verse;
        this.night_verse = night_verse;
    }

    public int get_date() {
        return _date;
    }

    public void set_date(int _date) {
        this._date = _date;
    }

    public String getDay_verse() {
        return day_verse;
    }

    public void setDay_verse(String day_verse) {
        this.day_verse = day_verse;
    }

    public String getNight_verse() {
        return night_verse;
    }

    public void setNight_verse(String night_verse) {
        this.night_verse = night_verse;
    }

} // class QtVerseTableInfo
