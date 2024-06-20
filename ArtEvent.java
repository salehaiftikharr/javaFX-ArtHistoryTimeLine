public class ArtEvent {
	protected String title;
    protected String date;
    protected String imagePath;
    protected double nodeX;
    protected double nodeY;
    protected String detailedDescription;
    protected String artistName;  // New field for artist's name
    protected String keyArtworkDescription;  // New field for the key artwork description

    // Updated constructor
    public ArtEvent(String title, String date, String imagePath, double nodeX, double nodeY,
                    String detailedDescription, String artistName, String keyArtworkDescription) {
        this.title = title;
        this.date = date;
        this.imagePath = imagePath;
        this.nodeX = nodeX;
        this.nodeY = nodeY;
        this.detailedDescription = detailedDescription;
        this.artistName = artistName;
        this.keyArtworkDescription = keyArtworkDescription;
    }

    // New getters
    public String getArtistName() {
        return artistName;
    }

    public String getKeyArtworkDescription() {
        return keyArtworkDescription;
    }
    
    // Getters for coordinates
    public double getNodeX() {
        return nodeX;
    }

    public double getNodeY() {
        return nodeY;
    }

    // Common getters
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImagePath() {
        return imagePath;
    }
    
    public String getDetailedDescription() {
        return detailedDescription;
    }

}
