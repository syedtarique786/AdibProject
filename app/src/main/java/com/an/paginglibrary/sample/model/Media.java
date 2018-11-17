package com.an.paginglibrary.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media implements Parcelable {


    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
    /**
     * type : image
     * subtype : photo
     * caption : Victims of the mass shooting in Thousand Oaks, Calif., were remembered Thursday night at a vigil.
     * copyright : Jenna Schoenefeld for The New York Times
     * approved_for_syndication : 1
     * media-metadata : [{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-square320-v3.jpg","format":"square320","height":320,"width":320},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-thumbStandard-v3.jpg","format":"Standard Thumbnail","height":75,"width":75},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-articleInline-v2.jpg","format":"Normal","height":127,"width":190},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-sfSpan-v2.jpg","format":"Large","height":263,"width":395},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-jumbo-v2.jpg","format":"Jumbo","height":683,"width":1024},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-superJumbo-v2.jpg","format":"superJumbo","height":1366,"width":2048},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-square640-v3.jpg","format":"square640","height":640,"width":640},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-thumbLarge-v3.jpg","format":"Large Thumbnail","height":150,"width":150},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-mediumThreeByTwo210-v2.jpg","format":"mediumThreeByTwo210","height":140,"width":210},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-mediumThreeByTwo440-v2.jpg","format":"mediumThreeByTwo440","height":293,"width":440}]
     */

    private String type;
    private String subtype;
    private String caption;
    @SerializedName("media-metadata")
    private List<MediametadataBean> mediametadata;

    protected Media(Parcel in) {
        type = in.readString();
        subtype = in.readString();
        caption = in.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<MediametadataBean> getMediametadata() {
        return mediametadata;
    }

    public void setMediametadata(List<MediametadataBean> mediametadata) {
        this.mediametadata = mediametadata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(subtype);
        dest.writeString(caption);
    }

    public static class MediametadataBean {
        /**
         * url : https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-square320-v3.jpg
         * format : square320
         * height : 320
         * width : 320
         */

        private String url;
        private String format;
        private int height;
        private int width;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
