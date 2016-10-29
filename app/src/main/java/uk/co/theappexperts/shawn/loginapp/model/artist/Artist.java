package uk.co.theappexperts.shawn.loginapp.model.artist;


    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    import java.util.ArrayList;
    import java.util.List;

    import uk.co.theappexperts.shawn.loginapp.model.IData;

public class Artist implements IData {

        @SerializedName("uri")
        @Expose
        private String uri;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("id")
        @Expose
        private Integer id;
    @SerializedName("identifier")
    @Expose
    private List<Identifier> identifier = new ArrayList<Identifier>();
        @SerializedName("onTourUntil")
        @Expose
        private String onTourUntil;

        /**
         *
         * @return
         * The uri
         */
        public String getUri() {
            return uri;
        }

        /**
         *
         * @param uri
         * The uri
         */
        public void setUri(String uri) {
            this.uri = uri;
        }

        /**
         *
         * @return
         * The displayName
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         *
         * @param displayName
         * The displayName
         */
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The onTourUntil
         */
        public String getOnTourUntil() {
            return onTourUntil;
        }

        /**
         *
         * @param onTourUntil
         * The onTourUntil
         */
        public void setOnTourUntil(String onTourUntil) {
            this.onTourUntil = onTourUntil;
        }
        public List<Identifier> getIdentifier() {
            return identifier;
        }

        /**
         *
         * @param identifier
         * The identifier
         */
        public void setIdentifier(List<Identifier> identifier) {
            this.identifier = identifier;
        }


    }
