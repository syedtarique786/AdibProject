package com.an.paginglibrary.sample.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results implements Parcelable {


    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
    public static DiffUtil.ItemCallback<Results> DIFF_CALLBACK = new DiffUtil.ItemCallback<Results>() {
        @Override
        public boolean areItemsTheSame(@NonNull Results oldItem, @NonNull Results newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Results oldItem, @NonNull Results newItem) {
            return oldItem.equals(newItem);
        }
    };
    /**
     * url : https://www.nytimes.com/2018/11/08/us/shooting-california-thousand-oaks.html
     * adx_keywords : Thousand Oaks, Calif, Shooting (2018);Long, Ian David (d 2018);Borderline Bar and Grill (Thousand Oaks, Calif, Nightclub);Helus, Ronald L (1964-2018);Thousand Oaks (Calif);Bars and Nightclubs
     * column : null
     * section : U.S.
     * byline : By JOSE A. DEL REAL, JENNIFER MEDINA and TIM ARANGO
     * type : Article
     * title : California Shooting Kills 12 at Country Music Bar, a Year After Las Vegas
     * abstract : The Borderline, a popular hangout for country music fans, had become a place of solace for dozens of survivors of the Vegas massacre.
     * published_date : 2018-11-08
     * source : The New York Times
     * id : 100000006204989
     * asset_id : 100000006204989
     * views : 1
     * des_facet : ["THOUSAND OAKS, CALIF, SHOOTING (2018)","BARS AND NIGHTCLUBS"]
     * org_facet : ["BORDERLINE BAR AND GRILL (THOUSAND OAKS, CALIF, NIGHTCLUB)"]
     * per_facet : ["LONG, IAN DAVID (D 2018)","HELUS, RONALD L (1964-2018)"]
     * geo_facet : ["THOUSAND OAKS (CALIF)"]
     * media : [{"type":"image","subtype":"photo","caption":"Victims of the mass shooting in Thousand Oaks, Calif., were remembered Thursday night at a vigil.","copyright":"Jenna Schoenefeld for The New York Times","approved_for_syndication":1,"media-metadata":[{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-square320-v3.jpg","format":"square320","height":320,"width":320},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-thumbStandard-v3.jpg","format":"Standard Thumbnail","height":75,"width":75},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-articleInline-v2.jpg","format":"Normal","height":127,"width":190},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-sfSpan-v2.jpg","format":"Large","height":263,"width":395},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-jumbo-v2.jpg","format":"Jumbo","height":683,"width":1024},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-superJumbo-v2.jpg","format":"superJumbo","height":1366,"width":2048},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-square640-v3.jpg","format":"square640","height":640,"width":640},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-thumbLarge-v3.jpg","format":"Large Thumbnail","height":150,"width":150},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-mediumThreeByTwo210-v2.jpg","format":"mediumThreeByTwo210","height":140,"width":210},{"url":"https://static01.nyt.com/images/2018/11/10/us/10shooting/10shooting-mediumThreeByTwo440-v2.jpg","format":"mediumThreeByTwo440","height":293,"width":440}]}]
     */

    private String url;
    private String section;
    private String type;
    private String title;
    @SerializedName("byline")
    private String byline;
    @SerializedName("abstract")
    private String abstractX;
    private String published_date;
    private String source;
    private long id;
    private int views;
    private List<MediaBean> media;

    protected Results(Parcel in) {
        url = in.readString();
        section = in.readString();
        type = in.readString();
        title = in.readString();
        byline = in.readString();
        abstractX = in.readString();
        published_date = in.readString();
        source = in.readString();
        id = in.readLong();
        views = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(section);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(byline);
        dest.writeString(abstractX);
        dest.writeString(published_date);
        dest.writeString(source);
        dest.writeLong(id);
        dest.writeInt(views);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<MediaBean> getMedia() {
        return media;
    }

    public void setMedia(List<MediaBean> media) {
        this.media = media;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Results article = (Results) obj;
        return article.id == this.id;
    }

    public static class MediaBean {
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
        @SerializedName("media-metadata")
        private List<MediametadataBean> mediametadata;

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

        public List<MediametadataBean> getMediametadata() {
            return mediametadata;
        }

        public void setMediametadata(List<MediametadataBean> mediametadata) {
            this.mediametadata = mediametadata;
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

}
