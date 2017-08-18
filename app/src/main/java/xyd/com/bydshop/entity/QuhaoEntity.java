package xyd.com.bydshop.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${zxl} on 2017/4/12.
 * D: 区号实体类
 * C:
 */

public class QuhaoEntity implements Parcelable {
    /**
     * initialLetter : A
     * area : 阿尔巴尼亚
     * area_code : +355
     */

    private String initialLetter;
    private String area;
    private String area_code;

    protected QuhaoEntity(Parcel in) {
        initialLetter = in.readString();
        area = in.readString();
        area_code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(initialLetter);
        dest.writeString(area);
        dest.writeString(area_code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuhaoEntity> CREATOR = new Creator<QuhaoEntity>() {
        @Override
        public QuhaoEntity createFromParcel(Parcel in) {
            return new QuhaoEntity(in);
        }

        @Override
        public QuhaoEntity[] newArray(int size) {
            return new QuhaoEntity[size];
        }
    };

    public String getInitialLetter() {
        if (initialLetter == null) {
           return "#";
        }
        return initialLetter;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }


//
//    protected String initialLetter;
//    protected String name;
//
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//
//    public String getInitialLetter() {
//        if (initialLetter == null) {
//            return "#";
//        }
//        return initialLetter;
//    }
//
//    public void setInitialLetter(String initialLetter) {
//        this.initialLetter = initialLetter;
//    }
//
//    public QuhaoEntity(){}
//    private QuhaoEntity(Parcel in) {
//        name = in.readString();
//    }
//
//    public static final Creator<QuhaoEntity> CREATOR = new Creator<QuhaoEntity>() {
//        @Override
//        public QuhaoEntity createFromParcel(Parcel in) {
//            return new QuhaoEntity(in);
//        }
//
//        @Override
//        public QuhaoEntity[] newArray(int size) {
//            return new QuhaoEntity[size];
//        }
//    };
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//    }
}
